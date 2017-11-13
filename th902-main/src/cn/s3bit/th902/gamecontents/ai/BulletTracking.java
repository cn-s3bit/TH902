package cn.s3bit.th902.gamecontents.ai;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.NullShape2D;

public class BulletTracking extends BaseProj {
	private int time=0;
	public static Entity Create(Vector2 position, BulletType form, BulletType color, float bulletSpeed) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(
			cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile.bulletTypeArray[form.getType()][color.getType()]),0));
		entity.AddComponent(new BulletTracking(form.getType(),bulletSpeed));
		return entity;
	}
	public BulletTracking(int type,float speed,Float acceleration) {
		this(type,speed);
		bulletAcceleration=acceleration;
	}

	public BulletTracking(int type,float speed) {
		this(type, NullShape2D.instance);
		this.bulletSpeed=speed;
	}

	public BulletTracking(int bulletForm, Shape2D judgeShape) {
		type = bulletForm;
		judge = ImmutableWrapper.wrap(judgeShape);
	}

	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		dirVec = JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor();
		transform.rotation = dirVec.angle() - 90;
		dirVec.scl(bulletSpeed);
	}

	@Override
	public void Update() {
		super.Update();
		//acceleration
		dirVec=JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor().scl(bulletSpeed);
		transform.rotation = dirVec.angle() - 90;
		if(++time>300){
			entity.Destroy();
		}
	}
}

