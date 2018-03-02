package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cn.s3bit.th902.utils.LaserLikeDrawing;

public class LaserLikeRenderer extends AbstractRenderer {
	public LaserLikeDrawing laserLikeDrawing;
	
	public LaserLikeRenderer(Texture texture) {
		this(texture, 200);
	}
	
	public LaserLikeRenderer(Texture texture, int maxverts) {
		laserLikeDrawing = new LaserLikeDrawing(texture, maxverts);
	}

	@Override
	public Actor getActor() {
		return laserLikeDrawing;
	}

	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_ALL;
	}
}
