package cn.s3bit.th902.contents.stage1.lunatic;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;

public class AIS1L4Boss extends Component {
	Transform transform;
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
	}

	public int batch = 0;
	public int existTime = 0;
	@Override
	public void Update() {
		existTime++;
		if (existTime % 20 == 0) {
			batch++;
			for (int i = batch * 20; i < batch * 20 + 360; i += 20) {
				//BaseProjectile.Create(transform.position.cpy(), BulletType., colorred, Ves)
			}
		}
	}
	
}
