package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.IntMap.Entry;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;

public class MBGBullet extends Component {
	public MBGBulletEmitter emitter;
	public int life;
	public Color color = new Color(1f, 1f, 1f, 1f);
	public MoveBasic moveBasic;
	public Transform transform;
	public Entity entity;
	
	public MBGBulletEmitter binded = null;
	
	public MBGBullet(MBGBulletEmitter bulletEmitter) {
		emitter = bulletEmitter;
	}
	
	@Override
	public void Initialize(Entity entity) {
		this.entity = entity;
		moveBasic = entity.GetComponent(MoveBasic.class);
		transform = entity.GetComponent(Transform.class);
		life = 0;
		for (Entry<MBGBulletEmitter> sub : emitter.mbgScene.bulletEmitters) {
			if (sub.value.bulletEmitter.绑定状态.Parent == emitter.bulletEmitter) {
				binded = sub.value;
				binded.subEmitters.add(transform);
			}
		}
	}

	@Override
	public void Update() {
		if (emitter.bulletEmitter.出屏即消 && FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
		life++;
		if (life >= emitter.bulletEmitter.子弹生命) {
			entity.Destroy();
		}
		transform.rotation = -90 - moveBasic.velocity.angle();
	}

}
