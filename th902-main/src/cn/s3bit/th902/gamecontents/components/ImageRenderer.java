package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;

public class ImageRenderer extends Component {
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
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		GameMain.instance.activeStage.addActor(image);
		setDepth(mDepth);
	}
	
	@Override
	public void Update() {
		image.setPosition(transform.position.x, transform.position.y, Align.center);
		image.setRotation(transform.rotation);
		image.setScale(transform.scale.x, transform.scale.y);
	}

	@Override
	public void Kill() {
		image.remove();
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
			image.toFront();
		}
		else {
			image.setZIndex(mDepth);
		}
	}
}
