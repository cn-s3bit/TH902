package cn.s3bit.mbgparser.event;

import cn.s3bit.mbgparser.MRef;
import cn.s3bit.mbgparser.ValueWithRand;

public final class DataOperateAction implements IAction
{
	public String LValue;
	public int TweenTime;
	public Integer Times;
	public ValueWithRand RValue;

	public enum TweenFunctionType
	{
		Proportional,
		Fixed,
		Sin;

		public int getValue()
		{
			return this.ordinal();
		}

		public static TweenFunctionType forValue(int value)
		{
			return values()[value];
		}
	}

	public TweenFunctionType TweenFunction = TweenFunctionType.values()[0];

	public enum OperatorType
	{
		ChangeTo,
		Add,
		Subtraction;

		public int getValue()
		{
			return this.ordinal();
		}

		public static OperatorType forValue(int value)
		{
			return values()[value];
		}
	}

	public OperatorType Operator = OperatorType.values()[0];

	public static DataOperateAction parseFrom(String c)
	{
		String[] sents = c.split("[，]", -1);

		DataOperateAction d = new DataOperateAction();
		MRef<String> tempRef_LValue = new MRef<String>(d.LValue);
		MRef<OperatorType> tempRef_Operator = new MRef<OperatorType>(d.Operator);
		MRef<String> tempRef_RValue = new MRef<String>("");
		ActionHelper.parseFirstSentence(sents[0], tempRef_LValue, tempRef_Operator, tempRef_RValue);
		d.LValue = tempRef_LValue.argValue;
		d.Operator = tempRef_Operator.argValue;
		d.RValue = new ValueWithRand();
		String r = tempRef_RValue.argValue;
		if (r.contains("+")) {
			d.RValue.baseValue = r.split("\\+")[0].equals("自机") ? -99999f : Float.parseFloat(r.split("\\+")[0]);
			d.RValue.randValue = Float.parseFloat(r.split("\\+")[1]);
		} else
			d.RValue.baseValue = r.equals("自机") ? -99999f: Float.parseFloat(r);

		switch (sents[1])
		{
			case "固定":
				d.TweenFunction = TweenFunctionType.Fixed;
				break;

			case "正比":
				d.TweenFunction = TweenFunctionType.Proportional;
				break;

			case "正弦":
				d.TweenFunction = TweenFunctionType.Sin;
				break;

			default:
				throw new IllegalArgumentException("无法解析变化曲线名称:" + sents[1]);
		}

		int tweenTimeEnd = sents[2].indexOf("帧");
		d.TweenTime = Integer.parseInt(sents[2].substring(0, tweenTimeEnd));

		d.Times = null;

		int timesL = sents[2].lastIndexOf('(');
		int timesR = sents[2].lastIndexOf(')');
		if (timesL != -1 && timesR != -1)
		{
			timesL++;
			d.Times = Integer.valueOf(sents[2].substring(timesL, timesR));
		}

		return d;
	}

	public DataOperateAction clone() {
		DataOperateAction varCopy = new DataOperateAction();

		varCopy.LValue = this.LValue;
		varCopy.TweenTime = this.TweenTime;
		varCopy.Times = this.Times;
		varCopy.RValue = this.RValue;
		varCopy.TweenFunction = this.TweenFunction;
		varCopy.Operator = this.Operator;

		return varCopy;
	}
}