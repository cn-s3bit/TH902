package cn.s3bit.th902.tests;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ai.MoveTracking;
import cn.s3bit.th902.gamecontents.components.enemy.MovingLaser;

public class LaserTestScene extends DanmakuScene {

	@Override
	public void Initialize(Entity entity) {
		yield.append(() -> {
			Entity proj = MovingLaser.Create(new Vector2(285, 500), 4, 30, 1200, 5f);
			proj.AddComponent(new MoveTracking(3f));
		});
	}

}
