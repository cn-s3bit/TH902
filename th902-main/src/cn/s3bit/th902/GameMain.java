package cn.s3bit.th902;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

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
	public static PlatformRelatedInterfaces PRI;
	
	public static final boolean DEBUG = true;
	
	public Stage activeStage = null;
	
	public GameMain(final PlatformRelatedInterfaces PRI) {
		super();
		GameMain.PRI = PRI;
	}
	
	Task renderTask;
	
	@Override
	public void create() {
		instance = this;
		Gdx.graphics.setVSync(false);
		Gdx.graphics.setContinuousRendering(false);
		ResourceManager.Load();
		setScreen(new MainMenuScreen());
		renderTask = new Task() {
			@Override
			public void run() {
				Gdx.graphics.requestRendering();
			}
		};
		Timer.schedule(renderTask, 0f, 1f / 60f);
		//setScreen(new FightScreen());
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
		super.render();
		activeStage.draw();
		activeStage.getBatch().begin();
		FightScreen.bf.draw(activeStage.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), 850, 30);
		activeStage.getBatch().end();
		if (DEBUG)
			debugging();
	}
	
	protected void debugging() {
		if (Gdx.input.isKeyPressed(Keys.F1))
			Gdx.graphics.setContinuousRendering(true);
		else
			Gdx.graphics.setContinuousRendering(false);
		if (Gdx.input.isKeyJustPressed(Keys.F2)) {
			if (renderTask.isScheduled()) {
				renderTask.cancel();
			} else {
				Timer.schedule(renderTask, 0f, 1f / 60f);
			}
		}
	}
}
