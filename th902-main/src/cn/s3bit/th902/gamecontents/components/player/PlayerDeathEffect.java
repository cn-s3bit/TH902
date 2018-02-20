package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.gamecontents.components.CircularDrawingStaticRenderer;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;

public class PlayerDeathEffect extends Component {
	Texture texture;
	static int timeleft = 0;
	int cutDeg;
	public PlayerDeathEffect(Texture texture, int cutDeg) {
		this.texture = texture;
		this.cutDeg = cutDeg;
	}

	Entity leftP, rightP;
	CircularDrawingStaticRenderer leftR, rightR;
	MoveBasic leftM, rightM;
	@Override
	public void Initialize(Entity entity) {
		timeleft = 60;
		leftP = Entity.Create();
		leftP.AddComponent(new Transform(JudgingSystem.playerJudge.cpy(), new Vector2(0.4f, 0.4f)));
		leftP.AddComponent(leftR = new CircularDrawingStaticRenderer(texture, -cutDeg, -cutDeg + 180, 0));
		leftP.AddComponent(leftM = new MoveBasic(new Vector2(0.5f, 0).rotate(cutDeg - 90), new Vector2(0.05f, 0).rotate(cutDeg - 90).add(0, -0.08f)));
		rightP = Entity.Create();
		rightP.AddComponent(new Transform(JudgingSystem.playerJudge.cpy(), new Vector2(0.4f, 0.4f)));
		rightP.AddComponent(rightR = new CircularDrawingStaticRenderer(texture, -cutDeg + 180, -cutDeg + 360, 0));
		rightP.AddComponent(rightM = new MoveBasic(new Vector2(0.5f, 0).rotate(cutDeg + 90), new Vector2(0.05f, 0).rotate(cutDeg + 90).add(0, -0.08f)));
	}

	@Override
	public void Update() {
		timeleft--;
		leftR.circularDrawingStatic.setColor(1, 1, 1, timeleft / 60f);
		rightR.circularDrawingStatic.setColor(1, 1, 1, timeleft / 60f);
		//leftP.GetComponent(Transform.class).rotation = leftM.velocity.angle();
		//rightP.GetComponent(Transform.class).rotation = rightM.velocity.angle();
		if (timeleft <= 0)
			Kill();
	}
	
	public static int getTimeLeft() {
		return timeleft;
	}

}
