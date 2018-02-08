package cn.s3bit.th902.danmaku;

import cn.s3bit.th902.gamecontents.components.enemy.BulletType;

public class JudgeSizeCollection {
	public static float getJudgeRadius(BulletType type) {
		switch (type) {
		case FormCircleS:
			return 6f;

		default:
			return 0f;
		}
	}
}
