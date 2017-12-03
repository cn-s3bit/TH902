package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class Bombs extends Component implements IJudgeCallback {

	public static final int TypeReimuAFast = 0;
	public static final int TypeReimuASlow = 1;
	public static final int TypeReimuBFast = 246;
	public static final int TypeReimuBSlow = 245;

	private int mType;
	private int mDamage = 2;
	private Vector2 nearestEnemyPosition = null;
	protected Transform transform;
	protected Entity entity;
	private Vector2 mTempTargetVelocity;
	private Vector2 mTempTargetVelocity2;
	private float mRotation = 0;
	private float mRotation2 = 0;
	private int mBombBulletTimeCount = 0;
	private float mScale = 1;

	public static void Create(Vector2 position, int type, Vector2 v, Vector2 vChange, float rotation, float rChange,
			float scale) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		Bombs bullet1 = new Bombs();
		bullet1.mType = type;
		bullet1.mRotation = rotation;
		bullet1.mRotation2 = rChange;
		bullet1.mScale = scale;
		bullet1.mTempTargetVelocity = v == null ? new Vector2(0, 27) : v;
		bullet1.mTempTargetVelocity2 = vChange == null ? Vector2.Zero : vChange;
		switch (type) {
		case TypeReimuAFast:
			bullet1.mDamage = 1;
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(type), 0));
			break;
		case TypeReimuASlow:
			bullet1.mDamage = 1;
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(type), 0));
			break;
		case TypeReimuBFast:
			entity.AddComponent(
					new ImageRenderer(ResourceManager.barrages.get(type), 0, 256, (int) FightScreen.TOP * 2));
			break;
		case TypeReimuBSlow:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(type), 0, 300, 300));
			break;
		}
		entity.AddComponent(bullet1);
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		//JudgingSystem.registerFriendlyJudge(transform.immutablePosition, this);
		this.entity = entity;
	}

	@Override
	public void Update() {
		mBombBulletTimeCount++;
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
			if (mBombBulletTimeCount > 120) {
				entity.Destroy();
			}
			if (!(mRotation == 0f)) {
				transform.rotation += mRotation;
				mRotation -= mRotation2;
			}
			if (!(mScale == 1.00f)) {
				transform.scale.x *= mScale;
				transform.scale.y *= mScale;
			}
			break;
		case TypeReimuBFast:
			if (mBombBulletTimeCount > 70) {
				entity.Destroy();
			}
			transform.rotation = mRotation;
			transform.position.add(mTempTargetVelocity);
			if (!mTempTargetVelocity2.equals(Vector2.Zero)) {
				mTempTargetVelocity.add(mTempTargetVelocity2);
			}
			break;

		}
		if (FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
	}

	@Override
	public void Kill() {
		//JudgingSystem.unregisterFriendlyJudge(transform.immutablePosition);
		super.Kill();
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
