package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.KeyCodes;
import cn.s3bit.th902.KeySettings;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.contents.THSoundEffects;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageGroupRenderer;
import cn.s3bit.th902.utils.AnimationDrawable;

public class PlayerReimu extends Player {

	Animation<TextureRegion> animationStay;
	Animation<TextureRegion> animationLeft;
	Animation<TextureRegion> animationRight;
	AnimationDrawable animationDrawable = new AnimationDrawable();
	public int existTime;
	public static boolean ReimuWingShoot = false;
	private boolean mShoot = false;
	private int mType;
	private ImageGroupRenderer mRenderer = null;
	public float alpha = 1;

	public PlayerReimu(int type) {
		mType = type;
		Texture texture = ResourceManager.textures.get("Reimu");
		TextureRegion[][] splitted = TextureRegion.split(texture, texture.getWidth() / 8, texture.getHeight() / 3);
		animationStay = new Animation<TextureRegion>(5, splitted[0]);
		animationStay.setPlayMode(PlayMode.LOOP);
		animationLeft = new Animation<TextureRegion>(4, splitted[1]);
		animationLeft.setPlayMode(PlayMode.LOOP);
		animationRight = new Animation<TextureRegion>(4, splitted[2]);
		animationRight.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void Initialize(Entity entity) {
		super.Initialize(entity);
		animationDrawable.setAnimation(animationStay);
		existTime = 0;
		entity.AddComponent(
				mRenderer = new ImageGroupRenderer(new Drawable[] { animationDrawable, playerAnimation }, 0, null));
		mRenderer.attachToGroup(FightScreen.drawingLayers.entity2);
		new ReimuWing(transform.position.cpy(), 0);
		new ReimuWing(transform.position.cpy(), 1);
	}

	@Override
	public void Update() {
		super.Update();
		if (deathEffect == null) {
			alpha = Chaos ? 0.2f + Math.abs(Interpolation.linear.apply(-0.8f, 0.8f, (existTime % 30) / 30f)) : 1;
		} else {
			if (deathEffect.isDead()) {
				deathEffect = null;
				Chaos = true;
				ChaosTime = ChaosTime > 120 ? ChaosTime : 120;
				existTime = 15;
			} else {
				mRenderer.group.setColor(1, 1, 1, 0);
			}
			return;
		}
		mRenderer.group.setColor(1, 1, 1, alpha);
		animationDrawable.advance(1);
		if (velocity.x > 0 && animationDrawable.getAnimation() != animationRight) {
			animationDrawable.setAnimation(animationRight);
		} else if (velocity.x < 0 && animationDrawable.getAnimation() != animationLeft) {
			animationDrawable.setAnimation(animationLeft);
		} else if (velocity.x == 0 && animationDrawable.getAnimation() != animationStay) {
			animationDrawable.setAnimation(animationStay);
		}
		existTime++;
		ReimuWingShoot = KeyCodes.mask(actionBits, KeyCodes.positiveKey) && wingShoot() && bombFrames <= 60;
		mShoot = KeyCodes.mask(actionBits, KeyCodes.positiveKey) && existTime % 3 == 1 && bombFrames <= 60;
		if (mShoot && existTime % 9 == 1) {
			THSoundEffects.PlShoot.sound.play(0.6f);
		}
		if (mType == FightScreen.PlayerTypeA) {
			if (mShoot) {
				if (slow) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 60), ReimuBullet1.BulletTypeSelfSlow);
				} else {
					ReimuBullet1.Create(transform.position.cpy().add(0, 40), ReimuBullet1.BulletTypeSelfFast);
				}
			}
		} else {
			if (ReimuWingShoot && slow) {
				ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeWingSlowStraight);
			}
			if (mShoot) {
				if (slow) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeSelfSlow);
				} else {
					ReimuBullet1.Create(transform.position.cpy().add(0, 6), ReimuBullet1.BulletTypeSelfFast);
				}
			}
		}
	}

	public boolean wingShoot() {
		return existTime % 5 == 1;
		/*
		 * if (FightScreen.powerCount > 99) { return existTime % 4 == 1; } else
		 * if (FightScreen.powerCount > 79) { return existTime % 7 == 1; } else
		 * if (FightScreen.powerCount > 59) { return existTime % 15 == 1; } else
		 * if (FightScreen.powerCount > 39) { return existTime % 20 == 1; } else
		 * if (FightScreen.powerCount > 19) { return existTime % 25 == 1; } else
		 * { return existTime % 30 == 1; }
		 */
	}

	@Override
	public void typeABomb() {
		needNewBombEntity = false;
		Entity.Create().AddComponent(new SpellFantasySeal(this));
	}

	@Override
	public void typeBBomb() {
		needNewBombEntity = false;
	}

	@Override
	public void setBombTime() {
		bombFrames = FightScreen.PlayerType == FightScreen.PlayerTypeA ? 360 : 180;
	}

	PlayerDeathEffect deathEffect = null;

	@Override
	public void invokeDeathEffect(int deg) {
		Entity deathEffectManager = Entity.Create();
		deathEffectManager
				.AddComponent(deathEffect = new PlayerDeathEffect(ResourceManager.textures.get("ReimuOneFrame"), deg));
	}
}
