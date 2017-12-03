package cn.s3bit.th902.gamecontents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Stream;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.LineSegment;

public class JudgingSystem {
	public static Vector2 playerJudge = new Vector2(-1000, -1000);
	public static HashMap<ImmutableWrapper<Circle>, IJudgeCallback> enemyJudges = new HashMap<>();
	public static HashMap<ImmutableWrapper<LineSegment>, IJudgeCallback> friendlyJudges = new HashMap<>();
	public static HashMap<ImmutableWrapper<Vector2>, IJudgeCallback> chaseableEnemyPositions = new HashMap<>();
	private static Entry<ImmutableWrapper<Circle>, IJudgeCallback> mJudgeEntry;
	public static HashMap<ImmutableWrapper<Vector2>, Entity> clearByBombs = new HashMap<>();
	
	public static IJudgeCallback playerCollision() {
		Stream<Entry<ImmutableWrapper<Circle>, IJudgeCallback>> stream = enemyJudges.entrySet().parallelStream();
		mJudgeEntry = null;
		stream.forEach((entry) -> {
			if (entry.getValue().getDamage() > 0 && entry.getKey().getData().contains(playerJudge)) {
				mJudgeEntry = entry;
			}
		});
		stream.close();
		return mJudgeEntry == null ? null : mJudgeEntry.getValue();
	}
	
	public static IJudgeCallback collideFriendlyBullets(Circle judge) {
		for (Iterator<Entry<ImmutableWrapper<LineSegment>, IJudgeCallback>> iterator = friendlyJudges.entrySet().iterator(); iterator.hasNext();) {
			Entry<ImmutableWrapper<LineSegment>, IJudgeCallback> pos = iterator.next();
			if (pos.getKey().getData().intersectCircle(judge))
				return pos.getValue();
		}
		return null;
	}
	
	public static void judgeEnemyHurt() {
		for (Iterator<Entry<ImmutableWrapper<Circle>, IJudgeCallback>> iterator = enemyJudges.entrySet().iterator(); iterator.hasNext();) {
			Entry<ImmutableWrapper<Circle>, IJudgeCallback> wrapper = iterator.next();
			if (wrapper.getValue().canHurt()) {
				IJudgeCallback callback = null;
				do {
					callback = collideFriendlyBullets(wrapper.getKey().getData());
					if (callback != null) {
						callback.onCollide();
						wrapper.getValue().onHurt(callback.getDamage());
					}
				} while (callback != null);
			}
		}
	}
	
	public static void registerEnemyJudge(ImmutableWrapper<Circle> judge, IJudgeCallback callback) {
		enemyJudges.put(judge, callback);
	}
	
	public static void registerFriendlyJudge(ImmutableWrapper<LineSegment> judge, IJudgeCallback callback) {
		friendlyJudges.put(judge, callback);
	}
	
	public static void unregisterEnemyJudge(ImmutableWrapper<Circle> judge) {
		enemyJudges.remove(judge);
	}
	
	public static void unregisterFriendlyJudge(ImmutableWrapper<LineSegment> judge) {
		friendlyJudges.remove(judge);
	}
	
	public static void registerChaseablePosition(ImmutableWrapper<Vector2> position, IJudgeCallback callback) {
		chaseableEnemyPositions.put(position, callback);
	}
	
	public static void unregisterChaseablePosition(ImmutableWrapper<Vector2> position) {
		chaseableEnemyPositions.remove(position);
	}
	
	/**
	 * Calculates the Nearest Chaseable Position.
	 * If there are no enemies, will return null.
	 */
	public static Entry<ImmutableWrapper<Vector2>, IJudgeCallback> calculateNearestChaseable(Vector2 position) {
		float dst2 = Float.MAX_VALUE;
		Entry<ImmutableWrapper<Vector2>, IJudgeCallback> nearest = null;
		for (Entry<ImmutableWrapper<Vector2>, IJudgeCallback> entry : chaseableEnemyPositions.entrySet()) {
			float dst2x = entry.getKey().getData().dst2(position);
			if (dst2 > dst2x) {
				dst2 = dst2x;
				nearest = entry;
			}
		}
		return nearest;
	}
}
