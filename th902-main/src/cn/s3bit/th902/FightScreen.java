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
	private int _difficulty = DifficultySelectScreen.difficulty;
	private String mDifficulty[] = {"Easy","Normal","Hard","Lunatic"};

	@Override
	public void show() {
		super.show();
		bf = new BitmapFont();
		Entity player = Entity.Create();
		Entity fightScreen = Entity.Create();
		Entity difficulty = Entity.Create();
		Entity score = Entity.Create();
		Entity highScore = Entity.Create();
		Entity graze = Entity.Create();
		Entity players = Entity.Create();
		Entity spellCard = Entity.Create();
		Entity bomb = Entity.Create();
		Entity heart = Entity.Create();
		
		fightScreen.AddComponent(new Transform(new Vector2(480, 360)));
		player.AddComponent(new Transform(new Vector2(240, 160), new Vector2(0.4f, 0.4f)));
		difficulty.AddComponent(new Transform(new Vector2(730, 670))); 
		score.AddComponent(new Transform(new Vector2(480, 360)));
		highScore.AddComponent(new Transform(new Vector2(480, 360)));
		graze.AddComponent(new Transform(new Vector2(480, 360)));
		players.AddComponent(new Transform(new Vector2(480, 360)));
		spellCard.AddComponent(new Transform(new Vector2(480, 360)));
		bomb.AddComponent(new Transform(new Vector2(480, 360)));
		heart.AddComponent(new Transform(new Vector2(480, 360)));
		
		fightScreen.AddComponent(new ImageRenderer(ResourceManager.textures.get("FightScreen"), 0));
		_difficulty = 4;
		if(_difficulty > 0 && _difficulty < 5)
			difficulty.AddComponent(new ImageRenderer(ResourceManager.textures.get(mDifficulty[_difficulty - 1]), 1));;
			score.AddComponent(new ImageRenderer(ResourceManager.textures.get("Score"), 0));
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
