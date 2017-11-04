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
		// select = 5;
		switch (select) {
		case 2:
			button1Entity.AddComponent(new Transform(new Vector2(300, 650)));
			break;
		case 3:
			button1Entity.AddComponent(new Transform(new Vector2(280, 630)));
			break;
		case 4:
			button1Entity.AddComponent(new Transform(new Vector2(900, 650)));
			break;
		case 5:
			button1Entity.AddComponent(new Transform(new Vector2(870, 600)));
			break;
		default:
			button1Entity.AddComponent(new Transform(new Vector2(620, 240)));
			break;
		}
		backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
		for (byte i = 1; i <= 5; i++)
			if (i == select)
				backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background" + i), 0));
		button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Button1"), 1));
	}

	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
	}
}
