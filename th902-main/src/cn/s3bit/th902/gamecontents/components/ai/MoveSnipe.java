package cn.s3bit.th902.gamecontents.components.ai;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public class MoveSnipe extends Component implements IMovement {
	protected Transform transform;
	protected Vector2 velocity;
	protected final float speed;

	public MoveSnipe(float speed) {
		this.speed = speed;
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		velocity = JudgingSystem.playerJudge.cpy().sub(transform.position).nor().scl(speed);
	}

	@Override
	public void Update() {
		transform.position.add(velocity);
	}

	@Override
	public Vector2 getCurrentVelocity() {
		return velocity;
	}
}
