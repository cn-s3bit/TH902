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
		// TODO Add UI Entities Here
		Entity backgroundEntity = Entity.Create();
		Entity button1Entity = Entity.Create();
		byte select = (byte) (Math.random() * 5 + 1);
		//select = 5;
		switch (select) {
		case 1:
			backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
			backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background1"), 0));
			button1Entity.AddComponent(new Transform(new Vector2(620, 240)));
			button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Button1"), 1));
			break;
		case 2:
			backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
			backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background2"), 0));
			button1Entity.AddComponent(new Transform(new Vector2(300, 650)));
			button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Button1"), 1));
			break;
		case 3:
			backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
			backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background3"), 0));
			button1Entity.AddComponent(new Transform(new Vector2(280, 630)));
			button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Button1"), 1));
			break;
		case 4:
			backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
			backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background4"), 0));
			button1Entity.AddComponent(new Transform(new Vector2(900, 650)));
			button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Button1"), 1));
			break;
		case 5:
			backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
			backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background5"), 0));
			button1Entity.AddComponent(new Transform(new Vector2(870, 600)));
			button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Button1"), 1));
			break;
		default:
			backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
			backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background1"), 0));
			button1Entity.AddComponent(new Transform(new Vector2(620, 240)));
			button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Button1"), 1));
			break;
		}
	}

	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
	}
}
