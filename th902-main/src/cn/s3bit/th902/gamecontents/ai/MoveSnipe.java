package cn.s3bit.th902.gamecontents.ai;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public class MoveSnipe extends Component {
	protected Transform transform;
	protected Entity entity;
	protected Vector2 dirVec;
	protected float moveSpeed=0;
	protected float moveAcceleration=0;
	private Vector2 VecAcc;
	protected boolean isBullet=true;
	public MoveSnipe(float speed,Boolean isBullet,Float acceleration) {
		this(speed,isBullet);
		moveAcceleration=acceleration;
	}

	public MoveSnipe(float speed,Boolean isBullet) {
		this.moveSpeed=speed;
		this.isBullet=isBullet;
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		this.entity = entity;
		dirVec = JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor();
		if (isBullet) {
			transform.rotation = dirVec.angle() - 90;
		}else {
			transform.rotation=0;
		}
		VecAcc=new Vector2(moveAcceleration,0).rotate(dirVec.angle());
		dirVec.scl(moveSpeed);
	}

	@Override
	public void Update() {
		//acceleration
		dirVec.add(VecAcc);
		transform.position.add(dirVec);
		}
}
