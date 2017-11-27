package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class Bombs extends Component implements IJudgeCallback {

	public static final int TypeReimuAFast = 0;
	public static final int TypeReimuASlow = 1;
	public static final int TypeReimuBFast = 2;
	public static final int TypeReimuBSlow = 3;

	private int mType;
	private int mDamage = 2;
	private Vector2 nearestEnemyPosition = null;
	protected Transform transform;
	protected Entity entity;
	private Vector2 mTempTargetVelocity;

	public static Entity Create(Vector2 position, int bulletType) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		Bombs bullet1 = new Bombs();
		bullet1.mType = bulletType;
		switch (bulletType) {
		case TypeReimuAFast:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(0), 0));
			bullet1.mDamage = 1;
			break;
		case TypeReimuASlow:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(1), 0));
			bullet1.mDamage = 1;
			break;
		case TypeReimuBFast:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			break;
		case TypeReimuBSlow:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(2), 0));
			break;
		}
		entity.AddComponent(bullet1);
		return entity;
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		JudgingSystem.registerFriendlyJudge(transform.immutablePosition, this);
		this.entity = entity;
		mTempTargetVelocity = new Vector2();
	}

	@Override
	public void Update() {
		switch (mType) {
		case TypeReimuASlow:
			nearestEnemyPosition = getNearestEnemy();
			if (nearestEnemyPosition.equals(Vector2.Zero)) {
				if (mTempTargetVelocity.equals(Vector2.Zero)) {
					transform.position.add(0, 13);
				} else {
					transform.position.add(mTempTargetVelocity);
				}
			} else {
				mTempTargetVelocity.set(nearestEnemyPosition).sub(transform.position).nor().scl(13);
				transform.position.add(mTempTargetVelocity);
				transform.rotation = 90 + mTempTargetVelocity.angle();
			}
			break;
		case TypeReimuAFast:
			nearestEnemyPosition = getNearestEnemy();
			if (nearestEnemyPosition.equals(Vector2.Zero)) {
				if (mTempTargetVelocity.equals(Vector2.Zero)) {
					transform.position.add(0, 13);
				} else {
					transform.position.add(mTempTargetVelocity);
				}
			} else {
				mTempTargetVelocity.set(nearestEnemyPosition).sub(transform.position).nor().scl(13);
				transform.position.add(mTempTargetVelocity);
				transform.rotation = 90 + mTempTargetVelocity.angle();
			}
			break;
		case TypeReimuBSlow:
			transform.position.add(0, 27);
			break;
		case TypeReimuBFast:
			transform.position.add(0, 27);
			break;

		}
		if (FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
	}

	@Override
	public void Kill() {
		JudgingSystem.unregisterFriendlyJudge(transform.immutablePosition);
		super.Kill();
	}

	@Override
	public void onCollide() {

	}

	@Override
	public int getDamage() {
		return mDamage;
	}

	// To simulate get the nearest enemy's position
	float ex = 10;
	float ey = 600;

	public Vector2 getNearestEnemy() {
		// return Vector2.Zero;
		return ex < 300 ? new Vector2(ex += 10, ey) : Vector2.Zero;
	}
}
