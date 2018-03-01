package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;

public abstract class AbstractRenderer extends Component {
	protected Transform transform;
	private int mOrigin = Align.center;
	
	@Override
	public void Initialize(Entity entity) {
		GameMain.instance.activeStage.addActor(getActor());
		transform = entity.GetComponent(Transform.class);
		Update();
	}

	@Override
	public void Update() {
		byte updateFlag = shouldUpdateWithTransform();
		if ((updateFlag & UPDATE_POSITION) == 1)
			getActor().setPosition(transform.position.x - ResourceManager.textures.get("bloodGaugeInner").getWidth() / 2, transform.position.y - ResourceManager.textures.get("bloodGaugeInner").getHeight() / 2, mOrigin);
		getActor().setOrigin(mOrigin);
		if ((updateFlag & UPDATE_ROTATION) == 1)
			getActor().setRotation(transform.rotation);
		if ((updateFlag & UPDATE_SCALE) == 1)
			getActor().setRotation(transform.rotation);
	}
	
	@Override
	public void Kill() {
		getActor().remove();
		super.Kill();
	}
	
	public abstract Actor getActor();
	
	public static final byte UPDATE_POSITION = 1, UPDATE_SCALE = 2, UPDATE_ROTATION = 4,
			UPDATE_ALL = 7, UPDATE_NONE = 0;
	public abstract byte shouldUpdateWithTransform();

	public void attachToGroup(Group layer) {
		getActor().remove();
		if (layer != null)
			layer.addActor(getActor());
		else
			GameMain.instance.activeStage.addActor(getActor());
	}

	public int getOrigin() {
		return mOrigin;
	}

	public void setOrigin(int alignment) {
		this.mOrigin = alignment;
	}
}
