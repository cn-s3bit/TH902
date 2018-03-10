package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;
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
			obj.moveBasic.velocity.rotate(-getFloatDelta(task));
			break; 
		case "子弹加速度":
			task.lastVal = obj.moveBasic.acc.len() + getFloatDelta(task);
			obj.moveBasic.acc.nor().scl(task.lastVal);
			break; 
		case "子弹加速度方向":
			task.lastVal += getFloatDelta(task);
			obj.moveBasic.acc.rotate(-getFloatDelta(task));
			break;
		default:
			System.err.println("Warning: Unimplemented");
			break;
		}
	}
}
