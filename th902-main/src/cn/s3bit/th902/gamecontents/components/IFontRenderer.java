package cn.s3bit.th902.gamecontents.components;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.utils.IFont;
import cn.s3bit.th902.utils.IFontDrawing;

public class IFontRenderer extends Component {
	public IFontDrawing fontDrawing;
	public IFontRenderer(String text, IFont font) {
		fontDrawing = new IFontDrawing();
		fontDrawing.font = font;
		fontDrawing.text = text;
	}

	private Transform mTransform;
	
	@Override
	public void Initialize(Entity entity) {
		mTransform = entity.GetComponent(Transform.class);
		GameMain.instance.activeStage.addActor(fontDrawing);
	}

	@Override
	public void Update() {
		fontDrawing.setPosition(mTransform.position.x, mTransform.position.y);
		fontDrawing.setScale(mTransform.scale.x);
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
	public void Kill() {
		fontDrawing.remove();
		super.Kill();
	}
}
