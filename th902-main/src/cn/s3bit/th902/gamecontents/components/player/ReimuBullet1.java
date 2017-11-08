package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.TrailRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class ReimuBullet1 extends Component {
	/**
	 * An easy method to create the bullet.
	 * 
	 * @return The created Entity.
	 */

	public static final int BulletTypeSelfFast = 0;
	public static final int BulletTypeSelfSlow = 1;
	public static final int BulletTypeWingFast = 2;
	public static final int BulletTypeWingSlow = 3;

	public static Entity Create(Vector2 position, int bulletType) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		switch (bulletType) {
		case BulletTypeSelfFast:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(239), 0));
			entity.AddComponent(new TrailRenderer(ResourceManager.barrages.get(240), 6, 0));
			break;
		case BulletTypeSelfSlow:

			break;
		case BulletTypeWingFast:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(237), 0));
			break;
		case BulletTypeWingSlow:
			entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(238), 0));
			break;
		}
		entity.AddComponent(new ReimuBullet1());
		return entity;
	}

	protected Transform transform;
	protected Entity entity;

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		JudgingSystem.friendlyJudges.add(transform.position);
		this.entity = entity;
	}

	@Override
	public void Update() {
		transform.position.add(0, 27);
		if (transform.position.y > 777) {
			entity.Destroy();
		}
	}

	@Override
	public void Kill() {
		JudgingSystem.friendlyJudges.remove(transform.position);
		super.Kill();
	}
}
