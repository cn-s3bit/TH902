package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMovement;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class EnemyJudgeCircle extends Component {
	public ImmutableWrapper<Ellipse> wrapper;
	public Ellipse circle;
	public float biasX, biasY;
	public Vector2 bias;
	public Transform transform;
	public IJudgeCallback callback;
	
	public EnemyJudgeCircle(float radius, float biasX, float biasY, IJudgeCallback callback) {
		circle = new Ellipse(0, 0, radius, radius);
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
	}

	@Override
	public void Update() {
		updateCircle();
		if (!registered) {
			registered = true;
			JudgingSystem.registerEnemyJudge(wrapper, transform, callback, entity.GetComponent(IMovement.class));
		}
	}

	protected void updateCircle() {
		bias.set(biasX, biasY).rotate(transform.rotation);
		circle.setPosition(transform.position.x + biasX, transform.position.y + biasY);
	}
	
	private Circle mRoundedCircle = new Circle();
	public Circle getCircle() {
		mRoundedCircle.set(circle.x, circle.y, Math.min(circle.width, circle.height));
		return mRoundedCircle;
	}
	
	@Override
	public void Kill() {
		JudgingSystem.unregisterEnemyJudge(wrapper);
		super.Kill();
	}
}
