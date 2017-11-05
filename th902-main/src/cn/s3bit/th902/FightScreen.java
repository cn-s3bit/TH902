package cn.s3bit.th902;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.player.PlayerReimu;

public class FightScreen extends ScreenAdapter {
	private BitmapFont bf;

	@Override
	public void show() {
		super.show();
		bf = new BitmapFont();
		Entity fightScreenEntity = Entity.Create();
		fightScreenEntity.AddComponent(new Transform(new Vector2(480, 360)));
		fightScreenEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("FightScreen"), 0));
		
		Entity player = Entity.Create();
		player.AddComponent(new Transform(new Vector2(240, 160), new Vector2(0.4f, 0.4f)));
		player.AddComponent(new PlayerReimu());
	}

	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
		GameMain.instance.activeStage.getBatch().begin();
		bf.draw(GameMain.instance.activeStage.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), 20, 35);
		GameMain.instance.activeStage.getBatch().end();
	}
}
