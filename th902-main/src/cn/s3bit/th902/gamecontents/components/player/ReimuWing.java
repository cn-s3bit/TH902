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
	private ImageRenderer mRenderer;

	public ReimuWing(Vector2 position, float rotation, int type) {
		mType = type;
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(mRenderer = new ImageRenderer(ResourceManager.barrages.get(236), 0));
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
		mRenderer.setDepth(0);
		if (mVector2.epsilonEquals(-200f, -200f, 1e-3f)) {
			mRenderer.image.setColor(1, 1, 1, 0);
			return;
		}
		mRenderer.image.setColor(1, 1, 1, 1);
		transform.position.add(mVector2.sub(transform.position).scl(0.2f));
		mVector2.set(-200f, -200f);
		existTime++;
		transform.rotation = isShooting ? transform.rotation + mRotationFlag * 3 : mRotationFlag;
		if (mType == FightScreen.PlayerTypeA) {
			transform.rotation += mRotationFlag;
			if (isShooting) {
				ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeWingFastInduce);
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
