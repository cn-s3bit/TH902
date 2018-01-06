package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.utils.CircularDrawingStatic;

public class CircularDrawingStaticRenderer extends Component {
	public CircularDrawingStatic circularDrawingStatic;
	Transform transform;
	private int mDepth;
	public CircularDrawingStaticRenderer(Texture texture, int beginDegrees, int endDegrees, int depth) {
		circularDrawingStatic = new CircularDrawingStatic(texture, beginDegrees, endDegrees);
		setDepth(depth);
	}
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		GameMain.instance.activeStage.addActor(circularDrawingStatic);
		Update();
	}

	@Override
	public void Update() {
		circularDrawingStatic.setPosition(transform.position.x, transform.position.y);
		circularDrawingStatic.setRotation(transform.rotation);
		circularDrawingStatic.setScale(transform.scale.x, transform.scale.y);
	}
	
	@Override
	public void Kill() {
		circularDrawingStatic.remove();
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
			circularDrawingStatic.toFront();
		}
		else {
			circularDrawingStatic.setZIndex(mDepth);
		}
	}
}
