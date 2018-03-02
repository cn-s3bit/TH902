package cn.s3bit.th902.gamecontents.components.enemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.IJudgeCallback;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.LaserLikeRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.IMovement;
import cn.s3bit.th902.gamecontents.components.ai.LifeCountDown;
import cn.s3bit.th902.utils.ImmutableWrapper;

public class MovingLaser extends Component {
	
	private LaserLikeRenderer mRenderer;
	private Transform mTransform;
	private int mTimeleft;
	
	LinkedList<Vector2> positions = new LinkedList<>();
	List<ImmutableWrapper<Circle>> judges = new ArrayList<>();
	
	Entity entity;
	IMovement movement;
	
	public static Entity Create(Vector2 position, int barrageID, int timeleft, int lifetime, float judgeSize) {
		Entity n = Entity.Create();
		n.AddComponent(new Transform(position));
		n.AddComponent(new LaserLikeRenderer(ResourceManager.barrages.get(barrageID), timeleft * 6).attachToGroup(FightScreen.drawingLayers.entity4));
		n.AddComponent(new LifeCountDown(lifetime));
		n.AddComponent(new MovingLaser(timeleft, judgeSize));
		return n;
	}
	
	public MovingLaser(int timeleft, float judgeSize) {
		mTimeleft = timeleft;
		for (int i=0; i<timeleft; i++)
			judges.add(ImmutableWrapper.wrap(new Circle(1000, 1000, judgeSize)));
	}

	@Override
	public void Initialize(Entity entity) {
		this.entity = entity;
		mRenderer = entity.GetComponent(LaserLikeRenderer.class);
		mTransform = entity.GetComponent(Transform.class);
		JudgingSystem.clearByBombs.put(mTransform.immutablePosition, entity);
		Update();
	}

	@Override
	public void Update() {
		if (movement == null)
			movement = entity.GetComponent(IMovement.class);
		if (positions.size() < mTimeleft) {
			JudgingSystem.registerEnemyJudge(judges.get(positions.size()), IJudgeCallback.NONE, movement);
			positions.add(mTransform.position.cpy());
		}
		else {
			Vector2 recycled = positions.removeFirst();
			recycled.set(mTransform.position);
			positions.add(recycled);
		}
		int pid = 0;
		for (Vector2 pos : positions) {
			judges.get(pid).getData().setPosition(pos);
			pid++;
		}
		mRenderer.laserLikeDrawing.setLaserPoints(positions, null);
	}
	
	@Override
	public void Kill() {
		super.Kill();
		for (ImmutableWrapper<Circle> w : judges) {
			JudgingSystem.unregisterEnemyJudge(w);
		}
	}

}
