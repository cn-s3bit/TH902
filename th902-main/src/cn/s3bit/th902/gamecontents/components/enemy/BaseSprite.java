package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.AnimationDrawable;

public class BaseSprite extends Component implements IJudgeCallback {
	public Transform transform;
	public AnimationDrawable animation;
	public Entity entity;
	public static Texture texture;
	public static TextureRegion[][] regions;
	//public int shootTime = 0;

	public static Entity Create(Vector2 position, int color, Component... Ves) {
		return Create(position, color, 10, Ves);
	}
	/**
	 * @param color
	 *            0 - blue; 1 - red; 2 - green; 3 - yellow
	 */
	public static Entity Create(Vector2 position, int color, int maxhp, Component... Ves) {
		if (color < 0 || color > 3) {
			throw new IllegalArgumentException("Color of the enemy (sprite) should be between 0 and 3!");
		}
		Entity entity = Entity.Create();
		Transform transform = new Transform(position, new Vector2(0.5f, 0.5f));
		entity.AddComponent(transform);
		BaseSprite component = new BaseSprite();
		component.animation = new AnimationDrawable();
		if (texture != ResourceManager.enemies.get(0)) {
			texture = ResourceManager.enemies.get(0);
			regions = TextureRegion.split(texture, texture.getWidth() / 12, texture.getHeight() / 4);
		}
		component.animation.setAnimation(new Animation<TextureRegion>(1, regions[color]));
		EnemyHP hp = new EnemyHP(maxhp);
		entity.AddComponent(hp);
		entity.AddComponent(new EnemyJudgeCircle(48 * transform.scale.x, hp));
		entity.AddComponent(new EnemyChaseable(hp));
		entity.AddComponent(component);
		for (Component tmpc : Ves) {
			entity.AddComponent(tmpc);
		}
		return entity;
	}

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		entity.AddComponent(new ImageRenderer(animation, 1));
		this.entity = entity;
	}

	protected boolean animateFlag = true;

	@Override
	public void Update() {
		if (FightScreen.isOutOfScreen(transform.position)) {
			entity.Destroy();
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
	}

	@Override
	public void Kill() {
		super.Kill();
	}
}
