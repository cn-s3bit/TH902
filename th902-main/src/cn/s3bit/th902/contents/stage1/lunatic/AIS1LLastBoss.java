package cn.s3bit.th902.contents.stage1.lunatic;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
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
						new EnemyJudgeCircle(13));
				final Transform pTr = proj.GetComponent(Transform.class);
				pTr.scale.scl(1.1f);
				proj.AddComponent(new LambdaComponent(() -> {
					Entity.postUpdate.add(() -> { proj.Destroy(); });
					fdCounter[ii]++;
					if (fdCounter[ii] > 2 + ii) {
						fdCounter[ii] = 0;
						BaseProjectile.Create(pTr.position.cpy(), BulletType.FormArrowM, BulletType.ColorBlueLight,
								new EnemyJudgeCircle(5, 0, 3),
								new MoveBasic(0, 1.2f, 0, 0.03f + ii * 0.015f));
						BaseProjectile.Create(pTr.position.cpy(), BulletType.FormArrowM, BulletType.ColorRed,
								new EnemyJudgeCircle(5, 0, 3),
								new MoveBasic(0, -1.2f, 0, -0.03f - ii * 0.015f));
					}
				}, 120, -1));
			}
		}
	}
	
	boolean flag3 = true;
	public void part3() {
		if (flag3) {
			flag3 = false;
			existTime = 0;
		}
		GameHelper.chase(moveBasic.velocity, new Vector2(285, 590), 3);
		//moveBasic.velocity.scl(Interpolation.elasticOut.apply(0, 5, existTime / 40f));
		moveBasic.acc.set(0, 0);
	}
}
