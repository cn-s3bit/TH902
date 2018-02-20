package cn.s3bit.th902.gamecontents;

import java.util.ArrayList;

import cn.s3bit.th902.contents.stage1.DanmakuS1L1;
import cn.s3bit.th902.contents.stage1.DanmakuS1L2;
import cn.s3bit.th902.contents.stage1.DanmakuS1L3;
import cn.s3bit.th902.contents.stage1.DanmakuS1L4;
import cn.s3bit.th902.contents.stage1.DanmakuS1L5;
import cn.s3bit.th902.contents.stage1.DanmakuS1L6;
import cn.s3bit.th902.contents.stage1.DanmakuS1LLast;
import cn.s3bit.th902.contents.stage2.DanmakuS2L1;

public class SceneSystem {
	public static SceneSystem Create(int difficulty, int stageid) {
		return Create(difficulty, stageid, null);
	}

	public static SceneSystem Create(int difficulty, int stageid, Runnable afterFinish) {
		// test
		SceneSystem system = new SceneSystem();
		system.mScenes.add(new DanmakuS1L1());
		system.mScenes.add(new DanmakuS1L2());
		system.mScenes.add(new DanmakuS1L3());
		system.mScenes.add(new DanmakuS1L4());
		system.mScenes.add(new DanmakuS1L5());
		system.mScenes.add(new DanmakuS1L6());
		system.mScenes.add(new DanmakuS1LLast());
		system.mScenes.add(new DanmakuS2L1());
		//system.mScenes.add(new ExampleDanmakuScene());
		//system.mScenes.add(new ExampleDanmakuScene());
		system.afterFinish = afterFinish;
		return system;
	}

	private SceneSystem() {
		mScenes = new ArrayList<>();
		mCurrentIndex = 0;
		afterFinish = null;
		sceneManager = null;
	}

	private ArrayList<DanmakuScene> mScenes;
	private int mCurrentIndex;
	private Runnable afterFinish;
	public Entity sceneManager;

	public void PreUpdate() {
		if (mCurrentIndex >= mScenes.size()) {
			if (afterFinish != null) {
				afterFinish.run();
				afterFinish = null;
			}
			return;
		}
		DanmakuScene scene = mScenes.get(mCurrentIndex);
		if (sceneManager == null) {
			sceneManager = Entity.Create();
			sceneManager.AddComponent(scene);
		}
	}

	public void PostUpdate() {
		if (mCurrentIndex >= mScenes.size() || sceneManager == null)
			return;
		DanmakuScene scene = mScenes.get(mCurrentIndex);
		if (scene.yield.isFinished()) {
			nextScene();
		}
	}
	
	public void nextScene() {
		mCurrentIndex++;
		sceneManager = null;
	}
}
