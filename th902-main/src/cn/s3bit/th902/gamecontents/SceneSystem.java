package cn.s3bit.th902.gamecontents;

import java.util.ArrayList;

import cn.s3bit.th902.contents.ExampleDanmakuScene;
import cn.s3bit.th902.contents.stage1.lunatic.DanmakuS1L1;
import cn.s3bit.th902.contents.stage1.lunatic.DanmakuS1L2;

public class SceneSystem {
	public static SceneSystem Create(int difficulty, int stageid) {
		return Create(difficulty, stageid, null);
	}

	public static SceneSystem Create(int difficulty, int stageid, Runnable afterFinish) {
		// test
		SceneSystem system = new SceneSystem();
		system.mScenes.add(new DanmakuS1L1());
		system.mScenes.add(new DanmakuS1L2());
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
		if (mCurrentIndex >= mScenes.size())
			return;
		DanmakuScene scene = mScenes.get(mCurrentIndex);
		if (scene.yield.isFinished()) {
			mCurrentIndex++;
			sceneManager = null;
		}
	}
}
