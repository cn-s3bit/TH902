package cn.s3bit.th902.gamecontents;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import cn.s3bit.th902.FightScreen;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.components.CircularProgressRenderer;
import cn.s3bit.th902.gamecontents.components.ImageRenderer;
import cn.s3bit.th902.gamecontents.components.LambdaComponent;
import cn.s3bit.th902.gamecontents.components.TextRenderer;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.enemy.BossHP;

public class EnemySpellInfoSystem {
	/**
	 * The Entity of Boss.
	 */
	public static Entity central;

	public static Entity spellName;
	public static Entity lifeGauge;
	public static Entity timeleft;
	public static Entity indicator;
	
	private static boolean mIsActive = false;
	
	public static boolean isActive() {
		return mIsActive;
	}
	
	public static void activate(Entity boss) {
		central = boss;
		final Transform cTransform = central.GetComponent(Transform.class);
		final BossHP bossHP = central.GetComponent(BossHP.class);
		
		spellName = Entity.Create();
		spellName.AddComponent(new Transform(new Vector2(546, 700)));
		final TextRenderer nameRenderer = new TextRenderer(bossHP.spellNames[bossHP.current]);
		nameRenderer.labelAlign = Align.topRight;
		nameRenderer.lineAlign = Align.right;
		nameRenderer.attachToGroup(FightScreen.drawingLayers.ui0);
		spellName.AddComponent(nameRenderer);
		spellName.AddComponent(new LambdaComponent(() -> {
			nameRenderer.setText(bossHP.spellNames[bossHP.current] == null ? "" : bossHP.spellNames[bossHP.current]);
		}, 0, 4));
		
		lifeGauge = Entity.Create();
		lifeGauge.AddComponent(new Transform(cTransform.position));
		final CircularProgressRenderer circularProgressRenderer = new CircularProgressRenderer();
		final ImageRenderer outerCircle = new ImageRenderer(ResourceManager.textures.get("bloodGaugeOuter"), -1);
		circularProgressRenderer.attachToGroup(FightScreen.drawingLayers.ui0);
		outerCircle.attachToGroup(FightScreen.drawingLayers.ui0);
		lifeGauge.AddComponent(outerCircle);
		lifeGauge.AddComponent(circularProgressRenderer);
		lifeGauge.AddComponent(new LambdaComponent(() -> {
			//circularProgressRenderer.progress.toBack();
			//outerCircle.image.toBack();
			circularProgressRenderer.progress.setPercent(bossHP.hp / (float) bossHP.maxhp);
		}, 1));
		
		timeleft = Entity.Create();
		timeleft.AddComponent(new Transform(new Vector2(285, 660)));
		final TextRenderer timeRenderer = new TextRenderer(bossHP.spellNames[bossHP.current]);
		timeRenderer.attachToGroup(FightScreen.drawingLayers.ui0);
		timeRenderer.labelAlign = Align.center;
		timeRenderer.lineAlign = Align.center;
		timeleft.AddComponent(timeRenderer);
		timeleft.AddComponent(new LambdaComponent(() -> {
			timeRenderer.setText(String.format("%.2f", (bossHP.time[bossHP.current] - bossHP.timer) / 60f));
		}, 1));
		mIsActive = true;
	}
	
	public static void deactivate() {
		mIsActive = false;
		timeleft.Destroy();
		lifeGauge.Destroy();
		spellName.Destroy();
	}
}
