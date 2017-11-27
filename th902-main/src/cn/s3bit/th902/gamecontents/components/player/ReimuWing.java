package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.MathUtils;
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
	protected boolean ifSlow = false;
	protected Transform transform;
	private float mRotationFlag;
	protected boolean ifShoot = false;
	protected float bulletRnd = 0;
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
		this.ifSlow = ifSlow;
		this.ifShoot = ifShoot;
	}

	@Override
	public void Update() {
		transform.position.add(mVector2.sub(transform.position).scl(0.2f));
		existTime++;
		if (mType == FightScreen.PlayerTypeA) {
			if (ifSlow) {
				if (ifShoot) {
					transform.rotation = transform.rotation + mRotationFlag * 3;
				} else {
					transform.rotation += mRotationFlag;
				}
			} else {
				transform.rotation += mRotationFlag;
				if (ifShoot) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 6), ReimuBullet1.BulletTypeWingFastInduce);
				}
			}
		} else {
			if (ifSlow) {
				if (ifShoot) {
					transform.rotation = transform.rotation + mRotationFlag * 3;
				} else {
					transform.rotation += mRotationFlag;
				}
			} else {
				transform.rotation += mRotationFlag;
				if (ifShoot) {
					bulletRnd = MathUtils.random(-6, 6);
					ReimuBullet1.Create(transform.position.cpy().add(-10, bulletRnd),
							ReimuBullet1.BulletTypeWingFastStraight);
					ReimuBullet1.Create(transform.position.cpy().add(0, bulletRnd + 6),
							ReimuBullet1.BulletTypeWingFastStraight);
					ReimuBullet1.Create(transform.position.cpy().add(10, bulletRnd),
							ReimuBullet1.BulletTypeWingFastStraight);
				}
			}
		}

	}

	@Override
	public void Kill() {
		super.Kill();
	}

}
