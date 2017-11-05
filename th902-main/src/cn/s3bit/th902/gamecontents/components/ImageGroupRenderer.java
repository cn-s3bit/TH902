package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;

public class ImageGroupRenderer extends Component {
	Transform transform = null;
	public Group group = null;
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
		for (int i=0; i<textures.length; i++) {
			Drawable texture = textures[i];
			Image image = new Image(texture);
			image.setBounds(0, 0, texture.getMinWidth(), texture.getMinHeight());
			if (bias != null)
				image.setPosition(bias[i].x, bias[i].y, Align.center);
			else
				image.setPosition(0, 0, Align.center);
			group.addActor(image);
		}
		mDepth = depth;
	}
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		GameMain.instance.activeStage.addActor(group);
		setDepth(mDepth);
	}
	
	@Override
	public void Update() {
		group.setPosition(transform.position.x, transform.position.y, Align.center);
		group.setOrigin(Align.center);
		group.setRotation(transform.rotation);
		group.setScale(transform.scale.x, transform.scale.y);
	}

	@Override
	public void Kill() {
		group.remove();
		super.Kill();
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
}
