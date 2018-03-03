package cn.s3bit.th902.danmaku.mbg.event;

import com.badlogic.gdx.math.Interpolation;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.mbgparser.event.CommandAction;
import cn.s3bit.mbgparser.event.DataOperateAction.OperatorType;
import cn.s3bit.mbgparser.event.DataOperateAction.TweenFunctionType;
import cn.s3bit.th902.danmaku.mbg.MBGBulletEmitter;
import cn.s3bit.th902.danmaku.mbg.MBGEventTask;
import cn.s3bit.th902.danmaku.mbg.MBGScene;

import static cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions.transformBackX;
import static cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions.transformBackY;

public final class BulletEmitterEvents {
	public static void fireDataOperation(MBGBulletEmitter emitter, MBGEventTask task) {
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
			break;
		case "半径方向":
			break; 
		case "条数":
			break; 
		case "周期":
			break; 
		case "角度":
			applyValueWithRand(emitter.bulletEmitter.发射角度, task);
			break; 
		case "范围":
			break; 
		case "速度":
			break; 
		case "速度方向":
			break; 
		case "加速度":
			break; 
		case "加速度方向":
			break; 
		case "生命":
			break; 
		case "类型":
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
			break; 
		case "子弹速度方向":
			break; 
		case "子弹加速度":
			break; 
		case "子弹加速度方向":
			break; 
		case "横比":
			break; 
		case "纵比":
			break; 
		case "出屏即消":
			break;
		default:
			System.err.println("Warning: Unimplemented");
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
	
	public static void fireCommand(MBGBulletEmitter emitter, CommandAction action) {
		switch (action.Command) {
		case "额外发射":
			emitter.emit();
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
