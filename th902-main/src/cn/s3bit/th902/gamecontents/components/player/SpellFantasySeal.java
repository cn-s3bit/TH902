package cn.s3bit.th902.gamecontents.components.player;

import java.util.Set;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.contents.THSoundEffects;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.JudgingSystem.PlayerCollisionData;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ParticleRenderer;
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
			entity.AddComponent(new ParticleRenderer(particleEffect).attachToGroup(FightScreen.drawingLayers.entity9));
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
						GameHelper.chase(mTransform.position, nearest.getKey().getData(), 6);
						if (nearest.getKey().getData().dst2(mTransform.position) <= 3600) {
							explode();
							hasExploded = true;
						}
					}
					mTransform.position.add(dir.set(relativePos).rotate90(1).nor().scl(4));
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
				Set<Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData>> set = JudgingSystem.enemyJudges.entrySet();
				for (Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData> entry : set) {
					if (entry.getValue().judgeCallback.canHurt() && Intersector.overlaps(GameHelper.roundEllipseToCircle(entry.getKey().getData(), false), judge)) {
						entry.getValue().judgeCallback.onHurt(entry.getValue().judgeCallback.getBombResist());
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
			particleEffect.dispose();
			super.Kill();
		}
		
		public void explode() {
			THSoundEffects.Enep0.sound.play();
			particleEffect.dispose();
			particleEffect = new ParticleEffect();
			mEntity.GetComponent(ParticleRenderer.class).actor.wrappedEffect = particleEffect;
			particleEffect.load(Gdx.files.internal("resources/Particles/FantasySeal/FantasySealCircleExplosion.dat"), Gdx.files.internal("resources/Particles/"));
			particleEffect.setPosition(mTransform.position.x, mTransform.position.y);
			particleEffect.scaleEffect(2);
			particleEffect.start();
			Set<Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData>> set = JudgingSystem.enemyJudges.entrySet();
			for (Entry<ImmutableWrapper<Ellipse>, PlayerCollisionData> entry : set) {
				if (entry.getValue().judgeCallback.canHurt() && Intersector.overlaps(GameHelper.roundEllipseToCircle(entry.getKey().getData(), false), judge)) {
					entry.getValue().judgeCallback.onHurt(200 * entry.getValue().judgeCallback.getBombResist());
				}
			}
			count--;
			if (count <= 0) {
				reimu.bombFrames = 60;
			}
		}
	}
	
	public SpellFantasySeal(PlayerReimu reimu) {
		this.reimu = reimu;
	}
	
	Entity entity;
	public static int count = 8;

	@Override
	public void Initialize(Entity entity) {
		Transform transform = reimu.transform;
		this.entity = entity;
		count = 8;
		for (int i=0; i<360; i+=45) {
			Entity cir = Entity.Create();
			cir.AddComponent(new Transform(transform.position.cpy()));
			cir.AddComponent(new FantasySealCircle(i));
		}
		THSoundEffects.Cat.sound.play();
		THSoundEffects.Gun.sound.play();
		THSoundEffects.MorisaBomb.sound.play();
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
