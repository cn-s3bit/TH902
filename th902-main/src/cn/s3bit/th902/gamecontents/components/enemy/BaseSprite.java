package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.ai.MoveBasic;
import cn.s3bit.th902.gamecontents.ai.MoveSnipe;
import cn.s3bit.th902.gamecontents.ai.MoveTracking;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.AnimationDrawable;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class BaseSprite extends Component {
	public Vector2 velocity;
	public Transform transform;
	public AnimationDrawable animation;
	public ImmutableWrapper<Shape2D> judgeWrapper;
	public Circle judgeCircle;
	public Entity entity;
	public static Texture texture;
	public static TextureRegion[][] regions;
	public int Hp = 0;
	public int shootTime = 0;
	public Vector2 bulletV;
	public Vector2 specialBulletV;
	public int selfColor;

	/**
	 * @param color
	 *            0 - blue; 1 - red; 2 - green; 3 - yellow
	 */
	public static Entity Create(Vector2 position, int color, Component... Ves) {

		if (color < 0 || color > 3) {
			throw new IllegalArgumentException("Color of the enemy (sprite) should be between 0 and 3!");
		}
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position, new Vector2(0.5f, 0.5f)));
		BaseSprite component = new BaseSprite();
		component.selfColor = color;
		component.animation = new AnimationDrawable();
		if (texture != ResourceManager.enemies.get(0)) {
			texture = ResourceManager.enemies.get(0);
			regions = TextureRegion.split(texture, texture.getWidth() / 12, texture.getHeight() / 4);
		}
		component.animation.setAnimation(new Animation<TextureRegion>(1, regions[color]));
		entity.AddComponent(component);
		for (Component tmpc : Ves) {
			entity.AddComponent(tmpc);
		}
		return entity;
	}

	@Override
	public void Initialize(Entity entity) {
		velocity = new Vector2();
		transform = entity.GetComponent(Transform.class);
		entity.AddComponent(new ImageRenderer(animation, 1));
		judgeCircle = new Circle(transform.position, 50 * transform.scale.x);
		judgeWrapper = ImmutableWrapper.wrap((Shape2D) judgeCircle);
		JudgingSystem.registerEnemyJudge(judgeWrapper, IJudgeCallback.NONE);
		this.entity = entity;
		Hp = 10;
		bulletV = new Vector2(3, 0);
	}

	protected boolean animateFlag = true;

	@Override
	public void Update() {
		shootTime++;
		judgeCircle.setPosition(transform.position);
		if (transform.position.x > 580 || transform.position.x < -50 || transform.position.y > 800
				|| transform.position.y < -50) {
			Kill();
		}
		if (animateFlag) {
			if (animation.currentTime >= 2.84) {
				animateFlag = false;
			}
			animation.advance(0.08f);
		} else {
			if (animation.currentTime <= 0.16) {
				animateFlag = true;
			}
			animation.advance(-0.08f);
		}
		IJudgeCallback collision = JudgingSystem.collideFriendlyBullets(judgeCircle);
		if (collision != null) {
			Hp--;
			collision.onCollide();
			if (Hp < 0) {
				switch (selfColor) {
				case 0:
					DropItem.CreateDropItem(transform.position, DropItem.TypePoint);
					break;
				case 1:
					DropItem.CreateDropItem(transform.position, DropItem.TypePower);
					break;
				case 2:

					break;
				case 3:

					break;

				}
				entity.Destroy();
			}
		}
		ShootLogic();
	}

	private void ShootLogic() {
		specialBulletV = new Vector2(MathUtils.random(-3, 3), MathUtils.random(-3, 3));
		while (specialBulletV.equals(Vector2.Zero)) {
			specialBulletV = new Vector2(MathUtils.random(-3, 3), MathUtils.random(-3, 3));
		}
		if (shootTime % 60 == 0) {

			switch (selfColor) {
			case 0:
				BaseProjectile.Create(transform.position.cpy(), BulletType.FormArrowL, BulletType.ColorBlue,
						new MoveSnipe(2));
				break;
			case 1:
				for (int i = 0; i < 3; i++) {
					BaseProjectile.Create(transform.position.cpy(), BulletType.FormKnife, BulletType.ColorRed,
							new MoveBasic(bulletV.cpy()));
					bulletV.rotate(60);
					BaseProjectile.Create(transform.position.cpy(), BulletType.FormArrowL, BulletType.ColorOrange,
							new MoveBasic(bulletV.cpy()));
					bulletV.rotate(60);
				}
				break;
			case 2:
				BaseProjectile.Create(transform.position.cpy(), BulletType.FormArrowL, BulletType.ColorGreen,
						new MoveTracking(2));
				break;
			case 3:
				if (MathUtils.random(0, 5) == 2) {
					BaseProjectile.CreateSpecialBullet(transform.position.cpy(), MathUtils.random(230, 235),
							new MoveSnipe(2));
				}
				break;

			}
			bulletV.rotate(7);
		}

	}

	@Override
	public void Kill() {
		JudgingSystem.unregisterEnemyJudge(judgeWrapper);
		super.Kill();
	}
}
