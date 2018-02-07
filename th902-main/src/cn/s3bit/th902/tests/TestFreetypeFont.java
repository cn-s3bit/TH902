package cn.s3bit.th902.tests;

import com.badlogic.gdx.graphics.Color;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.utils.IFont;

public class TestFreetypeFont {
	static IFont font = null;
	public static void draw() {
		if (font == null) font = GameMain.PRI.getFont("微软雅黑", false, false, 42);
		font.draw(GameMain.instance.activeStage.getBatch(), "Test\nSmile :)\n 中文测试", 100, 100, 1, Color.WHITE); 
	}
}
