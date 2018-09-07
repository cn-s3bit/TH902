package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.mbgparser.event.DataOperateAction;
import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;

import static cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions.transformBackX;
import static cn.s3bit.th902.danmaku.mbg.condition.BulletEmitterConditions.transformBackY;

public class BulletEmitterLValues implements ILValueProvider<AbstractMBGComponent<BulletEmitter>> {
	public static final BulletEmitterLValues instance = new BulletEmitterLValues();
	public ValueWithRand getCurrentVal(DataOperateAction action, AbstractMBGComponent<BulletEmitter> emitter) {
		ValueWithRand ret = new ValueWithRand();
		switch (action.LValue) {
			case "X坐标":
				ret.baseValue = transformBackX(emitter.mbgScene, emitter.transform.position.x);
				break;
			case "Y坐标":
				ret.baseValue = transformBackY(emitter.mbgScene, emitter.transform.position.y);
				break;
			case "半径":
				return emitter.mbgItem.半径;
			case "半径方向":
				return emitter.mbgItem.半径方向;
			case "条数":
				return emitter.mbgItem.条数;
			case "周期":
				return emitter.mbgItem.周期;
			case "角度":
				return emitter.mbgItem.发射角度;
			case "范围":
				return emitter.mbgItem.范围;
			case "速度":
				ret.baseValue = emitter.moveBasic.velocity.len();
				break;
			case "速度方向":
				return emitter.mbgItem.发射器运动.motion.speedDirection;
			case "加速度":
				ret.baseValue = emitter.moveBasic.acc.len();
				break; 
			case "加速度方向":
				return emitter.mbgItem.发射器运动.motion.accelerationDirection;
			case "生命":
				ret.baseValue = emitter.mbgItem.子弹生命;
				break; 
			case "类型":
				ret.baseValue = emitter.mbgItem.子弹类型;
				break; 
			case "宽比":
				ret.baseValue = emitter.mbgItem.宽比;
				break; 
			case "高比":
				ret.baseValue = emitter.mbgItem.高比;
				break; 
			case "R":
				break; 
			case "G":
				break; 
			case "B":
				break; 
			case "不透明度":
				ret.baseValue = emitter.mbgItem.子弹颜色.A;
				break; 
			case "朝向":
				break; 
			case "子弹速度":
				return emitter.mbgItem.子弹运动.motion.speed;
			case "子弹速度方向":
				return emitter.mbgItem.子弹运动.motion.speedDirection;
			case "子弹加速度":
				return emitter.mbgItem.子弹运动.motion.acceleration;
			case "子弹加速度方向":
				return emitter.mbgItem.子弹运动.motion.accelerationDirection;
			case "横比":
				break; 
			case "纵比":
				break; 
			case "出屏即消":
				ret.baseValue = emitter.mbgItem.出屏即消 ? 1f : 0f;
				break;
			default:
				System.err.println("Warning: Unimplemented");
				break;
		}
		return ret;
	}
}
