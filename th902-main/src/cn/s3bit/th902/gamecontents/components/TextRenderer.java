package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;

public class TextRenderer extends Component {
	public Label label;
	public LabelStyle labelStyle;
	public int labelAlign = Align.center, lineAlign = Align.center;
	
	Transform transform;
	
	/**
	 * Default: font - MSYH, color - WHITE
	 */
	public TextRenderer(String text) {
		this(text, ResourceManager.msyh, Color.WHITE);
	}
	
	public TextRenderer(String text, BitmapFont font, Color fontColor) {
		labelStyle = new LabelStyle(font, fontColor);
		label = new Label(text, labelStyle);
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		GameMain.instance.activeStage.addActor(label);
		label.toFront();
	}

	@Override
	public void Update() {
		label.setPosition(transform.position.x, transform.position.y, labelAlign);
		label.setAlignment(labelAlign, lineAlign);
	}
	
	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public void Kill() {
		label.remove();
		super.Kill();
	}
}
