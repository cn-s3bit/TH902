package cn.s3bit.th902;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.player.PlayerEntity;
import cn.s3bit.th902.player.PlayerReimu;

public class FightScreen extends ScreenAdapter {
	private InputMultiplexer InputMgr;
	private BitmapFont bf;
	private SpriteBatch sbatch;

	@Override
	public void show() {
		super.show();
		bf = new BitmapFont();
		sbatch = new SpriteBatch();
		Entity fightScreenEntity = Entity.Create();
		fightScreenEntity.AddComponent(new Transform(new Vector2(480, 360)));
		fightScreenEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("FightScreen"), 0));
		new PlayerReimu().PlayerInit();
		InputMgr = new InputMultiplexer();
		InputMgr.addProcessor(new PlayerEntity.PlayerInputProcessor());
		Gdx.input.setInputProcessor(InputMgr);
	}

	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		PlayerEntity.PlayerEntityInstance.PlayerUpdate();
		sbatch.begin();
		bf.draw(sbatch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 20, 35);
		sbatch.end();
		super.render(delta);
	}
}
