package cn.s3bit.th902.gamecontents;

public interface ISpellInfoProvider {
	public float getLife();
	public int getMaxLife();
	public int getCurrentTime();
	public int getMaxTime();
	public String getDisplayedName();
}
