package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.IntMap.Entry;
import com.badlogic.gdx.utils.ObjectSet;

import cn.s3bit.mbgparser.Action;
import cn.s3bit.mbgparser.Tuple;
import cn.s3bit.mbgparser.item.BulletEmitter;
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
	public ObjectSet<MBGBulletEmitter> depthBinded = null;
	
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
				if (sub.value.bulletEmitter.绑定状态.Depth) {
					if (depthBinded == null)
						depthBinded = new ObjectSet<MBGBulletEmitter>();
					Tuple<BulletEmitter, Action> bulletEmitter = BulletEmitter.parseFrom(((BulletEmitter) sub.value.bulletEmitter.绑定状态.Child).stringData, emitter.bulletEmitter.layer);
					MBGBulletEmitter depth = new MBGBulletEmitter(bulletEmitter.Item1, emitter.mbgScene, emitter.layer);
					depth.Initialize(entity);
					depthBinded.add(depth);
				}
				else
					sub.value.subEmitters.add(transform);
			}
		}
	}

	@Override
	public void Update() {
		if (emitter.bulletEmitter.出屏即消 && FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
		life++;
		transform.rotation = 270 + moveBasic.velocity.angle();
		if (depthBinded != null) {
			for (MBGBulletEmitter mbgBulletEmitter : depthBinded) {
				mbgBulletEmitter.Update();
			}
		}
		if (life > emitter.bulletEmitter.子弹生命) {
			entity.Destroy();
		}
	}

	@Override
	public void Kill() {
		if (depthBinded != null) {
			for (MBGBulletEmitter mbgBulletEmitter : depthBinded) {
				mbgBulletEmitter.Kill();
			}
		}
		super.Kill();
	}
}
