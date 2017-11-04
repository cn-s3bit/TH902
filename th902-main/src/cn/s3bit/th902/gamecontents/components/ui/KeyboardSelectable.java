package cn.s3bit.th902.gamecontents.components.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import cn.s3bit.th902.KeySettings;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.utils.LinkedNode;

public class KeyboardSelectable extends Component{
	protected final Runnable onPressAction;
	public LinkedNode<KeyboardSelectable> linkedNode;
	public boolean isSelected = false;
	protected ImageRenderer renderer;
	private int mEffectTimer = 0;
	public KeyboardSelectable() {
		this(null);
	}
	public KeyboardSelectable(Runnable onPress) {
		linkedNode = new LinkedNode<>(this);
		onPressAction = onPress;
	}
	@Override
	public void Initialize(Entity entity) {
		renderer = entity.GetComponent(ImageRenderer.class);
	}

	@Override
	public void Update() {
		LinkedNode<KeyboardSelectable> node = linkedNode;
		while (node.previous != null) {
			if (node.data.isSelected) {
				break;
			}
			node = linkedNode.previous;
		}
		if (node.previous == null) {
			node.data.isSelected = true;
		}
		if (!isSelected) {
			renderer.image.setColor(0.18f, 0.18f, 0.18f, 1);
			mEffectTimer = 0;
			return;
		}
		float colorFactor = MathUtils.cosDeg(mEffectTimer * 5) * 0.2f + 0.8f;
		renderer.image.setColor(colorFactor, colorFactor, colorFactor, 1);
		mEffectTimer++;
		if (Gdx.input.isKeyJustPressed(KeySettings.positiveKey) && onPressAction != null) {
			onPressAction.run();
		}
		if (Gdx.input.isKeyJustPressed(KeySettings.up) && linkedNode.previous != null) {
			isSelected = false;
			linkedNode.previous.data.isSelected = true;
		}
		if (Gdx.input.isKeyJustPressed(KeySettings.down) && linkedNode.next != null) {
			isSelected = false;
			linkedNode.next.data.isSelected = true;
		}
	}
	
	@Override
	public void Kill() {
		linkedNode.remove();
		super.Kill();
	}
}
