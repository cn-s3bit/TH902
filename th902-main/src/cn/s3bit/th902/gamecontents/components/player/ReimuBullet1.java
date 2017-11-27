package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.TrailRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class ReimuBullet1 extends Component implements IJudgeCallback {
	/**
	 * An easy method to create the bullet.
	 * 
	 * @return The created Entity.
	 */

	public static final int BulletTypeSelfFast = 0;
	public static final int BulletTypeSelfSlow = 1;
	public static final int BulletTypeWingFastStraight = 2;
	public static final int BulletTypeWingSlowStraight = 3;
	public static final int BulletTypeWingFastInduce = 4;
	public static final int BulletTypeWingSlowInduce = 5;

	private boolean isInduce = false;
	private int mDamage = 2;
	private Vector2 nearestEnemyPosition = null;

	public static Entity Create(Vector2 position, int bulletType) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		ReimuBullet1 bullet1 = new ReimuBullet1();
		switch (bulletType) {
		case BulletTypeSelfFast:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			break;
		case BulletTypeSelfSlow:

			break;
		case BulletTypeWingFastStraight:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(237), 0));
			break;
		case BulletTypeWingSlowStraight:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(238), 0));
			bullet1.mDamage = 4;
			break;
		case BulletTypeWingFastInduce:
			bullet1.isInduce = true;
			bullet1.mDamage = 1;
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			break;
		case BulletTypeWingSlowInduce:
			bullet1.isInduce = true;
			bullet1.mDamage = 1;
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(240), 0));
			break;
		}
		entity.AddComponent(bullet1);
		return entity;
	}

	protected Transform transform;
	protected Entity entity;
	private Vector2 mTempTargetVelocity;

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		JudgingSystem.registerFriendlyJudge(transform.immutablePosition, this);
		this.entity = entity;
		mTempTargetVelocity = new Vector2();
	}

	@Override
	public void Update() {
		if (isInduce) {
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
		} else {
			transform.position.add(0, 27);
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
		entity.Destroy();
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
