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
	private Vector2 nearestEnemyPosition = Vector2.Zero;
	private Vector2 mTarget=Vector2.Zero;

	protected Transform transform;
	protected Entity entity;
	private Vector2 mTempTargetVelocity=new Vector2();;
	public ReimuBullet1(Vector2 target){
		mTarget=target;
	}
	public ReimuBullet1(){
		
	}
	public static void Create(Vector2 position, int bulletType) {

		switch (bulletType) {
		case BulletTypeSelfFast:
			Entity entity11 = Entity.Create();
			entity11.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet11 = new ReimuBullet1(new Vector2(-3,27));			
			entity11.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity11.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			entity11.AddComponent(bullet11);
			Entity entity12 = Entity.Create();
			entity12.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet12 = new ReimuBullet1(new Vector2(-1,27));			
			entity12.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity12.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			entity12.AddComponent(bullet12);
			Entity entity13 = Entity.Create();
			entity13.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet13 = new ReimuBullet1(new Vector2(1,27));			
			entity13.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity13.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			entity13.AddComponent(bullet13);
			Entity entity14 = Entity.Create();
			entity14.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet14 = new ReimuBullet1(new Vector2(3,27));			
			entity14.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity14.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			entity14.AddComponent(bullet14);
			break;
		case BulletTypeSelfSlow:
			Entity entity21 = Entity.Create();
			entity21.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet21 = new ReimuBullet1(new Vector2(-2,27));			
			entity21.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity21.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			entity21.AddComponent(bullet21);
			Entity entity22 = Entity.Create();
			entity22.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet22 = new ReimuBullet1(new Vector2(0,27));			
			entity22.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity22.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			entity22.AddComponent(bullet22);
			Entity entity23 = Entity.Create();
			entity23.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet23 = new ReimuBullet1(new Vector2(2,27));			
			entity23.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity23.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			entity23.AddComponent(bullet23);
			break;
		case BulletTypeWingFastStraight:
			Entity entity211 = Entity.Create();
			entity211.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet211 = new ReimuBullet1(new Vector2(0,27));	
			entity211.AddComponent(new ImageRenderer(ResourceManager.barrages.get(237), 0));
			entity211.AddComponent(bullet211);
			break;
		case BulletTypeWingSlowStraight:
			Entity entity31 = Entity.Create();
			entity31.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet31 = new ReimuBullet1();	
			bullet31.mDamage = 4;
			entity31.AddComponent(new ImageRenderer(ResourceManager.barrages.get(238), 0));
			entity31.AddComponent(bullet31);
			break;
		case BulletTypeWingFastInduce:
			Entity entity41 = Entity.Create();
			entity41.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet41 = new ReimuBullet1();	
			bullet41.isInduce = true;
			bullet41.mDamage = 1;
			entity41.AddComponent(new ImageRenderer(ResourceManager.barrages.get(243), 0));
			entity41.AddComponent(bullet41);
			break;
		case BulletTypeWingSlowInduce:
			Entity entity51 = Entity.Create();
			entity51.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet51 = new ReimuBullet1();	
			bullet51.isInduce = true;
			bullet51.mDamage = 1;
			entity51.AddComponent(new ImageRenderer(ResourceManager.barrages.get(244), 0));
			entity51.AddComponent(bullet51);
			break;
		}
	}


	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		JudgingSystem.registerFriendlyJudge(transform.immutablePosition, this);
		this.entity = entity;
	}

	@Override
	public void Update() {
		if (isInduce) {
			nearestEnemyPosition = getNearestEnemy();
			if (nearestEnemyPosition.equals(Vector2.Zero)) {
				if (mTempTargetVelocity.equals(Vector2.Zero)) {
					mTarget.set(0, 19);
				} else {
					mTarget=mTempTargetVelocity;
				}
			} else {
				mTempTargetVelocity.set(nearestEnemyPosition).sub(transform.position).nor().scl(19);
				mTarget=mTempTargetVelocity;
				transform.rotation = 90 + mTempTargetVelocity.angle();
			}
		} else {
			if (mTarget.equals(Vector2.Zero)) {
				mTarget.set(0, 27);
			} 
		}
		transform.position.add(mTarget);
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
