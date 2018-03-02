package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.utils.IFont;
import cn.s3bit.th902.utils.IFontDrawing;

public class IFontRenderer extends AbstractRenderer {
	public IFontDrawing fontDrawing;
	public IFontRenderer(String text, IFont font) {
		fontDrawing = new IFontDrawing();
		fontDrawing.font = font;
		fontDrawing.text = text;
		setOrigin(Align.bottomLeft);
	}

	/**
	 * @param depth -1 for the front, 0 for the back.
	 *              Otherwise it is an index from 0 to count(Images) - 1.
	 *              A larger index is in the front of the smaller.
	 */
	public void setDepth(int depth) {
		if (depth == -1) {
			fontDrawing.toFront();
		}
		else {
			fontDrawing.setZIndex(depth);
		}
	}

	@Override
	public Actor getActor() {
		return fontDrawing;
	}

	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_POSITION | UPDATE_SCALE;
	}
}
