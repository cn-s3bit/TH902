package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class DropItem extends BaseProjectile{

	public DropItem(int type) {
		super(type);
	}
	public static final int TypePower=241;
	
	
	public static Entity CreateDropItem(Vector2 position, int dropType) {
		if (dropType < 241 || dropType > 241) {
			throw new IllegalArgumentException("Type of the item is wrong.");
		}
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(dropType), 0));
		entity.AddComponent(new DropItem(dropType));
		return entity;
	}
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		this.entity = entity;
		dirVec=new Vector2(0,5);
	}
	@Override
	public void Update() {
		transform.position.add(dirVec.add(0,-0.1f));
	}
	
	

}
