package cn.s3bit.th902;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class MainMenuScreen extends ScreenAdapter {
	@Override
	public void show() {
		super.show();
		//TODO Add UI Entities Here
		Entity exampleEntity = Entity.Create();
		exampleEntity.AddComponent(new Transform(new Vector2(128, 128), new Vector2(4, 4)));
		exampleEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("icon32")));
	}
	
	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
	}
}
