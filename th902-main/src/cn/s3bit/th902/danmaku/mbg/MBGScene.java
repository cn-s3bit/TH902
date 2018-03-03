package cn.s3bit.th902.danmaku.mbg;

import java.io.IOException;
import java.util.HashSet;

import com.badlogic.gdx.graphics.Texture;

import cn.s3bit.mbgparser.MBGData;
import cn.s3bit.mbgparser.item.BulletEmitter;
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
	MBGData mbgData;
	HashSet<MBGBulletEmitter> bulletEmitters;
	boolean isFirst, isLast;
	public MBGScene(int maxLife, int maxTime, float bombResist, Texture texture, boolean isFirst, boolean isLast, String mbg) {
		this.maxLife = maxLife;
		this.maxTime = maxTime;
		this.bombResist = bombResist;
		this.texture = texture;
		this.isFirst = isFirst;
		this.isLast = isLast;
		try {
			mbgData = MBGData.parseFrom(mbg);
			bulletEmitters = new HashSet<>();
			if (mbgData.layer1 != null)
				for (BulletEmitter bulletEmitter : mbgData.layer1.BulletEmitters)
					bulletEmitters.add(new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity7));
			if (mbgData.layer2 != null)
				for (BulletEmitter bulletEmitter : mbgData.layer2.BulletEmitters)
					bulletEmitters.add(new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity6));
			if (mbgData.layer3 != null)
				for (BulletEmitter bulletEmitter : mbgData.layer3.BulletEmitters)
					bulletEmitters.add(new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity5));
			if (mbgData.layer4 != null)
				for (BulletEmitter bulletEmitter : mbgData.layer4.BulletEmitters)
					bulletEmitters.add(new MBGBulletEmitter(bulletEmitter, this, FightScreen.drawingLayers.entity4));
		} catch (IOException e) {
			throw new AssertionError(e);
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
		bulletEmitters.forEach((emitter) -> {
			Entity em = Entity.Create();
			em.AddComponent(emitter);
		});
	}
	
	@Override
	public void act() {
		
	}
}
