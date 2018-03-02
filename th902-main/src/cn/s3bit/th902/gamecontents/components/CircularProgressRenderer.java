package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.utils.CircularProgress;

public class CircularProgressRenderer extends AbstractRenderer {
	public CircularProgress progress;
	@Override
	public void Initialize(Entity entity) {
		progress = new CircularProgress(new TextureRegion(ResourceManager.textures.get("bloodGaugeInner")));
		super.Initialize(entity);
	}

	@Override
	public void Update() {
		progress.setPosition(transform.position.x - ResourceManager.textures.get("bloodGaugeInner").getWidth() / 2, transform.position.y - ResourceManager.textures.get("bloodGaugeInner").getHeight() / 2);
	}

	@Override
	public Actor getActor() {
		return progress;
	}

	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_NONE;
	}
}
