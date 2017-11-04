package cn.s3bit.th902.player;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;

public class PlayerReimu extends PlayerEntity {

	public static ReimuEntity rm;
	public PlayerAnimation animation = null;

	@Override
	public void PlayerInit() {
		// TODO Auto-generated method stub

		super.PlayerInit();
		PlayerEntityInstance = this;
		Drawer.setSize(32, 32);
		rm = new ReimuEntity();
		rm.PlayerInit();
	}

	@Override
	protected Drawable GetDrawable() {
		if (animation == null)
			animation = new PlayerAnimation();
		return animation;
	}

	@Override
	public void PlayerUpdate() {
		// TODO Auto-generated method stub
		animation.Update();
		rm.PlayerUpdate();
		super.PlayerUpdate();
	}

	@Override
	public void PlayerKill() {
		// TODO Auto-generated method stub
		super.PlayerKill();
		rm.Drawer.remove();
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

	public static class ReimuEntity extends PlayerEntity {
		public HashMap<String, Drawable> playerAnim = new HashMap<String, Drawable>(30);
		TextureRegion[][] tmp;
		Texture walkSheet;
		int FRAME_COLS = 8;
		int FRAME_ROWS = 3;
		private Drawable d = null;
		protected int PlayerAnimTime = 0;

		@Override
		protected Drawable GetDrawable() {
			// return Resources.NPCDrawables.get("enemy2");
			return null;
		}
		
		protected Drawable GetNotMoveAnim() {
			// TODO Auto-generated method stub
			switch (PlayerAnimTime % 32) {
			case 1:
				d = playerAnim.get("anim0");
				break;
			case 5:
				d = playerAnim.get("anim1");
				break;
			case 9:
				d = playerAnim.get("anim2");
				break;
			case 13:
				d = playerAnim.get("anim3");
				break;
			case 17:
				d = playerAnim.get("anim4");
				break;
			case 21:
				d = playerAnim.get("anim5");
				break;
			case 25:
				d = playerAnim.get("anim6");
				break;
			case 29:
				d = playerAnim.get("anim7");
				break;
			}
			return d;
		}

	
		protected Drawable GetLeftMoveAnim() {
			// TODO Auto-generated method stub
		if(PlayerAnimTime>29){
			PlayerAnimTime=20;
		}
			switch (PlayerAnimTime % 32) {
			case 1:
				d = playerAnim.get("anim8");
				break;
			case 5:
				d = playerAnim.get("anim9");
				break;
			case 9:
				d = playerAnim.get("anim10");
				break;
			case 13:
				d = playerAnim.get("anim11");
				break;
			case 17:
				d = playerAnim.get("anim12");
				break;
			case 21:
				d = playerAnim.get("anim13");
				break;
			case 25:
				d = playerAnim.get("anim14");
				break;
			case 29:
				d = playerAnim.get("anim15");
				break;
			}
			return d;
		}

	
		protected Drawable GetRightMoveAnim() {
			if(PlayerAnimTime>29){
				PlayerAnimTime=20;
			}
			// TODO Auto-generated method stub
			switch (PlayerAnimTime % 32) {
			case 1:
				d = playerAnim.get("anim16");
				break;
			case 5:
				d = playerAnim.get("anim17");
				break;
			case 9:
				d = playerAnim.get("anim18");
				break;
			case 13:
				d = playerAnim.get("anim19");
				break;
			case 17:
				d = playerAnim.get("anim20");
				break;
			case 21:
				d = playerAnim.get("anim21");
				break;
			case 25:
				d = playerAnim.get("anim22");
				break;
			case 29:
				d = playerAnim.get("anim23");
				break;
			}
			return d;
		}
		@Override
		public void PlayerInit() {
			// TODO Auto-generated method stub
			super.PlayerInit();
			walkSheet = new Texture(Gdx.files.internal("resources/Reimu.png"));
			tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
			byte index = 0;
			for (byte i = 0; i < FRAME_ROWS; i++) {
				for (byte j = 0; j < FRAME_COLS; j++) {
					TextureRegionDrawable drawable = new TextureRegionDrawable(tmp[i][j]);
					playerAnim.put("anim" + index, drawable);
					index++;
				}
			}
			d=playerAnim.get("anim0");
			Drawer.setSize(67, 92);
		}

		@Override
		public void PlayerUpdate() {
			// TODO Auto-generated method stub
			PlayerAnimTime++;
			//Drawer.setDrawable(GetDrawable());
			if (PlayerEntity.PlayerEntityInstance.PlayerCenter.x > PlayerLastX) {
	            PlayerLastX = PlayerEntity.PlayerEntityInstance.PlayerCenter.x;
	            Drawer.setDrawable(GetRightMoveAnim());

	        } else if (PlayerEntityInstance.PlayerCenter.x < PlayerLastX) {
	            PlayerLastX = PlayerEntity.PlayerEntityInstance.PlayerCenter.x;
	            Drawer.setDrawable(GetLeftMoveAnim());
	        } else if (PlayerEntityInstance.PlayerCenter.x == PlayerLastX) {
	            PlayerLastX = PlayerEntity.PlayerEntityInstance.PlayerCenter.x;
	            Drawer.setDrawable(GetNotMoveAnim());
	        }else {
				Drawer.setDrawable(GetDrawable());
			}
			PlayerCenter.set(PlayerEntityInstance.PlayerCenter);
			Drawer.setPosition(PlayerCenter.x, PlayerCenter.y, Align.center);
		}

		

	}

	
}