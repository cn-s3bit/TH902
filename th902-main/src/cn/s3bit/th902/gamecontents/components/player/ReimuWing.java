package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.KeySettings;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class ReimuWing extends Component {
	
	protected Vector2 mVector2=new Vector2();
	protected int existTime;
	protected boolean mIfSlow=false;
	protected Transform transform;

	public ReimuWing(Vector2 position){
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(236), 0));
		entity.AddComponent(this);
	}
	
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
	}
	public void set(Vector2 v,boolean ifSlow){
		mVector2=v;
		mIfSlow=ifSlow;
	}
	@Override
	public void Update() {
		transform.position.set(mVector2);
		mVector2.setZero();
		existTime++;
		if (Gdx.input.isKeyPressed(KeySettings.positiveKey) && existTime % 4 == 1) {
			float rnd = MathUtils.random(-6, 6);
			if(!mIfSlow){
			ReimuBullet1.Create(transform.position.cpy().add(-10, rnd),ReimuBullet1.BulletTypeWingFast);
			ReimuBullet1.Create(transform.position.cpy().add(10, rnd),ReimuBullet1.BulletTypeWingFast);
			}
		}
	}

	@Override
	public void Kill() {
		super.Kill();
	}

	
}
