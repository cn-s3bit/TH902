package cn.s3bit.mbgparser.event;

public final class Condition
{
	public final static class Expression
	{
		public enum OpType
		{
			Greater,
			Less,
			Equals;

			public int getValue()
			{
				return this.ordinal();
			}

			public static OpType forValue(int value)
			{
				return values()[value];
			}
		}

		public String LValue;
		public OpType Operator = OpType.values()[0];
		public double RValue;

		public static Expression parseFrom(String c)
		{
			Expression e = new Expression();

			if (c.contains(">"))
			{
				e.Operator = OpType.Greater;
			}
			else if (c.contains("<"))
			{
				e.Operator = OpType.Less;
			}
			else if (c.contains("="))
			{
				e.Operator = OpType.Equals;
			}
			else
			{
				throw new IllegalArgumentException("未能解析表达式");
			}

			String[] values = c.split("[><=]");
			e.LValue = values[0];
			e.RValue = Double.parseDouble(values[1]);

			return e;
		}

		public Expression clone()
		{
			Expression varCopy = new Expression();

			varCopy.LValue = this.LValue;
			varCopy.Operator = this.Operator;
			varCopy.RValue = this.RValue;

			return varCopy;
		}
	}

	public final static class SecondCondition
	{
		public enum LogicOpType
		{
			And,
			Or;

			public int getValue()
			{
				return this.ordinal();
			}

			public static LogicOpType forValue(int value)
			{
				return values()[value];
			}
		}

		public LogicOpType LogincOp = LogicOpType.values()[0];
		public Expression Expr = new Expression();

		public SecondCondition clone()
		{
			SecondCondition varCopy = new SecondCondition();

			varCopy.LogincOp = this.LogincOp;
			varCopy.Expr = this.Expr.clone();

			return varCopy;
		}
	}

	public Expression First = new Expression();
	public SecondCondition Second;

	public static Condition parseFrom(String c)
	{
		SecondCondition.LogicOpType op = null;

		if (c.contains("且"))
		{
			op = SecondCondition.LogicOpType.And;
		}
		else if (c.contains("或"))
		{
			op = SecondCondition.LogicOpType.Or;
		}

		Condition condition = new Condition();
		if (op == null)
		{
			condition.First = Expression.parseFrom(c);
			condition.Second = null;
		}
		else
		{
			String[] exprs = c.split("[且或]");
			condition.First = Expression.parseFrom(exprs[0]);
			condition.Second = new SecondCondition();
			condition.Second.LogincOp = op;
			condition.Second.Expr = Expression.parseFrom(exprs[1]);
		}

		return condition;
	}

	public Condition clone()
	{
		Condition varCopy = new Condition();

		varCopy.First = this.First.clone();
		varCopy.Second = this.Second;

		return varCopy;
	}
}