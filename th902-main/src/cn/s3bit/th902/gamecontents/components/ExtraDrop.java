package cn.s3bit.th902.gamecontents.components;

import cn.s3bit.th902.gamecontents.Entity;

/**
 * Does the Loot Logic Only When Killed By Friendly Bullets
 */
public abstract class ExtraDrop extends Component {
	@Override
	public void Initialize(Entity entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
	public abstract void LootLogic();
}
