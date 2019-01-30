package cn.s3bit.th902.contents.stage2;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.DifficultySelectScreen;
import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;
import cn.s3bit.th902.gamecontents.components.enemy.ProjectileFactory;
import cn.s3bit.th902.utils.RandomPool;

public class DanmakuS2L3 extends DanmakuScene {

	int danmakuTime = 427;
	float cache = 30f;

	@Override
	public void Initialize(Entity entity) {
		enemy(0);
		waitEnemy(1000);
		enemy(1);
		waitEnemy(1000);
		enemy(2);
		waitEnemy(1000);
		enemy(3);
		waitEnemy(1000);
	}

	private void enemy(int color) {
		yield.append(() -> {
			BulletType bt;
			Entity sprite = BaseSprite.Create(new Vector2(290, FightScreen.TOP), color, 1400);
			final Transform transform = sprite.GetComponent(Transform.class);
			sprite.AddComponent(new MoveFunction(MoveFunctionTarget.VELOCITY, MoveFunctionType.ASSIGNMENT, (time) -> {
				if (time < 60) {
					switch (color) {
					case 0:
						return IMoveFunction.vct2_tmp1.set(0, -2);
					case 1:
						return IMoveFunction.vct2_tmp1.set(0, -3);
					case 2:
						return IMoveFunction.vct2_tmp1.set(0, -4);
					case 3:
						return IMoveFunction.vct2_tmp1.set(0, -5);
					default:
						return IMoveFunction.vct2_tmp1.set(0, 0);
					}
				} else {
					return IMoveFunction.vct2_tmp1.set(0, 0);
				}
			}));
			switch (color) {
			case 0:
				bt = BulletType.ColorBlue;
				break;
			case 1:
				bt = BulletType.ColorRed;
				break;
			case 2:
				bt = BulletType.ColorGreen;
				break;
			case 3:
				bt = BulletType.ColorYellow;
				break;
			default:
				bt = BulletType.ColorBlue;
				break;
			}
			sprite.AddComponent(new LambdaComponent(() -> {
				if (RandomPool.get(1).random(32767) % 4 + 1 <= DifficultySelectScreen.difficulty) {
					Vector2 vector2 = new Vector2(2 + 1.2f * DifficultySelectScreen.difficulty, 0);
					ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS, bt,
							new MoveBasic(vector2.cpy().rotate(danmakuTime * danmakuTime / cache)),
							new EnemyJudgeCircle(6));
					ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS, bt,
							new MoveBasic(vector2.cpy().rotate(danmakuTime * danmakuTime / cache + 180)),
							new EnemyJudgeCircle(6));
					ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS, bt,
							new MoveBasic(vector2.cpy().rotate(-(danmakuTime * danmakuTime / cache))),
							new EnemyJudgeCircle(6));
					ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS, bt,
							new MoveBasic(vector2.cpy().rotate(-(danmakuTime * danmakuTime / cache + 180))),
							new EnemyJudgeCircle(6));
				}
				danmakuTime++;
				if (danmakuTime > 1200) {
					danmakuTime = 420;
				}
			}, 60, 1));
			sprite.AddComponent(new ExtraDrop() {
				@Override
				public void LootLogic() {
					DropItem.CreateDropItem(transform.position.cpy(), 241);
				}
			});
		});
	}

	private void waitEnemy(int frames) {
		yield.append(() -> {
		}, frames * 2 / 3);
	}
}
