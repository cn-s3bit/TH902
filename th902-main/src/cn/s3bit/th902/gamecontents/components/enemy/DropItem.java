package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IItemGetLogic;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.player.Player;

public class DropItem extends BaseProjectile {

	public DropItem(int type, Vector2 velo) {
		super(type, velo);
	}

	public DropItem(int type, IItemGetLogic logic, Vector2 velo) {
		super(type, velo);
		itemGetLogic = logic;
	}

	public static final int TypePower = 241;

	public static Entity CreateDropItem(Vector2 position, int dropType) {
		if (dropType < 241 || dropType > 241) {
			throw new IllegalArgumentException("Type of the item is wrong.");
		}
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(dropType), 0));
		entity.AddComponent(new DropItem(dropType, null));
		return entity;
	}

	public IItemGetLogic itemGetLogic = IItemGetLogic.NONE;

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		this.entity = entity;
		dirVec = new Vector2(0, 5);
	}

	private boolean mIsDragged = false;

	@Override
	public void Update() {
		if (mIsDragged) {
			dirVec.set(JudgingSystem.playerJudge).sub(transform.position).nor().scl(9f);
		}
		transform.position.add(dirVec.add(0, -0.1f));
		if (transform.position.dst2(JudgingSystem.playerJudge) < 6400||Player.onLine||Player.Bomb) {
			mIsDragged = true;
			if (transform.position.dst2(JudgingSystem.playerJudge) < 400) {
				if (itemGetLogic.onGet()) {
					if (FightScreen.powerCount < 100) {
						FightScreen.powerCount += 5;
					}
					entity.Destroy();
				}
			}
		} else if (transform.position.y < -40) {
			entity.Destroy();
		}
	}
}
