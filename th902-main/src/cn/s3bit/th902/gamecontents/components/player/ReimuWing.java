package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class ReimuWing extends Component {

	protected Vector2 mVector2 = new Vector2();
	protected int existTime;
	protected Transform transform;
	private float mRotationFlag;
	private ImageRenderer mRenderer;
	private int mId;

	public ReimuWing(Vector2 position, int id) {
		mId = id;
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(mRenderer = new ImageRenderer(ResourceManager.barrages.get(236), 0));
		entity.AddComponent(this);
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		mRenderer.attachToGroup(FightScreen.drawingLayers.entity2);
		mRotationFlag = mId == 0 ? 4f : -4f;
	}

	@Override
	public void Update() {
		if (mVector2.epsilonEquals(-200f, -200f, 1e-3f)) {
			mRenderer.image.setColor(1, 1, 1, 0);
			return;
		}
		mRenderer.image.setColor(1, 1, 1, 1);
		transform.position.add(mVector2.sub(transform.position).scl(0.2f));
		mVector2.set(-200f, -200f);
		existTime++;
		transform.rotation += PlayerReimu.ReimuWingShoot ?  mRotationFlag * 3 : mRotationFlag;
		if (mId == 0) {
			if (Player.slow) {
				mVector2.set(JudgingSystem.playerJudge.x - 25, JudgingSystem.playerJudge.y + 25);
			} else {
				mVector2.set(JudgingSystem.playerJudge.x - 50, JudgingSystem.playerJudge.y);
			}
		} else {
			if (Player.slow) {
				mVector2.set(JudgingSystem.playerJudge.x + 25, JudgingSystem.playerJudge.y + 25);
			} else {
				mVector2.set(JudgingSystem.playerJudge.x + 50, JudgingSystem.playerJudge.y);
			}
		}
		if (FightScreen.PlayerType == FightScreen.PlayerTypeA) {
			// if (!Player.slow) {
			if (PlayerReimu.ReimuWingShoot) {
				ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeWingFastInduce);
				// }
			}
		} else {
			if (!Player.slow) {
				if (PlayerReimu.ReimuWingShoot) {
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
