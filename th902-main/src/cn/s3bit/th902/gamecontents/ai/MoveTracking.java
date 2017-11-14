package cn.s3bit.th902.gamecontents.ai;

import com.badlogic.gdx.math.Shape2D;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.NullShape2D;

public class MoveTracking extends BaseProj {
	private int time=0;
	public MoveTracking(float speed,Float acceleration) {
		this(speed);
		bulletAcceleration=acceleration;
	}

	public MoveTracking(float speed) {
		this(NullShape2D.instance);
		this.bulletSpeed=speed;
	}

	public MoveTracking( Shape2D judgeShape) {
		judge = ImmutableWrapper.wrap(judgeShape);
	}

	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		dirVec = JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor();
		transform.rotation = dirVec.angle() - 90;
		dirVec.scl(bulletSpeed);
	}

	@Override
	public void Update() {
		super.Update();
		dirVec=JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor().scl(bulletSpeed);
		transform.rotation = dirVec.angle() - 90;
		if(++time>300){
			entity.Destroy();
		}
	}
}

