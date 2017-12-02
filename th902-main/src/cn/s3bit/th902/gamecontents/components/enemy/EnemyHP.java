package cn.s3bit.th902.gamecontents.components.enemy;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.components.Component;

public class EnemyHP extends Component implements IJudgeCallback {
	public boolean immune = false;
	public boolean noMelee = false;
	public int hp = 0;
	public int maxhp = 0;
	
	private Entity mEntity;
	
	public EnemyHP(int maxlife) {
		hp = maxlife;
		maxhp = maxlife;
	}
	
	@Override
	public boolean canHurt() {
		return true;
	}
	
	@Override
	public void onHurt(int damage) {
		if (!immune) {
			hp -= damage;
		}
	}
	
	@Override
	public int getDamage() {
		return noMelee ? 0 : 1;
	}

	@Override
	public void Initialize(Entity entity) {
		mEntity = entity;
	}

	@Override
	public void Update() {
		if (hp <= 0) {
			mEntity.Destroy();
		}
	}
}
