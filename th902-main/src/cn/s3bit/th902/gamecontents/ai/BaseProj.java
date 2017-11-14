package cn.s3bit.th902.gamecontents.ai;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class BaseProj extends Component{
	
	protected Transform transform;
	protected Entity entity;
	protected Vector2 dirVec;
	protected ImmutableWrapper<Shape2D> judge = null;
	protected float bulletSpeed=0;
	protected float bulletAcceleration=0;
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		this.entity = entity;
		JudgingSystem.registerEnemyJudge(judge, IJudgeCallback.NONE);
		
	}

	@Override
	public void Update() {
		transform.position.add(dirVec);
	}
	@Override
	public void Kill() {
		JudgingSystem.unregisterEnemyJudge(judge);
		super.Kill();
	}
}
