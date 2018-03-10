package cn.s3bit.th902.danmaku.mbg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.utils.IntFloatMap;

import cn.s3bit.th902.danmaku.JudgeSizeCollection;
import cn.s3bit.th902.gamecontents.components.enemy.BulletType;
import cn.s3bit.th902.gamecontents.components.enemy.ProjectileFactory;

public final class MBGBulletTypeMap {
	public static final List<Integer> TYPE_MAP;
	public static final IntFloatMap JUDGE_SIZE_MAP;
	static {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		for (int i=5; i<=20; i++) list.add(i);
		for (int i=23; i<=38; i++) list.add(i);
		for (int i=40; i<=55; i++) list.add(i);
		for (int i=65; i<=80; i++) list.add(i);
		for (int i=82; i<=89; i++) list.add(i);
		for (int i=57; i<=64; i++) list.add(i);
		for (int i=90; i<=97; i++) list.add(i);
		for (int i=99; i<=106; i++) list.add(i);
		for (int i=115; i<=120; i++) list.add(i);
		list.add(122);
		for (int i=126; i<=133; i++) list.add(i);
		for (int i=135; i<=142; i++) list.add(i);
		for (int i=144; i<=159; i++) list.add(i);
		for (int i=107; i<=112; i++) list.add(i);
		list.add(114);
		for (int i=161; i<=184; i++) list.add(i);
		for (int i=186; i<=192; i++) list.add(i);
		for (int i=202; i<=206; i++) list.add(i);
		list.add(0); list.add(81);
		list.addAll(Arrays.asList(22, 56, 98, 134, 143));
		list.addAll(Arrays.asList(123, 124, 125, 21, 39, 160, 0));
		for (int i=208; i<=213; i++) list.add(i);
		list.add(185);
		list.addAll(Arrays.asList(233, 234, 235));
		for (int i=194; i<=201; i++) list.add(i);
		for (int i=214; i<=219; i++) list.add(i);
		list.addAll(Arrays.asList(221, 230, 231, 232));
		list.addAll(Arrays.asList(225, 229, 226, 222, 227, 223, 229, 224));
		TYPE_MAP = Collections.unmodifiableList(list);
		
		JUDGE_SIZE_MAP = new IntFloatMap();
		for (int i=0; i<ProjectileFactory.bulletTypeArray.length; i++)
			for (int j=0; j<ProjectileFactory.bulletTypeArray[i].length; j++)
			{
				int id;
				id = TYPE_MAP.indexOf(ProjectileFactory.bulletTypeArray[i][j]);
				if (id > 0) {
					JUDGE_SIZE_MAP.put(id, JudgeSizeCollection.getJudgeRadius(BulletType.values()[i]));
				}
			}
	}
}
