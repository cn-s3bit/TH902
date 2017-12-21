package cn.s3bit.th902.contents.stage2.lunatic;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.ai.MoveSnipe;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public final class DanmakuS2L1 extends DanmakuScene {
	private final int n1 = 1;
	private final int n2 = -1;
	int x1 = 167;
	int x2 = 424;

	@Override
	public void Initialize(Entity entity) {
		enemyA(n1, x1);
		waitEnemy(30);
		enemyA(n1, x1);
		waitEnemy(30);
		enemyA(n1, x1);
		waitEnemy(30);
		enemyA(n1, x1);

		waitEnemy(15);
		enemyB(n1, x2);
		waitEnemy(15);
		enemyA(n1, x1);
		waitEnemy(15);
		enemyB(n1, x2);
		waitEnemy(15);
		enemyA(n1, x1);
		waitEnemy(15);

		enemyB(n1, x2);
		waitEnemy(30);
		enemyB(n1, x2);
		waitEnemy(30);
		enemyB(n1, x2);
		waitEnemy(30);
		enemyB(n1, x2);
		waitEnemy(90);

		enemyA(n2, x2);
		waitEnemy(30);
		enemyA(n2, x2);
		waitEnemy(30);
		enemyA(n2, x2);
		waitEnemy(30);
		enemyA(n2, x2);

		waitEnemy(15);
		enemyB(n2, x1);
		waitEnemy(15);
		enemyA(n2, x2);
		waitEnemy(15);
		enemyB(n2, x1);
		waitEnemy(15);
		enemyA(n2, x2);
		waitEnemy(15);

		enemyB(n2, x1);
		waitEnemy(30);
		enemyB(n2, x1);
		waitEnemy(30);
		enemyB(n2, x1);
		waitEnemy(30);
		enemyB(n2, x1);
	}

	private void enemyA(int n, int x) {
		yield.append(() -> {
			Entity sprite = BaseSprite.Create(new Vector2(x, FightScreen.TOP), 1, 20);
			final Transform transform = sprite.GetComponent(Transform.class);
			sprite.AddComponent(new MoveBasic(0 * n, -2f, 0, 0));
			sprite.AddComponent(new LambdaComponent(() -> {
				BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed,
						new MoveSnipe(3f), new EnemyJudgeCircle(6));
			}, 60));
			sprite.AddComponent(new ExtraDrop() {
				@Override
				public void LootLogic() {
					DropItem.CreateDropItem(transform.position.cpy(), 241);
					Entity proj = BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS,
							BulletType.ColorRed);
					proj.AddComponent(new MoveSnipe(9f));
					proj.AddComponent(new EnemyJudgeCircle(6));
				}
			});
		});
	}

	private void enemyB(int n, int x) {
		yield.append(() -> {
			Entity sprite = BaseSprite.Create(new Vector2(x, FightScreen.TOP), 0, 20);
			final Transform transform = sprite.GetComponent(Transform.class);
			sprite.AddComponent(new MoveFunction(MoveFunctionTarget.POSITION, MoveFunctionType.ASSIGNMENT, (time) -> {
				if (time < 180) {
					return transform.position.cpy().add(0, -2);
				} else {
					return transform.position.cpy().add(-2 * n, 0);
				}
			}));
			sprite.AddComponent(new LambdaComponent(() -> {
				BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorBlue,
						new MoveSnipe(3f), new EnemyJudgeCircle(6));
			}, 60));
			sprite.AddComponent(new ExtraDrop() {
				@Override
				public void LootLogic() {
					DropItem.CreateDropItem(transform.position.cpy(), 242);
					Entity proj = BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS,
							BulletType.ColorRed);
					proj.AddComponent(new MoveSnipe(9f));
					proj.AddComponent(new EnemyJudgeCircle(6));
				}
			});
		});
	}

	private void waitEnemy(int frames) {
		yield.append(() -> {
		}, frames);
	}
}
