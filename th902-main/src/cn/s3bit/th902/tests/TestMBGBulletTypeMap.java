package cn.s3bit.th902.tests;

import cn.s3bit.th902.danmaku.mbg.MBGBulletTypeMap;

public class TestMBGBulletTypeMap {
	public static void main(String[] args) {
		if (MBGBulletTypeMap.TYPE_MAP.get(40) != 47)
			throw new AssertionError(MBGBulletTypeMap.TYPE_MAP.get(40));
		if (MBGBulletTypeMap.TYPE_MAP.get(99) != 117)
			throw new AssertionError(MBGBulletTypeMap.TYPE_MAP.get(99));
		if (MBGBulletTypeMap.TYPE_MAP.get(120) != 144)
			throw new AssertionError(MBGBulletTypeMap.TYPE_MAP.get(120));
		if (MBGBulletTypeMap.TYPE_MAP.get(150) != 168)
			throw new AssertionError(MBGBulletTypeMap.TYPE_MAP.get(150));
		if (MBGBulletTypeMap.TYPE_MAP.get(180) != 81)
			throw new AssertionError(MBGBulletTypeMap.TYPE_MAP.get(180));
		if (MBGBulletTypeMap.TYPE_MAP.size() != 229)
			throw new AssertionError(MBGBulletTypeMap.TYPE_MAP.size());
		System.out.println("Test Pass!");
	}

}
