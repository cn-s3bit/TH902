package cn.s3bit.mbgparser.item;

import java.util.*;

import cn.s3bit.mbgparser.*;
import cn.s3bit.mbgparser.event.*;
import static cn.s3bit.mbgparser.MBGUtils.*;

public class LazerEmitter implements BindState.IBindable
{
	public int ID, 层ID;

	public BindState 绑定状态 = new BindState();

	public Position<Float> 位置坐标 = new Position<>();

	public Life 生命 = new Life();

	public ValueWithRand 半径 = new ValueWithRand(), 半径方向 = new ValueWithRand();

	public Position<Float> 半径方向_坐标指定 = new Position<>();

	public ValueWithRand 条数 = new ValueWithRand(), 周期 = new ValueWithRand();

	public ValueWithRand 发射角度 = new ValueWithRand();

	public Position<Float> 发射角度_坐标指定 = new Position<>();

	public ValueWithRand 范围 = new ValueWithRand();

	public MotionWithPosition<ValueWithRand, Float> 发射器运动 = new MotionWithRandWithPosition<Float>();

	public int 子弹生命;

	public int 类型;

	public float 宽比, 长度;

	public float 不透明度;

	public MotionWithPosition<ValueWithRand, Float> 子弹运动 = new MotionWithRandWithPosition<Float>();

	public float 横比, 纵比;

	public boolean 高光效果, 出屏即消, 无敌状态;

	public ArrayList<EventGroup> 发射器事件组 = new ArrayList<>(), 子弹事件组 = new ArrayList<>();

	public boolean 启用射线激光;

	public static Tuple<LazerEmitter, Action> parseFrom(String c, Layer layer)
	{
		LazerEmitter l = new LazerEmitter();

		MRef<String> tempRef_c = new MRef<String>(c);
		l.ID = readInt(tempRef_c);
		c = tempRef_c.argValue;
		l.层ID = readInt(tempRef_c);
		//boolean 绑定状态 = readBool(tempRef_c); //CS里可能已废弃
		readBool(tempRef_c);
		int 绑定ID = readInt(tempRef_c);
		boolean 相对方向 = readBool(tempRef_c);
		readString(tempRef_c);
		 //TODO:CrazyStorm未描述此格数据内容
		l.位置坐标.x = readFloat(tempRef_c);
		l.位置坐标.y = readFloat(tempRef_c);
		l.生命.begin = readInt(tempRef_c);
		l.生命.lifeTime = readInt(tempRef_c);
		l.半径.baseValue = readFloat(tempRef_c);
		l.半径方向.baseValue = readFloat(tempRef_c);
		l.半径方向_坐标指定 = readPosition(tempRef_c);
		l.条数.baseValue = readFloat(tempRef_c);
		l.周期.baseValue = readFloat(tempRef_c);
		l.发射角度.baseValue = readFloat(tempRef_c);
		l.发射角度_坐标指定 = readPosition(tempRef_c);
		l.范围.baseValue = readFloat(tempRef_c);
		l.发射器运动.motion.speed.baseValue = readFloat(tempRef_c);
		l.发射器运动.motion.speedDirection.baseValue = readFloat(tempRef_c);
		l.发射器运动.speedDirectionPosition = readPosition(tempRef_c);
		l.发射器运动.motion.acceleration.baseValue = readFloat(tempRef_c);
		l.发射器运动.motion.accelerationDirection.baseValue = readFloat(tempRef_c);
		l.发射器运动.accelerationDirectionPosition = readPosition(tempRef_c);
		l.子弹生命 = readInt(tempRef_c);
		l.类型 = readInt(tempRef_c);
		l.宽比 = readFloat(tempRef_c);
		l.长度 = readFloat(tempRef_c);
		l.不透明度 = readFloat(tempRef_c);
		l.启用射线激光 = readBool(tempRef_c);
		l.子弹运动.motion.speed.baseValue = readFloat(tempRef_c);
		l.子弹运动.motion.speedDirection.baseValue = readFloat(tempRef_c);
		l.子弹运动.speedDirectionPosition = readPosition(tempRef_c);
		l.子弹运动.motion.acceleration.baseValue = readFloat(tempRef_c);
		l.子弹运动.motion.accelerationDirection.baseValue = readFloat(tempRef_c);
		l.子弹运动.accelerationDirectionPosition = readPosition(tempRef_c);
		l.横比 = readFloat(tempRef_c);
		l.纵比 = readFloat(tempRef_c);
		l.高光效果 = readBool(tempRef_c);
		l.出屏即消 = readBool(tempRef_c);
		l.无敌状态 = readBool(tempRef_c);
		readString(tempRef_c);
		l.发射器事件组 = EventGroup.parseEventGroups(readString(tempRef_c));
		l.子弹事件组 = EventGroup.parseEventGroups(readString(tempRef_c));
		l.半径.randValue = readFloat(tempRef_c);
		l.半径方向.randValue = readFloat(tempRef_c);
		l.条数.randValue = readFloat(tempRef_c);
		l.周期.randValue = readFloat(tempRef_c);
		l.发射角度.randValue = readFloat(tempRef_c);
		l.范围.randValue = readFloat(tempRef_c);
		l.发射器运动.motion.speed.randValue = readFloat(tempRef_c);
		l.发射器运动.motion.speedDirection.randValue = readFloat(tempRef_c);
		l.发射器运动.motion.acceleration.randValue = readFloat(tempRef_c);
		l.发射器运动.motion.accelerationDirection.randValue = readFloat(tempRef_c);
		l.子弹运动.motion.speed.randValue = readFloat(tempRef_c);
		l.子弹运动.motion.speedDirection.randValue = readFloat(tempRef_c);
		l.子弹运动.motion.acceleration.randValue = readFloat(tempRef_c);
		l.子弹运动.motion.accelerationDirection.randValue = readFloat(tempRef_c);
		boolean 深度绑定 = readBool(tempRef_c);

		Action binder = () ->
		{
		};
		if (绑定ID != -1)
		{
			binder = () -> l.绑定状态 = layer.findBulletEmitterByID(绑定ID).bind(l, 深度绑定, 相对方向);
		}

		c = tempRef_c.argValue;
		if (!c.equals(""))
		{
			throw new RuntimeException("激光发射器解析后剩余字符串：" + c);
		}

		return Tuple.Create(l, binder);
	}
}