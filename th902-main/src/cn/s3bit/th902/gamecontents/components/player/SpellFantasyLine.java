package cn.s3bit.th902.gamecontents.components.player;

import java.util.Set;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.ParticleSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class SpellFantasyLine extends Component {
	public final PlayerReimu reimu;
	public FantasySealCircle[] circles = new FantasySealCircle[8];
	
	public class FantasySealCircle extends Component {
		public Vector2 dir = new Vector2();
		public Vector2 relativePos = new Vector2();
		private Entity mEntity;
		private Transform mTransform;
		public ParticleEffect particleEffect = new ParticleEffect();
		public MoveBasic moveBasic;
		
		public FantasySealCircle(float angle) {
			String[] colors = {"Pink", "Purple", "Orange", "Green", "Blue"};
			particleEffect.load(Gdx.files.internal("resources/Particles/FantasySeal/FantasySealLine" + colors[MathUtils.random(4)] + ".dat"), Gdx.files.internal("resources/Particles/"));
			relativePos.set(180, 0).rotate(angle);
		}
		
		@Override
		public void Initialize(Entity entity) {
			ParticleSystem.register(particleEffect);
			mEntity = entity;
			mTransform = entity.GetComponent(Transform.class);
			
			particleEffect.start();
			mTransform.position.set(reimu.transform.position).add(relativePos);
			updateBiases();
			
			mEntity.AddComponent(moveBasic = new MoveBasic(0, 0));
		}
		
		Circle judge = new Circle();
		Vector2 targetP = new Vector2();
		@Override
		public void Update() {
			updateBiases();
			
			if (!FightScreen.isOutOfScreen(mTransform.position)) {
				if (JudgingSystem.calculateNearestChaseable(mTransform.position) != null) {
					targetP.set(JudgingSystem.calculateNearestChaseable(mTransform.position).getKey().getData());
				}
				else {
					targetP.set(0, 100000);
				}
				moveBasic.acc.set(targetP).sub(mTransform.position).nor().scl(0.3f);
			}
			
			if (reimu.bombFrames <= 1) {
				particleEffect.setDuration(0);
				if (particleEffect.isComplete()) {
					mEntity.Destroy();
				}
				return;
			}
			
			Set<Entry<ImmutableWrapper<Circle>, IJudgeCallback>> set = JudgingSystem.enemyJudges.entrySet();
			for (Entry<ImmutableWrapper<Circle>, IJudgeCallback> entry : set) {
				if (entry.getValue().canHurt() && Intersector.overlaps(entry.getKey().getData(), judge)) {
					entry.getValue().onHurt(5);
				}
			}
			for (final Entry<ImmutableWrapper<Vector2>, Entity> entry : JudgingSystem.clearByBombs.entrySet()) {
				if (judge.contains(entry.getKey().getData())) {
					Entity.postUpdate.add(() -> { entry.getValue().Destroy(); });
				}
			}
		}
		
		public void updateBiases() {
			particleEffect.setPosition(mTransform.position.x, mTransform.position.y);
			judge.set(mTransform.position, 60);
		}
		
		@Override
		public void Kill() {
			ParticleSystem.unregister(particleEffect);
			particleEffect.dispose();
			super.Kill();
		}
	}
	
	public SpellFantasyLine(PlayerReimu reimu) {
		this.reimu = reimu;
	}
	
	Entity entity;

	@Override
	public void Initialize(Entity entity) {
		Transform transform = reimu.transform;
		this.entity = entity;
		for (int i=0; i<360; i+=45) {
			Entity cir = Entity.Create();
			cir.AddComponent(new Transform(transform.position.cpy()));
			cir.AddComponent(new FantasySealCircle(i));
		}
	}
	
	Vector3 totalTranslation = new Vector3();
	Vector3 currentTranslation = new Vector3();

	@Override
	public void Update() {
		if (reimu.bombFrames > 0) {
			/*if (reimu.bombFrames % 3 == 0) {
				currentTranslation.set(MathUtils.random(-30, 30), MathUtils.random(-30, 30), 0);
				GameMain.instance.activeStage.getCamera().translate(currentTranslation);
				totalTranslation.add(currentTranslation);
				currentTranslation.set(totalTranslation);
				totalTranslation.x = MathUtils.clamp(totalTranslation.x, -30, 30);
				totalTranslation.y = MathUtils.clamp(totalTranslation.y, -30, 30);
				GameMain.instance.activeStage.getCamera().translate(currentTranslation.sub(totalTranslation).scl(-1));
			}*/
		}
		else {
			//GameMain.instance.activeStage.getCamera().translate(totalTranslation.scl(-1));
			entity.Destroy();
		}
	}

}
