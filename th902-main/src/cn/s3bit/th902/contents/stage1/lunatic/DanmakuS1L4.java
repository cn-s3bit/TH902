package cn.s3bit.th902.contents.stage1.lunatic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BossHP;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyChaseable;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class DanmakuS1L4 extends DanmakuScene {

	@Override
	public void Initialize(Entity entity) {
		yield.append(() -> {}, 300);
		yield.append(() -> {
			Entity boss = Entity.Create();
			Transform transform = new Transform(new Vector2(285, 730), new Vector2(2, 2));
			boss.AddComponent(transform);
			boss.AddComponent(new ImageRenderer(ResourceManager.barrages.get(230), 0));
			BossHP hp = new BossHP(
					new int[]{1600},
					new int[]{3600},
					new String[]{"TEST SPELL - [TEST]"},
					new Runnable[]{null},
					new Runnable[]{ () -> { DropItem.CreateDropItem(transform.position.cpy(), 241); } });
			boss.AddComponent(hp);
			boss.AddComponent(new EnemyJudgeCircle(54, hp));
			boss.AddComponent(new EnemyChaseable(hp));
			boss.AddComponent(new MoveFunction(MoveFunctionTarget.VELOCITY, MoveFunctionType.ASSIGNMENT, (time) -> {
				return IMoveFunction.vct2_tmp1.set(0, time < 40 ? -4f : 0);
			}));
			boss.AddComponent(new LambdaComponent(() -> {
				BaseProjectile.Create(transform.position.cpy(), BulletType.FormBulletM, BulletType.ColorPink,
						new MoveBasic(MathUtils.random(-4f, 4f), MathUtils.random(-3f, -1f) * (JudgingSystem.playerJudge.y > transform.position.y ? -1 : 1)),
						new EnemyJudgeCircle(4));
			}, 1));
		});
	}

}
