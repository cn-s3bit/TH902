package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.utils.Yield;

public class SpellFantasySeal extends Component {
	public final PlayerReimu reimu;
	public FantasySealCircle[] circles = new FantasySealCircle[8];
	
	public class FantasySealCircle extends Component {
		public Vector2 dir = new Vector2();
		public Vector2 relativePos = new Vector2();
		public Yield yield;
		private Entity mEntity;
		
		public FantasySealCircle(float angle) {
			dir.set(1, 0).rotate(angle).scl(12);
		}
		
		@Override
		public void Initialize(Entity entity) {
			final Transform transform = entity.GetComponent(Transform.class);
			yield.append(() -> {
				relativePos.add(dir);
				transform.position.set(reimu.transform.position).add(relativePos);
			}, 7);
			yield.append(() -> {
				relativePos.rotate(48);
				transform.position.set(reimu.transform.position).add(relativePos);
			}, 60);
		}

		@Override
		public void Update() {
			yield.yield();
			if (yield.isFinished()) {
				Kill();
			}
		}
		
		@Override
		public void Kill() {
			// TODO Auto-generated method stub
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
