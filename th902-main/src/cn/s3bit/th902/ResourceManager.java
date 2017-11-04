package cn.s3bit.th902;

import java.util.HashMap;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class ResourceManager {
	public static HashMap<String, Texture> textures = new HashMap<>();
	public static void Load() {
		textures.clear();
		AddTexture("Background1", "resources/Background1.png", FileType.Internal);
		AddTexture("Background2", "resources/Background2.png", FileType.Internal);
		AddTexture("Background3", "resources/Background3.png", FileType.Internal);
		AddTexture("Background4", "resources/Background4.png", FileType.Internal);
		AddTexture("Background5", "resources/Background5.png", FileType.Internal);
		AddTexture("Button1", "resources/Button1.png", FileType.Internal);
	}
	public static void AddTexture(String name, String path, FileType type) {
		textures.put(name, new Texture(Gdx.files.getFileHandle(path, type)));
	}
}
