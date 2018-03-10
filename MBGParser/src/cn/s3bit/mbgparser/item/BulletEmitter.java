package cn.s3bit.mbgparser.item;

import java.io.Serializable;
import java.util.*;

import cn.s3bit.mbgparser.*;
import cn.s3bit.mbgparser.event.*;
import static cn.s3bit.mbgparser.MBGUtils.*;

public class BulletEmitter implements BindState.IBindable, Serializable
{
	private static final long serialVersionUID = -7994028193621656916L;

	public int ID, 层ID;

	public BindState 绑定状态 = new BindState();

	public Position<ValueWithRand> 位置坐标 = new PositionWithRand();

	public Life 生命 = new Life();

	public Position<Float> 发射坐标 = new Position<>();

	public ValueWithRand 半径 = new ValueWithRand(), 半径方向 = new ValueWithRand();

	public Position<Float> 半径方向_坐标指定 = new Position<>();

	public ValueWithRand 条数 = new ValueWithRand(), 周期 = new ValueWithRand();

	public ValueWithRand 发射角度 = new ValueWithRand();

	public Position<Float> 发射角度_坐标指定 = new Position<>();

	public ValueWithRand 范围 = new ValueWithRand();

	public MotionWithPosition<ValueWithRand, Float> 发射器运动 = new MotionWithRandWithPosition<Float>();

	public int 子弹生命, 子弹类型;

	public float 宽比, 高比;

	public Color<Float> 子弹颜色 = new Color<>();

	public ValueWithRand 朝向 = new ValueWithRand();

	public Position<Float> 朝向_坐标指定 = new Position<>();

	public boolean 朝向与速度方向相同;

	public MotionWithPosition<ValueWithRand, Float> 子弹运动 = new MotionWithRandWithPosition<Float>();

	public float 横比, 纵比;

	public boolean 雾化效果, 消除效果, 高光效果, 拖影效果, 出屏即消, 无敌状态;

	public ArrayList<EventGroup> 发射器事件组 = new ArrayList<>(), 子弹事件组 = new ArrayList<>();

	public boolean 遮罩, 反弹板, 力场;
	
	public String stringData;
	public Layer layer;

	public static Tuple<BulletEmitter, Action> parseFrom(String content, Layer layer)
	{
		BulletEmitter e = new BulletEmitter();
		e.stringData = content;
		e.layer = layer;
		MRef<String> tempRef_content = new MRef<String>(content);
		e.ID = readInt(tempRef_content);
		e.层ID = readInt(tempRef_content);
		//boolean 绑定状态 = readBool(tempRef_content);
		readBool(tempRef_content);
		int 绑定ID = readInt(tempRef_content);
		boolean 相对方向 = readBool(tempRef_content);
		readString(tempRef_content);
		e.位置坐标.x.baseValue = readFloat(tempRef_content);
		e.位置坐标.y.baseValue = readFloat(tempRef_content);
		e.生命.begin = readInt(tempRef_content);
		e.生命.lifeTime = readInt(tempRef_content);
		e.发射坐标.x = readFloat(tempRef_content);
		e.发射坐标.y = readFloat(tempRef_content);
		e.半径.baseValue = readFloat(tempRef_content);
		e.半径方向.baseValue = readFloat(tempRef_content);
		e.半径方向_坐标指定 = readPosition(tempRef_content);
		e.条数.baseValue = readFloat(tempRef_content);
		e.周期.baseValue = readInt(tempRef_content);
		e.发射角度.baseValue = readFloat(tempRef_content);
		e.发射角度_坐标指定 = readPosition(tempRef_content);
		e.范围.baseValue = readFloat(tempRef_content);
		e.发射器运动.motion.speed.baseValue = readFloat(tempRef_content);
		e.发射器运动.motion.speedDirection.baseValue = readFloat(tempRef_content);
		e.发射器运动.speedDirectionPosition = readPosition(tempRef_content);
		e.发射器运动.motion.acceleration.baseValue = readFloat(tempRef_content);
		e.发射器运动.motion.accelerationDirection.baseValue = readFloat(tempRef_content);
		e.发射器运动.accelerationDirectionPosition = readPosition(tempRef_content);

		e.子弹生命 = readInt(tempRef_content);
		e.子弹类型 = readInt(tempRef_content);

		e.宽比 = readFloat(tempRef_content);
		e.高比 = readFloat(tempRef_content);

		e.子弹颜色.R = readFloat(tempRef_content);
		e.子弹颜色.G = readFloat(tempRef_content);
		e.子弹颜色.B = readFloat(tempRef_content);
		e.子弹颜色.A = readFloat(tempRef_content);

		e.朝向.baseValue = readFloat(tempRef_content);
		e.朝向_坐标指定 = readPosition(tempRef_content);
		e.朝向与速度方向相同 = readBool(tempRef_content);

		e.子弹运动.motion.speed.baseValue = readFloat(tempRef_content);
		e.子弹运动.motion.speedDirection.baseValue = readFloat(tempRef_content);
		e.子弹运动.speedDirectionPosition = readPosition(tempRef_content);
		e.子弹运动.motion.acceleration.baseValue = readFloat(tempRef_content);
		e.子弹运动.motion.accelerationDirection.baseValue = readFloat(tempRef_content);
		e.子弹运动.accelerationDirectionPosition = readPosition(tempRef_content);

		e.横比 = readFloat(tempRef_content);
		e.纵比 = readFloat(tempRef_content);

		e.雾化效果 = readBool(tempRef_content);
		e.消除效果 = readBool(tempRef_content);
		e.高光效果 = readBool(tempRef_content);
		e.拖影效果 = readBool(tempRef_content);
		e.出屏即消 = readBool(tempRef_content);
		e.无敌状态 = readBool(tempRef_content);

		e.发射器事件组 = EventGroup.parseEventGroups(readString(tempRef_content));
		e.子弹事件组 = EventGroup.parseEventGroups(readString(tempRef_content));

		e.位置坐标.x.randValue = readFloat(tempRef_content);
		e.位置坐标.y.randValue = readFloat(tempRef_content);

		e.半径.randValue = readFloat(tempRef_content);
		e.半径方向.randValue = readFloat(tempRef_content);

		e.条数.randValue = readFloat(tempRef_content);
		e.周期.randValue = readFloat(tempRef_content);

		e.发射角度.randValue = readFloat(tempRef_content);
		e.范围.randValue = readFloat(tempRef_content);
		e.发射器运动.motion.speed.randValue = readFloat(tempRef_content);
		e.发射器运动.motion.speedDirection.randValue = readFloat(tempRef_content);
		e.发射器运动.motion.acceleration.randValue = readFloat(tempRef_content);
		e.发射器运动.motion.accelerationDirection.randValue = readFloat(tempRef_content);
		e.朝向.randValue = readFloat(tempRef_content);

		e.子弹运动.motion.speed.randValue = readFloat(tempRef_content);
		e.子弹运动.motion.speedDirection.randValue = readFloat(tempRef_content);
		e.子弹运动.motion.acceleration.randValue = readFloat(tempRef_content);
		e.子弹运动.motion.accelerationDirection.randValue = readFloat(tempRef_content);

		e.遮罩 = readBool(tempRef_content);
		e.反弹板 = readBool(tempRef_content);
		e.力场 = readBool(tempRef_content);

		boolean 深度绑定 = readBool(tempRef_content);

		Action binder = ()->
		{
		};

		if (绑定ID != -1)
		{
			binder = () -> e.绑定状态 = layer.findBulletEmitterByID(绑定ID).bind(e,深度绑定,相对方向);
		}

		content = tempRef_content.argValue;
		if (!content.equals(""))
		{
			throw new RuntimeException("发射器解析后剩余字符串：" + content);
		}

		return Tuple.Create(e,binder);
	}

	public final BindState bind(BindState.IBindable bindable, boolean depth, boolean relative)
	{
		BindState state = new BindState();
		state.Child = bindable;
		state.Parent = this;
		state.Depth = depth;
		state.Relative = relative;
		return state;
	}
}