package cn.s3bit.th902.gamecontents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Stream;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.utils.ImmutableWrapper;

public class JudgingSystem {
	public static Vector2 playerJudge = new Vector2(-1000, -1000);
	protected static HashMap<ImmutableWrapper<Shape2D>, IJudgeCallback> enemyJudges = new HashMap<>();
	protected static HashMap<ImmutableWrapper<Vector2>, IJudgeCallback> friendlyJudges = new HashMap<>();
	public static HashMap<ImmutableWrapper<Vector2>, IJudgeCallback> chaseableEnemyPositions = new HashMap<>();
	private static Entry<ImmutableWrapper<Shape2D>, IJudgeCallback> mJudgeEntry;
	public static IJudgeCallback playerCollision() {
		Stream<Entry<ImmutableWrapper<Shape2D>, IJudgeCallback>> stream = enemyJudges.entrySet().parallelStream();
		mJudgeEntry = null;
		stream.forEach((entry) -> {
			if (entry.getValue().getDamage() > 0 && entry.getKey().getData().contains(playerJudge)) {
				mJudgeEntry = entry;
			}
		});
		stream.close();
		return mJudgeEntry == null ? null : mJudgeEntry.getValue();
	}
	
	public static IJudgeCallback collideFriendlyBullets(Shape2D judge) {
		for (Iterator<Entry<ImmutableWrapper<Vector2>, IJudgeCallback>> iterator = friendlyJudges.entrySet().iterator(); iterator.hasNext();) {
			Entry<ImmutableWrapper<Vector2>, IJudgeCallback> pos = iterator.next();
			if (judge.contains(pos.getKey().getData()))
				return pos.getValue();
		}
		return null;
	}
	
	public static void judgeEnemyHurt() {
		for (Iterator<Entry<ImmutableWrapper<Shape2D>, IJudgeCallback>> iterator = enemyJudges.entrySet().iterator(); iterator.hasNext();) {
			Entry<ImmutableWrapper<Shape2D>, IJudgeCallback> wrapper = iterator.next();
			if (wrapper.getValue().canHurt()) {
				IJudgeCallback callback = collideFriendlyBullets(wrapper.getKey().getData());
				if (callback != null) {
					callback.onCollide();
					wrapper.getValue().onHurt(callback.getDamage());
				}
			}
		}
	}
	
	public static void registerEnemyJudge(ImmutableWrapper<Shape2D> judge, IJudgeCallback callback) {
		enemyJudges.put(judge, callback);
	}
	
	public static void registerFriendlyJudge(ImmutableWrapper<Vector2> judge, IJudgeCallback callback) {
		friendlyJudges.put(judge, callback);
	}
	
	public static void unregisterEnemyJudge(ImmutableWrapper<Shape2D> judge) {
		enemyJudges.remove(judge);
	}
	
	public static void unregisterFriendlyJudge(ImmutableWrapper<Vector2> judge) {
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
