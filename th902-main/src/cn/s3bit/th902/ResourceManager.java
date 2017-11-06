package cn.s3bit.th902;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class ResourceManager {
	public static HashMap<String, Texture> textures = new HashMap<>();
	public static ArrayList<Texture> barrages = new ArrayList<>();
	public static ArrayList<Texture> enemies = new ArrayList<>();
	public static void Load() {
		textures.clear();
		ArrayList<String> toLoad = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			toLoad.add("Background" + i);
		}
		for (int i = 0; i <= 238; i++) {

			barrages.add(new Texture(Gdx.files.internal("resources/Barrages/proj" + i + ".png")));
		}
		for (int i = 0; i <= 0; i++) {
			enemies.add(new Texture(Gdx.files.internal("resources/Enemies/enemy" + i + ".png")));
		}
		Collections.addAll(toLoad, new String[] {
			"Bomb",
			"CharacterSelect",
			"DifficultySelect",
			"Easy",
			"Exit",	
			"FightScreen",
			"Hard",
			"Heart",
			"Lunatic",
			"Normal",
			"Players",	
			"SelectCharacter",
			"SelectDifficulty",	
			"SpellCard",
			"StoryMode",	
			"Reimu",
			"Graze",
			"HighScore",
			"Score",
			"Point",
			"Power"
		});
		for (String string : toLoad) {
			AddTexture(string, "resources/" + string + ".png", FileType.Internal);
		}
	}
	public static void AddTexture(String name, String path, FileType type) {
		textures.put(name, new Texture(Gdx.files.getFileHandle(path, type)));
	}
}
