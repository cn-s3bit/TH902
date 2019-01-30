package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ObjectSet;

import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions;
import cn.s3bit.th902.danmaku.mbg.condition.IConditionJudger;
import cn.s3bit.th902.danmaku.mbg.event.BulletEmitterEvents;
import cn.s3bit.th902.danmaku.mbg.event.BulletEmitterLValues;
import cn.s3bit.th902.danmaku.mbg.event.IEventFirer;
import cn.s3bit.th902.danmaku.mbg.event.ILValueProvider;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.utils.RandomPool;

import static cn.s3bit.th902.GameHelper.getValFromRandom;

import java.util.Iterator;

public class MBGBulletEmitter extends AbstractMBGComponent<BulletEmitter> {
	public ObjectSet<Transform> subEmitters = new ObjectSet<>();
	
	public float nextEmit = 0;
	public int emitTimer = 0;
	
	public MBGBulletEmitter(BulletEmitter bulletEmitter, MBGScene mbgScene, Group layer) {
		super(bulletEmitter, mbgScene, layer);
	}
	
	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		
		if (mbgItem.绑定状态.Child == null)
			subEmitters.add(transform);
	}
	
	@Override
	public void begin() {
		emitTimer = 0;
		nextEmit = getValFromRandom(mbgItem.周期);
		if (!mbgItem.绑定状态.Depth) {
			moveBasic.velocity
				.set(getValFromRandom(mbgItem.发射器运动.motion.speed), 0)
				.rotate(-getValFromRandom(mbgItem.发射器运动.motion.speedDirection));
			moveBasic.acc
				.set(getValFromRandom(mbgItem.发射器运动.motion.acceleration), 0)
				.rotate(-getValFromRandom(mbgItem.发射器运动.motion.accelerationDirection));
		}
	}
	
	@Override
	public void after() {
		
	}
	
	@Override
	public void during() {
		if (!mbgItem.绑定状态.Depth) {
			// System.out.println(getValFromRandom(mbgItem.发射器运动.motion.speedDirection));
			// System.out.println(getValFromRandom(mbgItem.发射器运动.motion.accelerationDirection));
			moveBasic.velocity.setAngle(-getValFromRandom(mbgItem.发射器运动.motion.speedDirection));
			moveBasic.acc.setAngle(-getValFromRandom(mbgItem.发射器运动.motion.accelerationDirection));
		}
		emitTimer++;
		runEventGroups(mbgItem.发射器事件组, life);
		if (emitTimer >= nextEmit) {
			emit(false);
		}
	}
	
	public void emit(boolean extra) {
		emitTimer = 0;
		for (Iterator<Transform> iterator = subEmitters.iterator(); iterator.hasNext();) {
			Transform sub = (Transform) iterator.next();
			if (sub.isDead()) {
				iterator.remove();
			}
			int count = Math.round(getValFromRandom(mbgItem.条数));
			float angle, range;
			if (mbgItem.发射角度.baseValue == -99999f)
				angle = -GameHelper.snipeVct(getPosition(sub), null, RandomPool.get(5).random(-mbgItem.发射角度.randValue, mbgItem.发射角度.randValue), IMoveFunction.vct2_tmp1).angle();
			else
				angle = getValFromRandom(mbgItem.发射角度);
			range = getValFromRandom(mbgItem.范围);
			for (int i = 0; i < count; i++) {
				emitOne(angle - range / count * ((count - 1) / 2f), sub);
				angle += range / count;
			}
		}
		if (!extra)
			nextEmit = getValFromRandom(mbgItem.周期);
	}

	public Entity emitOne(float angle, Transform sub) {
		float radius = getValFromRandom(mbgItem.半径);
		float radiusDir = getValFromRandom(mbgItem.半径方向);
		Vector2 emitPosition = new Vector2(radius, 0).rotate(-radiusDir - angle).add(getPosition(sub));
		emitPosition.x = emitPosition.x + RandomPool.get(5).random(-mbgItem.位置坐标.x.randValue, mbgItem.位置坐标.x.randValue);
		emitPosition.y = emitPosition.y + RandomPool.get(5).random(-mbgItem.位置坐标.y.randValue, mbgItem.位置坐标.y.randValue);
		float bulletSpeed = getValFromRandom(mbgItem.子弹运动.motion.speed);
		float bulletAcc, bulletAccDir;
		Vector2 bulletScale = new Vector2(mbgItem.宽比, mbgItem.高比);
		bulletAcc = getValFromRandom(mbgItem.子弹运动.motion.acceleration);
		bulletAccDir = getValFromRandom(mbgItem.子弹运动.motion.accelerationDirection);
		Vector2 emitVelocity = new Vector2(bulletSpeed + 1e-9f, 0).rotate(-angle);
		if (mbgItem.绑定状态.Relative) {
			emitVelocity.rotate(sub.rotation - 270);
		}
		Vector2 emitAcc = new Vector2(bulletAcc + 1e-9f, 0).rotate(-bulletAccDir);
		float bulletRotation = 270 + (mbgItem.朝向与速度方向相同 ? emitVelocity.angle() : getValFromRandom(mbgItem.朝向));
		
		Entity bullet = Entity.Create();
		bullet.AddComponent(new Transform(emitPosition, bulletRotation, bulletScale));
		bullet.AddComponent(new ImageRenderer(ResourceManager.barrages.get(MBGBulletTypeMap.TYPE_MAP.get(mbgItem.子弹类型)), -1).attachToGroup(layer));
		bullet.AddComponent(new MoveBasic(emitVelocity, emitAcc));
		bullet.AddComponent(new MBGBullet(this));
		return bullet; //TODO: 坐标指定
	}

	@Override
	public IEventFirer<AbstractMBGComponent<BulletEmitter>> getEventFirer() {
		return BulletEmitterEvents.instance;
	}

	@Override
	public IConditionJudger<AbstractMBGComponent<BulletEmitter>> getConditionJudger() {
		return BulletEmitterConditions.instance;
	}

	@Override
	public ILValueProvider<AbstractMBGComponent<BulletEmitter>> getLValueProvider() {
		return BulletEmitterLValues.instance;
	}

	@Override
	public int getBeginTime() {
		return mbgItem.生命.begin;
	}

	@Override
	public int getLife() {
		return mbgItem.生命.lifeTime;
	}

	@Override
	public Vector2 getInitialPosition() {
		float x, y;
		x = mbgItem.位置坐标.x.baseValue;
		y = mbgItem.位置坐标.y.baseValue;
		if (x == -99998f) x = transform.position.x;
		else x = transformX(x);
		if (y == -99998f) y = transform.position.y;
		else y = transformY(y);
		return new Vector2(x, y);
	}
}
