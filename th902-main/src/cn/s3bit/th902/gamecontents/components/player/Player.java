package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

import cn.s3bit.th902.KeySettings;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;

public abstract class Player extends Component {
	public Vector2 velocity;
	public boolean slow;
	protected Transform transform;
	protected PlayerAnimation playerAnimation;
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		velocity = new Vector2();
		playerAnimation = new PlayerAnimation();
		slow = false;
	}

	@Override
	public void Update() {
		JudgingSystem.playerJudge.set(transform.position);
		velocity.setZero();
		if (Gdx.input.isKeyPressed(KeySettings.down)) {
			velocity.add(0, -1);
		}
		if (Gdx.input.isKeyPressed(KeySettings.up)) {
			velocity.add(0, 1);
		}
		if (Gdx.input.isKeyPressed(KeySettings.left)) {
			velocity.add(-1, 0);
		}
		if (Gdx.input.isKeyPressed(KeySettings.right)) {
			velocity.add(1, 0);
		}
		slow = Gdx.input.isKeyPressed(KeySettings.shift);
		if (slow)
			velocity.nor().scl(3f);
		else
			velocity.nor().scl(6f);
		transform.position.add(velocity);
		transform.position.x = MathUtils.clamp(transform.position.x, 20, 490);
		transform.position.y = MathUtils.clamp(transform.position.y, 20, 695);
		playerAnimation.Update();
	}
	
	@Override
	public void Kill() {
		JudgingSystem.playerJudge.set(-1000, -1000);
		playerAnimation.dispose();
		super.Kill();
	}
	
	public static class PlayerAnimation extends TextureRegionDrawable implements Disposable {
		TextureRegion[] regions = new TextureRegion[8];
		int stat = 0;

		public PlayerAnimation() {
			for (int i = 0; i < regions.length; i++) {
				Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
				pixmap.setColor(0, 0, 1, 1);
				pixmap.fillCircle(32, 32, 10);
				pixmap.setColor(1, 1, 1, 1);
				pixmap.fillCircle(32, 32, 5);
				pixmap.setColor(1, 1, 1, 0.5f);
				pixmap.drawCircle(32, 32, (int) (i * 3f) + 7);
				pixmap.setColor(1, 1, 1, 1f);
				pixmap.drawCircle(32, 32, (int) (i * 3f) + 8);
				pixmap.setColor(1, 1, 1, 0.5f);
				pixmap.drawCircle(32, 32, (int) (i * 3f) + 9);
				regions[i] = new TextureRegion(new Texture(pixmap));
				pixmap.dispose();
			}
			setRegion(regions[0]);
		}

		public void Update() {
			stat++;
			setRegion(regions[stat % regions.length]);
		}

		@Override
		public void dispose() {
			for (int i = 0; i < regions.length; i++) {
				regions[i].getTexture().dispose();
			}
		}
	}
}
