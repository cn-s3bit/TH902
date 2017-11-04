package cn.s3bit.th902.player;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.KeySettings;

public abstract class PlayerEntity {
	public static PlayerEntity PlayerEntityInstance;
	public Image Drawer = null;
	public Vector2 PlayerCenter = new Vector2();
	public Vector2 PlayerVelocity = new Vector2();
	float PlayerLastX;

	public void PlayerInit() {
		Drawer = new Image();
		Drawable drawable = GetDrawable();
		Drawer.setDrawable(drawable);
		PlayerCenter.set(100, 200);
		PlayerLastX = PlayerCenter.x;
		GameMain.instance.activeStage.addActor(Drawer);
	}

	public void PlayerKill() {
		Drawer.remove();
	}

	public void PlayerUpdate() {
		PlayerCenter.add(PlayerVelocity);
		PlayerCenter.x = MathUtils.clamp(PlayerCenter.x, 1, 959);
		PlayerCenter.y = MathUtils.clamp(PlayerCenter.y, 1, 719);

		Drawer.setDrawable(GetDrawable());
		Drawer.setPosition(PlayerCenter.x, PlayerCenter.y, Align.center);
		Drawer.toFront();
	}

	protected abstract Drawable GetDrawable();

	public static class PlayerInputProcessor extends InputAdapter {

		private boolean MoveUP = false;
		private boolean MoveDown = false;
		private boolean MoveLeft = false;
		private boolean MoveRight = false;

		public Thread MoveThread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (MoveUP) {
						PlayerEntity.PlayerEntityInstance.PlayerCenter.y += 0.2;
					} else if (MoveDown) {
						PlayerEntity.PlayerEntityInstance.PlayerCenter.y -= 0.2;
					}
					
					if(MoveLeft){
						PlayerEntity.PlayerEntityInstance.PlayerCenter.x-= 0.2;
					}else if (MoveRight) {
						PlayerEntity.PlayerEntityInstance.PlayerCenter.x += 0.2;
					}

				}
			}
		};
		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			if(!MoveThread.isAlive()){
				MoveThread.start();
			}
			 switch(keycode){
			 case KeySettings.up:
				 MoveUP=true;
				 break;
			 case KeySettings.down:
				MoveDown=true;
				 break;
			 case KeySettings.left:
				MoveLeft=true;
				 break;
			 case KeySettings.right:
				MoveRight=true;
				 break;
			 }
			return super.keyDown(keycode);
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			switch (keycode) {
			case KeySettings.up:
				 MoveUP=false;
				 break;
			 case KeySettings.down:
				MoveDown=false;
				 break;
			 case KeySettings.left:
				MoveLeft=false;
				 break;
			 case KeySettings.right:
				MoveRight=false;
				 break;
			}
			return super.keyUp(keycode);
		}

	}
}
