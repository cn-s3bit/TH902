package cn.s3bit.th902.gamecontents;

import java.util.HashSet;
import java.util.Iterator;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class JudgingSystem {
	public static Vector2 playerJudge = new Vector2(-1000, -1000);
	public static HashSet<Shape2D> enemyJudges = new HashSet<>();
	public boolean Judge() {
		for (Iterator<Shape2D> iterator = enemyJudges.iterator(); iterator.hasNext();) {
			Shape2D judge = iterator.next();
			if (judge.contains(playerJudge)) {
				return true;
			}
		}
		return false;
	}
}
