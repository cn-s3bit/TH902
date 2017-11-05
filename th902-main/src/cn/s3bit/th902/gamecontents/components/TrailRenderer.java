package cn.s3bit.th902.gamecontents.components;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;

public class TrailRenderer extends Component {
	private Drawable mDrawable;
	private int mTrailLen;
	private int mDepth;
	public Group group;
	protected Transform transform;
	protected LinkedBlockingQueue<Vector2> mTrail;
	protected Image[] drawers;
	protected Pool<Vector2> vectorPool = new Pool<Vector2>() {
		@Override
		protected Vector2 newObject() {
			return new Vector2();
		}
	};
	public TrailRenderer(Texture texture, int trailLen, int depth) {
		this(new TextureRegionDrawable(new TextureRegion(texture)), trailLen, depth);
	}
	
	public TrailRenderer(Drawable drawable, int trailLen, int depth) {
		mDrawable = drawable;
		setTrailLen(trailLen);
		setDepth(depth);
	}
	@Override
	public void Initialize(Entity entity) {
		mTrail = new LinkedBlockingQueue<>();
		transform = entity.GetComponent(Transform.class);
	}

	@Override
	public void Update() {
		mTrail.add(vectorPool.obtain().set(transform.position));
		while (mTrail.size() > mTrailLen / 2 + 1) {
			vectorPool.free(mTrail.remove());
		}
		int i = 0;
		float alpha = (float) Math.pow(0.85, mTrailLen + 0.2);
		Vector2 oldTrail = null;
		for (Iterator<Vector2> iterator = mTrail.iterator(); iterator.hasNext();) {
			Vector2 trail = iterator.next();
			if (oldTrail == null) {
				oldTrail = trail;
				continue;
			}
			drawers[i].setPosition((oldTrail.x + trail.x) / 2, (oldTrail.y + trail.y) / 2, Align.center);
			drawers[i].setRotation(transform.rotation);
			drawers[i].setScale(transform.scale.x, transform.scale.y);
			drawers[i].setColor(1, 1, 1, alpha);
			i++;
			drawers[i].setPosition(trail.x, trail.y, Align.center);
			drawers[i].setRotation(transform.rotation);
			drawers[i].setScale(transform.scale.x, transform.scale.y);
			drawers[i].setColor(1, 1, 1, alpha);
			i++;
			alpha /= 0.85f;
			oldTrail = trail;
		}
	}

	public void setDepth(int depth) {
		mDepth = depth;
		if (mDepth == -1) {
			group.toFront();
		}
		else {
			group.setZIndex(mDepth);
		}
	}
	
	public void setTrailLen(int trailLen) {
		mTrailLen = trailLen;
		drawers = new Image[trailLen];
		if (group != null) {
			group.remove();
		}
		group = new Group();
		for (int i = 0; i < drawers.length; i++) {
			drawers[i] = new Image(mDrawable);
			drawers[i].setBounds(0, 0, mDrawable.getMinWidth(), mDrawable.getMinHeight());
			drawers[i].setPosition(0, 0, Align.center);
			group.addActor(drawers[i]);
		}
		GameMain.instance.activeStage.addActor(group);
	}
	
	public int getTrailLen() {
		return mTrailLen;
	}
	
	@Override
	public void Kill() {
		group.remove();
		super.Kill();
	}
}
