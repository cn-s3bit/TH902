package cn.s3bit.th902.gamecontents.components.enemy;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.LaserLikeRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.LifeCountDown;

public class MovingLaser extends Component {
	
	private LaserLikeRenderer mRenderer;
	private Transform mTransform;
	private int mTimeleft;
	
	LinkedList<Vector2> positions = new LinkedList<>();
	
	public static Entity Create(Vector2 position, int barrageID, int timeleft, int lifetime) {
		Entity n = Entity.Create();
		n.AddComponent(new Transform(position));
		n.AddComponent(new LaserLikeRenderer(ResourceManager.barrages.get(barrageID), timeleft * 6));
		n.AddComponent(new LifeCountDown(lifetime));
		n.AddComponent(new MovingLaser(timeleft));
		return n;
	}
	
	public MovingLaser(int timeleft) {
		mTimeleft = timeleft;
	}

	@Override
	public void Initialize(Entity entity) {
		mRenderer = entity.GetComponent(LaserLikeRenderer.class);
		mTransform = entity.GetComponent(Transform.class);
		Update();
	}

	@Override
	public void Update() {
		if (positions.size() < mTimeleft)
			positions.add(mTransform.position.cpy());
		else {
			Vector2 recycled = positions.removeFirst();
			recycled.set(mTransform.position);
			positions.add(recycled);
		}
		mRenderer.laserLikeDrawing.setLaserPoints(positions, null);
	}

}
