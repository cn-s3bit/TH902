package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.utils.LaserLikeDrawing;

public class LaserLikeRenderer extends Component {
	public LaserLikeDrawing laserLikeDrawing;
	Transform transform;
	private int mDepth;
	
	public LaserLikeRenderer(Texture texture) {
		this(texture, 200);
	}
	
	public LaserLikeRenderer(Texture texture, int maxverts) {
		laserLikeDrawing = new LaserLikeDrawing(texture, maxverts);
	}
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		GameMain.instance.activeStage.addActor(laserLikeDrawing);
		Update();
	}

	@Override
	public void Update() {
		laserLikeDrawing.setPosition(transform.position.x, transform.position.y);
		laserLikeDrawing.setRotation(transform.rotation);
		laserLikeDrawing.setScale(transform.scale.x, transform.scale.y);
	}
	
	@Override
	public void Kill() {
		laserLikeDrawing.remove();
		super.Kill();
	}

	public void setDepth(int depth) {
		mDepth = depth;
		if (mDepth == -1) {
			laserLikeDrawing.toFront();
		}
		else {
			laserLikeDrawing.setZIndex(mDepth);
		}
	}
}
