package cn.s3bit.th902.contents.stage1.easy;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveSnipe;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public final class DanmakuE1 extends DanmakuScene {
	@Override
	public void Initialize(Entity entity) {
		for (int i = 0; i < 5; i++) {
			yield.append(() -> {
			}, 30);
			yield.append(() -> {
				final Entity sprite = BaseSprite.Create(new Vector2(0, 500), 1);
				final Transform transform = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new ExtraDrop() {
					@Override
					public void LootLogic() {
						DropItem.CreateDropItem(transform.position.cpy(), DropItem.TypePower);
					}
				});
				sprite.AddComponent(new MoveBasic(1, -1f, 0, 0.005f));
				sprite.AddComponent(new LambdaComponent(() -> {
					BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed,
							new MoveSnipe(3f), new EnemyJudgeCircle(6));
				}, 60, -1));
			});
			yield.append(() -> {
			}, 30);
			yield.append(() -> {
				final Entity sprite = BaseSprite.Create(new Vector2(560, 500), 1);
				final Transform transform = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new ExtraDrop() {
					@Override
					public void LootLogic() {
						DropItem.CreateDropItem(transform.position.cpy(), DropItem.TypePower);
					}
				});
				sprite.AddComponent(new MoveBasic(-1, -1f, 0, 0.005f));
				sprite.AddComponent(new LambdaComponent(() -> {
					BaseProjectile.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed,
							new MoveSnipe(3f), new EnemyJudgeCircle(6));
				}, 60, -1));
			});
		}
		yield.append(() -> {
		}, 200);
	}
}
