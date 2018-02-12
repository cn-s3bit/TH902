package cn.s3bit.mbgparser.event;

import java.util.*;

import cn.s3bit.mbgparser.MRef;

import static cn.s3bit.mbgparser.MBGUtils.*;

public final class Event
{
	public Condition condition = new Condition();
	public IAction action;

	public static Event parseFrom(String c)
	{
		Event e = new Event();
		MRef<String> tempRef_c = new MRef<String>(c);
		e.condition = Condition.parseFrom(readString(tempRef_c, '：'));
		c = tempRef_c.argValue;
		e.action = ActionHelper.parseFrom(c);
		return e;
	}

	public static ArrayList<Event> parseEvents(String c)
	{
		if (isNullOrWhiteSpace(c))
		{
			return null;
		}
		else
		{
			ArrayList<Event> ret = new ArrayList<Event>();

			String[] events = c.split("[;]", -1);

			for (String e : events)
			{
				if (!isNullOrWhiteSpace(e))
				{
					ret.add(parseFrom(e));
				}
			}
			return ret;
		}
	}

	public Event clone()
	{
		Event varCopy = new Event();

		varCopy.condition = this.condition.clone();
		varCopy.action = this.action;

		return varCopy;
	}
}