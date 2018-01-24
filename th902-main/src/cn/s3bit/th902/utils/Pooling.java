package cn.s3bit.th902.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public final class Pooling {
	public static final Pool<Vector2> VECTOR2 = new Pool<Vector2>() {

		@Override
		protected Vector2 newObject() {
			return new Vector2();
		}
		
	};
	
	public static final Pool<Circle> CIRCLE = new Pool<Circle>() {

		@Override
		protected Circle newObject() {
			return new Circle();
		}
		
	};
	
	public static final Pool<Rectangle> RECTANGLE = new Pool<Rectangle>() {

		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
		
	};
}
