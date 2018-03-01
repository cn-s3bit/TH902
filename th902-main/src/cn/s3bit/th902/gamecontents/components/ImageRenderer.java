package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import cn.s3bit.th902.gamecontents.Entity;

public class ImageRenderer extends AbstractRenderer {
	Transform transform = null;
	public Image image = null;
	private int mDepth;
	/**
	 * @param texture The Texture.
	 * @param depth -1 for the front, 0 for the back.
	 *              Otherwise it is an index from 0 to count(Images) - 1.
	 *              A larger index is in the front of the smaller.
	 */
	public ImageRenderer(Texture texture, int depth) {
		image = new Image(texture);
		image.setBounds(0, 0, texture.getWidth(), texture.getHeight());
		mDepth = depth;
	}
	public ImageRenderer(Texture texture, int depth, int width, int height) {
		image = new Image(texture);
		image.setBounds(0, 0, width, height);
		mDepth = depth;
	}
	/**
	 * @param texture The Drawable Texture.
	 * @param depth -1 for the front, 0 for the back.
	 *              Otherwise it is an index from 0 to count(Images) - 1.
	 *              A larger index is in the front of the smaller.
	 */
	public ImageRenderer(Drawable texture, int depth) {
		image = new Image(texture);
		image.setBounds(0, 0, texture.getMinWidth(), texture.getMinHeight());
		mDepth = depth;
	}
	public ImageRenderer(Drawable texture, int depth, int width, int height) {
		image = new Image(texture);
		image.setBounds(0, 0, width, height);
		mDepth = depth;
	}
	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		image.setZIndex(mDepth);
	}
	@Override
	public Actor getActor() {
		return image;
	}
	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_ALL;
	}
}
