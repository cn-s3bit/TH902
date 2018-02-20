package cn.s3bit.th902.gamecontents;

import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.utils.Yield;

public abstract class DanmakuScene extends Component {
	public Yield yield = new Yield();
	@Override
	public void Update() {
		yield.yield();
		if (yield.isFinished()) {
			Kill();
		}
	}
}
