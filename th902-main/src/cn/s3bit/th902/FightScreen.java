package cn.s3bit.th902;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.player.PlayerReimu;

public class FightScreen extends ScreenAdapter {
	private BitmapFont bf;
	private int _difficulty = DifficultySelectScreen.difficulty;
	private String mDifficulty[] = { "Easy", "Normal", "Hard", "Lunatic" };
	private ImageRenderer mBombs[] = new ImageRenderer[8];
	private ImageRenderer mHearts[] = new ImageRenderer[8];
	public static int gameTime = 0;
	public static int playerCount = 2;
	public static int bombCount = 3;
	public static int powerCount = 0;

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
		Entity point = Entity.Create();
		Entity power = Entity.Create();
		Entity players = Entity.Create();
		Entity spellCard = Entity.Create();
		Entity bombs[] = new Entity[8];
		Entity hearts[] = new Entity[8];

		fightScreen.AddComponent(new Transform(new Vector2(480, 360)));
		player.AddComponent(new Transform(new Vector2(240, 160), new Vector2(0.4f, 0.4f)));
		difficulty.AddComponent(new Transform(new Vector2(760, 670)));
		score.AddComponent(new Transform(new Vector2(810, 570)));
		highScore.AddComponent(new Transform(new Vector2(635, 620)));
		graze.AddComponent(new Transform(new Vector2(810, 300)));
		point.AddComponent(new Transform(new Vector2(810, 250)));
		power.AddComponent(new Transform(new Vector2(810, 350)));
		players.AddComponent(new Transform(new Vector2(810, 500)));
		spellCard.AddComponent(new Transform(new Vector2(615, 450)));

		if (_difficulty > 0 && _difficulty < 5)
			difficulty.AddComponent(new ImageRenderer(ResourceManager.textures.get(mDifficulty[_difficulty - 1]), 1));
		score.AddComponent(new ImageRenderer(ResourceManager.textures.get("Score"), 1));
		highScore.AddComponent(new ImageRenderer(ResourceManager.textures.get("HighScore"), 1));
		players.AddComponent(new ImageRenderer(ResourceManager.textures.get("Players"), 1));
		spellCard.AddComponent(new ImageRenderer(ResourceManager.textures.get("SpellCard"), 1));
		power.AddComponent(new ImageRenderer(ResourceManager.textures.get("Power"), 1));
		graze.AddComponent(new ImageRenderer(ResourceManager.textures.get("Graze"), 1));
		point.AddComponent(new ImageRenderer(ResourceManager.textures.get("Point"), 1));
		player.AddComponent(new PlayerReimu());

		for (int i = 0; i < 8; i++) {
			bombs[i] = Entity.Create();
			hearts[i] = Entity.Create();
			bombs[i].AddComponent(new Transform(new Vector2(720.5f + 30.0f * i, 450.5f)));
			hearts[i].AddComponent(new Transform(new Vector2(720.5f + 30.0f * i, 500.5f)));
			bombs[i].AddComponent(new ImageRenderer(ResourceManager.textures.get("Bomb"), 1));
			hearts[i].AddComponent(new ImageRenderer(ResourceManager.textures.get("Heart"), 1));
			mBombs[i] = bombs[i].GetComponent(ImageRenderer.class);
			mHearts[i] = hearts[i].GetComponent(ImageRenderer.class);
			mBombs[i].image.setColor(1, 1, 1, 0);
			mHearts[i].image.setColor(1, 1, 1, 0);
		}
		fightScreen.AddComponent(new ImageRenderer(ResourceManager.textures.get("FightScreen"), 0));
	}

	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
		gameTime++;
		GameMain.instance.activeStage.getBatch().begin();
		bf.draw(GameMain.instance.activeStage.getBatch(),
				"FPS: " + Gdx.graphics.getFramesPerSecond() + "\ndifficulty:" + DifficultySelectScreen.difficulty
						+ "\nplayer:" + playerCount + "\nbomb:" + bombCount + "\npower:" + powerCount,
				20, 705);
		GameMain.instance.activeStage.getBatch().end();

		for (int i = 0; i < 8; i++)
			if (i < playerCount)
				mHearts[i].image.setColor(1, 1, 1, 1);
			else
				mHearts[i].image.setColor(1, 1, 1, 0);
		for (int i = 0; i < 8; i++)
			if (i < bombCount)
				mBombs[i].image.setColor(1, 1, 1, 1);
			else
				mBombs[i].image.setColor(1, 1, 1, 0);
		switch (gameTime) {
		case 30:
			BaseSprite.Create(new Vector2(000, 400), 0);
			break;
		case 60:
			BaseSprite.Create(new Vector2(100, 400), 1);
			break;
		case 120:
			BaseSprite.Create(new Vector2(150, 300), 2);
			break;
		case 180:
			BaseSprite.Create(new Vector2(250, 350), 3);
			gameTime = 0;
			break;
		}
	}
}
