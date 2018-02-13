package cn.s3bit.mbgparser.item;

import cn.s3bit.mbgparser.*;
import static cn.s3bit.mbgparser.MBGUtils.*;

public final class ForceField
{
	public int ID, 层ID;

	public Position<Float> 位置坐标 = new Position<>();

	public Life 生命 = new Life();

	public float 半宽, 半高;

	public boolean 启用圆形;

	public ControlType 类型 = ControlType.values()[0];

	public int 控制ID;

	public Motion<ValueWithRand> 运动 = new MotionWithRand();

	public float 力场加速度, 力场加速度方向;

	public boolean 中心吸力, 中心斥力;

	public float 影响速度;

	public static ForceField parseFrom(String content)
	{
		ForceField f = new ForceField();

		MRef<String> tempRef_content = new MRef<String>(content);
		f.ID = readInt(tempRef_content);
		content = tempRef_content.argValue;
		f.层ID = readInt(tempRef_content);
		f.位置坐标.x = readFloat(tempRef_content);
		f.位置坐标.y = readFloat(tempRef_content);
		f.生命.begin = readInt(tempRef_content);
		f.生命.lifeTime = readInt(tempRef_content);
		f.半宽 = readFloat(tempRef_content);
		f.半高 = readFloat(tempRef_content);
		f.启用圆形 = readBool(tempRef_content);
		f.类型 = ControlType.forValue(readInt(tempRef_content));
		f.控制ID = readInt(tempRef_content);
		f.运动.speed.baseValue = readFloat(tempRef_content);
		f.运动.speedDirection.baseValue = readFloat(tempRef_content);
		f.运动.acceleration.baseValue = readFloat(tempRef_content);
		f.运动.accelerationDirection.baseValue = readFloat(tempRef_content);
		f.力场加速度 = readFloat(tempRef_content);
		f.力场加速度方向 = readFloat(tempRef_content);
		f.中心吸力 = readBool(tempRef_content);
		f.中心斥力 = readBool(tempRef_content);
		f.影响速度 = readFloat(tempRef_content);
		f.运动.speed.randValue = readFloat(tempRef_content);
		f.运动.speedDirection.randValue = readFloat(tempRef_content);
		f.运动.acceleration.randValue = readFloat(tempRef_content);
		f.运动.accelerationDirection.randValue = readFloat(tempRef_content);
		content = tempRef_content.argValue;
		if (!content.equals(""))
		{
			throw new RuntimeException("力场解析后剩余字符串：" + content);
		}

		return f;
	}

	public ForceField clone()
	{
		ForceField varCopy = new ForceField();

		varCopy.ID = this.ID;
		varCopy.层ID = this.层ID;
		varCopy.位置坐标 = this.位置坐标;
		varCopy.生命 = this.生命;
		varCopy.半宽 = this.半宽;
		varCopy.半高 = this.半高;
		varCopy.启用圆形 = this.启用圆形;
		varCopy.类型 = this.类型;
		varCopy.控制ID = this.控制ID;
		varCopy.运动 = this.运动;
		varCopy.力场加速度 = this.力场加速度;
		varCopy.力场加速度方向 = this.力场加速度方向;
		varCopy.中心吸力 = this.中心吸力;
		varCopy.中心斥力 = this.中心斥力;
		varCopy.影响速度 = this.影响速度;

		return varCopy;
	}
}