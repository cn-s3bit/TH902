package cn.s3bit.th902;

import com.badlogic.gdx.ScreenAdapter;

import cn.s3bit.th902.gamecontents.Entity;

public class FightScreen extends ScreenAdapter {
	@Override
	public void show() {
		super.show();
	}
	
	@Override
	public void render(float delta) {
		Entity.UpdateAll();
		super.render(delta);
	}
}
