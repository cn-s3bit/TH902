package cn.s3bit.th902.gamecontents.components;

import cn.s3bit.th902.gamecontents.Entity;

public abstract class AlwaysDrop extends Component {

	@Override
	public void Initialize(Entity entity) {
		
	}

	@Override
	public void Update() {
		
	}

	@Override
	public void Kill() {
		LootLogic();
		super.Kill();
	}
	
	public abstract void LootLogic();
}
