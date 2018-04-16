package cn.s3bit.th902.danmaku.mbg;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.IntMap;

import cn.s3bit.mbgparser.MBGData;
import cn.s3bit.mbgparser.item.BulletEmitter;
import cn.s3bit.mbgparser.item.ReflexBoard;
import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.contents.BossSpell;
import cn.s3bit.th902.gamecontents.Entity;
/**
 * CrazyStorm的解析实现，这样就可以直接放到游戏里来了
 */
public class MBGScene extends BossSpell {
	int maxLife;
	int maxTime;
	float bombResist;
	Texture texture;
	public MBGData mbgData;
	LinkedBlockingQueue<Entity> entities;
	IntMap<MBGBulletEmitter> bulletEmitters;
	IntMap<MBGReflexBoard> reflexBoards;
	
	boolean isFirst, isLast;
	public int globalTime;
	public MBGScene(int maxLife, int maxTime, float bombResist, Texture texture, boolean isFirst, boolean isLast, FileHandle file) {
		this(maxLife, maxTime, bombResist, texture, isFirst, isLast, new String(file.readBytes()));
	}
		
	public MBGScene(int maxLife, int maxTime, float bombResist, Texture texture, boolean isFirst, boolean isLast, String mbg) {
		this.maxLife = maxLife;
		this.maxTime = maxTime;
		this.bombResist = bombResist;
		this.texture = texture;
		this.isFirst = isFirst;
		this.isLast = isLast;
		try {
			mbgData = MBGData.parseFrom(mbg);
			bulletEmitters = new IntMap<>();
			reflexBoards = new IntMap<>();
			mAddContent();
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}
	
	private void mAddContent() {
		if (mbgData.layer1 != null) {
			for (BulletEmitter bulletEmitter : mbgData.layer1.BulletEmitters)
				bulletEmitters.put(bulletEmitter.ID, new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity4));
			for (ReflexBoard reflexBoard : mbgData.layer1.ReflexBoards)
				reflexBoards.put(reflexBoard.ID, new MBGReflexBoard(reflexBoard, this, FightScreen.drawingLayers.entity4));
		}
		if (mbgData.layer2 != null) {
			for (BulletEmitter bulletEmitter : mbgData.layer2.BulletEmitters)
				bulletEmitters.put(bulletEmitter.ID, new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity5));
			for (ReflexBoard reflexBoard : mbgData.layer2.ReflexBoards)
				reflexBoards.put(reflexBoard.ID, new MBGReflexBoard(reflexBoard, this, FightScreen.drawingLayers.entity5));
		}
		if (mbgData.layer3 != null) {
			for (BulletEmitter bulletEmitter : mbgData.layer3.BulletEmitters)
				bulletEmitters.put(bulletEmitter.ID, new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity6));
			for (ReflexBoard reflexBoard : mbgData.layer3.ReflexBoards)
				reflexBoards.put(reflexBoard.ID, new MBGReflexBoard(reflexBoard, this, FightScreen.drawingLayers.entity6));
		}
		if (mbgData.layer4 != null) {
			for (BulletEmitter bulletEmitter : mbgData.layer4.BulletEmitters)
				bulletEmitters.put(bulletEmitter.ID, new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity7));
			for (ReflexBoard reflexBoard : mbgData.layer4.ReflexBoards)
				reflexBoards.put(reflexBoard.ID, new MBGReflexBoard(reflexBoard, this, FightScreen.drawingLayers.entity7));
		}
	}

	@Override
	public int getMaxLife() {
		return maxLife;
	}

	@Override
	public int getMaxTime() {
		return maxTime;
	}

	@Override
	public float getBombResist() {
		return bombResist;
	}

	@Override
	public Texture getTexture() {
		return texture;
	}

	@Override
	public boolean isFirst() {
		return isFirst;
	}
	
	@Override
	public boolean isLast() {
		return isLast;
	}
	
	@Override
	public void start() {
		globalTime = 0;
		entities = new LinkedBlockingQueue<>();
		bulletEmitters.forEach((emitter) -> {
			Entity em = Entity.Create();
			em.AddComponent(emitter.value);
			entities.add(em);
		});
		reflexBoards.forEach((board) -> {
			Entity em = Entity.Create();
			em.AddComponent(board.value);
			entities.add(em);
		});
	}
	
	@Override
	public void act() {
		globalTime++;
		if (globalTime > mbgData.totalFrame) {
			end();
			mAddContent();
			start();
		}
	}
	
	@Override
	public void end() {
		while (!entities.isEmpty()) {
			entities.remove().Destroy();
		}
		bulletEmitters.clear();
		reflexBoards.clear();
	}
}
