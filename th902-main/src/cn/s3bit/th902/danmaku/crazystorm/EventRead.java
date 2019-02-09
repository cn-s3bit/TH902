package cn.s3bit.th902.danmaku.crazystorm;

public class EventRead
{
	public float rand;

	public int special;

	public int special2;

	public String condition;

	public String result;

	public String condition2;

	public int contype;

	public int contype2;

	public String opreator;

	public String opreator2;

	public String collector;

	public int change;

	public int changetype;

	public int changevalue;

	public int changename;

	public float res;

	public int times;

	public int time;

	public boolean noloop;

	public EventRead Copy()
	{
		EventRead eventRead = new EventRead();
		eventRead.change = change;
		eventRead.changename = changename;
		eventRead.changetype = changetype;
		eventRead.changevalue = changevalue;
		eventRead.collector = collector;
		eventRead.condition = condition;
		eventRead.condition2 = condition2;
		eventRead.contype = contype;
		eventRead.contype2 = contype2;
		eventRead.noloop = noloop;
		eventRead.opreator = opreator;
		eventRead.opreator2 = opreator2;
		eventRead.rand = rand;
		eventRead.res = res;
		eventRead.result = result;
		eventRead.special = special;
		eventRead.special2 = special2;
		eventRead.time = time;
		eventRead.times = times;
		return eventRead;
	}
}
