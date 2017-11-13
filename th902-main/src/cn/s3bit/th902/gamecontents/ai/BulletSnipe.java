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

public class BulletSnipe extends BaseProj {
	private Vector2 VecAcc;

	public static Entity Create(Vector2 position, BulletType form, BulletType color, float bulletSpeed) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(
			cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile.bulletTypeArray[form.getType()][color.getType()]),0));
		entity.AddComponent(new BulletSnipe(form.getType(),bulletSpeed));
		return entity;
	}
	public static Entity Create(Vector2 position, BulletType form, BulletType color, float speed,float acceleration) {
		if (speed==0&&acceleration==0) {
			throw new IllegalArgumentException("spe&acc both are 0");
		}
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(
			cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile.bulletTypeArray[form.getType()][color.getType()]),0));
		entity.AddComponent(new BulletSnipe(form.getType(),speed,acceleration));
		return entity;
	}
	public BulletSnipe(int type,float speed,Float acceleration) {
		this(type,speed);
		bulletAcceleration=acceleration;
	}

	public BulletSnipe(int type,float speed) {
		this(type, NullShape2D.instance);
		this.bulletSpeed=speed;
	}

	public BulletSnipe(int bulletForm, Shape2D judgeShape) {
		type = bulletForm;
		judge = ImmutableWrapper.wrap(judgeShape);
	}

	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		dirVec = JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor();
		transform.rotation = dirVec.angle() - 90;
		VecAcc=new Vector2(bulletAcceleration,0).rotate(dirVec.angle());
		dirVec.scl(bulletSpeed);
	}

	@Override
	public void Update() {
		super.Update();
		//acceleration
		dirVec.add(VecAcc);
		}
}
