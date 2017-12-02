package cn.s3bit.th902.gamecontents.components.player;

import java.util.Map.Entry;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
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
		public Yield yield;
		private Entity mEntity;
		private Transform mTransform;
		
		private float mRotateSpeed = 48;
		public boolean isChasing = false;
		
		public Vector2[] biases = new Vector2[4];
		
		public FantasySealCircle(float angle) {
			dir.set(1, 0).rotate(angle).scl(12);
		}
		
		@Override
		public void Initialize(Entity entity) {
			mEntity = entity;
			mTransform = entity.GetComponent(Transform.class);
			yield.append(() -> {
				relativePos.add(dir);
			}, 7);
			yield.append(() -> {
				relativePos.rotate(mRotateSpeed);
			}, 40);
			yield.append(() -> {
				relativePos.rotate(mRotateSpeed);
				mRotateSpeed -= 1f;
			}, 24);
		}

		@Override
		public void Update() {
			updateBiases();
			if (yield.isFinished()) {
				if (isChasing) {
					Entry<ImmutableWrapper<Vector2>, IJudgeCallback> nearest = JudgingSystem.calculateNearestChaseable(mTransform.position);
					if (nearest != null) {
						GameHelper.chase(mTransform.position, nearest.getKey().getData(), 10);
						if (nearest.getKey().getData().dst2(mTransform.position) <= 900) {
						}
					}
				}
				else
					isChasing = MathUtils.randomBoolean(0.01f);
			}
			else 
				yield.yield();
		}
		
		public void updateBiases() {
			if (isChasing) {
				
			}
			else
				mTransform.position.set(reimu.transform.position).add(relativePos);
		}
		
		@Override
		public void Kill() {
			mEntity.Destroy();
			super.Kill();
		}
	}
	
	public SpellFantasySeal(PlayerReimu reimu) {
		this.reimu = reimu;
	}

	@Override
	public void Initialize(Entity entity) {
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
