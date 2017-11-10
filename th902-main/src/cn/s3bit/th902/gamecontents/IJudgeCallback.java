package cn.s3bit.th902.gamecontents;

public interface IJudgeCallback {
	public static final IJudgeCallback NONE = new IJudgeCallback() {
		@Override
		public void onCollide() {
			return;
		}
	};
	public void onCollide();
}
