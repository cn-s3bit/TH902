package cn.s3bit.th902.contents.stage1;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.EnemySpellInfoSystem;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.enemy.BossHP;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyChaseable;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class DanmakuS1LLast extends DanmakuScene {

	@Override
	public void Initialize(Entity entity) {
		yield.append(() -> {
			DanmakuS1L6.sprites[0].Destroy();
			DanmakuS1L6.sprites[1].Destroy();
		});
		yield.append(() -> {}, 300);
		yield.append(() -> {
			Entity boss = Entity.Create();
			Transform transform = new Transform(new Vector2(285, 730), new Vector2(2, 2));
			boss.AddComponent(transform);
			boss.AddComponent(new ImageRenderer(ResourceManager.barrages.get(230), 0).attachToGroup(FightScreen.drawingLayers.entity8));
			BossHP hp = new BossHP(
					new int[]{1800, 3600, 2100, 3600, 6000},
					new int[]{1200, 2400, 1200, 2400, 3000},
					new float[] {1f, 1.4f, 1.1f, 1.4f, 2.4f},
					new String[]{null, "「Ctrl-C, Ctrl-V」", null, "动符「闪烁」", "动符「CROSS」"},
					new Runnable[]{null, null, null, null, null},
					new Runnable[]{null, null, null, null, () -> { DropItem.CreateDropItem(transform.position.cpy(), 241); } });
			boss.AddComponent(hp);
			boss.AddComponent(new EnemyJudgeCircle(54, hp));
			boss.AddComponent(new EnemyChaseable(hp));
			boss.AddComponent(new MoveFunction(MoveFunctionTarget.VELOCITY, MoveFunctionType.ASSIGNMENT, (time) -> {
				return IMoveFunction.vct2_tmp1.set(0, time < 40 ? -4f : 0);
			}));
			boss.AddComponent(new AIS1LLastBoss());
		});
	}

	int countdown = 240;
	@Override
	public void Update() {
		yield.yield();
		if (yield.isFinished()) {
			yield.append(() -> { return null; }, 1048576);
			countdown--;
		}
		if (countdown < 240 && !EnemySpellInfoSystem.isActive()) {
			if (countdown > 0) {
				countdown--;
			}
			else {
				Kill();
				FightScreen.sceneSystem.nextScene();
			}
		}
	}
}
