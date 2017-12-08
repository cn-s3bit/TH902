package cn.s3bit.th902.contents.stage1.lunatic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveSnipe;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class DanmakuS1L1 extends DanmakuScene {

	@Override
	public void Initialize(Entity entity) {
		for (int i=0; i<12; i++)
		{
			//center 285, 368
			yield.append(() -> { }, 15);
			yield.append(() -> {
				Entity sprite = BaseSprite.Create(new Vector2(20, 730), 3, 20);
				final Transform transform1 = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new MoveBasic(0.1f, -2f, MathUtils.random(0f, 0.05f), MathUtils.random(-0.02f, 0)));
				
				sprite.AddComponent(new ExtraDrop() {
					@Override
					public void LootLogic() {
						DropItem.CreateDropItem(transform1.position.cpy(), 241 + MathUtils.random(1));
						Entity proj = BaseProjectile.Create(transform1.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed);
						proj.AddComponent(new MoveSnipe(4));
						proj.AddComponent(new EnemyJudgeCircle(6));
					}
				});
				
				sprite = BaseSprite.Create(new Vector2(560, 730), 3, 20);
				final Transform transform2 = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new MoveBasic(-0.1f, -2f, MathUtils.random(-0.05f, 0f), MathUtils.random(-0.02f, 0)));
				sprite.AddComponent(new ExtraDrop() {
					@Override
					public void LootLogic() {
						DropItem.CreateDropItem(transform2.position.cpy(), 241 + MathUtils.random(1));
						for (int j=0; j<3; j++)
						{
							Entity proj = BaseProjectile.Create(transform2.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed);
							proj.AddComponent(new MoveBasic(MathUtils.random(-3f, -1f), MathUtils.random(-3f, 3f)));
							proj.AddComponent(new EnemyJudgeCircle(6));
							proj = BaseProjectile.Create(transform2.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed);
							proj.AddComponent(new MoveBasic(MathUtils.random(1f, 3f), MathUtils.random(-3f, 3f)));
							proj.AddComponent(new EnemyJudgeCircle(6));
						}
					}
				});
			});
		}
		yield.append(() -> { }, 120);
	}

}