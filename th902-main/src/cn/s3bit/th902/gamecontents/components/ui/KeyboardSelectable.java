package cn.s3bit.th902.gamecontents.components.ui;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.utils.LinkedNode;

public class KeyboardSelectable extends Component{
	protected int mIndex;
	public LinkedNode<KeyboardSelectable> linkedNode;
	public KeyboardSelectable(int index) {
		this(index, null);
	}
	public KeyboardSelectable(int index, Runnable action) {
		mIndex = index;
		linkedNode = new LinkedNode<>(this);
	}
	@Override
	public void Initialize(Entity entity) {
		
	}

	@Override
	public void Update() {
		
	}
	
	@Override
	public void Kill() {
		linkedNode.remove();
		super.Kill();
	}
}
