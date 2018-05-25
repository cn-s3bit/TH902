package cn.s3bit.th902.gamecontents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Stream;

import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMovement;
import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.LineSegment;

public class JudgingSystem {
	public static Vector2 playerJudge = new Vector2(-1000, -1000);
	public static HashMap<ImmutableWrapper<Ellipse>, PlayerCollisionData> enemyJudges = new HashMap<>();
	public static HashMap<ImmutableWrapper<LineSegment>, IJudgeCallback> friendlyJudges = new HashMap<>();
	public static HashMap<ImmutableWrapper<Vector2>, IJudgeCallback> chaseableEnemyPositions = new HashMap<>();
	private static Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData> mJudgeEntry;
	public static HashMap<ImmutableWrapper<Vector2>, Entity> clearByBombs = new HashMap<>();
	
	public static class PlayerCollisionData {
		public Ellipse judge;
		public Transform transform;
		public IJudgeCallback judgeCallback;
		public IMovement movement;
		public PlayerCollisionData(Ellipse judge, Transform transform, IJudgeCallback judgeCallback, IMovement movement) {
			this.judge = judge;
			this.judgeCallback = judgeCallback;
			this.movement = movement;
			this.transform = transform;
		}
	}
	
	public static PlayerCollisionData playerCollision() {
		Stream<Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData>> stream = enemyJudges.entrySet().parallelStream();
		mJudgeEntry = null;
		stream.forEach((entry) -> {
			if (entry.getValue().judgeCallback.getDamage() <= 0 || Math.max(entry.getKey().getData().width, entry.getKey().getData().height) < 0.1f) {
				return;
			}
			Vector2 tmp = new Vector2(playerJudge);
			tmp.sub(entry.getKey().getData().x, entry.getKey().getData().y).rotate(-entry.getValue().transform.rotation).add(entry.getKey().getData().x, entry.getKey().getData().y);
			if (entry.getKey().getData().contains(tmp)) {
				mJudgeEntry = entry;
			}
		});
		stream.close();
		return mJudgeEntry == null ? null : mJudgeEntry.getValue();
	}
	
	public static IJudgeCallback collideFriendlyBullets(Ellipse judge, float rotation) {
		for (Iterator<Entry<ImmutableWrapper<LineSegment>, IJudgeCallback>> iterator = friendlyJudges.entrySet().iterator(); iterator.hasNext();) {
			Entry<ImmutableWrapper<LineSegment>, IJudgeCallback> pos = iterator.next();
			if (pos.getKey().getData().intersectEllipse(judge, rotation))
				return pos.getValue();
		}
		return null;
	}
	
	public static void judgeEnemyHurt() {
		for (Iterator<Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData>> iterator = enemyJudges.entrySet().iterator(); iterator.hasNext();) {
			Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData> wrapper = iterator.next();
			if (wrapper.getValue().judgeCallback.canHurt()) {
				IJudgeCallback callback = null;
				do {
					callback = collideFriendlyBullets(wrapper.getKey().getData(), wrapper.getValue().transform.rotation);
					if (callback != null) {
						callback.onCollide();
						wrapper.getValue().judgeCallback.onHurt(callback.getDamage());
					}
				} while (callback != null);
			}
		}
	}
	
	public static void registerEnemyJudge(ImmutableWrapper<Ellipse> judge, Transform transform, IJudgeCallback callback, IMovement movement) {
		enemyJudges.put(judge, new PlayerCollisionData(judge.getData(), transform, callback, movement));
	}
	
	public static void registerFriendlyJudge(ImmutableWrapper<LineSegment> judge, IJudgeCallback callback) {
		friendlyJudges.put(judge, callback);
	}
	
	public static void unregisterEnemyJudge(ImmutableWrapper<Ellipse> judge) {
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
