package cn.s3bit.th902.danmaku.crazystorm;

import java.util.ArrayList;

public class Event
{
	public int index;

	public String tag = "新事件组";

	public int t = 1;

	public int loop;

	public int addtime;

	public int special;

	public ArrayList<String> events = new ArrayList<String>();

	public ArrayList<EventRead> results = new ArrayList<EventRead>();

	public Event(int idx)
	{
		index = idx;
	}
}
