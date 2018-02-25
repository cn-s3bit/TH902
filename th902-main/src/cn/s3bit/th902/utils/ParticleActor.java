package cn.s3bit.th902.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleActor extends Actor {
	public ParticleEffect wrappedEffect;
	public ParticleActor(ParticleEffect wrappedEffect) {
		this.wrappedEffect = wrappedEffect;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		wrappedEffect.draw(batch);
		wrappedEffect.update(1f / 60f);
		super.draw(batch, parentAlpha);
	}
}
