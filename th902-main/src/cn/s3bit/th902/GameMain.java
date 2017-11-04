package cn.s3bit.th902;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import cn.s3bit.th902.gamecontents.Entity;

/**
 * @author Obsidianss
 * <p>
 * Game class.
 * </p>
 */
public class GameMain extends Game {
	public static final String GAME_TITLE = "TH902";
	public static GameMain instance = null;
	
	public Stage activeStage = null;
	@Override
	public void create() {
		instance = this;
		ResourceManager.Load();
		//setScreen(new MainMenuScreen());
		setScreen(new FightScreen());
		//setScreen(new DifficultySelectScreen());
		//setScreen(new CharacterSelectScreen());
	}
	
	@Override
	public void setScreen(Screen screen) {
		if (getScreen() != screen) {
			if (activeStage != null) {
				activeStage.dispose();
			}
			activeStage = new Stage();
			Entity.Reset();
		}
		super.setScreen(screen);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		activeStage.draw();
		super.render();
	}
}
