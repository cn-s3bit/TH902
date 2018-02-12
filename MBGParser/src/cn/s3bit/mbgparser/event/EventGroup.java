package cn.s3bit.mbgparser.event;

import java.util.*;

import cn.s3bit.mbgparser.MRef;

import static cn.s3bit.mbgparser.MBGUtils.*;

public final class EventGroup
{
	public String Name;
	public int Interval, IntervalIncrement;

	public ArrayList<Event> Events;

	public static EventGroup parseFrom(String c)
	{
		EventGroup eg = new EventGroup();

		MRef<String> tempRef_c = new MRef<String>(c);
		eg.Name = readString(tempRef_c, '|');
		c = tempRef_c.argValue;
		MRef<String> tempRef_c2 = new MRef<String>(c);
		eg.Interval = readInt(tempRef_c2, '|');
		c = tempRef_c2.argValue;
		MRef<String> tempRef_c3 = new MRef<String>(c);
		eg.IntervalIncrement = readInt(tempRef_c3, '|');
		c = tempRef_c3.argValue;

		eg.Events = Event.parseEvents(c);

		return eg;
	}

	public static ArrayList<EventGroup> parseEventGroups(String c)
	{
		if (isNullOrWhiteSpace(c))
		{
			return null;
		}
		else
		{
			ArrayList<EventGroup> ret = new ArrayList<EventGroup>();
			String[] egs = c.split("[&]", -1);
			for (String i : egs)
			{
				if (!isNullOrWhiteSpace(i))
				{
					ret.add(parseFrom(i));
				}
			}
			return ret;
		}
	}

	public EventGroup clone()
	{
		EventGroup varCopy = new EventGroup();

		varCopy.Name = this.Name;
		varCopy.Interval = this.Interval;
		varCopy.IntervalIncrement = this.IntervalIncrement;
		varCopy.Events = this.Events;

		return varCopy;
	}
}