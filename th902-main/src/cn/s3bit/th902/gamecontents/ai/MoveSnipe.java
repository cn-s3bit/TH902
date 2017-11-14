package cn.s3bit.th902.gamecontents.ai;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.NullShape2D;

public class MoveSnipe extends BaseProj {
	private Vector2 VecAcc;
	public MoveSnipe(float speed,Float acceleration) {
		this(speed);
		bulletAcceleration=acceleration;
	}

	public MoveSnipe(float speed) {
		this(NullShape2D.instance);
		this.bulletSpeed=speed;
	}
	public MoveSnipe(Shape2D judgeShape) {
		judge = ImmutableWrapper.wrap(judgeShape);
	}

	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		dirVec = JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor();
		transform.rotation = dirVec.angle() - 90;
		VecAcc=new Vector2(bulletAcceleration,0).rotate(dirVec.angle());
		dirVec.scl(bulletSpeed);
	}

	@Override
	public void Update() {
		super.Update();
		//acceleration
		dirVec.add(VecAcc);
		}
}
