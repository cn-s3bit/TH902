package cn.s3bit.th902.gamecontents.components;

/**
 * @author Obsidianss
 * <p>
 * Component class.
 * </p>
 */
public abstract class Component {
	private boolean mIsDead = false;
	/**
	 * Update Logic.
	 */
	public abstract void Update();
	/**
	 * Do some stuff before destroying and remove the component.
	 * <br />
	 * Remember to call super.Kill() when extending.
	 */
	public void Kill() {
		mIsDead = true;
	}
	
	public boolean isDead() {
		return mIsDead;
	}
}
