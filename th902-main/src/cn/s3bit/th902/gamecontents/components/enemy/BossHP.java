package cn.s3bit.th902.gamecontents.components.enemy;

import cn.s3bit.th902.gamecontents.EnemySpellInfoSystem;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.components.Component;

public class BossHP extends Component implements IJudgeCallback {
	public boolean immune = false;
	public boolean noMelee = false;
	public int hp = 0;
	public int maxhp = 0;
	
	public int[] life, time;
	public String[] spellNames;
	public Runnable[] dropLogics, extraDropLogics;
	
	public int current = 0;
	public int timer = 0;
	
	private Entity mEntity;
	
	public BossHP(int[] life, int[] time, String[] spellNames, Runnable[] dropLogics, Runnable[] extraDropLogics) {
		this.life = life;
		this.time = time;
		this.spellNames = spellNames;
		this.dropLogics = dropLogics;
		this.extraDropLogics = extraDropLogics;
		hp = maxhp = life[current];
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
		EnemySpellInfoSystem.activate(entity);
	}

	@Override
	public void Update() {
		timer++;
		if (hp <= 0 || timer >= time[current]) {
			if (dropLogics[current] != null) {
				dropLogics[current].run();
			}
			if (extraDropLogics[current] != null && hp <= 0) {
				extraDropLogics[current].run();
			}
			if (current < life.length - 1) {
				current++;
				hp = maxhp = life[current];
				timer = 0;
			}
			else {
				EnemySpellInfoSystem.deactivate();
				mEntity.Destroy();
			}
		}
	}
}
