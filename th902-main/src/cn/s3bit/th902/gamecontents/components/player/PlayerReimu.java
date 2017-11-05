package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.ImageGroupRenderer;
import cn.s3bit.th902.utils.AnimationDrawable;

public class PlayerReimu extends Player {
	Animation<TextureRegion> animationStay;
	Animation<TextureRegion> animationLeft;
	Animation<TextureRegion> animationRight;
	AnimationDrawable animationDrawable = new AnimationDrawable();
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
		entity.AddComponent(new ImageGroupRenderer(new Drawable[] { animationDrawable, playerAnimation }, 1, null));
	}
	@Override
	public void Update() {
		super.Update();
		animationDrawable.advance(1);
		if (velocity.x > 0 && animationDrawable.getAnimation() != animationRight) {
			animationDrawable.setAnimation(animationRight);
		}
		else if (velocity.x < 0 && animationDrawable.getAnimation() != animationLeft) {
			animationDrawable.setAnimation(animationLeft);
		}
		else if (velocity.x == 0 && animationDrawable.getAnimation() != animationStay) {
			animationDrawable.setAnimation(animationStay);
		}
	}
}
