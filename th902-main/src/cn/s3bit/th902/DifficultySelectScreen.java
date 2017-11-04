package cn.s3bit.th902;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ui.KeyboardSelectable;

public class DifficultySelectScreen extends ScreenAdapter {
	public static int difficulty = -1;
	@Override
	public void show() {
		super.show();
		Entity difficultySelectScreenEntity = Entity.Create();
		Entity selectDifficultyEntity = Entity.Create();
		Entity easyEntity = Entity.Create();
		Entity normalEntity = Entity.Create();
		Entity hardEntity = Entity.Create();
		Entity lunaticEntity = Entity.Create();
		
		difficultySelectScreenEntity.AddComponent(new Transform(new Vector2(480, 360)));
		selectDifficultyEntity.AddComponent(new Transform(new Vector2(600, 580)));
		easyEntity.AddComponent(new Transform(new Vector2(680, 500)));
		normalEntity.AddComponent(new Transform(new Vector2(660, 420)));
		hardEntity.AddComponent(new Transform(new Vector2(680, 340)));
		lunaticEntity.AddComponent(new Transform(new Vector2(665, 260)));
		
		difficultySelectScreenEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("DifficultySelect"), 0));
		selectDifficultyEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("SelectDifficulty"), 1));
		
		final Runnable nextScreen = () -> { GameMain.instance.setScreen(new CharacterSelectScreen()); };
		KeyboardSelectable[] actions = {
				new KeyboardSelectable(() -> { difficulty = 1; Entity.postUpdate.add(nextScreen); }),
				new KeyboardSelectable(() -> { difficulty = 2; Entity.postUpdate.add(nextScreen); }),
				new KeyboardSelectable(() -> { difficulty = 3; Entity.postUpdate.add(nextScreen); }),
				new KeyboardSelectable(() -> { difficulty = 4; Entity.postUpdate.add(nextScreen); })
		};
		for (int i=0; i<3; i++) {
			actions[i].linkedNode.insertAfter(actions[i + 1].linkedNode);
		}
		actions[0].isSelected = true;
		
		easyEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Easy"), 2));
		easyEntity.AddComponent(actions[0]);
		normalEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Normal"), 2));
		normalEntity.AddComponent(actions[1]);
		hardEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Hard"), 2));
		hardEntity.AddComponent(actions[2]);
		lunaticEntity.AddComponent(new ImageRenderer(ResourceManager.textures.get("Lunatic"), 2));
		lunaticEntity.AddComponent(actions[3]);
	}
	
	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
	}
}
