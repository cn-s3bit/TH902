package cn.s3bit.th902.contents.stage6;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.DifficultySelectScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.contents.BossSpell;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;
import cn.s3bit.th902.gamecontents.components.enemy.ProjectileFactory;

public class SpellWaveParticleChanged1 extends BossSpell {

	@Override
	public int getMaxLife() {
		return 6000;
	}

	@Override
	public int getMaxTime() {
		return 2400;
	}

	@Override
	public float getBombResist() {
		return 2.2f;
	}

	@Override
	public Texture getTexture() {
		return ResourceManager.barrages.get(230);
	}
	
	@Override
	public boolean isFirst() {
		return false;
	}
	
	@Override
	public boolean isLast() {
		return false;
	}

	protected float p1 = 0f, p2 = 0f;
	@Override
	public void act() {
		final int r;
		final int t;
		switch (DifficultySelectScreen.difficulty) {
		case 1:
			r = 12;
			t = 8;
			break;
		case 2:
			r = 22;
			t = 5;
			break;
		case 3:
			r = 25;
			t = 3;
			break;
		case 4:
			r = 36;
			t = 3;
			break;
		default:
			throw new AssertionError();
		}
		if (existTime % t < t - 1) return;
		for (int i=0; i<r; i++)
			ProjectileFactory.Create(
					transform.position.cpy(),
					BulletType.FormCircleS2,
					BulletType.ColorPink,
					new MoveBasic(new Vector2(1f, 0f).rotate(i * 360 / r + p2)),
					new EnemyJudgeCircle(4.8f)
				).GetComponent(Transform.class).scale.set(0.8f, 0.8f);
		p1 += 0.25f;
		p2 += p1;
	}
	
	@Override
	public String getName() {
		return "里冬「极疏介质中的波与粒」";
	}
}
