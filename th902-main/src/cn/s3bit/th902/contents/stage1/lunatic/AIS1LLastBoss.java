package cn.s3bit.th902.contents.stage1.lunatic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BossHP;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class AIS1LLastBoss extends Component {
	Transform transform;
	BossHP bossHp;
	MoveFunction moveFunction;
	MoveBasic moveBasic;
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		bossHp = entity.GetComponent(BossHP.class);
		moveFunction = entity.GetComponent(MoveFunction.class);
		moveBasic = entity.GetComponent(MoveBasic.class);
	}

	public int batch = 0;
	public int existTime = 0;
	Vector2 vct2_tmp = new Vector2();
	@Override
	public void Update() {
		existTime++;
		switch (bossHp.current) {
		case 0:
			part1();
			break;
		case 1:
			part2();
			break;
		case 2:
			part3();
			break;
		case 3:
			part4();
			break;
		case 4:
			part5();
			break;
		default:
			break;
		}
	}

	public void part1() {
		if (existTime % 10 == 0) {
			batch++;
			int co = batch;
			for (int i = batch * 17; i < batch * 17 + 360; i += 20) {
				co++;
				float size = Math.abs(MathUtils.cosDeg(i * 2.5f)) * 0.75f + 0.2f;
				Entity proj = BaseProjectile.CreateSpecialBullet(transform.position.cpy(), co % 2 == 0 ? 225 : 226);
				proj.AddComponent(new MoveBasic(vct2_tmp.set(8, 0).rotate(i)));
				proj.GetComponent(Transform.class).scale.scl(size);
				proj.AddComponent(new EnemyJudgeCircle(15 * size));
			}
		}
	}
	
	int p2interval = 24;
	int p2cooldown = 0;
	int[] fdCounter = {0, 0};
	
	public void part2() {
		if (p2interval == 24) {
			p2interval--;
			moveFunction.Kill();
			existTime = 0;
			Entity.postUpdate.add(() -> { GameHelper.clearEnemyBullets(); });
		}
		if (existTime < 60) {
			moveBasic.velocity.set(-4, 0.3f);
		}
		else if (existTime < 120) {
			moveBasic.velocity.set(0, 0);
		}
		else if (existTime < 360 && p2interval > 1) {
			moveBasic.velocity.set(8f / p2interval, 0);
		}
		else if (transform.position.x > 285) {
			moveBasic.acc.set(-0.03f, MathUtils.random(-0.02f, 0f));
		}
		else {
			moveBasic.acc.set(0.03f, MathUtils.random(0f, 0.02f));
		}
		if (transform.position.y <= 333 && moveBasic.velocity.y < 0) {
			moveBasic.velocity.y *= -1;
		}
		else if (transform.position.y >= 580 && moveBasic.velocity.y > 0) {
			moveBasic.velocity.y *= -1;
		}
		if (p2cooldown > 0)
			p2cooldown--;
		else if (existTime > 120 && existTime % p2interval == 0) {
			if (p2interval > 1) {
				p2interval -= 2;
			}
			p2cooldown --;
			if (p2cooldown < -60) {
				p2cooldown = 4;
			}
			for (int i=0; i<2; i++)
			{
				final int ii = i;
				final Entity proj = BaseProjectile.Create(transform.position.cpy().add(i * 5, i * 80 - 40), BulletType.FormCircleLightM, BulletType.ColorGray,
						new EnemyJudgeCircle(15));
				final Transform pTr = proj.GetComponent(Transform.class);
				pTr.scale.scl(1.2f);
				proj.AddComponent(new LambdaComponent(() -> {
					Entity.postUpdate.add(() -> { proj.Destroy(); });
					fdCounter[ii]++;
					if (fdCounter[ii] > 1 + ii) {
						fdCounter[ii] = 0;
						BaseProjectile.Create(pTr.position.cpy(), BulletType.FormArrowM, BulletType.ColorBlueLight,
								new EnemyJudgeCircle(5, 0, 3),
								new MoveBasic(-0.3f, 1.2f, -(0.03f + ii * 0.015f) / 4, 0.03f + ii * 0.015f));
						BaseProjectile.Create(pTr.position.cpy(), BulletType.FormArrowM, BulletType.ColorRed,
								new EnemyJudgeCircle(5, 0, 3),
								new MoveBasic(0.3f, -1.2f, (0.03f + ii * 0.015f) / 4, -0.03f - ii * 0.015f));
					}
				}, 120, -1));
			}
		}
	}
	
	boolean flag3 = true;
	Vector2 p3pos = new Vector2(285, 578);
	int[] p3ang = new int[24];
	public void part3() {
		if (flag3) {
			flag3 = false;
			existTime = 0;
			moveBasic.acc.set(0, 0);
			Entity.postUpdate.add(() -> { GameHelper.clearEnemyBullets(); });
			for (int i=0; i<24; i++) p3ang[i] = i * 15;
		}
		GameHelper.snipeVct(transform.position, p3pos, 0, moveBasic.velocity);
		moveBasic.velocity.scl(1 / 40f);
		if (existTime > 120) {
			if (existTime % 14 == 0)
				for (int i=0; i<24; i+=2)
				{
					Entity proj = BaseProjectile.CreateSpecialBullet(transform.position.cpy(), 225);
					proj.AddComponent(new MoveBasic(vct2_tmp.set(5, 0).rotate(p3ang[i])));
					proj.AddComponent(new EnemyJudgeCircle(15));
					p3ang[i] -= 23;
				}
			else if (existTime % 14 == 7)
				for (int i=1; i<24; i+=2)
				{
					Entity proj = BaseProjectile.CreateSpecialBullet(transform.position.cpy(), 226);
					proj.AddComponent(new MoveBasic(vct2_tmp.set(9, 0).rotate(p3ang[i])));
					proj.AddComponent(new EnemyJudgeCircle(15));
					p3ang[i] += 17;
				}
		}
		//moveBasic.velocity.scl(Interpolation.elasticOut.apply(0, 5, existTime / 40f));
	}
	
	boolean flag4 = true;
	float[] alpha4s = {1, 1, 1, 1};
	static final BulletType[] color4s = {BulletType.ColorRed, BulletType.ColorOrange, BulletType.ColorBlue, BulletType.ColorGreen};
	public void part4() {
		if (flag4) {
			flag4 = false;
			existTime = 0;
			moveBasic.velocity.set(0, 0);
			moveBasic.acc.set(0, 0);
			Entity.postUpdate.add(() -> { GameHelper.clearEnemyBullets(); });
		}
		if (existTime > 60) {
			for (int i=0; i<2; i++)
			{
				final int c = MathUtils.random(3);
				final Entity proj = BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS, color4s[c]);
				final ImageRenderer renderer = proj.GetComponent(ImageRenderer.class);
				proj.AddComponent(new EnemyJudgeCircle(5));
				proj.AddComponent(new LambdaComponent(() -> {
					renderer.image.setColor(1, 1, 1, alpha4s[c]);
				}, 5));
				proj.AddComponent(new MoveBasic(new Vector2(1, 0).rotate(MathUtils.random(360f)).scl(MathUtils.random(2f, 7f))));
			}
		}
		if (existTime % 20 == 0) {
			for (int i=0; i<4; i++)
				alpha4s[i] = MathUtils.randomBoolean(0.6f) ? 0.88f : 0.12f;
		}
	}
	
	public void part5() {
		
	}
}
