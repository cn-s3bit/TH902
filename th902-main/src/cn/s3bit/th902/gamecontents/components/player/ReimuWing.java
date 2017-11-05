package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.graphics.Texture;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;

public class ReimuWing extends Component {
	Texture Wing;

	@Override
	public void Initialize(Entity entity) {
		// TODO Auto-generated method stub
		Wing= ResourceManager.barrages.get(234);
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
		
	}

}
