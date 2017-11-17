package cn.s3bit.th902.gamecontents.components;

import cn.s3bit.th902.gamecontents.Entity;

public class LambdaComponent extends Component {
	
	public final Runnable runnable;
	public final int delay, interval;
	private int mTimer = 0;
	public LambdaComponent(Runnable runnable) {
		this(runnable, -1);
	}
	
	public LambdaComponent(Runnable runnable, int interval) {
		this(runnable, 0, interval);
	}
	
	public LambdaComponent(Runnable runnable, int delay, int interval) {
		this.runnable = runnable;
		this.delay = delay;
		this.interval = interval;
	}

	@Override
	public void Initialize(Entity entity) {
		
	}

	@Override
	public void Update() {
		if (mTimer >= delay) {
			if (interval <= 0) {
				runnable.run();
				Kill();
			}
			else if ((mTimer - delay) % interval == 0) {
				runnable.run();
			}
		}
		mTimer++;
	}
}
