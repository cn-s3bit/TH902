package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cn.s3bit.th902.utils.ParticleActor;

public class ParticleRenderer extends AbstractRenderer {
	public ParticleActor actor;
	public ParticleRenderer(ParticleEffect effect) {
		actor = new ParticleActor(effect);
	}
	
	@Override
	public Actor getActor() {
		return actor;
	}

	@Override
	public byte shouldUpdateWithTransform() {
		return UPDATE_NONE;
	}
}
