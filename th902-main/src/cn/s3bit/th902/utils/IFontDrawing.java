package cn.s3bit.th902.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class IFontDrawing extends Actor {
	public IFont font;
	public String text;
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.draw(batch, text, (int) getX(), (int) getY(), getScaleX(), new Color(Color.WHITE).mul(parentAlpha));
	}
}
