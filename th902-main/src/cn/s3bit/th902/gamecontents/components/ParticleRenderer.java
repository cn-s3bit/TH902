package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.utils.ParticleActor;

public class ParticleRenderer extends Component {
	Entity entity;
	public ParticleActor actor;
	public ParticleRenderer(ParticleEffect effect) {
		actor = new ParticleActor(effect);
	}
	
	@Override
	public void Initialize(Entity entity) {
		this.entity = entity;
		GameMain.instance.activeStage.addActor(actor);
	}

	@Override
	public void Update() {
		
	}

	@Override
	public void Kill() {
		actor.remove();
		super.Kill();
	}
}
