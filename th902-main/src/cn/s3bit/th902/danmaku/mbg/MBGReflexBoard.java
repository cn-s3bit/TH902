package cn.s3bit.th902.danmaku.mbg;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectIntMap.Entries;
import com.badlogic.gdx.utils.ObjectIntMap.Entry;

import cn.s3bit.mbgparser.item.ReflexBoard;
import cn.s3bit.th902.danmaku.mbg.condition.IConditionJudger;
import cn.s3bit.th902.danmaku.mbg.event.IEventFirer;
import cn.s3bit.th902.danmaku.mbg.event.ILValueProvider;
import cn.s3bit.th902.gamecontents.components.enemy.EnemyJudgeCircle;
import cn.s3bit.th902.utils.LineSegment;

public class MBGReflexBoard extends AbstractMBGComponent<ReflexBoard> {

	public MBGReflexBoard(ReflexBoard mbgItem, MBGScene mbgScene, Group layer) {
		super(mbgItem, mbgScene, layer);
	}

	@Override
	public IEventFirer<AbstractMBGComponent<ReflexBoard>> getEventFirer() {
		return null;
	}

	@Override
	public IConditionJudger<AbstractMBGComponent<ReflexBoard>> getConditionJudger() {
		return null;
	}

	@Override
	public ILValueProvider<AbstractMBGComponent<ReflexBoard>> getLValueProvider() {
		return null;
	}

	@Override
	public int getBeginTime() {
		return mbgItem.生命.begin;
	}

	@Override
	public int getLife() {
		return mbgItem.生命.lifeTime;
	}

	@Override
	public Vector2 getInitialPosition() {
		float x, y;
		x = mbgItem.位置坐标.x;
		y = mbgItem.位置坐标.y;
		x = transformX(x);
		y = transformY(y);
		return new Vector2(x, y);
	}
	
	public LineSegment judgeLine;

	@Override
	public void begin() {
		judgeLine = new LineSegment();
		judgeLine.pStart.set(transform.position);
		judgeLine.pEnd.set(1, 0).rotate(-mbgItem.角度).scl(mbgItem.长度).add(transform.position);
		direction.set(1, 0).rotate(-mbgItem.角度);
	}

	@Override
	public void during() {
		Entries<EnemyJudgeCircle> entries = hitCount.iterator();
		while (entries.hasNext()) {
			Entry<EnemyJudgeCircle> entry = entries.next();
			if (entry.key.isDead()) {
				entries.remove();
			}
		}
		entries = hitCoolDown.iterator();
		while (entries.hasNext()) {
			Entry<EnemyJudgeCircle> entry = entries.next();
			if (entry.key.isDead()) {
				entries.remove();
			}
		}
	}

	@Override
	public void after() {
		
	}

	ObjectIntMap<EnemyJudgeCircle> hitCount = new ObjectIntMap<>();
	ObjectIntMap<EnemyJudgeCircle> hitCoolDown = new ObjectIntMap<>();
	public boolean judgeHitBullet(MBGBullet bullet) {
		if (hitCount.get(bullet.judgeCircle, 0) >= mbgItem.次数) {
			return false;
		}
		if (hitCoolDown.get(bullet.judgeCircle, 0) > 0) {
			if (hitCoolDown.getAndIncrement(bullet.judgeCircle, 0, -1) <= 1)
				hitCoolDown.remove(bullet.judgeCircle, 0);
			return false;
		}
		if (judgeLine.intersectCircle(bullet.judgeCircle.circle)) {
			hitCount.getAndIncrement(bullet.judgeCircle, 0, 1);
			hitCoolDown.put(bullet.judgeCircle, 5);
			return true;
		}
		return false;
	}
	
	Vector2 direction = new Vector2();
	public void manipulateBullet(MBGBullet bullet) {
		float in = bullet.moveBasic.velocity.angle(direction);
		bullet.moveBasic.velocity.setAngle(direction.angle() + in); // angle(out) = angle(in)
	}
}
