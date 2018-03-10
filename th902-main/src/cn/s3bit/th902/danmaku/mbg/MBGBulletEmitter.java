package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

import cn.s3bit.mbgparser.event.CommandAction;
import cn.s3bit.mbgparser.event.DataOperateAction;
import cn.s3bit.mbgparser.event.Event;
import cn.s3bit.mbgparser.event.EventGroup;
import cn.s3bit.mbgparser.event.DataOperateAction.TweenFunctionType;
import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions;
import cn.s3bit.th902.danmaku.mbg.event.BulletEmitterEvents;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;

import static cn.s3bit.th902.GameHelper.getValFromRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MBGBulletEmitter extends Component {
	public MBGScene mbgScene;
	public BulletEmitter bulletEmitter;
	public Group layer;
	public int timerBegin;
	public int life;
	public Transform transform;
	public ObjectSet<Transform> subEmitters = new ObjectSet<>();
	public MoveBasic moveBasic;
	
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
		if ((transform = entity.GetComponent(Transform.class)) == null) {
			transform = new Transform(
					new Vector2(transformX(getValFromRandom(bulletEmitter.位置坐标.x)),
								transformY(getValFromRandom(bulletEmitter.位置坐标.y)))
				);
			entity.AddComponent(transform);
		}
		if ((moveBasic = entity.GetComponent(MoveBasic.class)) == null) {
			moveBasic = new MoveBasic(0, 0);
			entity.AddComponent(moveBasic);
		}
		
		if (bulletEmitter.绑定状态.Child == null)
			subEmitters.add(transform);
	}

	@Override
	public void Update() {
		if (timerBegin < bulletEmitter.生命.begin) {
			timerBegin++;
			return;
		} else if (timerBegin == bulletEmitter.生命.begin) {
			timerBegin++;
			life = 0;
			if (!bulletEmitter.绑定状态.Depth) {
				moveBasic.velocity
					.set(getValFromRandom(bulletEmitter.发射器运动.motion.speed), 0)
					.rotate(-getValFromRandom(bulletEmitter.发射器运动.motion.speedDirection));
				moveBasic.acc
					.set(getValFromRandom(bulletEmitter.发射器运动.motion.acceleration), 0)
					.rotate(-getValFromRandom(bulletEmitter.发射器运动.motion.accelerationDirection));
			}
		} else if (life > bulletEmitter.生命.lifeTime) {
			//TODO: After Death
		} else {
			emitTimer++;
			life++;
			runEventGroups(bulletEmitter.发射器事件组, life);
			if (emitTimer >= nextEmit) {
				emit();
			}
		}
	}
	
	public void emit() {
		emitTimer = 0;
		for (Iterator<Transform> iterator = subEmitters.iterator(); iterator.hasNext();) {
			Transform sub = (Transform) iterator.next();
			if (sub.isDead()) {
				iterator.remove();
			}
			int count = Math.round(getValFromRandom(bulletEmitter.条数));
			float angle, range;
			if (bulletEmitter.发射角度.baseValue == -99999f)
				angle = -GameHelper.snipeVct(getPosition(sub), null, MathUtils.random(-bulletEmitter.发射角度.randValue, bulletEmitter.发射角度.randValue), IMoveFunction.vct2_tmp1).angle();
			else
				angle = getValFromRandom(bulletEmitter.发射角度);
			range = getValFromRandom(bulletEmitter.范围);
			for (int i = 0; i < count; i++) {
				emitOne(angle, sub);
				angle += range / count;
			}
		}
		nextEmit = getValFromRandom(bulletEmitter.周期);
	}
	
	float transformX(float x) {
		return (x - mbgScene.mbgData.center.Position.x) * 285f / 315f + 285f;
	}
	
	float transformY(float y) {
		return -(y - mbgScene.mbgData.center.Position.y) * 360f / 240f + 360f;
	}
	
	Vector2 getPosition(Transform sub) {
		float x, y;
		x = bulletEmitter.发射坐标.x;
		y = bulletEmitter.发射坐标.y;
		if (x == -99998f) x = sub.position.x;
		else x = transformX(x);
		if (y == -99998f) y = sub.position.y;
		else y = transformY(y);
		return new Vector2(x, y);
	}

	public Entity emitOne(float angle, Transform sub) {
		float radius = getValFromRandom(bulletEmitter.半径);
		float radiusDir = getValFromRandom(bulletEmitter.半径方向);
		Vector2 emitPosition = new Vector2(radius, 0).rotate(-radiusDir).add(getPosition(sub));
		
		float bulletSpeed = getValFromRandom(bulletEmitter.子弹运动.motion.speed);
		float bulletAcc, bulletAccDir;
		Vector2 bulletScale = new Vector2(bulletEmitter.宽比, bulletEmitter.高比);
		bulletAcc = getValFromRandom(bulletEmitter.子弹运动.motion.acceleration);
		bulletAccDir = getValFromRandom(bulletEmitter.子弹运动.motion.accelerationDirection);
		Vector2 emitVelocity = new Vector2(bulletSpeed, 0).rotate(-angle);
		if (bulletEmitter.绑定状态.Relative) {
			emitVelocity.rotate(sub.rotation - 270);
		}
		Vector2 emitAcc = new Vector2(bulletAcc, 0).rotate(-bulletAccDir);
		float bulletRotation = 270 + (bulletEmitter.朝向与速度方向相同 ? emitVelocity.angle() : getValFromRandom(bulletEmitter.朝向));
		
		Entity bullet = Entity.Create();
		bullet.AddComponent(new Transform(emitPosition, bulletRotation, bulletScale));
		bullet.AddComponent(new ImageRenderer(ResourceManager.barrages.get(MBGBulletTypeMap.TYPE_MAP.get(bulletEmitter.子弹类型)), -1).attachToGroup(layer));
		bullet.AddComponent(new MoveBasic(emitVelocity, emitAcc));
		bullet.AddComponent(new MBGBullet(this));
		return bullet; //TODO: 坐标指定
	}
	
	@Override
	public void Kill() {
		super.Kill();
	}
	
	public ObjectMap<EventGroup, ArrayList<MBGEventTask>> eventTasks = new ObjectMap<>();
	
	public void runEventGroups(List<EventGroup> eventGroups, int time) {
		if (eventGroups == null) return;
		for (int i = 0; i < eventGroups.size(); i++)
			runEvents(eventGroups.get(i), time);
	}
	
	public void runEvents(EventGroup eventGroup, int time) {
		if (eventGroup.Interval == eventGroup.IntervalIncrement) {
			if (time % eventGroup.Interval == 0)
				if (eventTasks.containsKey(eventGroup))
					eventTasks.get(eventGroup).clear();
			time %= eventGroup.Interval;
		}
		for (Event event : eventGroup.Events) {
			if (BulletEmitterConditions.judgeCondition(this, event.condition, time)) {
				if (!eventTasks.containsKey(eventGroup))
					eventTasks.put(eventGroup, new ArrayList<>());
				if (event.action instanceof CommandAction) {
					CommandAction commandAction = (CommandAction) event.action;
					BulletEmitterEvents.fireCommand(this, commandAction);
				}
				else if (event.action instanceof DataOperateAction) {
					DataOperateAction action = (DataOperateAction) event.action;
					if (action.TweenFunction == TweenFunctionType.Fixed)
						BulletEmitterEvents.fireDataOperation(this, new MBGEventTask(1, action, this) {
							{
								this.timeLeft = 0;
							}
						});
					else {
						eventTasks.get(eventGroup).add(new MBGEventTask(action.TweenTime, action, this));
					}
				} else
					throw new AssertionError("Unknown Action: " + event.action);
			}
		}
		if (eventTasks.containsKey(eventGroup))
			for (ListIterator<MBGEventTask> iterator = eventTasks.get(eventGroup).listIterator(); iterator.hasNext();) {
				MBGEventTask task = iterator.next();
				task.timeLeft--;
				BulletEmitterEvents.fireDataOperation(this, task);
				if (task.timeLeft <= 0)
					iterator.remove();
			}
	}
}
