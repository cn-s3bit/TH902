package cn.s3bit.th902.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

public interface IFont {
	public void initialize(String fontName, boolean isBold, boolean isItalic, int size);
	public void draw(Batch batch, String string, int x, int y, float scale, Color color);
	public void setExtraLineSpace(int space);
	public void setExtraCharSpace(int space);
}
