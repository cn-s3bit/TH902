package cn.s3bit.th902.gamecontents.components.ai;

import com.badlogic.gdx.math.Vector2;

public interface IMoveFunction {
	/**
	 * Three static Vector2s to be used in calculation, so as to save space.<br />
	 * Use vct2.set to assign value.
	 */
	public static final Vector2 vct2_tmp1 = new Vector2(),
								vct2_tmp2 = new Vector2(),
								vct2_tmp3 = new Vector2();
	public Vector2 getTargetVal(int time);
}
