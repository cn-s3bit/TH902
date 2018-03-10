package cn.s3bit.th902.danmaku.mbg.condition;

import cn.s3bit.mbgparser.event.Condition;
import cn.s3bit.mbgparser.event.Condition.Expression;
import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.danmaku.mbg.AbstractMBGComponent;
import cn.s3bit.th902.danmaku.mbg.MBGScene;

public final class BulletConditions implements IConditionJudger<AbstractMBGComponent<BulletEmitter>> {
	public static final BulletConditions instance = new BulletConditions();
	public boolean judgeCondition(AbstractMBGComponent<BulletEmitter> bullet, Condition condition, int scaledTime) {
		if (condition.Second != null) {
			switch (condition.Second.LogincOp) {
			case And:
				return judgeExpr(bullet, condition.First, scaledTime) && judgeExpr(bullet, condition.Second.Expr, scaledTime);
			case Or:
				return judgeExpr(bullet, condition.First, scaledTime) || judgeExpr(bullet, condition.Second.Expr, scaledTime);
			}
			throw new AssertionError("见鬼了，Enum中出了个叛徒：" + condition.Second.LogincOp);
		}
		return judgeExpr(bullet, condition.First, scaledTime);
	}
	
	public static boolean conditionOp(Condition.Expression.OpType opType, float p1, float p2) {
		switch (opType) {
		case Equals:
			return p1 == p2;
		case Less:
			return p1 < p2;
		case Greater:
			return p1 > p2;
		}
		throw new AssertionError("见鬼了，Enum中出了个叛徒：" + opType);
	}
	
	public boolean judgeExpr(AbstractMBGComponent<BulletEmitter> bullet, Expression expression, int scaledTime) {
		switch (expression.LValue) {
		case "X坐标":
			float x = transformBackX(bullet.mbgScene, bullet.transform.position.x);
			return conditionOp(expression.Operator, x, expression.RValue);
		case "Y坐标":
			float y = transformBackY(bullet.mbgScene, bullet.transform.position.y);
			return conditionOp(expression.Operator, y, expression.RValue);
		case "当前帧":
			return conditionOp(expression.Operator, scaledTime, expression.RValue);
		default:
			System.err.println("Warning: Currently not implemented condition: " + expression.LValue);
			return false;
		}
	}
	
	public static float transformBackX(MBGScene mbgScene, float x) {
		return (x - 285f) * 315f / 285f + mbgScene.mbgData.center.Position.x;
	}
	
	public static float transformBackY(MBGScene mbgScene, float y) {
		return -(y - 360f) * 240f / 360f + mbgScene.mbgData.center.Position.y;
	}
}
