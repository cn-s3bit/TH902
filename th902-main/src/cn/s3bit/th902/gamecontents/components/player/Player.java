package cn.s3bit.th902.gamecontents.components.player;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.KeyCodes;
import cn.s3bit.th902.KeySettings;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.ReplaySystem;
import cn.s3bit.th902.gamecontents.JudgingSystem.PlayerCollisionData;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.SerializableBitSet;

public abstract class Player extends Component {
	public Vector2 velocity;
	public boolean slow;
	protected Transform transform;
	protected PlayerAnimation playerAnimation;
	public static boolean Bomb = false;
	public int bombFrames = 0;
	public int ChaosTime = 0;
	public boolean Chaos = false;
	public static boolean onLine = false;
	public boolean needNewBombEntity = true;
	public int bombTimeCount = 0;
	public boolean wingShoot = false;
	public SerializableBitSet actionBits = new SerializableBitSet(16);
	public int existTime = 0;
	public int lastPower = 0;

	public BaseWing wing1 = null;
	public BaseWing wing2 = null;
	public BaseWing wing3 = null;
	public BaseWing wing4 = null;

	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		velocity = new Vector2();
		playerAnimation = new PlayerAnimation();
		slow = false;
	}

	public abstract void typeABomb();

	public abstract void typeBBomb();

	public abstract void setBombTime();

	public abstract void invokeDeathEffect(int deg);

	@Override
	public void Update() {
		try {
			if (ReplaySystem.replayMode) {
				actionBits.loadFrom(ReplaySystem.playerAction.reader());
			} else {
				KeyCodes.set(actionBits, KeyCodes.down, Gdx.input.isKeyPressed(KeySettings.down));
				KeyCodes.set(actionBits, KeyCodes.up, Gdx.input.isKeyPressed(KeySettings.up));
				KeyCodes.set(actionBits, KeyCodes.left, Gdx.input.isKeyPressed(KeySettings.left));
				KeyCodes.set(actionBits, KeyCodes.right, Gdx.input.isKeyPressed(KeySettings.right));
				KeyCodes.set(actionBits, KeyCodes.positiveKey, Gdx.input.isKeyPressed(KeySettings.positiveKey));
				KeyCodes.set(actionBits, KeyCodes.negativeKey, Gdx.input.isKeyPressed(KeySettings.negativeKey));
				KeyCodes.set(actionBits, KeyCodes.shift, Gdx.input.isKeyPressed(KeySettings.shift));
				actionBits.writeTo(ReplaySystem.playerAction.writer());
			}
		} catch (IOException e) {
		}

		existTime++;
		onLine = transform.position.y >= 500;
		JudgingSystem.playerJudge.set(transform.position);
		PlayerCollisionData collision = JudgingSystem.playerCollision();
		if (Chaos) {
			ChaosTime--;
			if (ChaosTime < 0) {
				Chaos = false;
			}
		} else {
			if (collision != null) {
				collision.judgeCallback.onCollide();
				invokeDeathEffect(
						collision.movement == null ? 0 : (int) collision.movement.getCurrentVelocity().angle());
				Chaos = true;
				ChaosTime = 120;
			}
		}
		velocity.setZero();
		if (KeyCodes.mask(actionBits, KeyCodes.down)) {
			velocity.add(0, -1);
		} else if (KeyCodes.mask(actionBits, KeyCodes.up)) {
			velocity.add(0, 1);
		}
		if (KeyCodes.mask(actionBits, KeyCodes.left)) {
			velocity.add(-1, 0);
		} else if (KeyCodes.mask(actionBits, KeyCodes.right)) {
			velocity.add(1, 0);
		}
		slow = KeyCodes.mask(actionBits, KeyCodes.shift);
		if (slow) {
			velocity.nor().scl(3f);
		} else {
			velocity.nor().scl(6f);
		}
		useBomb();
		transform.position.add(velocity);
		transform.position.x = MathUtils.clamp(transform.position.x, 30, 540);
		transform.position.y = MathUtils.clamp(transform.position.y, 48, 680);
		playerAnimation.Update(!slow);
		try {
			if (ReplaySystem.replayMode) {
				transform.position.set(ReplaySystem.playerAction.reader().readFloat(), ReplaySystem.playerAction.reader().readFloat());
			} else {
				ReplaySystem.playerAction.writer().writeFloat(transform.position.x);
				ReplaySystem.playerAction.writer().writeFloat(transform.position.y);
			}
		} catch (IOException e) {
		}
	}

	public void useBomb() {
		if (PlayerDeathEffect.getTimeLeft() == 49) {
			FightScreen.playerCount--;
			FightScreen.bombCount = 3;
			transform.position.set(280f, 100f);
		}
		if (KeyCodes.mask(actionBits, KeyCodes.negativeKey) && !Bomb && FightScreen.bombCount > 0) {
			if (PlayerDeathEffect.getTimeLeft() > 0 && PlayerDeathEffect.getTimeLeft() < 50)
				return;
			PlayerDeathEffect.timeleft = Math.min(PlayerDeathEffect.timeleft, 1);
			Bomb = true;
			bombTimeCount = 0;
			setBombTime();
			ChaosTime = bombFrames + 120;
			Chaos = true;
			FightScreen.bombCount--;
		}
		if (Bomb) {
			bombTimeCount++;
			if (needNewBombEntity) {
				if (FightScreen.PlayerType == FightScreen.PlayerTypeA) {
					typeABomb();
				} else {
					typeBBomb();
				}
			}
			bombFrames--;
			if (bombFrames == 0) {
				Bomb = false;
				needNewBombEntity = true;
			}
		}
	}

	@Override
	public void Kill() {
		JudgingSystem.playerJudge.set(-1000, -1000);
		playerAnimation.dispose();
		super.Kill();
	}

	public static class PlayerAnimation extends TextureRegionDrawable implements Disposable {
		TextureRegion[] regions = new TextureRegion[8];
		TextureRegion nullRegion = null;
		int stat = 0;

		public PlayerAnimation() {
			nullRegion = new TextureRegion(new Texture(new Pixmap(1, 1, Pixmap.Format.RGBA8888)));
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

		public void Update(boolean black) {
			if (black) {
				setRegion(nullRegion);
			} else {
				stat++;
				setRegion(regions[stat % regions.length]);
			}
		}

		@Override
		public void dispose() {
			for (int i = 0; i < regions.length; i++) {
				regions[i].getTexture().dispose();
			}
		}
	}
}
