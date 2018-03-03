package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;

import static cn.s3bit.th902.GameHelper.getValFromRandom;

public class MBGBulletEmitter extends Component {
	public MBGScene mbgScene;
	public BulletEmitter bulletEmitter;
	public Group layer;
	public int timerBegin;
	public int life;
	private Transform mTransform;
	private MoveBasic mMoveBasic;
	
	public float nextEmit = 0;
	public int emitTimer = 0;
	
	public Entity entity;
	
	public MBGBulletEmitter(BulletEmitter bulletEmitter, MBGScene mbgScene, Group layer) {
		this.bulletEmitter = bulletEmitter;
		this.mbgScene = mbgScene;
		this.layer = layer;
	}
	
	@Override
	public void Initialize(Entity entity) {
		this.entity = entity;
		timerBegin = 0;
		mTransform = new Transform(
				new Vector2(transformX(getValFromRandom(bulletEmitter.位置坐标.x)),
							transformY(getValFromRandom(bulletEmitter.位置坐标.y)))
			);
		entity.AddComponent(mTransform);
		mMoveBasic = new MoveBasic(0, 0);
		entity.AddComponent(mMoveBasic);
	}

	@Override
	public void Update() {
		if (timerBegin < bulletEmitter.生命.begin) {
			timerBegin++;
			return;
		} else if (timerBegin == bulletEmitter.生命.begin) {
			timerBegin++;
			life = 0;
			mMoveBasic.velocity
				.set(getValFromRandom(bulletEmitter.发射器运动.motion.speed), 0)
				.rotate(-getValFromRandom(bulletEmitter.发射器运动.motion.speedDirection));
			mMoveBasic.acc
				.set(getValFromRandom(bulletEmitter.发射器运动.motion.acceleration), 0)
				.rotate(-getValFromRandom(bulletEmitter.发射器运动.motion.accelerationDirection));
		} else if (life >= bulletEmitter.生命.lifeTime) {
			//TODO: After Death
		} else {
			emitTimer++;
			life++;
			if (emitTimer >= nextEmit) {
				emitTimer = 0;
				int count = Math.round(getValFromRandom(bulletEmitter.条数));
				float angle, range;
				angle = getValFromRandom(bulletEmitter.发射角度);
				range = getValFromRandom(bulletEmitter.范围);
				for (int i = 0; i < count; i++) {
					emitOne(angle);
					angle += range / count;
				}
				nextEmit = getValFromRandom(bulletEmitter.周期);
			}
		}
	}
	
	float transformX(float x) {
		return (x - mbgScene.mbgData.center.Position.x) * 285f / 315f + 315f;
	}
	
	float transformY(float y) {
		return -(y - mbgScene.mbgData.center.Position.y) * 360f / 240f + 360f;
	}

	public Entity emitOne(float angle) {
		float x, y;
		float radius, radiusDir;
		x = bulletEmitter.发射坐标.x;
		y = bulletEmitter.发射坐标.y;
		if (x == -99998f) x = mTransform.position.x;
		else x = transformX(x);
		if (y == -99998f) y = mTransform.position.y;
		else y = transformY(y);
		radius = getValFromRandom(bulletEmitter.半径);
		radiusDir = getValFromRandom(bulletEmitter.半径方向);
		Vector2 emitPosition = new Vector2(radius, 0);
		emitPosition.rotate(-radiusDir);
		emitPosition.add(x, y);
		
		float bulletSpeed = getValFromRandom(bulletEmitter.子弹运动.motion.speed);
		float bulletAcc, bulletAccDir;
		Vector2 bulletScale = new Vector2(bulletEmitter.宽比, bulletEmitter.高比);
		bulletAcc = getValFromRandom(bulletEmitter.子弹运动.motion.acceleration);
		bulletAccDir = getValFromRandom(bulletEmitter.子弹运动.motion.accelerationDirection);
		Vector2 emitVelocity = new Vector2(bulletSpeed, 0).rotate(-angle);
		Vector2 emitAcc = new Vector2(bulletAcc, 0).rotate(-bulletAccDir);
		
		Entity bullet = Entity.Create();
		bullet.AddComponent(new Transform(
				emitPosition,
				270 + (bulletEmitter.朝向与速度方向相同 ? emitVelocity.angle() : getValFromRandom(bulletEmitter.朝向)),
				bulletScale
			)
		);
		bullet.AddComponent(new ImageRenderer(ResourceManager.barrages.get(MBGBulletTypeMap.TYPE_MAP.get(bulletEmitter.子弹类型)), -1).attachToGroup(layer));
		bullet.AddComponent(new MoveBasic(emitVelocity, emitAcc));
		return bullet; //TODO: Implemention
	}
}
