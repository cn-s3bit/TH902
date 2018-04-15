package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;
import cn.s3bit.th902.danmaku.mbg.MBGBullet;
import cn.s3bit.th902.danmaku.mbg.MBGEventTask;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;

import static cn.s3bit.th902.danmaku.mbg.event.BulletEmitterEvents.getFloatDelta;

public final class BulletEvents implements IEventFirer<AbstractMBGComponent<BulletEmitter>> {
	public static final BulletEvents instance = new BulletEvents();
	@Override
	public void fireDataOperation(AbstractMBGComponent<BulletEmitter> obj, MBGEventTask task) {
		float dt;
		switch (task.action.LValue) {
		case "子弹速度":
			dt = getFloatDelta(task);
			task.lastVal += dt;
			IMoveFunction.vct2_tmp1.set(obj.moveBasic.velocity);
			obj.moveBasic.velocity.nor().scl(dt).add(IMoveFunction.vct2_tmp1);
			break; 
		case "子弹速度方向":
			dt = getFloatDelta(task);
			task.lastVal += dt;
			if (task.action.RValue.baseValue <= -99998f) {
				obj.moveBasic.velocity.setAngle(-task.lastVal);
			}
			else {
				obj.moveBasic.velocity.setAngle(obj.moveBasic.velocity.angle() - dt);
			}
			break; 
		case "子弹加速度":
			dt = getFloatDelta(task);
			task.lastVal += dt;
			IMoveFunction.vct2_tmp1.set(obj.moveBasic.acc);
			obj.moveBasic.acc.nor().scl(dt).add(IMoveFunction.vct2_tmp1);
			break; 
		case "子弹加速度方向":
			dt = getFloatDelta(task);
			task.lastVal += dt;
			if (task.action.RValue.baseValue <= -99998f) {
				obj.moveBasic.acc.setAngle(-task.lastVal);
			}
			else {
				obj.moveBasic.acc.setAngle(obj.moveBasic.acc.angle() - dt);
			}
			break;
		case "不透明度":
			task.lastVal += getFloatDelta(task);
			if (obj instanceof MBGBullet) {
				((MBGBullet) obj).color.a = task.lastVal / 100f;
			}
			break;
		case "宽比":
			task.lastVal += getFloatDelta(task);
			obj.transform.scale.x = task.lastVal;
			break;
		case "高比":
			task.lastVal += getFloatDelta(task);
			obj.transform.scale.y = task.lastVal;
			break;
		default:
			break;
		}
	}
}
