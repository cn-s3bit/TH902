package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.contents.ShootHitEffectSystem;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.AbstractRenderer;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.TrailRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.LineSegment;

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

	private int mDamage = 4;
	private Vector2 mTarget = new Vector2();
	public Vector2 oldPosition = new Vector2();
	public ImmutableWrapper<LineSegment> judge;
	protected Transform transform;
	protected Entity entity;
	public ReimuBullet1(Vector2 target){
		mTarget=target;
	}
	public ReimuBullet1(){
		
	}
	public static void Create(Vector2 position, int bulletType) {
		switch (bulletType) {
		case BulletTypeSelfFast:
			for (float i=-30f; i<=30f; i+=20) {
				Entity entity14 = Entity.Create();
				entity14.AddComponent(new Transform(position.cpy().add(i, 0)));
				ReimuBullet1 bullet14 = new ReimuBullet1(new Vector2(0, 17));			
				entity14.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
				entity14.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
				entity14.AddComponent(bullet14);
			}
			break;
		case BulletTypeSelfSlow:
			for (float i=-22.5f; i<22.6f; i+=15) {
				Entity entity21 = Entity.Create();
				entity21.AddComponent(new Transform(position.cpy().add(i, 0)));
				ReimuBullet1 bullet21 = new ReimuBullet1(new Vector2(0,17));			
				entity21.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
				entity21.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
				entity21.AddComponent(bullet21);
			}
			break;
		case BulletTypeWingFastStraight:
			Entity entity211 = Entity.Create();
			entity211.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet211 = new ReimuBullet1(new Vector2(0,17));	
			entity211.AddComponent(new ImageRenderer(ResourceManager.barrages.get(237), 0));
			entity211.AddComponent(bullet211);
			break;
		case BulletTypeWingSlowStraight:
			Entity entity31 = Entity.Create();
			entity31.AddComponent(new Transform(position.cpy()));
			ReimuBullet1 bullet31 = new ReimuBullet1(new Vector2(0,17));	
			bullet31.mDamage = 4;
			entity31.AddComponent(new ImageRenderer(ResourceManager.barrages.get(238), 0));
			entity31.AddComponent(bullet31);
			break;
		case BulletTypeWingFastInduce:
			ReimuBulletInduce.Create(position, false);
			break;
		case BulletTypeWingSlowInduce:
			ReimuBulletInduce.Create(position, true);
			break;
		}
	}

	@Override
	public void Initialize(Entity entity) {
		entity.GetComponent(AbstractRenderer.class).attachToGroup(FightScreen.drawingLayers.entity1);
		transform = entity.GetComponent(Transform.class);
		oldPosition.set(transform.position);
		LineSegment lineSegment = new LineSegment();
		lineSegment.pStart = oldPosition;
		lineSegment.pEnd = transform.position;
		judge = ImmutableWrapper.wrap(lineSegment);
		JudgingSystem.registerFriendlyJudge(judge, this);
		this.entity = entity;
	}

	@Override
	public void Update() {
		oldPosition.set(transform.position);
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
		ShootHitEffectSystem.addEffect(transform.position);
		entity.Destroy();
	}

	@Override
	public float getDamage() {
		return mDamage;
	}
}
