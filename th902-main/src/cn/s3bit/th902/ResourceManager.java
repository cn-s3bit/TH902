package cn.s3bit.th902;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class ResourceManager {
	public static HashMap<String, Texture> textures = new HashMap<>();
	public static void Load() {
		textures.clear();
		ArrayList<String> toLoad = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			toLoad.add("Background" + i);
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
			"Reimu"
		});
		for (String string : toLoad) {
			AddTexture(string, "resources/" + string + ".png", FileType.Internal);
		}
	}
	public static void AddTexture(String name, String path, FileType type) {
		textures.put(name, new Texture(Gdx.files.getFileHandle(path, type)));
	}
}
