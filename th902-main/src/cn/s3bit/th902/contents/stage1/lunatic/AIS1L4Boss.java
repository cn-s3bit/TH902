package cn.s3bit.th902.contents.stage1.lunatic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BossHP;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class AIS1L4Boss extends Component {
	Transform transform;
	BossHP bossHp;
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		bossHp = entity.GetComponent(BossHP.class);
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
	
	public void part2() {
		if (existTime % 10 == 0) {
			batch++;
			for (int i = batch * 17; i < batch * 17 + 360; i += 30) {
				float size = Math.abs(MathUtils.cosDeg(i * 2.5f)) * 0.75f + 0.2f;
				Entity proj = BaseProjectile.CreateSpecialBullet(transform.position.cpy(), 225);
				proj.AddComponent(new MoveBasic(vct2_tmp.set(8, 0).rotate(i)));
				proj.GetComponent(Transform.class).scale.scl(size);
				proj.AddComponent(new EnemyJudgeCircle(15 * size));
			}
			for (int i = -batch * 17; i < -batch * 17 + 360; i += 60) {
				float size = 0.5f;
				Entity proj = BaseProjectile.CreateSpecialBullet(transform.position.cpy(), 226);
				proj.AddComponent(new MoveBasic(vct2_tmp.set(5, 0).rotate(i)));
				proj.GetComponent(Transform.class).scale.scl(size);
				proj.AddComponent(new EnemyJudgeCircle(15 * size));
			}
		}
	}
}
