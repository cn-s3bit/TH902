package cn.s3bit.th902.danmaku.mbg.event;

import com.badlogic.gdx.math.Interpolation;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.mbgparser.event.CommandAction;
import cn.s3bit.mbgparser.event.DataOperateAction.OperatorType;
import cn.s3bit.mbgparser.event.DataOperateAction.TweenFunctionType;
import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;
import cn.s3bit.th902.danmaku.mbg.MBGBulletEmitter;
import cn.s3bit.th902.danmaku.mbg.MBGEventTask;
import cn.s3bit.th902.danmaku.mbg.MBGScene;

import static cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions.transformBackX;
import static cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions.transformBackY;

public final class BulletEmitterEvents implements IEventFirer<AbstractMBGComponent<BulletEmitter>> {
	public static final BulletEmitterEvents instance = new BulletEmitterEvents();
	public void fireDataOperation(AbstractMBGComponent<BulletEmitter> emitter, MBGEventTask task) {
		switch (task.action.LValue) {
		case "X坐标":
			emitter.transform.position.x = transformX(emitter.mbgScene,
					task.lastVal = transformBackX(emitter.mbgScene, emitter.transform.position.x) + getFloatDelta(task)
				);
			break;
		case "Y坐标":
			emitter.transform.position.y = transformY(emitter.mbgScene,
					task.lastVal = transformBackY(emitter.mbgScene, emitter.transform.position.y) + getFloatDelta(task)
				);
			break;
		case "半径":
			applyValueWithRand(emitter.mbgItem.半径, task);
			break;
		case "半径方向":
			applyValueWithRand(emitter.mbgItem.半径方向, task);
			break; 
		case "条数":
			applyValueWithRand(emitter.mbgItem.条数, task);
			break; 
		case "周期":
			applyValueWithRand(emitter.mbgItem.周期, task);
			break; 
		case "角度":
			applyValueWithRand(emitter.mbgItem.发射角度, task);
			break; 
		case "范围":
			applyValueWithRand(emitter.mbgItem.范围, task);
			break; 
		case "速度":
			task.lastVal = emitter.moveBasic.velocity.len() + getFloatDelta(task);
			emitter.moveBasic.velocity.nor().scl(task.lastVal);
			break;
		case "速度方向":
			task.lastVal += getFloatDelta(task);
			emitter.moveBasic.velocity.setAngle(-task.lastVal);
			break; 
		case "加速度":
			task.lastVal = emitter.moveBasic.acc.len() + getFloatDelta(task);
			emitter.moveBasic.acc.nor().scl(task.lastVal);
			break; 
		case "加速度方向":
			task.lastVal += getFloatDelta(task);
			emitter.moveBasic.acc.setAngle(-task.lastVal);
			break; 
		case "生命":
			task.lastVal = emitter.mbgItem.子弹生命 + getFloatDelta(task);
			emitter.mbgItem.子弹生命 = (int) task.lastVal;
			break;
		case "类型":
			task.lastVal = emitter.mbgItem.子弹类型 + getFloatDelta(task);
			emitter.mbgItem.子弹类型 = (int) task.lastVal;
			break;
		case "宽比":
			break;
		case "高比":
			break;
		case "R":
			break;
		case "G":
			break;
		case "B":
			break;
		case "不透明度":
			break; 
		case "朝向":
			break;
		case "子弹速度":
			applyValueWithRand(emitter.mbgItem.子弹运动.motion.speed, task);
			break;
		case "子弹速度方向":
			applyValueWithRand(emitter.mbgItem.子弹运动.motion.speedDirection, task);
			break;
		case "子弹加速度":
			applyValueWithRand(emitter.mbgItem.子弹运动.motion.acceleration, task);
			break;
		case "子弹加速度方向":
			applyValueWithRand(emitter.mbgItem.子弹运动.motion.accelerationDirection, task);
			break;
		case "横比":
			break;
		case "纵比":
			break;
		case "出屏即消":
			task.lastVal += getFloatDelta(task);
			emitter.mbgItem.出屏即消 = task.lastVal >= 0.01f;
			break;
		default:
			System.err.println("Warning: Unimplemented - " + task.action.LValue);
			break;
		}
	}
	
	public static float applyOp(OperatorType op, ValueWithRand valueWithRand, float origin, float lastVal, float rval, float progress, TweenFunctionType tweenFunctionType) {
		float result;
		if (op == OperatorType.ChangeTo) {
			valueWithRand.randValue = 0;
		}
		result = applyOp(op, origin, rval, progress, tweenFunctionType);
		valueWithRand.baseValue += result - lastVal;
		return result;
	}
	
	public static float applyValueWithRand(ValueWithRand valueWithRand, MBGEventTask task) {
		task.lastVal = applyOp(task.action.Operator,
				valueWithRand,
				task.origin.baseValue,
				task.lastVal,
				task.target,
				1f - task.timeLeft / (float) task.timefull,
				task.action.TweenFunction
			);
		return task.lastVal;
	}
	
	public static float getFloatDelta(MBGEventTask task) {
		float result = applyOp(task.action.Operator,
				task.origin.baseValue,
				task.target,
				1f - task.timeLeft / (float) task.timefull,
				task.action.TweenFunction);
		return result - task.lastVal;
	}
	
	public static float applyOp(OperatorType op, float original, float rval, float progress, TweenFunctionType tweenFunctionType) {
		float target;
		switch (op) {
		case ChangeTo:
			target = rval;
			break;
		case Add:
			target = original + rval;
			break;
		case Subtraction:
			target = original - rval;
			break;
		default:
			throw new AssertionError();
		}
		switch (tweenFunctionType) {
		case Proportional:
			return Interpolation.linear.apply(original, target, progress);
		case Fixed:
			return target;
		case Sin:
			return Interpolation.sineOut.apply(original, target, progress);
		default:
			throw new AssertionError();
		}
	}
	
	@Override
	public void fireCommand(AbstractMBGComponent<BulletEmitter> emitter, CommandAction action) {
		switch (action.Command) {
		case "额外发射":
			if (emitter instanceof MBGBulletEmitter)
				((MBGBulletEmitter) emitter).emit(true);
			break;
		case "恢复到初始状态":
			System.err.println("Warning: Unimplemented"); // TODO: Impl
			break;
		}
	}
	
	public static float transformX(MBGScene mbgScene, float x) {
		return (x - mbgScene.mbgData.center.Position.x) * 285f / 315f + 285f;
	}
	
	public static float transformY(MBGScene mbgScene, float y) {
		return -(y - mbgScene.mbgData.center.Position.y) * 360f / 240f + 360f;
	}
}
