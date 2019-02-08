package cn.s3bit.th902.gamecontents.components.player;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class BaseWing extends Component {

	protected Player player = null;
	protected Vector2 nowPosition = new Vector2();
	protected int existTime = 0;
	protected Transform transform;
	protected ImageRenderer mRenderer;
	protected int mId;
	protected int[] subPlanePosition;

	@Override
	public void Initialize(Entity entity) {

	}

	@Override
	public void Update() {

		if (nowPosition.epsilonEquals(-200f, -200f, 1e-3f)) {
			mRenderer.image.setColor(1, 1, 1, 0);
			return;
		}
		transform.rotation += 5;
		mRenderer.image.setColor(1, 1, 1, 1);
		transform.position.add(nowPosition.sub(transform.position).scl(0.2f));
		nowPosition.set(-200f, -200f);
		existTime++;

		switch (FightScreen.powerCount > 4 ? 4 : FightScreen.powerCount) {
		case 1:
			if (player.slow) {
				nowPosition.set(player.transform.position.x + subPlanePosition[0],
						player.transform.position.y + subPlanePosition[1]);
			} else {
				nowPosition.set(player.transform.position.x + subPlanePosition[2],
						player.transform.position.y + subPlanePosition[3]);
			}
			break;
		case 2:
			if (player.slow) {
				switch (mId) {
				case 1:
					nowPosition.set(player.transform.position.x + subPlanePosition[4],
							player.transform.position.y + subPlanePosition[5]);
					break;
				case 2:
					nowPosition.set(player.transform.position.x + subPlanePosition[6],
							player.transform.position.y + subPlanePosition[7]);
					break;
				}
			} else {
				switch (mId) {
				case 1:
					nowPosition.set(player.transform.position.x + subPlanePosition[8],
							player.transform.position.y + subPlanePosition[9]);
					break;
				case 2:
					nowPosition.set(player.transform.position.x + subPlanePosition[10],
							player.transform.position.y + subPlanePosition[11]);
					break;
				}
			}
			break;
		case 3:
			if (player.slow) {
				switch (mId) {
				case 1:
					nowPosition.set(player.transform.position.x + subPlanePosition[12],
							player.transform.position.y + subPlanePosition[13]);
					break;
				case 2:
					nowPosition.set(player.transform.position.x + subPlanePosition[14],
							player.transform.position.y + subPlanePosition[15]);
					break;
				case 3:
					nowPosition.set(player.transform.position.x + subPlanePosition[16],
							player.transform.position.y + subPlanePosition[17]);
					break;
				}
			} else {
				switch (mId) {
				case 1:
					nowPosition.set(player.transform.position.x + subPlanePosition[18],
							player.transform.position.y + subPlanePosition[19]);
					break;
				case 2:
					nowPosition.set(player.transform.position.x + subPlanePosition[20],
							player.transform.position.y + subPlanePosition[21]);
					break;
				case 3:
					nowPosition.set(player.transform.position.x + subPlanePosition[22],
							player.transform.position.y + subPlanePosition[23]);
					break;
				}
			}
			break;
		case 4:
			if (player.slow) {
				switch (mId) {
				case 1:
					nowPosition.set(player.transform.position.x + subPlanePosition[24],
							player.transform.position.y + subPlanePosition[25]);
					break;
				case 2:
					nowPosition.set(player.transform.position.x + subPlanePosition[26],
							player.transform.position.y + subPlanePosition[27]);
					break;
				case 3:
					nowPosition.set(player.transform.position.x + subPlanePosition[28],
							player.transform.position.y + subPlanePosition[29]);
					break;
				case 4:
					nowPosition.set(player.transform.position.x + subPlanePosition[30],
							player.transform.position.y + subPlanePosition[31]);
					break;
				}
			} else {
				switch (mId) {
				case 1:
					nowPosition.set(player.transform.position.x + subPlanePosition[32],
							player.transform.position.y + subPlanePosition[33]);
					break;
				case 2:
					nowPosition.set(player.transform.position.x + subPlanePosition[34],
							player.transform.position.y + subPlanePosition[35]);
					break;
				case 3:
					nowPosition.set(player.transform.position.x + subPlanePosition[36],
							player.transform.position.y + subPlanePosition[37]);
					break;
				case 4:
					nowPosition.set(player.transform.position.x + subPlanePosition[38],
							player.transform.position.y + subPlanePosition[39]);
					break;
				}
			}
			break;
		}
	}

}
