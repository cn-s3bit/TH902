package cn.s3bit.th902.contents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ParticleRenderer;

public class ShootHitEffectSystem {
	static ParticleEffect[] pool = new ParticleEffect[8];
	static Entity[] managers = new Entity[8];
	static int poolPt = 0;
	static {
		for (int i=0; i<pool.length; i++)
		{
			ParticleEffect effect = new ParticleEffect();
			effect.load(Gdx.files.internal("resources/Particles/ShootHitEffect.dat"), Gdx.files.internal("resources/Particles"));
			pool[i] = effect;
		}
	}
	
	public static void addEffect(Vector2 position) {
		pool[poolPt].setPosition(position.x, position.y);
		if (managers[poolPt] != null)
			managers[poolPt].Destroy();
		managers[poolPt] = Entity.Create();
		managers[poolPt].AddComponent(new ParticleRenderer(pool[poolPt]));
		pool[poolPt++].start();
		if (poolPt >= pool.length)
			poolPt = 0;
	}
}
