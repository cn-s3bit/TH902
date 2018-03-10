package cn.s3bit.th902.danmaku.mbg.event;

import cn.s3bit.mbgparser.event.CommandAction;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;
import cn.s3bit.th902.danmaku.mbg.MBGEventTask;

public interface IEventFirer<T extends AbstractMBGComponent<?>> {
	public void fireDataOperation(T obj, MBGEventTask task);
	public default void fireCommand(T obj, CommandAction action) {
		return; //For most components MBG doesn't have CommandActions.
	}
}
