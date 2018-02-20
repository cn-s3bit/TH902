package cn.s3bit.th902.contents.stage2;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.ai.MoveSnipe;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;
import cn.s3bit.th902.gamecontents.components.enemy.ProjectileFactory;

public class DanmakuS2L2 extends DanmakuScene {
	int x1 = 167;
	int x2 = 424;

	@Override
	public void Initialize(Entity entity) {
		// TODO Auto-generated method stub
		enemyA(x1);
		enemyA(x2);
		waitEnemy(180);
		enemyA(x1);
		enemyA(x2);
		waitEnemy(180);
		enemyA(x1);
		enemyA(x2);
		waitEnemy(180);
		enemyA(x1);
		enemyA(x2);
	}

	private void enemyA(int x) {
		yield.append(() -> {
			Entity sprite = BaseSprite.Create(new Vector2(x, FightScreen.TOP), 1, 20);
			final Transform transform = sprite.GetComponent(Transform.class);
			final Vector2 playerPosition = JudgingSystem.playerJudge.cpy().sub(transform.position).nor().scl(3f);
			sprite.AddComponent(new MoveFunction(MoveFunctionTarget.VELOCITY, MoveFunctionType.ASSIGNMENT, (time) -> {
				if (time < 60) {
					return IMoveFunction.vct2_tmp1.set(0, -3);
				} else if (time < 90) {
					return IMoveFunction.vct2_tmp1.set(0, 0);
				} else {
					return IMoveFunction.vct2_tmp1.set(playerPosition);
				}
			}));
			sprite.AddComponent(new LambdaComponent(() -> {
				ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed,
						new MoveSnipe(3f), new EnemyJudgeCircle(6));
			}, 20));
			sprite.AddComponent(new ExtraDrop() {
				@Override
				public void LootLogic() {
					DropItem.CreateDropItem(transform.position.cpy(), 241);
					Entity proj = ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS,
							BulletType.ColorRed);
					proj.AddComponent(new MoveSnipe(9f));
					proj.AddComponent(new EnemyJudgeCircle(6));
				}
			});
		});
	}

	private void waitEnemy(int frames) {
		yield.append(() -> {
		}, frames * 2 / 3);
	}
}
