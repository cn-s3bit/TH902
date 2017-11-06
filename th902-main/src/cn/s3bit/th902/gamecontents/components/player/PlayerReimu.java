package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import cn.s3bit.th902.KeySettings;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageGroupRenderer;
import cn.s3bit.th902.utils.AnimationDrawable;

public class PlayerReimu extends Player {

	Animation<TextureRegion> animationStay;
	Animation<TextureRegion> animationLeft;
	Animation<TextureRegion> animationRight;
	AnimationDrawable animationDrawable = new AnimationDrawable();
	public int existTime;
	private ReimuWing mReimuWing1;
	private ReimuWing mReimuWing2;

	public PlayerReimu() {
		Texture texture = ResourceManager.textures.get("Reimu");
		TextureRegion[][] splitted = TextureRegion.split(texture, texture.getWidth() / 8, texture.getHeight() / 3);
		animationStay = new Animation<TextureRegion>(5, splitted[0]);
		animationStay.setPlayMode(PlayMode.LOOP);
		animationLeft = new Animation<TextureRegion>(2, splitted[1]);
		animationLeft.setPlayMode(PlayMode.NORMAL);
		animationRight = new Animation<TextureRegion>(2, splitted[2]);
		animationRight.setPlayMode(PlayMode.NORMAL);
	}

	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		animationDrawable.setAnimation(animationStay);
		existTime = 0;
		entity.AddComponent(new ImageGroupRenderer(new Drawable[] { animationDrawable, playerAnimation }, 0, null));
		mReimuWing1 = new ReimuWing(new Vector2(transform.position.x, transform.position.y + 30));
		mReimuWing2 = new ReimuWing(new Vector2(transform.position.x + 30, transform.position.y));
	}

	@Override
	public void Update() {
		super.Update();

		if (slow) {
			mReimuWing1.set(new Vector2(transform.position.x + 25, transform.position.y + 40), slow);
			mReimuWing2.set(new Vector2(transform.position.x - 25, transform.position.y + 40), slow);
		} else {
			mReimuWing1.set(new Vector2(transform.position.x + 60, transform.position.y), slow);
			mReimuWing2.set(new Vector2(transform.position.x - 60, transform.position.y), slow);
		}

		animationDrawable.advance(1);
		if (velocity.x > 0 && animationDrawable.getAnimation() != animationRight) {
			animationDrawable.setAnimation(animationRight);
		} else if (velocity.x < 0 && animationDrawable.getAnimation() != animationLeft) {
			animationDrawable.setAnimation(animationLeft);
		} else if (velocity.x == 0 && animationDrawable.getAnimation() != animationStay) {
			animationDrawable.setAnimation(animationStay);
		}
		existTime++;
		if (Gdx.input.isKeyPressed(KeySettings.positiveKey) && existTime % 3 == 1) {
			float rnd = MathUtils.random(-6, 6);
			ReimuBullet1.Create(transform.position.cpy().add(-12, rnd), ReimuBullet1.BulletTypeSelfFast);
			ReimuBullet1.Create(transform.position.cpy().add(12, rnd), ReimuBullet1.BulletTypeSelfFast);
			if (slow) {
				ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeWingSlow);
			}
		}
	}
}
