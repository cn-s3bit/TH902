package cn.s3bit.th902;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class CharacterSelectScreen extends ScreenAdapter {
	@Override
	public void show() {
		super.show();
		Entity characterSelectScreenEntity = Entity.Create();
		Entity selectCharacter = Entity.Create();
		
		characterSelectScreenEntity.AddComponent(new Transform(new Vector2(480, 360)));
		selectCharacter.AddComponent(new Transform(new Vector2(600, 600)));
		
		
		characterSelectScreenEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("CharacterSelect"), 0));
		selectCharacter.AddComponent(new ImageRenderer(ResourceManager.textures.get("SelectCharacter"), 1));
	}
	
	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
	}
}
