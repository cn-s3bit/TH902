package cn.s3bit.th902;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ui.KeyboardSelectable;

public class MainMenuScreen extends ScreenAdapter {
	@Override
	public void show() {
		super.show();
		Entity backgroundEntity = Entity.Create();
		Entity button1Entity = Entity.Create();
		Entity exit = Entity.Create();
		byte select = (byte) (Math.random() * 5 + 1);
		// select = 5;
		switch (select) {
		case 2:
			button1Entity.AddComponent(new Transform(new Vector2(300, 650)));
			exit.AddComponent(new Transform(new Vector2(300, 600)));
			break;
		case 3:
			button1Entity.AddComponent(new Transform(new Vector2(280, 630)));
			exit.AddComponent(new Transform(new Vector2(280, 580)));
			break;
		case 4:
			button1Entity.AddComponent(new Transform(new Vector2(900, 650)));
			exit.AddComponent(new Transform(new Vector2(900, 600)));
			break;
		case 5:
			button1Entity.AddComponent(new Transform(new Vector2(870, 600)));
			exit.AddComponent(new Transform(new Vector2(870, 550)));
			break;
		default:
			button1Entity.AddComponent(new Transform(new Vector2(620, 240)));
			exit.AddComponent(new Transform(new Vector2(620, 190)));
			break;
		}
		backgroundEntity.AddComponent(new Transform(new Vector2(480, 360)));
		backgroundEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Background" + select), 0));
		button1Entity.AddComponent(new ImageRenderer(ResourceManager.textures.get("StoryMode"), 1));
		exit.AddComponent(new ImageRenderer(ResourceManager.textures.get("Exit"), 1));
		KeyboardSelectable last = new KeyboardSelectable(() -> {
			Entity.postUpdate.add(() -> { GameMain.instance.setScreen(new DifficultySelectScreen()); });
		});
		last.isSelected = true;
		KeyboardSelectable exitSelectable = new KeyboardSelectable(() -> {
			System.exit(0);
		});
		last.linkedNode.insertAfter(exitSelectable.linkedNode);
		exit.AddComponent(exitSelectable);
		button1Entity.AddComponent(last);
		MusicManager.PlayBGM(Gdx.files.internal("resources/BGM/Starting Scene.mp3"), true);
	}

	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
	}
}
