package cn.s3bit.th902.gamecontents.ai;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public class MoveBasic extends Component{
	
	protected Transform transform;
	protected Entity entity;
	protected Vector2 dirVec;
	protected boolean isBullet=true;
	public MoveBasic(float x,float y,Boolean isBullet){
		dirVec=new Vector2(x,y);
		this.isBullet=isBullet;
	}
	public MoveBasic(Vector2 v,Boolean isBullet){
		dirVec=v;
		this.isBullet=isBullet;
	}
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		this.entity = entity;
		if (isBullet) {
			transform.rotation = dirVec.angle() - 90;
		}else {
			transform.rotation=0;
		}
	}

	@Override
	public void Update() {
		transform.position.add(dirVec);
	}
}
