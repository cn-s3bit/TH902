package cn.s3bit.th902.backend.win64;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.PlatformRelatedInterfaces;
import cn.s3bit.th902.utils.IFont;
/**
 * @author Obsidianss
 * <p>
 * The launcher of the game and the wrapper of LwjglApplication.
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
		Game game = new GameMain(new PlatformRelatedInterfaces() {
			@Override
			public IFont getFont(String fontName, boolean isBold, boolean isItalic, int size) {
				FreetypeFont font = new FreetypeFont();
				font.initialize(fontName, isBold, isItalic, size);
				return font;
			}
		});
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.width = 960;
		config.height = 720;
		config.resizable = false;
		config.title = GameMain.GAME_TITLE;
		config.foregroundFPS = 0;
		config.addIcon("resources/icon32.png", FileType.Internal);
		lwjglApplication = new LwjglApplication(game, config);
	}
}
