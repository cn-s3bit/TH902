package cn.s3bit.th902;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.JudgingSystem;

public final class GameHelper {
	public static final Vector2 vct2_tmp1 = new Vector2();
	public static void chase(Vector2 toModify, final Vector2 target, final float speed) {
		vct2_tmp1.set(target).sub(toModify);
		toModify.add(vct2_tmp1.nor().scl(speed));
	}
	
	public static void snipeVct(Vector2 current, Vector2 target, float biasAng, Vector2 out) {
		target = target == null ? JudgingSystem.playerJudge : target;
		vct2_tmp1.set(target).sub(current).rotate(biasAng);
		out.set(vct2_tmp1);
	}
}
