// CrazyStorm_1._03.EventRead
using System;

[Serializable]
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
		return MemberwiseClone();
	}
}
