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

import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.ParticleSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.ImmutableWrapper;
import cn.s3bit.th902.utils.Yield;

public class SpellFantasySeal extends Component {
	public final PlayerReimu reimu;
	public FantasySealCircle[] circles = new FantasySealCircle[8];
	
	public class FantasySealCircle extends Component {
		public Vector2 dir = new Vector2();
		public Vector2 relativePos = new Vector2();
		public Yield yield = new Yield();
		private Entity mEntity;
		private Transform mTransform;
		public ParticleEffect particleEffect = new ParticleEffect();
		
		private float mRotateSpeed = 6;
		public boolean isChasing = false;
		
		public FantasySealCircle(float angle) {
			String[] colors = {"Pink", "Purple", "Orange", "Green", "Blue"};
			particleEffect.load(Gdx.files.internal("resources/Particles/FantasySeal/FantasySealCircle" + colors[MathUtils.random(4)] + ".dat"), Gdx.files.internal("resources/Particles/"));
			particleEffect.scaleEffect(2);
			dir.set(1, 0).rotate(angle).scl(3);
		}
		
		@Override
		public void Initialize(Entity entity) {
			ParticleSystem.register(particleEffect);
			mEntity = entity;
			mTransform = entity.GetComponent(Transform.class);
			yield.append(() -> {
				relativePos.add(dir);
				dir.rotate(mRotateSpeed);
				relativePos.rotate(mRotateSpeed);
			}, 60);
			yield.append(() -> {
				relativePos.rotate(mRotateSpeed);
			}, 90);
			
			particleEffect.start();
			updateBiases();
		}
		Circle judge = new Circle();
		private boolean hasExploded = false;
		@Override
		public void Update() {
			updateBiases();
			if (yield.isFinished()) {
				relativePos.rotate(mRotateSpeed * 0.8f);
				if (isChasing && !hasExploded && particleEffect.getEmitters().first().durationTimer < 4500) {
					Entry<ImmutableWrapper<Vector2>, IJudgeCallback> nearest = JudgingSystem.calculateNearestChaseable(mTransform.position);
					if (nearest != null) {
						GameHelper.chase(mTransform.position, nearest.getKey().getData(), 4);
						if (nearest.getKey().getData().dst2(mTransform.position) <= 3600) {
							explode();
							hasExploded = true;
						}
					}
					mTransform.position.add(dir.set(relativePos).rotate90(1).nor().scl(3));
				}
				else if (!isChasing)
					isChasing = MathUtils.randomBoolean(0.4f);
				if (particleEffect.isComplete() || reimu.bombFrames <= 1) {
					yield.append(() -> { mEntity.Destroy(); }, 1);
				}
			}
			else 
				yield.yield();
			
			if (!hasExploded) {
				Set<Entry<ImmutableWrapper<Circle>, IJudgeCallback>> set = JudgingSystem.enemyJudges.entrySet();
				for (Entry<ImmutableWrapper<Circle>, IJudgeCallback> entry : set) {
					if (entry.getValue().canHurt() && Intersector.overlaps(entry.getKey().getData(), judge)) {
						entry.getValue().onHurt(1);
					}
				}
				for (final Entry<ImmutableWrapper<Vector2>, Entity> entry : JudgingSystem.clearByBombs.entrySet()) {
					if (judge.contains(entry.getKey().getData())) {
						Entity.postUpdate.add(() -> { entry.getValue().Destroy(); });
					}
				}
			}
		}
		
		public void updateBiases() {
			if (!isChasing) {
				mTransform.position.set(reimu.transform.position).add(relativePos);
			}
			if (!hasExploded) {
				particleEffect.setPosition(mTransform.position.x, mTransform.position.y);
			}
			judge.set(mTransform.position, 120);
		}
		
		@Override
		public void Kill() {
			ParticleSystem.unregister(particleEffect);
			particleEffect.dispose();
			super.Kill();
		}
		
		public void explode() {
			ParticleSystem.unregister(particleEffect);
			particleEffect.dispose();
			particleEffect = new ParticleEffect();
			particleEffect.load(Gdx.files.internal("resources/Particles/FantasySeal/FantasySealCircleExplosion.dat"), Gdx.files.internal("resources/Particles/"));
			particleEffect.setPosition(mTransform.position.x, mTransform.position.y);
			particleEffect.scaleEffect(2);
			particleEffect.start();
			ParticleSystem.register(particleEffect);
			Set<Entry<ImmutableWrapper<Circle>, IJudgeCallback>> set = JudgingSystem.enemyJudges.entrySet();
			for (Entry<ImmutableWrapper<Circle>, IJudgeCallback> entry : set) {
				if (entry.getValue().canHurt() && Intersector.overlaps(entry.getKey().getData(), judge)) {
					entry.getValue().onHurt(150);
				}
			}
		}
	}
	
	public SpellFantasySeal(PlayerReimu reimu) {
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
