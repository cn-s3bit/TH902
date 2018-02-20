package cn.s3bit.th902.gamecontents.components.ai;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public class MoveBasic extends Component implements IMovement {
	protected Transform transform;
	public Vector2 velocity;
	public Vector2 acc;
	public MoveBasic(float x, float y) {
		this(x, y, 0, 0);
	}
	public MoveBasic(Vector2 v){
		this(v.x, v.y);
	}
	public MoveBasic(float x, float y, float ax, float ay) {
		acc = new Vector2(ax, ay);
		velocity = new Vector2(x, y);
	}
	public MoveBasic(Vector2 vel, Vector2 accel) {
		this(vel.x, vel.y, accel.x, accel.y);
	}
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
	}

	@Override
	public void Update() {
		velocity.add(acc);
		transform.position.add(velocity);
	}
	@Override
	public Vector2 getCurrentVelocity() {
		return velocity;
	}
}
