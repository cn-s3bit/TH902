package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMovement;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class EnemyJudgeCircle extends Component {
	public ImmutableWrapper<Circle> wrapper;
	public Circle circle;
	public float biasX, biasY;
	public Vector2 bias;
	public Transform transform;
	public IJudgeCallback callback;
	
	public EnemyJudgeCircle(float radius, float biasX, float biasY, IJudgeCallback callback) {
		circle = new Circle(0, 0, radius);
		wrapper = ImmutableWrapper.wrap(circle);
		this.biasX = biasX;
		this.biasY = biasY;
		bias = new Vector2();
		this.callback = callback;
	}
	
	public EnemyJudgeCircle(float radius) {
		this(radius, 0, 0, IJudgeCallback.NONE);
	}
	
	public EnemyJudgeCircle(float radius, IJudgeCallback callback) {
		this(radius, 0, 0, callback);
	}
	
	public EnemyJudgeCircle(float radius, float biasX, float biasY) {
		this(radius, biasX, biasY, IJudgeCallback.NONE);
	}
	
	boolean registered = false;
	Entity entity;
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		this.entity = entity;
		updateCircle();
	}

	@Override
	public void Update() {
		updateCircle();
		if ((!registered) && entity.GetComponent(IMovement.class) != null) {
			registered = true;
			JudgingSystem.registerEnemyJudge(wrapper, callback, entity.GetComponent(IMovement.class));
		}
	}

	protected void updateCircle() {
		bias.set(biasX, biasY).rotate(transform.rotation);
		circle.setPosition(transform.position.x + biasX, transform.position.y + biasY);
	}
	
	@Override
	public void Kill() {
		JudgingSystem.unregisterEnemyJudge(wrapper);
		super.Kill();
	}
}
