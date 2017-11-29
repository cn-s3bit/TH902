package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class ReimuWing extends Component {

	protected Vector2 mVector2 = new Vector2();
	protected int existTime;
	protected boolean isSlow = false;
	protected Transform transform;
	private float mRotationFlag;
	protected boolean isShooting = false;
	private int mType;

	public ReimuWing(Vector2 position, float rotation, int type) {
		mType = type;
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(236), 0));
		entity.AddComponent(this);
		mRotationFlag = rotation;
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
	}

	public void set(Vector2 v, boolean ifSlow, boolean ifShoot) {
		mVector2 = v;
		this.isSlow = ifSlow;
		this.isShooting = ifShoot;
	}

	@Override
	public void Update() {
		transform.position.add(mVector2.sub(transform.position).scl(0.2f));
		existTime++;
		transform.rotation = isShooting ? transform.rotation + mRotationFlag * 3 : mRotationFlag;
		if (mType == FightScreen.PlayerTypeA) {
			if (!isSlow) {
				transform.rotation += mRotationFlag;
				if (isShooting) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 6), ReimuBullet1.BulletTypeWingFastInduce);
				}
			}
		} else {
			if (!isSlow) {
				transform.rotation += mRotationFlag;
				if (isShooting) {
					ReimuBullet1.Create(transform.position.cpy().add(-15, 6), ReimuBullet1.BulletTypeWingFastStraight);
					ReimuBullet1.Create(transform.position.cpy().add(0, 12), ReimuBullet1.BulletTypeWingFastStraight);
					ReimuBullet1.Create(transform.position.cpy().add(15, 6), ReimuBullet1.BulletTypeWingFastStraight);
				}
			}
		}

	}

	@Override
	public void Kill() {
		super.Kill();
	}

}
