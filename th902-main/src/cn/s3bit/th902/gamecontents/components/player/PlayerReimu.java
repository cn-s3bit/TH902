package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import cn.s3bit.th902.FightScreen;
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
	private boolean mWingShoot = false;
	private boolean mShoot = false;
	private int mType;
	private ImageGroupRenderer mRenderer = null;

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
		entity.AddComponent(mRenderer = new ImageGroupRenderer(new Drawable[] { animationDrawable, playerAnimation }, 0, null));
		mReimuWing1 = new ReimuWing(new Vector2(transform.position.x, transform.position.y + 20), 4f, mType);
		mReimuWing2 = new ReimuWing(new Vector2(transform.position.x, transform.position.y), -4f, mType);
	}

	@Override
	public void Update() {
		super.Update();
		mRenderer.setDepth(0);
		if (slow) {
			mReimuWing1.set(new Vector2(transform.position.x + 25, transform.position.y + 25), slow, mWingShoot);
			mReimuWing2.set(new Vector2(transform.position.x - 25, transform.position.y + 25), slow, mWingShoot);
		} else {
			mReimuWing1.set(new Vector2(transform.position.x + 50, transform.position.y), slow, mWingShoot);
			mReimuWing2.set(new Vector2(transform.position.x - 50, transform.position.y), slow, mWingShoot);
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
		mWingShoot = Gdx.input.isKeyPressed(KeySettings.positiveKey) && existTime % 5 == 1 && bombFrames <= 60;
		mShoot = Gdx.input.isKeyPressed(KeySettings.positiveKey) && existTime % 3 == 1 && bombFrames <= 60;
		if (mType == FightScreen.PlayerTypeA) {
			if (slow) {
				if (mShoot) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 40), ReimuBullet1.BulletTypeSelfSlow);
				}
			} else {
				if (mShoot) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 40), ReimuBullet1.BulletTypeSelfFast);
				}
			}
		} else {
			if (slow) {
				if (mWingShoot) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeWingSlowStraight);
					mWingShoot = false;
				}
				if (mShoot) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 24), ReimuBullet1.BulletTypeSelfSlow);
				}
			} else {
				if (mShoot) {
					ReimuBullet1.Create(transform.position.cpy().add(0, 6), ReimuBullet1.BulletTypeSelfFast);
				}
			}
		}
	}

	@Override
	public void typeAFastBomb() {
		needNewBombEntity = false;
		Entity.Create().AddComponent(new SpellFantasyLine(this));
	}

	@Override
	public void typeASlowBomb() {
		needNewBombEntity = false;
		Entity.Create().AddComponent(new SpellFantasySeal(this));
	}

	@Override
	public void typeBFastBomb() {
		needNewBombEntity = false;
		Bombs.Create(transform.position.cpy(), Bombs.TypeReimuBFast, new Vector2(0, 14), new Vector2(0, -0.2f), -90, 0,
				0);
		Bombs.Create(transform.position.cpy(), Bombs.TypeReimuBFast, new Vector2(-14, 0), new Vector2(0.2f, 0), 0, 0,
				0);
		Bombs.Create(transform.position.cpy(), Bombs.TypeReimuBFast, new Vector2(14, 0), new Vector2(-0.2f, 0), 180, 0,
				0);
		Bombs.Create(transform.position.cpy(), Bombs.TypeReimuBFast, new Vector2(0, -14), new Vector2(0, 0.2f), 90, 0,
				0);
		/*
		 * if (bombTimeCount==10) { Bombs.Create(transform.position.cpy(),
		 * Bombs.TypeReimuBFast,new Vector2(0,5),0,0,0); }else if
		 * (bombTimeCount==20) { Bombs.Create(transform.position.cpy().sub(30,
		 * 0), Bombs.TypeReimuBFast,new Vector2(-5,5),0,0,0);
		 * Bombs.Create(transform.position.cpy().add(30, 0),
		 * Bombs.TypeReimuBFast,new Vector2(5,5),0,0,0); }else if
		 * (bombTimeCount==30) { Bombs.Create(transform.position.cpy().sub(60,
		 * 0), Bombs.TypeReimuBFast,new Vector2(-5,0),0,0,0);
		 * Bombs.Create(transform.position.cpy().add(60, 0),
		 * Bombs.TypeReimuBFast,new Vector2(5,0),0,0,0); bombTimeCount=0; }
		 */
	}

	@Override
	public void typeBSlowBomb() {
		needNewBombEntity = false;
		Bombs.Create(transform.position, Bombs.TypeReimuBSlow, null, null, 0, 0, 0.995f);
		Bombs.Create(transform.position, Bombs.TypeReimuBSlow, null, null, 10, 0.07f, 1);
		Bombs.Create(transform.position, Bombs.TypeReimuBSlow, null, null, -10, -0.07f, 1);
	}

	@Override
	public void setBombTime() {
		if (ifSlowBomb) {
			bombFrames = FightScreen.PlayerType == FightScreen.PlayerTypeA ? 360 : 180;
		} else {
			bombFrames = FightScreen.PlayerType == FightScreen.PlayerTypeA ? 180 : 600;
		}
	}

}
