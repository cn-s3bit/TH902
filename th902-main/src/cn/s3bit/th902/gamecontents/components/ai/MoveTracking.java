package cn.s3bit.th902.gamecontents.components.ai;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public class MoveTracking extends Component {
	protected Transform transform;
	protected Vector2 velocity;
	public float speed;

	public MoveTracking(float speed) {
		this.speed = speed;
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		velocity = new Vector2();
	}

	@Override
	public void Update() {
		velocity.set(JudgingSystem.playerJudge).sub(transform.position).nor().scl(speed);
		transform.position.add(velocity);
	}
}

