package cn.s3bit.th902;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

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
	
	public GameMain(final PlatformRelatedInterfaces PRI) {
		super();
		GameMain.PRI = PRI;
	}
	
	@Override
	public void create() {
		instance = this;
		ResourceManager.Load();
		//setScreen(new MainMenuScreen());
		//setScreen(new FightScreen());
		//setScreen(new DifficultySelectScreen());
		//setScreen(new CharacterSelectScreen());
	}
	
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
