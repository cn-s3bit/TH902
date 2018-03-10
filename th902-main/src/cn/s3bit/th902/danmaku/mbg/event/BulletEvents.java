package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;
import cn.s3bit.th902.danmaku.mbg.MBGBullet;
import cn.s3bit.th902.danmaku.mbg.MBGEventTask;

import static cn.s3bit.th902.danmaku.mbg.event.BulletEmitterEvents.getFloatDelta;

public final class BulletEvents implements IEventFirer<AbstractMBGComponent<BulletEmitter>> {
	public static final BulletEvents instance = new BulletEvents();
	@Override
	public void fireDataOperation(AbstractMBGComponent<BulletEmitter> obj, MBGEventTask task) {
		switch (task.action.LValue) {
		case "子弹速度":
			task.lastVal = obj.moveBasic.velocity.len() + getFloatDelta(task);
			obj.moveBasic.velocity.nor().scl(task.lastVal);
			break; 
		case "子弹速度方向":
			task.lastVal += getFloatDelta(task);
			obj.moveBasic.velocity.setAngle(-task.lastVal);
			break; 
		case "子弹加速度":
			task.lastVal = obj.moveBasic.acc.len() + getFloatDelta(task);
			obj.moveBasic.acc.nor().scl(task.lastVal);
			break; 
		case "子弹加速度方向":
			task.lastVal += getFloatDelta(task);
			obj.moveBasic.acc.setAngle(-task.lastVal);
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
