package cn.s3bit.th902.contents;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
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
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public final class ExampleDanmakuScene extends DanmakuScene {
	@Override
	public void Initialize(Entity entity) {
		yield.append(() -> { }, 20); //Wait For 20 Frames.
		yield.append(() -> {
			final Entity sprite = BaseSprite.Create(new Vector2(FightScreen.TOP/2, FightScreen.RIGHT/2), 0);
			final Transform transform = sprite.GetComponent(Transform.class);
			sprite.AddComponent(new ExtraDrop() {
				@Override
				public void LootLogic() {
					DropItem.CreateDropItem(transform.position.cpy(), DropItem.TypePower);
				}
			});
			sprite.AddComponent(new MoveFunction(
					MoveFunctionTarget.POSITION,
					MoveFunctionType.ASSIGNMENT,
					(time) -> { return IMoveFunction.vct2_tmp1.set(60, 60).rotate(time * 2).add(300, 400); }));
			sprite.AddComponent(new LambdaComponent(() -> {
				BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorBlue, new MoveSnipe(3f), new EnemyJudgeCircle(6));
			}, 30));
		});
		yield.append(() -> { }, 20);
	}
}
