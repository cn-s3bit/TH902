package cn.s3bit.mbgparser.event;

import static cn.s3bit.mbgparser.event.DataOperateAction.*;

import cn.s3bit.mbgparser.MRef;

public final class ActionHelper
{
	public static IAction parseFrom(String c)
	{
		if (c.contains("变化到") || c.contains("增加") || c.contains("减少"))
		{
			return parseFrom(c);
		}
		else
		{
			return CommandAction.parseFrom(c);
		}
	}

	public static void parseFirstSentence(String firstSentence, MRef<String> lValue, MRef<OperatorType> op, MRef<String> rValue)
	{
		int pos = firstSentence.indexOf("变化到");
		op.argValue = OperatorType.ChangeTo;
		if (pos == -1)
		{
			pos = firstSentence.indexOf("增加");
			op.argValue = OperatorType.Add;
			if (pos == -1)
			{
				pos = firstSentence.indexOf("减少");
				op.argValue = OperatorType.Subtraction;
				if (pos == -1)
				{
					throw new RuntimeException("无法解析操作符");
				}
			}
		}

		lValue.argValue = firstSentence.substring(0, pos);

		int operatorLength = 2;
		if (op.argValue == OperatorType.ChangeTo)
			operatorLength = 3;

		rValue.argValue = firstSentence.substring(pos + operatorLength);
	}
}