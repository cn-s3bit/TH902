package cn.s3bit.th902.gamecontents.components.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;

public class BaseProjectile extends Component {
	/**
	 * An easy method to create the bullet.
	 * 
	 * @return The created Entity.
	 */

	public static final int[][] bulletTypeArray = new int[][] { { 5, 6, 7, 8, 9, 10, 11, 12 }, // 0
			{ 13, 14, 15, 16, 17, 18, 19, 20 }, // 1
			{ 23, 24, 25, 26, 27, 28, 29, 30 }, // 2
			{ 31, 32, 33, 34, 35, 36, 37, 38 }, // 3
			{ 40, 41, 42, 43, 44, 45, 46, 47 }, // 4
			{ 48, 49, 50, 51, 52, 53, 54, 55 }, // 5
			{ 57, 58, 59, 60, 61, 62, 63, 64 }, // 6
			{ 65, 66, 67, 68, 69, 70, 71, 72 }, // 7
			{ 73, 74, 75, 76, 77, 78, 79, 80 }, // 8
			{ 82, 83, 84, 85, 86, 87, 88, 89 }, // 9
			{ 90, 91, 92, 93, 94, 95, 96, 97 }, // 10
			{ 99, 100, 101, 102, 103, 104, 105, 106 }, // 11
			{ 107, 108, 109, 110, 111, 112, 113, 114 }, // 12
			{ 115, 116, 117, 118, 119, 120, 121, 122 }, // 13
			{ 126, 127, 128, 129, 130, 131, 132, 133 }, // 14
			{ 135, 136, 137, 138, 139, 140, 141, 142 }, // 15
			{ 144, 145, 146, 147, 148, 149, 150, 151 }, // 16
			{ 152, 153, 154, 155, 156, 157, 158, 159 }, // 17
			{ 161, 162, 163, 164, 165, 166, 167, 168 }, // 18
			{ 169, 170, 171, 172, 173, 174, 175, 176 }, // 19
			{ 177, 178, 179, 180, 181, 182, 183, 184 }, // 20
			{ 186, 187, 188, 189, 190, 191, 192, 193 }, // 21
			{ 194, 195, 196, 197, 198, 199, 200, 201 }, // 22
			{ 214, 215, 216, 217, 218, 219, 220, 221 }, // 23
			{ 222, 223, 224, 225, 226, 227, 228, 229 } // 24
	};

	public static class bulletType {
		public static final int FormArrowM = 0;
		public static final int FormCircleS = 1;
		public static final int FormCircleS2 = 2;
		public static final int FormArrowS = 3;
		public static final int FormOvalS = 4;
		public static final int FormBulletM = 5;
		public static final int FormCircleVerySmall = 6;
		public static final int FormSquareS = 7;
		public static final int FormOvalS2 = 8;
		public static final int FormStarS = 9;
		public static final int FormOvalS3 = 10;
		public static final int FormOvalS4 = 11;
		public static final int FormButterflyS = 12;
		public static final int FormCircleVerySmall2 = 13;
		public static final int FormStarM = 14;
		public static final int FormCircleM = 15;
		public static final int FormButterflyM = 16;
		public static final int FormKnife = 17;
		public static final int FormOvalM = 18;
		public static final int FormSquareVerySmall = 19;
		public static final int FormHeart = 20;
		public static final int FormArrowL = 21;
		public static final int FormBulletS = 22;
		public static final int FormThunder = 23;
		public static final int FormCircleLightM = 24;

		public static final int ColorRed = 0;
		public static final int ColorPink = 1;
		public static final int ColorBlue = 2;
		public static final int ColorBlueLight = 3;
		public static final int ColorGreen = 4;
		public static final int ColorYellow = 5;
		public static final int ColorOrange = 6;
		public static final int ColorGray = 7;
		
		public static final int SimpleRed=0;
		public static final int SimpleGreen=1;
		public static final int SimpleBlue=2;
		public static final int BlueLaser=3;
		public static final int RoundedRectangle=4;
		public static final int BigRed=21;
		public static final int BlueWithShadow=22;
		public static final int BigLightBlue=39;
		public static final int RedWithShadow=56;
		public static final int GreenGlue=81;
		public static final int PurpleWithShadow=98;
		public static final int GreenWithShadow=134;
		public static final int OrangeWithShadow=143;
		public static final int Anchor=160;
		public static final int Bat=185;
		public static final int BigBallRed=202;
		public static final int BigBallBlue=203;
		public static final int BigBallPurple=204;
		public static final int BigBallGreen=205;
		public static final int BigBallYellow=206;
		public static final int BlackLightBall=208;
		public static final int MagicCircleRed=208;
		public static final int MagicCirclePurple=209;
		public static final int MagicCircleBlue=210;
		public static final int MagicCircleLightBlue=211;
		public static final int MagicCircleGreen=212;
		public static final int MagicCircleYellow=213;
		public static final int FlowerYellow=230;
		public static final int FlowerRed=231;
		public static final int FlowerOrange=232;
		public static final int RoseRed=233;
		public static final int RoseBlue=234;
		public static final int RoseYellow=235;
	}

	private Vector2 dirVec;
	public static Entity Create(Vector2 position, int bulletForm, int bulletColor) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(bulletTypeArray[bulletForm][bulletColor]), 0));
		entity.AddComponent(new BaseProjectile(bulletForm));
		return entity;
	}
	public static Entity CreateSpecialBullet(Vector2 position, int bulletType) {
		Entity entity = Entity.Create();
		entity.AddComponent(new Transform(position));
		entity.AddComponent(new ImageRenderer(ResourceManager.barrages.get(bulletType), 0));
		entity.AddComponent(new BaseProjectile(bulletType));
		return entity;
	}

	protected Transform transform;
	protected Entity entity;
	protected int bulletType;
	
	public BaseProjectile(int bullrtForm) {
		bulletType=bullrtForm;
	}
	
	@Override
	public void Initialize(Entity entity) {
		transform = entity.GetComponent(Transform.class);
		this.entity = entity;
		dirVec=new Vector2(MathUtils.random(-5,5),MathUtils.random(-5,5));
		if (dirVec.equals(new Vector2(0,0))) {
			entity.Destroy();
		}
	}

	@Override
	public void Update() {
		transform.position.add(dirVec);
		if (bulletType==9||bulletType==14||bulletType==230||bulletType==231||bulletType==232
				||bulletType==233||bulletType==234||bulletType==235) {
			transform.rotation+=7;
		}else {
			transform.rotation=dirVec.angle()-90;
		}
		if (transform.position.x > 570 || transform.position.x < 0 || transform.position.y > 740
				|| transform.position.y < 0) {
			entity.Destroy();
		}
	}

	@Override
	public void Kill() {
		super.Kill();
	}
}
