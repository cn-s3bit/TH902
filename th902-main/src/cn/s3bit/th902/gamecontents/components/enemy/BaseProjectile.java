package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
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
		this.entity = entity;
		JudgingSystem.clearByBombs.put(transform.immutablePosition, entity);
		oldPos = new Vector2();
	}
	
	@Override
	public void Update() {
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
