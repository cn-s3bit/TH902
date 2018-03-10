package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.mbgparser.event.DataOperateAction;
import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;
import cn.s3bit.th902.danmaku.mbg.MBGBullet;

public class BulletLValues implements ILValueProvider<AbstractMBGComponent<BulletEmitter>> {
	public static final BulletLValues instance = new BulletLValues();
	public ValueWithRand getCurrentVal(DataOperateAction action, AbstractMBGComponent<BulletEmitter> bullet) {
		ValueWithRand ret = new ValueWithRand();
		switch (action.LValue) {
		case "子弹速度":
			ret.baseValue = bullet.moveBasic.velocity.len();
			break;
		case "子弹速度方向":
			ret.baseValue = -bullet.moveBasic.velocity.angle();
			break;
		case "子弹加速度":
			ret.baseValue = bullet.moveBasic.acc.len();
			break;
		case "子弹加速度方向":
			ret.baseValue = -bullet.moveBasic.acc.angle();
			break;
		case "不透明度":
			if (bullet instanceof MBGBullet)
				ret.baseValue = ((MBGBullet) bullet).color.a * 100f;
			break;
		case "宽比":
			ret.baseValue = bullet.transform.scale.x;
			break;
		case "高比":
			ret.baseValue = bullet.transform.scale.y;
			break;
		default:
			System.err.println("Warning: Unimplemented - " + action.LValue);
			break;
		}
		return ret;
	}
}
