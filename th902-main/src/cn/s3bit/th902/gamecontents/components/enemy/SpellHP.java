package cn.s3bit.th902.gamecontents.components.enemy;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.ISpellInfoProvider;
import cn.s3bit.th902.gamecontents.components.Component;

public class SpellHP extends Component implements IJudgeCallback, ISpellInfoProvider {
	public boolean immune = false;
	public boolean noMelee = false;
	public float hp = 0;
	public int maxhp = 0;
	
	public int time;
	public float bombResist;
	public String spellName;
	
	public int timer = 0;
	
	public SpellHP(int life, int time, float bombResist, String spellName) {
		this.time = time;
		this.spellName = spellName;
		this.bombResist = bombResist;
		hp = maxhp = life;
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
		return bombResist;
	}

	@Override
	public void Initialize(Entity entity) {

	}

	@Override
	public void Update() {
		timer++;
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
		return time;
	}

	@Override
	public String getDisplayedName() {
		return spellName;
	}
}
