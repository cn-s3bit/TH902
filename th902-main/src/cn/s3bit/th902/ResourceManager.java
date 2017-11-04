package cn.s3bit.th902;

import java.util.HashMap;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class ResourceManager {
	public static HashMap<String, Texture> textures = new HashMap<>();
	public static void Load() {
		textures.clear();
		AddTexture("icon32", "resources/icon32.png", FileType.Internal);
	}
	public static void AddTexture(String name, String path, FileType type) {
		textures.put(name, new Texture(Gdx.files.getFileHandle(path, type)));
	}
}
