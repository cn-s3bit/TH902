package cn.s3bit.th902.contents.stage1;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;
import cn.s3bit.th902.gamecontents.components.enemy.ProjectileFactory;

public class DanmakuS1L5 extends DanmakuScene {

	@Override
	public void Initialize(Entity entity) {
		for (int i=0; i<4; i++) {
			addPart(i);
			yield.append(() -> { }, 180);
		}
	}

	static Vector2[] poss = { new Vector2(285, 400), new Vector2(200, 300), new Vector2(370, 460), new Vector2(285, 400), new Vector2(285, 260) };
	public void addPart(final int part) {
		for (int i=0; i<200 + part * 30; i++) {
			final int angle = i * 21;
			yield.append(() -> {
				Entity proj = ProjectileFactory.Create(poss[part + angle % 2].cpy(), BulletType.FormCircleS, BulletType.ColorBlueLight);
				final Transform tr = proj.GetComponent(Transform.class);
				final Vector2 snipe = new Vector2();
				proj.AddComponent(new MoveFunction(MoveFunctionTarget.VELOCITY, MoveFunctionType.ASSIGNMENT, (time) -> {
					if (time < 120 + part * 5)
						return IMoveFunction.vct2_tmp1.set(1, 0).rotate(angle).scl(2.8f - time / 60f);
					if (snipe.x == 0 && snipe.y == 0) {
						GameHelper.snipeVct(tr.position, JudgingSystem.playerJudge, 0, snipe);
						snipe.nor().scl(7);
					}
					return snipe;
				}));
				proj.AddComponent(new EnemyJudgeCircle(5));
			});
		}
	}
}
