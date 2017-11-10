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
	private static Entry<ImmutableWrapper<Shape2D>, IJudgeCallback> mJudgeEntry;
	public static IJudgeCallback playerCollision() {
		Stream<Entry<ImmutableWrapper<Shape2D>, IJudgeCallback>> stream = enemyJudges.entrySet().parallelStream();
		mJudgeEntry = null;
		stream.forEach((entry) -> {
			if (entry.getKey().getData().contains(playerJudge)) {
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
}
