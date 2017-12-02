package cn.s3bit.th902;

import com.badlogic.gdx.math.Vector2;

public final class GameHelper {
	public static final Vector2 vct2_tmp1 = new Vector2();
	public static void chase(Vector2 toModify, final Vector2 target, final float speed) {
		vct2_tmp1.set(target).sub(toModify);
		toModify.add(vct2_tmp1.nor().scl(speed));
	}
}
