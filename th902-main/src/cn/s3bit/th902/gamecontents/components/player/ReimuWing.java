package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class ReimuWing extends BaseWing {

	private int[] pos = new int[] { //
			0, 40, // 1p低速x y
			0, 64, // 1p高速x y
			-32, 40, 32, 40, // 2p 低速 1号子机x y 2号子机x y
			-64, 0, 64, 0, // 2p 高速 1号子机x y 2号子机x y
			-32, 40, 0, 60, 32, 40, //
			-64, 0, 0, 64, 64, 0, //
			-32, 40, -16, 60, 16, 60, 32, 40, //
			-64, 0, -32, 64, 32, 64, 64, 0//
	};

	public ReimuWing(PlayerReimu playerReimu, int id) {
		mId = id;
		player = playerReimu;
		subPlanePosition = pos;
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(playerReimu.transform.position.cpy()));
		entity.AddComponent(mRenderer = new ImageRenderer(ResourceManager.barrages.get(236), 0));
		entity.AddComponent(this);
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		mRenderer.attachToGroup(FightScreen.drawingLayers.entity2);
		mRenderer.getActor().toBack();
	}

	@Override
	public void Update() {
		super.Update();
		if (FightScreen.PlayerType == FightScreen.PlayerTypeA) {
			if (player.wingShoot) {
				ReimuBullet1.Create(transform.position.cpy().add(-15, 6), ReimuBullet1.BulletTypeWingFastStraight);
				ReimuBullet1.Create(transform.position.cpy().add(0, 12), ReimuBullet1.BulletTypeWingFastStraight);
				ReimuBullet1.Create(transform.position.cpy().add(15, 6), ReimuBullet1.BulletTypeWingFastStraight);
			}
		} else if (FightScreen.PlayerType == FightScreen.PlayerTypeB) {
			if (player.wingShoot) {
				ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeWingFastInduce);
			}
		}
	}

	@Override
	public void Kill() {
		super.Kill();
	}

}
