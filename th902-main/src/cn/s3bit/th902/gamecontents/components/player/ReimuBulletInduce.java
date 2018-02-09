package cn.s3bit.th902.gamecontents.components.player;

import java.util.Map.Entry;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.LineSegment;

public class ReimuBulletInduce extends Component implements IJudgeCallback {
	/**
	 * An easy method to create the bullet.
	 * 
	 * @return The created Entity.
	 */

	private int mDamage;
	private Vector2 nearestEnemyPosition = new Vector2();
	private Vector2 mTarget = new Vector2();
	public Vector2 oldPosition = new Vector2();
	public ImmutableWrapper<LineSegment> judge;
	protected Transform transform;
	protected Entity entity;
	private Vector2 mTempTargetVelocity = new Vector2();

	public ReimuBulletInduce() {

	}

	public static void Create(Vector2 position, boolean slow) {
		Entity entity1 = Entity.Create();
		entity1.AddComponent(new Transform(position.cpy(), new Vector2(0.25f, 0.25f)));
		ReimuBulletInduce bullet51 = new ReimuBulletInduce();
		if (slow) {
			bullet51.mDamage = 2;
		} else {
			bullet51.mDamage = 1;
		}
		entity1.AddComponent(new ImageRenderer(ResourceManager.barrages.get(244), 0));
		entity1.AddComponent(bullet51);
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		oldPosition.set(transform.position);
		LineSegment lineSegment = new LineSegment();
		lineSegment.pStart = oldPosition;
		lineSegment.pEnd = transform.position;
		judge = ImmutableWrapper.wrap(lineSegment);
		JudgingSystem.registerFriendlyJudge(judge, this);
		this.entity = entity;
		mTarget.set(0, 12);
	}

	@Override
	public void Update() {
		oldPosition.set(transform.position);
		nearestEnemyPosition = getNearestEnemy();
		if (!(nearestEnemyPosition.equals(Vector2.Zero) || nearestEnemyPosition.dst2(transform.position) > 90000)) {
			mTempTargetVelocity.set(nearestEnemyPosition).sub(transform.position).nor().scl(3);
			transform.rotation = 90 + mTarget.angle();
			mTarget.add(mTempTargetVelocity).nor().scl(12);
		}
		transform.position.add(mTarget);
		if (FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
	}

	@Override
	public void Kill() {
		JudgingSystem.unregisterFriendlyJudge(judge);
		super.Kill();
	}

	@Override
	public void onCollide() {
		entity.Destroy();
	}

	@Override
	public float getDamage() {
		return mDamage;
	}

	public Vector2 getNearestEnemy() {
		Vector2 vct2_tmp1 = new Vector2();
		Entry<ImmutableWrapper<Vector2>, IJudgeCallback> judge = JudgingSystem
				.calculateNearestChaseable(transform.position);
		if (judge == null)
			vct2_tmp1.set(0, 100000);
		else
			vct2_tmp1.set(judge.getKey().getData());
		return vct2_tmp1;
	}
}
