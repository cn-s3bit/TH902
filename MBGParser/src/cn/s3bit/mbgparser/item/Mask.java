package cn.s3bit.mbgparser.item;

import java.util.*;

import cn.s3bit.mbgparser.*;
import cn.s3bit.mbgparser.event.*;
import static cn.s3bit.mbgparser.MBGUtils.*;

public class Mask implements BindState.IBindable
{
	public int ID, 层ID;

	public BindState 绑定状态;

	public Position<Float> 位置坐标;

	public Life 生命;

	public float 半宽, 半高;

	public boolean 启用圆形;

	public ControlType 类型 = ControlType.values()[0];

	public int 控制ID;

	public MotionWithPosition<ValueWithRand,Float> 运动;

	public ArrayList<EventGroup> 发射器事件组, 子弹事件组;

	public static Tuple<Mask, Action> parseFrom(String content, Layer layer)
	{
		Mask m = new Mask();

		MRef<String> tempRef_content = new MRef<String>(content);
		m.ID = readInt(tempRef_content);
		content = tempRef_content.argValue;
		m.层ID = readInt(tempRef_content);
		m.位置坐标.x = readFloat(tempRef_content);
		m.位置坐标.y = readFloat(tempRef_content);

		m.生命.begin = readInt(tempRef_content);
		m.生命.lifeTime = readInt(tempRef_content);
		m.半宽 = readFloat(tempRef_content);
		m.半高 = readFloat(tempRef_content);
		m.启用圆形 = readBool(tempRef_content);

		m.类型 = ControlType.forValue(readInt(tempRef_content));
		m.控制ID = readInt(tempRef_content);
		m.运动.motion.speed.baseValue = readFloat(tempRef_content);
		m.运动.motion.speedDirection.baseValue = readFloat(tempRef_content);
		m.运动.speedDirectionPosition = readPosition(tempRef_content);
		m.运动.motion.acceleration.baseValue = readFloat(tempRef_content);
		m.运动.motion.accelerationDirection.baseValue = readFloat(tempRef_content);
		m.运动.accelerationDirectionPosition = readPosition(tempRef_content);

		m.发射器事件组 = EventGroup.parseEventGroups(readString(tempRef_content));
		m.子弹事件组 = EventGroup.parseEventGroups(readString(tempRef_content));

		m.运动.motion.speed.randValue = readFloat(tempRef_content);
		m.运动.motion.speedDirection.randValue = readFloat(tempRef_content);
		m.运动.motion.acceleration.randValue = readFloat(tempRef_content);
		m.运动.motion.accelerationDirection.randValue = readFloat(tempRef_content);

		int 绑定ID = readInt(tempRef_content);
		boolean 深度绑定 = readBool(tempRef_content);

		Action binder = () ->
		{
		};
		if (绑定ID != -1)
		{
			binder = () -> m.绑定状态 = layer.findBulletEmitterByID(绑定ID).bind(m, 深度绑定, false);
		}

		content = tempRef_content.argValue;
		if (!content.equals(""))
		{
			throw new RuntimeException("遮罩解析后剩余字符串：" + content);
		}

		return Tuple.Create(m,binder);
	}
}