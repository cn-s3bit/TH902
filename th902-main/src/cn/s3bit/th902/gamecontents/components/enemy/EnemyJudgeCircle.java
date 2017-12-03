package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.Circle;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class EnemyJudgeCircle extends Component {
	public ImmutableWrapper<Circle> wrapper;
	public Circle circle;
	public float biasX, biasY;
	public Transform transform;
	public IJudgeCallback callback;
	
	public EnemyJudgeCircle(float radius, float biasX, float biasY, IJudgeCallback callback) {
		circle = new Circle(0, 0, radius);
		wrapper = ImmutableWrapper.wrap(circle);
		this.biasX = biasX;
		this.biasY = biasY;
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
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		updateCircle();
		JudgingSystem.registerEnemyJudge(wrapper, callback);
	}

	@Override
	public void Update() {
		updateCircle();
	}

	protected void updateCircle() {
		circle.setPosition(transform.position.x + biasX, transform.position.y + biasY);
	}
	
	@Override
	public void Kill() {
		JudgingSystem.unregisterEnemyJudge(wrapper);
		super.Kill();
	}
}
