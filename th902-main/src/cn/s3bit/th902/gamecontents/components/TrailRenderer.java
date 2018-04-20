package cn.s3bit.th902.gamecontents.components;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;

public class TrailRenderer extends AbstractRenderer {
	private Drawable mDrawable;
	private int mTrailLen;
	private int mDepth;
	private float mAlphaCoefficient;
	public Group group;
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
	
	public TrailRenderer(Texture texture, int trailLen, int depth, float alphaCoefficient) {
		this(new TextureRegionDrawable(new TextureRegion(texture)), trailLen, depth, alphaCoefficient);
	}
	
	public TrailRenderer(Drawable drawable, int trailLen, int depth) {
		this(drawable, trailLen, depth, 0.85f);
	}
	
	public TrailRenderer(Drawable drawable, int trailLen, int depth, float alphaCoefficient) {
		mDrawable = drawable;
		mAlphaCoefficient = alphaCoefficient;
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
		float alpha = (float) Math.pow(mAlphaCoefficient, mTrailLen + 0.2);
		Vector2 oldTrail = null;
		for (Iterator<Vector2> iterator = mTrail.iterator(); iterator.hasNext();) {
			Vector2 trail = iterator.next();
			if (oldTrail == null) {
				oldTrail = trail;
				continue;
			}
			drawers[i].setPosition((oldTrail.x + trail.x) / 2, (oldTrail.y + trail.y) / 2, Align.center);
			drawers[i].setOrigin(Align.center);
			drawers[i].setRotation(transform.rotation);
			drawers[i].setScale(transform.scale.x, transform.scale.y);
			drawers[i].setColor(1, 1, 1, alpha);
			i++;
			drawers[i].setPosition(trail.x, trail.y, Align.center);
			drawers[i].setOrigin(Align.center);
			drawers[i].setRotation(transform.rotation);
			drawers[i].setScale(transform.scale.x, transform.scale.y);
			drawers[i].setColor(1, 1, 1, alpha);
			i++;
			alpha /= mAlphaCoefficient;
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
		Group parent = null;
		if (group != null) {
			group.remove();
			parent = group.getParent();
		}
		group = new Group();
		for (int i = 0; i < drawers.length; i++) {
			drawers[i] = new Image(mDrawable);
			drawers[i].setBounds(0, 0, mDrawable.getMinWidth(), mDrawable.getMinHeight());
			drawers[i].setPosition(0, 0, Align.center);
			group.addActor(drawers[i]);
		}
		if (parent == null)
			GameMain.instance.activeStage.addActor(group);
		else
			parent.addActor(group);
	}
	
	public int getTrailLen() {
		return mTrailLen;
	}

	@Override
	public Actor getActor() {
		return group;
	}

	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_NONE;
	}
}
