package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ExtraDrop;
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
		if (FightScreen.isOutOfScreen(transform.position)) {
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
			Hp -= collision.getDamage();
			collision.onCollide();
			if (Hp < 0) {
				ExtraDrop extraDrop = entity.GetComponent(ExtraDrop.class);
				if (extraDrop != null)
					extraDrop.LootLogic();
				entity.Destroy();
			}
		}
	}

	@Override
	public void Kill() {
		JudgingSystem.unregisterEnemyJudge(judgeWrapper);
		super.Kill();
	}
}
