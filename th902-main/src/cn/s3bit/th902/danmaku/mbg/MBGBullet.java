package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntMap.Entry;
import com.badlogic.gdx.utils.ObjectSet;

import cn.s3bit.mbgparser.Action;
import cn.s3bit.mbgparser.Tuple;
import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.danmaku.mbg.condition.BulletConditions;
import cn.s3bit.th902.danmaku.mbg.condition.IConditionJudger;
import cn.s3bit.th902.danmaku.mbg.event.BulletEvents;
import cn.s3bit.th902.danmaku.mbg.event.BulletLValues;
import cn.s3bit.th902.danmaku.mbg.event.IEventFirer;
import cn.s3bit.th902.danmaku.mbg.event.ILValueProvider;

public class MBGBullet extends AbstractMBGComponent<BulletEmitter> {
	public MBGBulletEmitter emitter;
	public Color color = new Color(1f, 1f, 1f, 1f);
	public ObjectSet<MBGBulletEmitter> depthBinded = null;
	
	public MBGBullet(MBGBulletEmitter bulletEmitter) {
		super(bulletEmitter.mbgItem, bulletEmitter.mbgScene, bulletEmitter.layer);
		emitter = bulletEmitter;
	}
	
	@Override
	public IEventFirer<AbstractMBGComponent<BulletEmitter>> getEventFirer() {
		return BulletEvents.instance;
	}

	@Override
	public IConditionJudger<AbstractMBGComponent<BulletEmitter>> getConditionJudger() {
		return BulletConditions.instance;
	}

	@Override
	public ILValueProvider<AbstractMBGComponent<BulletEmitter>> getLValueProvider() {
		return BulletLValues.instance;
	}

	@Override
	public int getBeginTime() {
		return 0;
	}

	@Override
	public int getLife() {
		return mbgItem.子弹生命;
	}

	@Override
	public Vector2 getInitialPosition() {
		return transform.position.cpy();
	}

	@Override
	public void begin() {
		for (Entry<MBGBulletEmitter> sub : emitter.mbgScene.bulletEmitters) {
			if (sub.value.mbgItem.绑定状态.Parent == emitter.mbgItem) {
				if (sub.value.mbgItem.绑定状态.Depth) {
					if (depthBinded == null)
						depthBinded = new ObjectSet<MBGBulletEmitter>();
					Tuple<BulletEmitter, Action> bulletEmitter = BulletEmitter.parseFrom(((BulletEmitter) sub.value.mbgItem.绑定状态.Child).stringData, emitter.mbgItem.layer);
					bulletEmitter.Item1.绑定状态.Depth = true;
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
	public void during() {
		if (depthBinded != null) {
			for (MBGBulletEmitter mbgBulletEmitter : depthBinded) {
				mbgBulletEmitter.Update();
			}
		}
		if (mbgItem.出屏即消 && FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
		transform.rotation = 270 + moveBasic.velocity.angle();
		runEventGroups(mbgItem.子弹事件组, life);
		if (life > mbgItem.子弹生命) {
			entity.Destroy();
		}
	}

	@Override
	public void after() {
		if (depthBinded != null) {
			for (MBGBulletEmitter mbgBulletEmitter : depthBinded) {
				mbgBulletEmitter.Kill();
			}
		}
		entity.Destroy();
	}
}
