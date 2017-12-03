package cn.s3bit.th902.gamecontents;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import cn.s3bit.th902.GameMain;

public class ParticleSystem {
	static HashSet<ParticleEffect> effects = new HashSet<>();
	public static void register(ParticleEffect effect) {
		effects.add(effect);
	}
	public static void unregister(ParticleEffect effect) {
		effects.remove(effect);
	}
	public static void draw() {
		GameMain.instance.activeStage.getBatch().begin();
		for (ParticleEffect particleEffect : effects) {
			particleEffect.draw(GameMain.instance.activeStage.getBatch());
			particleEffect.update(Gdx.graphics.getDeltaTime());
		}
		GameMain.instance.activeStage.getBatch().end();
	}
}
