package cn.s3bit.th902.danmaku;

public enum BulletType {
	FormArrowM(0),
	FormCircleS(1),
	FormCircleS2(2),
	FormArrowS(3),
	FormOvalS(4),
	FormBulletM(5),
	FormCircleVerySmall(6),
	FormSquareS(7),
	FormOvalS2(8),
	FormStarS(9),
	FormOvalS3(10),
	FormOvalS4(11),
	FormButterflyS(12),
	FormCircleVerySmall2(13),
	FormStarM(14),
	FormCircleM(15),
	FormButterflyM(16),
	FormKnife(17),
	FormOvalM(18),
	FormSquareVerySmall(19),
	FormHeart(20),
	FormArrowL(21),
	FormBulletS(22),
	FormThunder(23),
	FormCircleLightM(24),

	ColorRed(0),
	ColorPink(1),
	ColorBlue(2),
	ColorBlueLight(3),
	ColorGreen(4),
	ColorYellow(5),
	ColorOrange(6),
	ColorGray(7),
	
	SimpleRed(0),
	SimpleGreen(1),
	SimpleBlue(2),
	BlueLaser(3),
	RoundedRectangle(4),
	BigRed(21),
	BlueWithShadow(22),
	BigLightBlue(39),
	RedWithShadow(56),
	GreenGlue(81),
	PurpleWithShadow(98),
	GreenWithShadow(134),
	OrangeWithShadow(143),
	Anchor(160),
	Bat(185),
	BigBallRed(202),
	BigBallBlue(203),
	BigBallPurple(204),
	BigBallGreen(205),
	BigBallYellow(206),
	BlackLightBall(208),
	MagicCircleRed(208),
	MagicCirclePurple(209),
	MagicCircleBlue(210),
	MagicCircleLightBlue(211),
	MagicCircleGreen(212),
	MagicCircleYellow(213),
	FlowerYellow(230),
	FlowerRed(231),
	FlowerOrange(232),
	RoseRed(233),
	RoseBlue(234),
	RoseYellow(235);
	
	private final int mType;
	private BulletType(int t) {
		mType = t;
	}
	
	public int getType() {
		return mType;
	}
}