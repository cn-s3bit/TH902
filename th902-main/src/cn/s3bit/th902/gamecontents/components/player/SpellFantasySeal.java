package cn.s3bit.th902.gamecontents.components.player;

import java.util.Set;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

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
			particleEffect.load(Gdx.files.internal("resources/Particles/FantasySealCircle.dat"), Gdx.files.internal("resources/Particles/"));
			dir.set(1, 0).rotate(angle).scl(12);
		}
		
		@Override
		public void Initialize(Entity entity) {
			ParticleSystem.register(particleEffect);
			mEntity = entity;
			mTransform = entity.GetComponent(Transform.class);
			yield.append(() -> {
				relativePos.add(dir);
			}, 7);
			yield.append(() -> {
				relativePos.rotate(mRotateSpeed);
			}, 120);
			
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
				if (isChasing && !hasExploded) {
					Entry<ImmutableWrapper<Vector2>, IJudgeCallback> nearest = JudgingSystem.calculateNearestChaseable(mTransform.position);
					if (nearest != null) {
						GameHelper.chase(mTransform.position, nearest.getKey().getData(), 3);
						if (nearest.getKey().getData().dst2(mTransform.position) <= 600) {
							explode();
							hasExploded = true;
						}
					}
					mTransform.position.add(dir.set(relativePos).rotate90(1).nor().scl(3));
				}
				else
					isChasing = MathUtils.randomBoolean(0.02f) || reimu.bombFrames < 60;
				if (particleEffect.isComplete()) {
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
			judge.set(mTransform.position, 30);
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
			particleEffect.load(Gdx.files.internal("resources/Particles/FantasySealCircleExplosion.dat"), Gdx.files.internal("resources/Particles/"));
			particleEffect.setPosition(mTransform.position.x, mTransform.position.y);
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

	@Override
	public void Initialize(Entity entity) {
		Transform transform = reimu.transform;
		for (int i=0; i<360; i+=45) {
			Entity cir = Entity.Create();
			cir.AddComponent(new Transform(transform.position.cpy()));
			cir.AddComponent(new FantasySealCircle(i));
		}
	}

	@Override
	public void Update() {
		
	}

}
