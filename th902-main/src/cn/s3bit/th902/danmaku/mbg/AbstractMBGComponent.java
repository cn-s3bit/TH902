package cn.s3bit.th902.danmaku.mbg;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ObjectMap;

import cn.s3bit.mbgparser.event.CommandAction;
import cn.s3bit.mbgparser.event.DataOperateAction;
import cn.s3bit.mbgparser.event.Event;
import cn.s3bit.mbgparser.event.EventGroup;
import cn.s3bit.mbgparser.event.DataOperateAction.TweenFunctionType;
import cn.s3bit.th902.danmaku.mbg.condition.IConditionJudger;
import cn.s3bit.th902.danmaku.mbg.event.IEventFirer;
import cn.s3bit.th902.danmaku.mbg.event.ILValueProvider;
import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;
import cn.s3bit.th902.gamecontents.components.Transform;
import cn.s3bit.th902.gamecontents.components.ai.MoveBasic;

public abstract class AbstractMBGComponent<T> extends Component {
	public MoveBasic moveBasic;
	public MBGScene mbgScene;
	public Group layer;
	public int timerBegin;
	public int life;
	public Transform transform;
	public Entity entity;
	
	public T mbgItem;
	
	public AbstractMBGComponent(T mbgItem, MBGScene mbgScene, Group layer) {
		this.mbgItem = mbgItem;
		this.mbgScene = mbgScene;
		this.layer = layer;
	}
	
	@Override
	public void Initialize(Entity entity) {
		this.entity = entity;
		timerBegin = 0;
		if ((transform = entity.GetComponent(Transform.class)) == null) {
			transform = new Transform(getInitialPosition());
			entity.AddComponent(transform);
		}
		if ((moveBasic = entity.GetComponent(MoveBasic.class)) == null) {
			moveBasic = new MoveBasic(0, 0);
			entity.AddComponent(moveBasic);
		}
	}
	
	@Override
	public void Update() {
		if (timerBegin < getBeginTime()) {
			timerBegin++;
			return;
		} else if (timerBegin == getBeginTime()) {
			timerBegin++;
			life = 0;
			begin();
		} else if (life > getLife()) {
			after();
		} else {
			life++;
			during();
		}
	}
	
	public float transformX(float x) {
		return (x - mbgScene.mbgData.center.Position.x) * 285f / 315f + 285f;
	}
	
	public float transformY(float y) {
		return -(y - mbgScene.mbgData.center.Position.y) * 360f / 240f + 360f;
	}
	
	public Vector2 getPosition(Transform sub) {
		return sub.position.cpy();
	}
	
	public abstract IEventFirer<AbstractMBGComponent<T>> getEventFirer();
	public abstract IConditionJudger<AbstractMBGComponent<T>> getConditionJudger();
	public abstract ILValueProvider<AbstractMBGComponent<T>> getLValueProvider();
	
	public abstract int getBeginTime();
	public abstract int getLife();
	public abstract Vector2 getInitialPosition();
	
	public abstract void begin();
	public abstract void during();
	public abstract void after();
	
	public ObjectMap<EventGroup, ArrayList<MBGEventTask>> eventTasks = new ObjectMap<>();
	
	public void runEventGroups(List<EventGroup> eventGroups, int time) {
		if (eventGroups == null) return;
		for (int i = 0; i < eventGroups.size(); i++)
			runEvents(eventGroups.get(i), time);
	}
	
	public void runEvents(EventGroup eventGroup, int time) {
		if (eventGroup.Events == null) return;
		if (eventGroup.Interval == eventGroup.IntervalIncrement) {
			if (time % eventGroup.Interval == 0)
				if (eventTasks.containsKey(eventGroup))
					eventTasks.get(eventGroup).clear();
			time %= eventGroup.Interval;
		}
		for (Event event : eventGroup.Events) {
			if (getConditionJudger().judgeCondition(this, event.condition, time)) {
				if (!eventTasks.containsKey(eventGroup))
					eventTasks.put(eventGroup, new ArrayList<>());
				if (event.action instanceof CommandAction) {
					CommandAction commandAction = (CommandAction) event.action;
					getEventFirer().fireCommand(this, commandAction);
				}
				else if (event.action instanceof DataOperateAction) {
					DataOperateAction action = (DataOperateAction) event.action;
					if (action.TweenFunction == TweenFunctionType.Fixed || action.TweenTime <= 1)
						getEventFirer().fireDataOperation(this, new MBGEventTask(1, action, this) {
							{
								this.timeLeft = 0;
							}
						});
					else {
						eventTasks.get(eventGroup).add(new MBGEventTask(action.TweenTime, action, this));
					}
				} else
					throw new AssertionError("Unknown Action: " + event.action);
			}
		}
		if (eventTasks.containsKey(eventGroup))
			for (ListIterator<MBGEventTask> iterator = eventTasks.get(eventGroup).listIterator(); iterator.hasNext();) {
				MBGEventTask task = iterator.next();
				task.timeLeft--;
				getEventFirer().fireDataOperation(this, task);
				if (task.timeLeft <= 0)
					iterator.remove();
			}
	}
}
