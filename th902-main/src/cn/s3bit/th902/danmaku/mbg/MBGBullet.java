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
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.LifeCountDown;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class MBGBullet extends AbstractMBGComponent<BulletEmitter> {
	public MBGBulletEmitter emitter;
	public Color color = new Color(1f, 1f, 1f, 1f);
	public ObjectSet<MBGBulletEmitter> depthBinded = null;
	
	public EnemyJudgeCircle judgeCircle;
	
	public ImageRenderer renderer;
	
	public int timeCont = 0;
	
	public MBGBullet(MBGBulletEmitter bulletEmitter) {
		super(bulletEmitter.mbgItem, bulletEmitter.mbgScene, bulletEmitter.layer);
		emitter = bulletEmitter;
		timeCont = bulletEmitter.mbgItem.子弹生命;
		judgeCircle = new EnemyJudgeCircle(MBGBulletTypeMap.JUDGE_SIZE_MAP.get(bulletEmitter.mbgItem.子弹类型, 2f) * Math.min(mbgItem.宽比, mbgItem.高比));
		color.r = mbgItem.子弹颜色.R / 255f;
		color.g = mbgItem.子弹颜色.G / 255f;
		color.b = mbgItem.子弹颜色.B / 255f;
		color.a = mbgItem.子弹颜色.A / 100f;
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
		return timeCont;
	}

	@Override
	public Vector2 getInitialPosition() {
		return transform.position.cpy();
	}
	
	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		renderer = entity.GetComponent(ImageRenderer.class);
		renderer.getActor().setColor(color);
	}

	@Override
	public void begin() {
		entity.AddComponent(judgeCircle);
		JudgingSystem.clearByBombs.put(transform.immutablePosition, entity);
		for (Entry<MBGBulletEmitter> sub : emitter.mbgScene.bulletEmitters) {
			if (sub.value.mbgItem.绑定状态.Parent != null && sub.value.mbgItem.绑定状态.Parent.ID == emitter.mbgItem.ID) {
				if (sub.value.mbgItem.绑定状态.Depth) {
					if (depthBinded == null)
						depthBinded = new ObjectSet<MBGBulletEmitter>();
					Tuple<BulletEmitter, Action> bulletEmitter = BulletEmitter.parseFrom(((BulletEmitter) sub.value.mbgItem.绑定状态.Child).stringData, emitter.mbgItem.layer);
					bulletEmitter.Item1.绑定状态.Depth = true;
					bulletEmitter.Item1.绑定状态.Relative = sub.value.mbgItem.绑定状态.Relative;
					MBGBulletEmitter depth = new MBGBulletEmitter(bulletEmitter.Item1, emitter.mbgScene, emitter.layer);
					depth.Initialize(entity);
					depthBinded.add(depth);
				}
				else {
					sub.value.subEmitters.add(transform);
				}
			}
		}
	}

	@Override
	public void during() {
		renderer.getActor().setColor(color);
		judgeCircle.circle.width = 2 * MBGBulletTypeMap.JUDGE_SIZE_MAP.get(mbgItem.子弹类型, 2f) * transform.scale.x;
		judgeCircle.circle.height = 2 * MBGBulletTypeMap.JUDGE_SIZE_MAP.get(mbgItem.子弹类型, 2f) * transform.scale.y;
		if (color.a < 0.95f) {
			judgeCircle.circle.width = judgeCircle.circle.height = 0f;
		}
		if (mbgItem.出屏即消 && FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
		}
		else {
			if (mbgItem.朝向与速度方向相同)
				transform.rotation = 270 + moveBasic.velocity.angle();
			runEventGroups(mbgItem.子弹事件组, life);
			if (depthBinded != null) {
				for (MBGBulletEmitter mbgBulletEmitter : depthBinded) {
					mbgBulletEmitter.Update();
				}
			}
			if (mbgItem.反弹板) {
				for (MBGReflexBoard reflexBoard : mbgScene.reflexBoards.values()) {
					if (reflexBoard.judgeHitBullet(this)) {
						reflexBoard.manipulateBullet(this);
					}
				}
			}
			if (mbgItem.拖影效果) {
				Entity entity = Entity.Create();
				entity.AddComponent(new Transform(transform.position.cpy(), transform.rotation, transform.scale));
				ImageRenderer imageRenderer = new ImageRenderer(renderer.image.getDrawable(), 0);
				imageRenderer.image.setColor(1f, 1f, 1f, 0.3f);
				entity.AddComponent(imageRenderer);
				entity.AddComponent(new LifeCountDown(15));
			}
		}
	}

	@Override
	public void after() {
		JudgingSystem.clearByBombs.remove(transform.immutablePosition);
		if (depthBinded != null) {
			for (MBGBulletEmitter mbgBulletEmitter : depthBinded) {
				mbgBulletEmitter.Kill();
			}
		}
		entity.Destroy();
	}
	
	@Override
	public void Kill() {
		JudgingSystem.clearByBombs.remove(transform.immutablePosition);
		super.Kill();
	}
}
