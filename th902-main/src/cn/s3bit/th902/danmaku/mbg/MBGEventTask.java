package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.mbgparser.event.DataOperateAction;
import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.danmaku.mbg.event.BulletEmitterLValues;

public class MBGEventTask {
	public MBGEventTask(int timeLeft, DataOperateAction action, MBGBulletEmitter emitter) {
		this.action = action;
		this.timeLeft = timeLeft;
		timefull = timeLeft;
		origin = BulletEmitterLValues.getCurrentVal(action, emitter);
		ValueWithRand originClone = new ValueWithRand();
		originClone.baseValue = origin.baseValue;
		originClone.randValue = origin.randValue;
		if (originClone.baseValue == -99999f) {
			originClone.baseValue = -GameHelper.snipeVct(emitter.getPosition(), null, 0, new Vector2()).angle();
		}
		origin = originClone;
		if (action.RValue.baseValue == -99999f) {
			action.RValue.baseValue = -GameHelper.snipeVct(emitter.getPosition(), null, 0, new Vector2()).angle();
			target = GameHelper.getValFromRandom(action.RValue);
			action.RValue.baseValue = -99999f;
		}
		else
			target = GameHelper.getValFromRandom(action.RValue);
		lastVal = origin.baseValue;
	}
	public final int timefull;
	public int timeLeft;//实现到了可以做波粒了！ 还差判定……
	public final DataOperateAction action;
	public ValueWithRand origin;
	public final float target;
	public float lastVal;
}
