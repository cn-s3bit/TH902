package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.graphics.Texture;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;

public class PlayerDeathEffect extends Component {
	Texture texture;
	public PlayerDeathEffect(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void Initialize(Entity entity) {
		
	}

	@Override
	public void Update() {
		
	}

}
