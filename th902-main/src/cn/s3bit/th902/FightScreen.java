package cn.s3bit.th902;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.player.PlayerReimu;

public class FightScreen extends ScreenAdapter {
	private BitmapFont bf;
	private int _difficulty = DifficultySelectScreen.difficulty;
	private String mDifficulty[] = { "Easy", "Normal", "Hard", "Lunatic" };
	public static int gameTime=0;
	public static int playerCount=2;
	public static int bombCount=3;

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
		Entity bomb = Entity.Create();
		Entity heart = Entity.Create();

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
		bomb.AddComponent(new Transform(new Vector2(845, 450)));
		heart.AddComponent(new Transform(new Vector2(720, 500)));

		fightScreen.AddComponent(new ImageRenderer(ResourceManager.textures.get("FightScreen"), 0));
		if (_difficulty > 0 && _difficulty < 5)
			difficulty.AddComponent(new ImageRenderer(ResourceManager.textures.get(mDifficulty[_difficulty - 1]), 1));
		score.AddComponent(new ImageRenderer(ResourceManager.textures.get("Score"), 1));
		highScore.AddComponent(new ImageRenderer(ResourceManager.textures.get("HighScore"), 1));
		players.AddComponent(new ImageRenderer(ResourceManager.textures.get("Players"), 1));
		heart.AddComponent(new ImageRenderer(ResourceManager.textures.get("Heart"), 1));
		spellCard.AddComponent(new ImageRenderer(ResourceManager.textures.get("SpellCard"), 1));
		bomb.AddComponent(new ImageRenderer(ResourceManager.textures.get("Bomb"), 1));
		power.AddComponent(new ImageRenderer(ResourceManager.textures.get("Power"), 1));
		graze.AddComponent(new ImageRenderer(ResourceManager.textures.get("Graze"), 1));
		point.AddComponent(new ImageRenderer(ResourceManager.textures.get("Point"), 1));
		player.AddComponent(new PlayerReimu());
	}

	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
		drawStatus();
		gameTime++;
		GameMain.instance.activeStage.getBatch().begin();
		bf.draw(GameMain.instance.activeStage.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond()
		+"\ndifficulty:"+DifficultySelectScreen.difficulty
		+"\nplayer:"+playerCount
		+"\nbomb:"+bombCount
		+"\nbullets:"+BaseProjectile.bulletCount
		, 20, 705);
		GameMain.instance.activeStage.getBatch().end();

		switch (gameTime) {
        case 30:
        	BaseSprite.Create(new Vector2(000,400), 0);
            break;
        case 60:
        	BaseSprite.Create(new Vector2(100,400), 1);
            break;
        case 120:
        	BaseSprite.Create(new Vector2(150,300), 2);
            break;
        case 180:
        	BaseSprite.Create(new Vector2(700,350), 3);
        	gameTime=0;
        	break;
		}
	}
	private void drawStatus(){
		
	}
}
