package cn.s3bit.mbgparser.item;

public enum ControlType
{
	All(0),
	ID(1);

	private int intValue;
	private static java.util.HashMap<Integer, ControlType> mappings;
	private static java.util.HashMap<Integer, ControlType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ControlType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ControlType>();
				}
			}
		}
		return mappings;
	}

	private ControlType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ControlType forValue(int value)
	{
		return getMappings().get(value);
	}
}