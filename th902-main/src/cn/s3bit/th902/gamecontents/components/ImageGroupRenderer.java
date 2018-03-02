package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class ImageGroupRenderer extends AbstractRenderer {
	public Group group = null;
	public Vector2[] biases = null;
	public Image[] images = null;
	private int mDepth;
	/**
	 * @param texture The Texture.
	 * @param depth -1 for the front, 0 for the back.
	 *              Otherwise it is an index from 0 to count(Images) - 1.
	 *              A larger index is in the front of the smaller.
	 * @param bias The transform of each texture to the parent's center.
	 */
	public ImageGroupRenderer(Texture[] textures, int depth, Vector2[] bias) {
		Drawable[] drawables = new Drawable[textures.length];
		for (int i = 0; i < drawables.length; i++)
			drawables[i] = new TextureRegionDrawable(new TextureRegion(textures[i]));
		_init(drawables, depth, bias);
	}
	
	public ImageGroupRenderer(Drawable[] textures, int depth, Vector2[] bias) {
		_init(textures, depth, bias);
	}
	
	public void _init(Drawable[] textures, int depth, Vector2[] bias) {
		if (bias != null && bias.length != textures.length)
			throw new IllegalArgumentException();
		group = new Group();
		images = new Image[textures.length];
		for (int i=0; i<textures.length; i++) {
			Drawable texture = textures[i];
			Image image = new Image(texture);
			images[i] = image;
			image.setBounds(0, 0, texture.getMinWidth(), texture.getMinHeight());
			if (bias != null)
				image.setPosition(bias[i].x, bias[i].y, Align.center);
			else
				image.setPosition(0, 0, Align.center);
			group.addActor(image);
		}
		mDepth = depth;
		biases = bias;
	}
	
	@Override
	public void Update() {
		super.Update();
		if (biases != null) {
			for (int i = 0; i < biases.length; i++) {
				images[i].setPosition(biases[i].x, biases[i].y, Align.center);
			}
		}
	}
	
	/**
	 * @param depth -1 for the front, 0 for the back.
	 *              Otherwise it is an index from 0 to count(Images) - 1.
	 *              A larger index is in the front of the smaller.
	 */
	public void setDepth(int depth) {
		mDepth = depth;
		if (mDepth == -1) {
			group.toFront();
		}
		else {
			group.setZIndex(mDepth);
		}
	}

	@Override
	public Actor getActor() {
		return group;
	}

	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_ALL;
	}
}
