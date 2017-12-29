package cn.s3bit.th902.contents.stage1.lunatic;

import java.util.concurrent.Callable;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.DanmakuScene;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunction;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionTarget;
import cn.s3bit.th902.gamecontents.components.ai.MoveFunctionType;
import cn.s3bit.th902.gamecontents.components.enemy.BaseProjectile;
import cn.s3bit.th902.gamecontents.components.enemy.BaseSprite;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;

public class DanmakuS1L6 extends DanmakuScene {
	static Entity[] sprites = {null, null};
	@Override
	public void Initialize(Entity entity) {
		Callable<Object> pause = () -> { return null; };
		yield.append(pause, 120);
		yield.append(() -> {
			for (int i=0; i<=1; i++) {
				Entity sprite = BaseSprite.Create(new Vector2(135 + i * 300, 730), i, 300 + i * 300);
				sprites[i] = sprite;
				final Transform tr = sprite.GetComponent(Transform.class);
				tr.scale.set(0.5f, 0.5f);
				sprite.AddComponent(new LambdaComponent(() -> {
					for (int angle=MathUtils.random(360), k=0; k<24; angle+=15, k++)
						BaseProjectile.Create(tr.position.cpy(), BulletType.FormCircleLightM, BulletType.ColorYellow,
								new EnemyJudgeCircle(13),
								new MoveBasic(new Vector2(7.2f, 0).rotate(angle)));
				}, 40, 30));
				sprite.AddComponent(new MoveFunction(MoveFunctionTarget.VELOCITY, MoveFunctionType.ASSIGNMENT, (time) -> {
					return IMoveFunction.vct2_tmp1.set(0, time < 30 ? -8 : 0);
				}));
			}
		});
		yield.append(pause, 900);
	}

}
