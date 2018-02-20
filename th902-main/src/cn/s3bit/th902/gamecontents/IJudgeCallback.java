package cn.s3bit.th902.gamecontents;

public interface IJudgeCallback {
	public static final IJudgeCallback NONE = new IJudgeCallback() {
		
	};
	default public float getDamage() {
		return 1;
	}
	default public boolean canHurt() {
		return false;
	}
	default public void onHurt(float damage) {
		return;
	}
	default public void onCollide() {
		return;
	}
	default public float getBombResist() {
		return 1f;
	}
}
