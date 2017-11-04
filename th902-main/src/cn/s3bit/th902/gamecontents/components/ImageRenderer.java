package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;

public class ImageRenderer extends Component {
	Transform transform = null;
	Image image = null;
	public ImageRenderer(Texture texture) {
		image = new Image(texture);
		image.setBounds(0, 0, texture.getWidth(), texture.getHeight());
		GameMain.instance.activeStage.addActor(image);
	}
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
	}
	
	@Override
	public void Update() {
		image.setPosition(transform.position.x, transform.position.y, Align.center);
		image.setRotation(transform.rotation);
		image.setScale(transform.scale.x, transform.scale.y);
	}

	@Override
	public void Kill() {
		image.remove();
		super.Kill();
	}
}
