package cn.s3bit.th902.gamecontents.components;

import cn.s3bit.th902.contents.THSoundEffects;
import cn.s3bit.th902.gamecontents.Entity;

/**
 * Does the Loot Logic Only When Killed By Friendly Bullets
 */
public abstract class ExtraDrop extends Component {
	@Override
	public void Initialize(Entity entity) {
		
	}
	@Override
	public void Update() {
		
	}
	public void Loot() {
		LootLogic();
		THSoundEffects.Enep0.sound.play();
	}
	public abstract void LootLogic();
}
