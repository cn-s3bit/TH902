package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class EnemyChaseable extends Component {
	ImmutableWrapper<Vector2> immutablePosition;
	public IJudgeCallback callback;
	private boolean mChaseable;
	public EnemyChaseable(IJudgeCallback judgeCallback) {
		callback = judgeCallback;
		mChaseable = true;
	}
	
	@Override
	public void Initialize(Entity entity) {
		immutablePosition = entity.GetComponent(Transform.class).immutablePosition;
		JudgingSystem.registerChaseablePosition(immutablePosition, callback);
	}

	@Override
	public void Update() {
		
	}
	
	@Override
	public void Kill() {
		JudgingSystem.unregisterChaseablePosition(immutablePosition);
		super.Kill();
	}

	public void setStatus(boolean chaseable) {
		if (chaseable && !mChaseable) {
			mChaseable = true;
			JudgingSystem.registerChaseablePosition(immutablePosition, callback);
		}
		else if (mChaseable && !chaseable) {
			mChaseable = false;
			JudgingSystem.unregisterChaseablePosition(immutablePosition);
		}
	}
}
