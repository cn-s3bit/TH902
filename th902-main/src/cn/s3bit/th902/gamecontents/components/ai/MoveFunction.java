package cn.s3bit.th902.gamecontents.components.ai;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

/**
 * It will automatically add a {@link MoveBasic} to the entity.
 *
 */
public class MoveFunction extends Component {
	private int mTimer;
	private final MoveFunctionTarget mTarget;
	private final MoveFunctionType mType;
	private final IMoveFunction mFunction;
	protected Transform transform;
	protected MoveBasic moveBasic;
	protected Vector2 lastVal = new Vector2();
	public MoveFunction(MoveFunctionTarget target, MoveFunctionType type, IMoveFunction function) {
		mTarget = target;
		mType = type;
		mFunction = function;
	}

	@Override
	public void Initialize(Entity entity) {
		mTimer = 0;
		transform = entity.GetComponent(Transform.class);
		moveBasic = entity.GetComponent(MoveBasic.class);
		if (moveBasic == null) {
			moveBasic = new MoveBasic(0, 0);
			entity.AddComponent(moveBasic);
		}
	}

	@Override
	public void Update() {
		mTimer++;
		Vector2 target = null;
		switch (mTarget) {
		case POSITION:
			target = transform.position;
			break;
		case VELOCITY:
			target = moveBasic.velocity;
			break;
		case ACCEL:
			target = moveBasic.acc;
			break;
		default:
			break;
		}
		switch (mType) {
		case ADDITIONAL:
			target.sub(lastVal);
			lastVal.set(mFunction.getTargetVal(mTimer));
			target.add(lastVal);
			break;
		case ASSIGNMENT:
			lastVal.set(mFunction.getTargetVal(mTimer));
			target.set(lastVal);
		default:
			break;
		}
	}

}
