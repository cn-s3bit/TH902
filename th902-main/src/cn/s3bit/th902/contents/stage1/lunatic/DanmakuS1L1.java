package cn.s3bit.th902.contents.stage1.lunatic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveSnipe;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class DanmakuS1L1 extends DanmakuScene {

	@Override
	public void Initialize(Entity entity) {
		for (int i=0; i<10; i++)
		{
			//center 285, 368
			yield.append(() -> { }, 20);
			yield.append(() -> {
				Entity sprite = BaseSprite.Create(new Vector2(285 - 0.5f * 270, 730), 3, 20);
				final Transform transform1 = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new MoveBasic(0.5f, -3, 0, -0.0001f));
				sprite.AddComponent(new LambdaComponent(() -> {
					Entity proj = BaseProjectile.Create(transform1.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed);
					proj.AddComponent(new MoveSnipe(4));
					proj.AddComponent(new EnemyJudgeCircle(6));
				}, 30, -1));
				
				sprite = BaseSprite.Create(new Vector2(285 + 0.5f * 270, 730), 3, 20);
				final Transform transform2 = sprite.GetComponent(Transform.class);
				sprite.AddComponent(new MoveBasic(-0.5f, -3, 0, -0.0001f));
				sprite.AddComponent(new LambdaComponent(() -> {
					for (int j=0; j<6; j++)
					{
						Entity proj = BaseProjectile.Create(transform2.position.cpy(), BulletType.FormCircleS, BulletType.ColorRed);
						proj.AddComponent(new MoveBasic(MathUtils.random(-3f, 3f), MathUtils.random(-3f, 3f)));
						proj.AddComponent(new EnemyJudgeCircle(6));
					}
				}, 30, -1));
			});
		}
	}

}
