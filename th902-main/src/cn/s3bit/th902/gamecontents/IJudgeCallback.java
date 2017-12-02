package cn.s3bit.th902.gamecontents;

public interface IJudgeCallback {
	public static final IJudgeCallback NONE = new IJudgeCallback() {
		
	};
	default public int getDamage() {
		return 1;
	}
	default public boolean canHurt() {
		return false;
	}
	default public void onHurt(int damage) {
		return;
	}
	default public void onCollide() {
		return;
	}
}
