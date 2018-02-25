package cn.s3bit.th902.contents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.ParticleSystem;

public class ShootHitEffectSystem {
	static ParticleEffect[] pool = new ParticleEffect[8];
	static int poolPt = 0;
	static {
		for (int i=0; i<pool.length; i++)
		{
			ParticleEffect effect = new ParticleEffect();
			ParticleSystem.register(effect);
			effect.load(Gdx.files.internal("resources/Particles/ShootHitEffect.dat"), Gdx.files.internal("resources/Particles"));
			pool[i] = effect;
		}
	}
	
	public static void addEffect(Vector2 position) {
		pool[poolPt].setPosition(position.x, position.y);
		pool[poolPt++].start();
		if (poolPt >= pool.length)
			poolPt = 0;
	}
}
