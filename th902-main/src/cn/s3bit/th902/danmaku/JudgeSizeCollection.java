package cn.s3bit.th902.danmaku;

import cn.s3bit.th902.gamecontents.components.enemy.BulletType;

public class JudgeSizeCollection {
	public static float getJudgeRadius(BulletType type) {
		switch (type) {
			case FormArrowM:
				return 4f;
			case FormCircleS:
				return 6f;
			case FormCircleS2:
				return 6f;
			case FormArrowS:
				return 4f;
			case FormOvalS:
				return 4f;
			case FormBulletM:
				return 4f;
			case FormCircleVerySmall:
				return 4f;
			case FormSquareS:
				return 4f;
			case FormOvalS2:
				return 4f;
			case FormStarS:
				return 6f;
			case FormOvalS3:
				return 4f;
			case FormOvalS4:
				return 3f;
			case FormButterflyS:
				return 6f;
			case FormCircleVerySmall2:
				return 4f;
			case FormStarM:
				return 8f;
			case FormCircleM:
				return 10f;
			case FormButterflyM:
				return 8f;
			case FormKnife:
				return 8f;
			case FormOvalM:
				return 8f;
			case FormSquareVerySmall:
				return 4f;
			case FormHeart:
				return 10f;
			case FormArrowL：
				return 6f;
			case FormBulletS:
				return 4f;
			case FormThunder:
				return 6f;
			case FormCircleLightMFormCircleLightM:
				return 10f;
		default:
			return 0f;
		}
	}
}
