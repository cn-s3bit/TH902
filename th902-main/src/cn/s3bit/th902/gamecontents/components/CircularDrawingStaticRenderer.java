package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.utils.CircularDrawingStatic;

public class CircularDrawingStaticRenderer extends AbstractRenderer {
	public CircularDrawingStatic circularDrawingStatic;
	Transform transform;
	private int mDepth;
	public CircularDrawingStaticRenderer(Texture texture, int beginDegrees, int endDegrees, int depth) {
		circularDrawingStatic = new CircularDrawingStatic(texture, beginDegrees, endDegrees);
		setDepth(depth);
		setOrigin(Align.bottomLeft);
	}

	/**
	 * @param depth -1 for the front, 0 for the back.
	 *              Otherwise it is an index from 0 to count(Images) - 1.
	 *              A larger index is in the front of the smaller.
	 */
	public void setDepth(int depth) {
		mDepth = depth;
		if (mDepth == -1) {
			circularDrawingStatic.toFront();
		}
		else {
			circularDrawingStatic.setZIndex(mDepth);
		}
	}

	@Override
	public Actor getActor() {
		return circularDrawingStatic;
	}

	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_ALL;
	}
}
