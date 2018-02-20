package cn.s3bit.th902.gamecontents;

public interface IItemGetLogic {
	public static final IItemGetLogic NONE = new IItemGetLogic() {
		@Override
		public boolean onGet() {
			return true;
		}
	};
	public boolean onGet();
}
