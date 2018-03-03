package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.graphics.Color;

import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;

public class MBGBullet extends Component {
	public BulletEmitter emitter;
	public int life;
	public Color color = new Color(1f, 1f, 1f, 1f);
	public MoveBasic moveBasic;
	
	public MBGBullet(BulletEmitter bulletEmitter) {
		emitter = bulletEmitter;
	}
	
	@Override
	public void Initialize(Entity entity) {
		moveBasic = entity.GetComponent(MoveBasic.class);
	}

	@Override
	public void Update() {
		
	}

}
