package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.mbgparser.event.DataOperateAction;
import cn.s3bit.th902.danmaku.mbg.MBGBulletEmitter;

public class BulletEmitterLValues {
	public static ValueWithRand getCurrentVal(DataOperateAction action, MBGBulletEmitter emitter) {
		switch (action.LValue) {
			case "X坐标":
				break;
			case "Y坐标":
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
				return emitter.bulletEmitter.发射角度;
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
		return new ValueWithRand();
	}
}
