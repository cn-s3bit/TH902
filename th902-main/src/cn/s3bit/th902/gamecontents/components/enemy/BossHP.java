package cn.s3bit.th902.gamecontents.components.enemy;

import cn.s3bit.th902.contents.THSoundEffects;
import cn.s3bit.th902.gamecontents.EnemySpellInfoSystem;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.ISpellInfoProvider;
import cn.s3bit.th902.gamecontents.components.Component;

public class BossHP extends Component implements IJudgeCallback, ISpellInfoProvider {
	public boolean immune = false;
	public boolean noMelee = false;
	public float hp = 0;
	public int maxhp = 0;
	
	public int[] life, time;
	public float[] bombResist;
	public String[] spellNames;
	public Runnable[] dropLogics, extraDropLogics;
	
	public int current = 0;
	public int timer = 0;
	
	private Entity mEntity;
	
	public BossHP(int[] life, int[] time, float[] bombResist, String[] spellNames, Runnable[] dropLogics, Runnable[] extraDropLogics) {
		this.life = life;
		this.time = time;
		this.spellNames = spellNames;
		this.dropLogics = dropLogics;
		this.extraDropLogics = extraDropLogics;
		this.bombResist = bombResist;
		hp = maxhp = life[current];
	}
	
	@Override
	public boolean canHurt() {
		return true;
	}
	
	@Override
	public void onHurt(float damage) {
		if (!immune) {
			hp -= damage * (timer < 180 ? (float)(Math.pow(1.02, timer) / Math.pow(1.02, 180)): 1);
		}
	}
	
	@Override
	public float getDamage() {
		return noMelee ? 0 : 1;
	}
	
	@Override
	public float getBombResist() {
		return bombResist == null ? 0.8f : bombResist[current];
	}

	@Override
	public void Initialize(Entity entity) {
		mEntity = entity;
		EnemySpellInfoSystem.activate(entity);
		THSoundEffects.Cat.sound.play();
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
				THSoundEffects.Cat.sound.play();
				hp = maxhp = life[current];
				timer = 0;
			}
			else {
				EnemySpellInfoSystem.deactivate();
				mEntity.Destroy();
				THSoundEffects.Enep1.sound.play();
			}
		}
	}

	@Override
	public float getLife() {
		return hp;
	}

	@Override
	public int getMaxLife() {
		return maxhp;
	}

	@Override
	public int getCurrentTime() {
		return timer;
	}

	@Override
	public int getMaxTime() {
		return time[current];
	}

	@Override
	public String getDisplayedName() {
		return spellNames[current];
	}
}
