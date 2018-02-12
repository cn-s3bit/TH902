package cn.s3bit.mbgparser.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import cn.s3bit.mbgparser.MRef;

import static cn.s3bit.mbgparser.MBGUtils.*;

public final class GlobalEvents
{
	public int Frame;

	public boolean JumpEnabled;
	public int JumpTarget, JumpTimes;

	public boolean VibrateEnabled;
	public float VibrateForce;
	public int VibrateTime;

	public enum SleepModeType
	{
		Tween(0),
		Full(1);

		private int intValue;
		private static java.util.HashMap<Integer, SleepModeType> mappings;
		private static java.util.HashMap<Integer, SleepModeType> getMappings()
		{
			if (mappings == null)
			{
				synchronized (SleepModeType.class)
				{
					if (mappings == null)
					{
						mappings = new java.util.HashMap<Integer, SleepModeType>();
					}
				}
			}
			return mappings;
		}

		private SleepModeType(int value)
		{
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}

		public static SleepModeType forValue(int value)
		{
			return getMappings().get(value);
		}
	}

	public boolean SleepEnabled;
	public int SleepTime;
	public SleepModeType SleepType = SleepModeType.values()[0];

	private static GlobalEvents parseFrom(String c)
	{
		GlobalEvents ge = new GlobalEvents();

		MRef<String> tempRef_c = new MRef<String>(c);
		ge.Frame = readInt(tempRef_c, '_');
		c = tempRef_c.argValue;
		MRef<String> tempRef_c2 = new MRef<String>(c);
		readString(tempRef_c2, '_');
		c = tempRef_c2.argValue;
		MRef<String> tempRef_c3 = new MRef<String>(c);
		readString(tempRef_c3, '_');
		c = tempRef_c3.argValue;
		MRef<String> tempRef_c4 = new MRef<String>(c);
		readString(tempRef_c4, '_');
		c = tempRef_c4.argValue;

		MRef<String> tempRef_c5 = new MRef<String>(c);
		ge.JumpEnabled = readBool(tempRef_c5, '_');
		c = tempRef_c5.argValue;
		MRef<String> tempRef_c6 = new MRef<String>(c);
		ge.JumpTimes = readInt(tempRef_c6, '_');
		c = tempRef_c6.argValue;
		MRef<String> tempRef_c7 = new MRef<String>(c);
		ge.JumpTarget = readInt(tempRef_c7, '_');
		c = tempRef_c7.argValue;

		MRef<String> tempRef_c8 = new MRef<String>(c);
		readString(tempRef_c8, '_');
		c = tempRef_c8.argValue;
		MRef<String> tempRef_c9 = new MRef<String>(c);
		readString(tempRef_c9, '_');
		c = tempRef_c9.argValue;
		MRef<String> tempRef_c10 = new MRef<String>(c);
		readString(tempRef_c10, '_');
		c = tempRef_c10.argValue;

		MRef<String> tempRef_c11 = new MRef<String>(c);
		ge.VibrateEnabled = readBool(tempRef_c11, '_');
		c = tempRef_c11.argValue;
		MRef<String> tempRef_c12 = new MRef<String>(c);
		ge.VibrateTime = readInt(tempRef_c12, '_');
		c = tempRef_c12.argValue;
		MRef<String> tempRef_c13 = new MRef<String>(c);
		ge.VibrateForce = readFloat(tempRef_c13, '_');
		c = tempRef_c13.argValue;

		MRef<String> tempRef_c14 = new MRef<String>(c);
		readString(tempRef_c14, '_');
		c = tempRef_c14.argValue;
		MRef<String> tempRef_c15 = new MRef<String>(c);
		readString(tempRef_c15, '_');
		c = tempRef_c15.argValue;
		MRef<String> tempRef_c16 = new MRef<String>(c);
		readString(tempRef_c16, '_');
		c = tempRef_c16.argValue;

		MRef<String> tempRef_c17 = new MRef<String>(c);
		ge.SleepEnabled = readBool(tempRef_c17, '_');
		c = tempRef_c17.argValue;
		MRef<String> tempRef_c18 = new MRef<String>(c);
		ge.SleepTime = readInt(tempRef_c18, '_');
		c = tempRef_c18.argValue;
		MRef<String> tempRef_c19 = new MRef<String>(c);
		ge.SleepType = SleepModeType.forValue(readInt(tempRef_c19, '_'));
		c = tempRef_c19.argValue;

		if (!c.equals(""))
		{
			throw new RuntimeException("全局帧事件字符串解析剩余：" + c);
		}

		return ge;
	}

	public static ArrayList<GlobalEvents> parseEvents(String title, BufferedReader mbg) throws IOException
	{
		int soundCount = Integer.parseInt(title.substring(0, title.indexOf("GlobalEvents")).trim());
		ArrayList<GlobalEvents> ret = new ArrayList<GlobalEvents>();
		for (int i = 0; i < soundCount; ++i)
		{
			ret.add(parseFrom(mbg.readLine()));
		}
		return ret;
	}

	public GlobalEvents clone()
	{
		GlobalEvents varCopy = new GlobalEvents();

		varCopy.Frame = this.Frame;
		varCopy.JumpEnabled = this.JumpEnabled;
		varCopy.JumpTarget = this.JumpTarget;
		varCopy.JumpTimes = this.JumpTimes;
		varCopy.VibrateEnabled = this.VibrateEnabled;
		varCopy.VibrateForce = this.VibrateForce;
		varCopy.VibrateTime = this.VibrateTime;
		varCopy.SleepEnabled = this.SleepEnabled;
		varCopy.SleepTime = this.SleepTime;
		varCopy.SleepType = this.SleepType;

		return varCopy;
	}
}