package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.utils.CircularProgress;

public class CircularProgressRenderer extends Component {
	public CircularProgress progress;
	Transform transform;
	@Override
	public void Initialize(Entity entity) {
		progress = new CircularProgress(new TextureRegion(ResourceManager.textures.get("bloodGaugeInner")));
		GameMain.instance.activeStage.addActor(progress);
		transform = entity.GetComponent(Transform.class);
	}

	@Override
	public void Update() {
		progress.setPosition(transform.position.x - ResourceManager.textures.get("bloodGaugeInner").getWidth() / 2, transform.position.y - ResourceManager.textures.get("bloodGaugeInner").getHeight() / 2);
	}

	@Override
	public void Kill() {
		progress.remove();
		super.Kill();
	}
}
