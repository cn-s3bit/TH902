package cn.s3bit.th902.gamecontents.ai;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public class MoveTracking extends Component {
	private int time=0;
	protected Transform transform;
	protected Entity entity;
	protected Vector2 dirVec;
	protected float moveSpeed=0;
	protected float moveAcceleration=0;
	protected boolean isBullet=true;
	public MoveTracking(float speed,Boolean isBullet,Float acceleration) {
		this(speed,isBullet);
		moveAcceleration=acceleration;
	}

	public MoveTracking(float speed,Boolean isBullet) {
		this.moveSpeed=speed;
		this.isBullet=isBullet;
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
	this.entity = entity;
		dirVec = JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor();
		transform.rotation = dirVec.angle() - 90;
		dirVec.scl(moveSpeed);
	}

	@Override
	public void Update() {
		dirVec=JudgingSystem.playerJudge.cpy().sub(transform.position.cpy()).nor().scl(moveSpeed);
		if (isBullet) {
			transform.rotation = dirVec.angle() - 90;
		}else {
			transform.rotation=0;
		}
		transform.position.add(dirVec);
		if(++time>300){
			entity.Destroy();
		}
	}
}

