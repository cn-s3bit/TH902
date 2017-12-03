package cn.s3bit.th902.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class LineSegment {
	public Vector2 pStart, pEnd;
	public LineSegment() {
		pStart = new Vector2();
		pEnd = new Vector2();
	}
	
	public LineSegment(Vector2 start, Vector2 end) {
		this();
		pStart.set(start);
		pEnd.set(end);
	}
	
	public LineSegment(float startX, float startY, float endX, float endY) {
		this();
		pStart.set(startX, startY);
		pEnd.set(endX, endY);
	}
	
	static final Vector2 vct2_tmp1 = new Vector2();
	public boolean intersectCircle(Circle circle) {
		vct2_tmp1.set(circle.x, circle.y);
		return Intersector.intersectSegmentCircle(pStart, pEnd, vct2_tmp1, circle.radius * circle.radius);
	}
}
