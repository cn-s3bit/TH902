package cn.s3bit.th902.backend.win64;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import cn.s3bit.th902.GameMain;
//9Test9Test
/**
 * @author Obsidianss
 * <p>
 * The launcher of the game and the wrapper of LwjglApplication
 * </p>
 */
public class Launcher {
	/**
	 * The {@link LwjglApplication} instance.
	 */
	public static LwjglApplication lwjglApplication;
	/**
	 * Entry point.
	 */
	public static void main(String[] args) {
		Game game = new GameMain();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.width = 960;
		config.height = 720;
		config.resizable = false;
		config.title = GameMain.GAME_TITLE;
		config.addIcon("resources/icon32.png", FileType.Internal);
		lwjglApplication = new LwjglApplication(game, config);
	}
}
