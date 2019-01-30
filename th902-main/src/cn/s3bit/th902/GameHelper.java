package cn.s3bit.th902;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.mbgparser.ValueWithRand;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.utils.RandomPool;

public final class GameHelper {
	public static final Vector2 vct2_tmp1 = new Vector2();
	public static void chase(Vector2 toModify, final Vector2 target, final float speed) {
		vct2_tmp1.set(target).sub(toModify);
		toModify.add(vct2_tmp1.nor().scl(speed));
	}
	
	public static Vector2 snipeVct(Vector2 current, Vector2 target, float biasAng, Vector2 out) {
		target = target == null ? JudgingSystem.playerJudge : target;
		vct2_tmp1.set(target).sub(current).rotate(biasAng);
		out.set(vct2_tmp1);
		return out;
	}
	
	public static void clearEnemyBullets() {
		Entity[] entities = (Entity[]) JudgingSystem.clearByBombs.values().toArray(new Entity[JudgingSystem.clearByBombs.values().size()]);
		for (Entity entity : entities) {
			entity.Destroy();
		}
	}
	
	public static float getValFromRandom(ValueWithRand valueWithRand) {
		return RandomPool.get(5).random(valueWithRand.baseValue - valueWithRand.randValue, valueWithRand.baseValue + valueWithRand.randValue);
	}
	
	static Circle circle_tmp = new Circle();
	public static Circle roundEllipseToCircle(Ellipse ellipse, boolean alloc) {
		if (!alloc) {
			circle_tmp.set(ellipse.x, ellipse.y, Math.min(ellipse.width, ellipse.height));
			return circle_tmp;
		} else
			return new Circle(ellipse.x, ellipse.y, Math.min(ellipse.width, ellipse.height));
	}
}
