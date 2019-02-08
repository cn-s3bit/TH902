package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.AbstractRenderer;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public class BaseProjectile extends Component {

	protected Transform transform;
	protected Vector2 oldPos;
	protected Entity entity;
	public int type;
	public boolean clearWhenOutOfScreen;
	
	public BaseProjectile(int bulletForm) {
		this(bulletForm, true);
	}
	
	public BaseProjectile(int bulletForm, boolean clearWhenOutOfScreen) {
		type = bulletForm;
		this.clearWhenOutOfScreen = clearWhenOutOfScreen;
	}
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		entity.GetComponent(AbstractRenderer.class).attachToGroup(FightScreen.drawingLayers.entity4);
		this.entity = entity;
		JudgingSystem.clearByBombs.put(transform.immutablePosition, entity);
		oldPos = new Vector2();
	}

	private int mInOutAnimTimer = 0;
	Color initialColor;
	float initialSX, initialSY;
	public final int INTIME = 12;
	@Override
	public void Update() {
		if (mInOutAnimTimer == 0) {
			initialSX = entity.GetComponent(AbstractRenderer.class).getActor().getScaleX();
			initialSY = entity.GetComponent(AbstractRenderer.class).getActor().getScaleY();
			initialColor = entity.GetComponent(AbstractRenderer.class).getActor().getColor().cpy();
		}
		if (mInOutAnimTimer < INTIME) {
			++mInOutAnimTimer;
			Color color = entity.GetComponent(AbstractRenderer.class).getActor().getColor();
			color.a = initialColor.a * mInOutAnimTimer / INTIME;
			entity.GetComponent(AbstractRenderer.class).getActor().setColor(color);
			entity.GetComponent(AbstractRenderer.class).getActor().setScale(initialSX * (4f - 3f * Interpolation.linear.apply(mInOutAnimTimer) / INTIME), initialSY * (4f - 3f * Interpolation.linear.apply(mInOutAnimTimer) / INTIME));
		}
		if (type==9||type==14||type==230||type==231||type==232
				||type==233||type==234||type==235) {
			transform.rotation += 7;
		}
		else transform.rotation = 90 + oldPos.sub(transform.position).angle();
		if (FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
		oldPos.set(transform.position);
	}

	@Override
	public void Kill() {
		JudgingSystem.clearByBombs.remove(transform.immutablePosition);
		super.Kill();
	}
}
