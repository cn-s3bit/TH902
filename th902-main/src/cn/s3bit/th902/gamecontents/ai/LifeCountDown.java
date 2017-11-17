package cn.s3bit.th902.gamecontents.ai;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;

public class LifeCountDown extends Component {
	public int countDown;
	protected Entity entity;
	/**
	 * @param time
	 * Time before killing the entity, in frames.
	 */
	public LifeCountDown(int time) {
		countDown = time;
	}

	@Override
	public void Initialize(Entity entity) {
		this.entity = entity;
	}

	@Override
	public void Update() {
		countDown--;
		if (countDown <= 0) {
			entity.Destroy();
		}
	}
}
