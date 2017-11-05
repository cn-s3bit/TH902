package cn.s3bit.th902.gamecontents;

import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Stream;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class JudgingSystem {
	public static Vector2 playerJudge = new Vector2(-1000, -1000);
	public static HashSet<Shape2D> enemyJudges = new HashSet<>();
	public static HashSet<Vector2> friendlyJudges = new HashSet<>();
	public static boolean isPlayerCollided = false;
	public static void CalculatePlayerCollided() {
		Stream<Shape2D> stream = enemyJudges.parallelStream();
		isPlayerCollided = false;
		stream.forEach((judge) -> {
			isPlayerCollided = isPlayerCollided || judge.contains(playerJudge);
		});
		stream.close();
	}
	
	public static boolean isCollidedByFriendlyBullets(Shape2D judge) {
		boolean result = false;
		for (Iterator<Vector2> iterator = friendlyJudges.iterator(); iterator.hasNext();) {
			Vector2 pos = iterator.next();
			result |= judge.contains(pos);
			if (result)
				break;
		}
		return result;
	}
}
