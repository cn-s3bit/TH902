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

	/**
	 * @param color
	 *            0 - blue; 1 - red; 2 - green; 3 - yellow
	 */
	public static Entity Create(Vector2 position, int color) {

		if (color < 0 || color > 3) {
			throw new IllegalArgumentException("Color of the enemy (sprite) should be between 0 and 3!");
		}
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position, new Vector2(0.5f, 0.5f)));
		BaseSprite component = new BaseSprite();
		component.animation = new AnimationDrawable();
		if (texture != ResourceManager.enemies.get(0)) {
			texture = ResourceManager.enemies.get(0);
			regions = TextureRegion.split(texture, texture.getWidth() / 12, texture.getHeight() / 4);
		}
		component.animation.setAnimation(new Animation<TextureRegion>(1, regions[color]));
		entity.AddComponent(component);
		return entity;
	}

	@Override
	public void Initialize(Entity entity) {
		velocity = new Vector2();
		transform = entity.GetComponent(Transform.class);
		entity.AddComponent(new ImageRenderer(animation, 1));
		judgeCircle = new Circle(transform.position, 50 * transform.scale.x);
		judgeWrapper = ImmutableWrapper.wrap((Shape2D)judgeCircle);
		JudgingSystem.registerEnemyJudge(judgeWrapper, IJudgeCallback.NONE);
		this.entity = entity;
		Hp = 20;
	}

	protected boolean animateFlag = true;

	@Override
	public void Update() {
		MoveLogic();
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
				entity.Destroy();
			}
		}
		ShootLogic();
	}

	private void MoveLogic() {
		transform.position.add(0.3f, 0.5f);
	}

	private void ShootLogic() {
		if (shootTime % 5 == 0) {
			BaseProjectile.Create(transform.position.cpy(), MathUtils.random(0, 24), MathUtils.random(0, 7));
			if (MathUtils.random(0, 5) == 2) {
				BaseProjectile.CreateSpecialBullet(transform.position.cpy(), MathUtils.random(230, 235));
			}
			
		}
	}

	@Override
	public void Kill() {
		JudgingSystem.unregisterEnemyJudge(judgeWrapper);
		super.Kill();
	}
}
