package cn.s3bit.th902.contents.stage1.easy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.EnemyJudgeCircle;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.ai.MoveSnipe;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;

public final class DanmakuE2 extends DanmakuScene {
	@Override
	public void Initialize(Entity entity) {
		for (int i = 0; i < 5; i++) {
			yield.append(() -> {}, 30);
			yield.append(() -> {
				final Entity sprite = BaseSprite.Create(new Vector2(FightScreen.LEFT, FightScreen.TOP * 3 / 4), 1);
				final Transform transform = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new ExtraDrop() {
					@Override
					public void LootLogic() {
						DropItem.CreateDropItem(transform.position.cpy(), DropItem.TypePower);
					}
				});
				Vector2 vector2 = new Vector2(MathUtils.random(100, 400), MathUtils.random(100, 700));
				sprite.AddComponent(new MoveFunction(MoveFunctionTarget.POSITION, MoveFunctionType.ASSIGNMENT, (time) -> {
					if (time <= 80) {
						if (time % 80 == 0) {
							BaseProjectile.Create(transform.position.cpy(), BulletType.FormArrowL, BulletType.ColorRed,
									new MoveSnipe(3f), new EnemyJudgeCircle(6));
						}
						return IMoveFunction.vct2_tmp2
								.set(transform.position.add(vector2.cpy().sub(transform.position).scl(0.02f)));
					} else {
						return IMoveFunction.vct2_tmp2
								.set(transform.position.add(new Vector2(300, 600).sub(transform.position).scl(0.02f)));
					}
				}));
				sprite.AddComponent(new LambdaComponent(() -> {
					BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleLightM, BulletType.ColorRed,
							new MoveSnipe(3f), new EnemyJudgeCircle(6));
				}, 40, -1));
			});
			yield.append(() -> {}, 30);
			yield.append(() -> {
				final Entity sprite = BaseSprite.Create(new Vector2(FightScreen.RIGHT, FightScreen.TOP * 3 / 4), 1);
				final Transform transform = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new ExtraDrop() {
					@Override
					public void LootLogic() {
						DropItem.CreateDropItem(transform.position.cpy(), DropItem.TypePower);
					}
				});
				Vector2 vector2 = new Vector2(MathUtils.random(100, 400), MathUtils.random(100, 700));
				sprite.AddComponent(new MoveFunction(MoveFunctionTarget.POSITION, MoveFunctionType.ASSIGNMENT, (time) -> {
					if (time <= 80) {
						if (time % 80 == 0) {
							BaseProjectile.Create(transform.position.cpy(), BulletType.FormArrowL, BulletType.ColorRed,
									new MoveSnipe(3f), new EnemyJudgeCircle(6));
						}
						return IMoveFunction.vct2_tmp2
								.set(transform.position.add(vector2.cpy().sub(transform.position).scl(0.02f)));
					} else {
						return IMoveFunction.vct2_tmp2
								.set(transform.position.add(new Vector2(300, 600).sub(transform.position).scl(0.02f)));
					}
				}));
				sprite.AddComponent(new LambdaComponent(() -> {
					BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleLightM, BulletType.ColorRed,
							new MoveSnipe(3f), new EnemyJudgeCircle(6));
				}, 40, -1));
			});
		}
		
	}
}
