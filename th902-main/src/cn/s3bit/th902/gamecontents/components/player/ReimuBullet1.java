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
	 * @return The created Entity.
	 */
	public static Entity Create(Vector2 position) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(65), 0));
		entity.AddComponent(new TrailRenderer(ResourceManager.barrages.get(67), 6, 0));
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
