package cn.s3bit.th902.utils;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Obsidianss
 * A "Null" Shape2D which always returns false on calling "contains",
 * so as to stand for a shape that covers no area.
 */
public class NullShape2D implements Shape2D {
	/**
	 * The "Null" Shapes are always the same.
	 */
	public static final NullShape2D instance = new NullShape2D();
	private NullShape2D() {
		
	}
	
	@Override
	public boolean contains(Vector2 arg0) {
		return false;
	}

	@Override
	public boolean contains(float arg0, float arg1) {
		return false;
	}
	
}
