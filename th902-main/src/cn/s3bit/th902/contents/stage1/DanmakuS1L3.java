package cn.s3bit.th902.contents.stage1;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.DropItem;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;
import cn.s3bit.th902.gamecontents.components.enemy.ProjectileFactory;
import cn.s3bit.th902.utils.RandomPool;

public class DanmakuS1L3 extends DanmakuScene {
	int count = 0;
	@Override
	public void Initialize(Entity entity) {
		yield.append(() -> {}, 60);
		for (int i = 1; i <= 145; i++) {
			yield.append(() -> {}, 17 - i / 10);
			yield.append(() -> {
				int dir = RandomPool.get(1).randomSign();
				Vector2 pos = new Vector2(dir * 300 + 285, RandomPool.get(1).random(500, 570));
				final Entity sprite = BaseSprite.Create(pos, 2, 8, new MoveBasic(-10 * dir, RandomPool.get(1).random(-3f, 0f)));
				final Transform transform = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new LambdaComponent(() -> {
					for (int j=0; j<5; j++)
					{
						Entity proj = ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed);
						proj.AddComponent(new MoveBasic(RandomPool.get(1).random(-3f, 3f), RandomPool.get(1).random(-3f, -1f) * (JudgingSystem.playerJudge.y > transform.position.y ? -1 : 1)));
						proj.AddComponent(new EnemyJudgeCircle(6));
						proj = ProjectileFactory.Create(transform.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed);
						proj.AddComponent(new MoveBasic(RandomPool.get(1).random(-3f, 3f), RandomPool.get(1).random(-3f, -1f) * (JudgingSystem.playerJudge.y > transform.position.y ? -1 : 1)));
						proj.AddComponent(new EnemyJudgeCircle(6));
					}
				}, RandomPool.get(1).random(1, 30), -1));
				sprite.AddComponent(new ExtraDrop() {
					@Override
					public void LootLogic() {
						count++;
						if (count % 3 == 0) {
							DropItem.CreateDropItem(transform.position.cpy(), 242);
						}
					}
				});
			});
		}
	}

}
