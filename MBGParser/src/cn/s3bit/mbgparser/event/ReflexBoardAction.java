package cn.s3bit.mbgparser.event;

import java.util.*;

import cn.s3bit.mbgparser.MRef;
import cn.s3bit.mbgparser.event.DataOperateAction.OperatorType;

import static cn.s3bit.mbgparser.MBGUtils.*;

public final class ReflexBoardAction implements IAction
{
	public String LValue;
	public String RValue;
	public OperatorType Operator = OperatorType.values()[0];

	public static ReflexBoardAction parseFrom(String c)
	{
		ReflexBoardAction r = new ReflexBoardAction();
		MRef<String> tempRef_LValue = new MRef<String>(r.LValue);
		MRef<OperatorType> tempRef_Operator = new MRef<OperatorType>(r.Operator);
		MRef<String> tempRef_RValue = new MRef<String>(r.RValue);
		ActionHelper.parseFirstSentence(c, tempRef_LValue, tempRef_Operator, tempRef_RValue);
		r.LValue = tempRef_LValue.argValue;
		r.Operator = tempRef_Operator.argValue;
		r.RValue = tempRef_RValue.argValue;
		return r;
	}

	public static ArrayList<ReflexBoardAction> parseActions(String c)
	{
		if (isNullOrWhiteSpace(c))
		{
			return null;
		}
		else
		{
			ArrayList<ReflexBoardAction> ret = new ArrayList<ReflexBoardAction>();
			String[] actions = c.split("[&]", -1);
			for (String a : actions)
			{
				if (!isNullOrWhiteSpace(a))
				{
					ret.add(parseFrom(a));
				}
			}
			return ret;
		}
	}

	public ReflexBoardAction clone()
	{
		ReflexBoardAction varCopy = new ReflexBoardAction();

		varCopy.LValue = this.LValue;
		varCopy.RValue = this.RValue;
		varCopy.Operator = this.Operator;

		return varCopy;
	}
}