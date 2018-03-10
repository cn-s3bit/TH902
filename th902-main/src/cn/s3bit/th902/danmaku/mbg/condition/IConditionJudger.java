package cn.s3bit.th902.danmaku.mbg.condition;

import cn.s3bit.mbgparser.event.Condition;
import cn.s3bit.mbgparser.event.Condition.Expression;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;

public interface IConditionJudger<T extends AbstractMBGComponent<?>> {
	public default boolean judgeCondition(T obj, Condition condition, int scaledTime) {
		if (condition.Second != null) {
			switch (condition.Second.LogincOp) {
			case And:
				return judgeExpr(obj, condition.First, scaledTime) && judgeExpr(obj, condition.Second.Expr, scaledTime);
			case Or:
				return judgeExpr(obj, condition.First, scaledTime) || judgeExpr(obj, condition.Second.Expr, scaledTime);
			}
			throw new AssertionError("见鬼了，Enum中出了个叛徒：" + condition.Second.LogincOp);
		}
		return judgeExpr(obj, condition.First, scaledTime);
	}
	public boolean judgeExpr(T obj, Expression expression, int scaledTime);
}
