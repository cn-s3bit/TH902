package cn.s3bit.th902.gamecontents.components.enemy;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;

public class EnemyHP extends Component implements IJudgeCallback {
	public boolean immune = false;
	public boolean noMelee = false;
	public float hp = 0;
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
	public void onHurt(float damage) {
		if (!immune) {
			hp -= damage;
		}
	}
	
	@Override
	public float getDamage() {
		return noMelee ? 0 : 1;
	}

	@Override
	public void Initialize(Entity entity) {
		mEntity = entity;
	}

	@Override
	public void Update() {
		if (hp <= 0) {
			ExtraDrop drop = mEntity.GetComponent(ExtraDrop.class);
			if (drop != null) {
				drop.LootLogic();
			}
			mEntity.Destroy();
		}
	}
}
