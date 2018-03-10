package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.mbgparser.event.DataOperateAction;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;

public interface ILValueProvider<T extends AbstractMBGComponent<?>> {
	public ValueWithRand getCurrentVal(DataOperateAction action, T mbgComponent);
}
