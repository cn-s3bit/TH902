package cn.s3bit.th902.contents.stage1.lunatic;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.enemy.BossHP;

public class AIS1LLastBoss extends Component {
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
		default:
			break;
		}
	}

	public void part1() {
		
	}
}
