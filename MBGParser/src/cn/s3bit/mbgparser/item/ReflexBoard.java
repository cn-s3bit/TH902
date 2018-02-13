package cn.s3bit.mbgparser.item;

import java.util.*;

import cn.s3bit.mbgparser.*;
import cn.s3bit.mbgparser.event.*;
import static cn.s3bit.mbgparser.MBGUtils.*;

public final class ReflexBoard
{
	public int ID, 层ID;

	public Position<Float> 位置坐标 = new Position<>();

	public Life 生命 = new Life();

	public float 长度, 角度;

	public int 次数;

	public Motion<ValueWithRand> 运动 = new MotionWithRand();

	public ArrayList<ReflexBoardAction> 碰撞事件组 = new ArrayList<>();

	public static ReflexBoard parseFrom(String content)
	{
		ReflexBoard r = new ReflexBoard();

		MRef<String> tempRef_content = new MRef<String>(content);
		r.ID = readInt(tempRef_content);
		content = tempRef_content.argValue;
		r.层ID = readInt(tempRef_content);

		r.位置坐标.x = readFloat(tempRef_content);
		r.位置坐标.y = readFloat(tempRef_content);

		r.生命.begin = readInt(tempRef_content);
		r.生命.lifeTime = readInt(tempRef_content);

		r.长度 = readFloat(tempRef_content);
		r.角度 = readFloat(tempRef_content);
		r.次数 = readInt(tempRef_content);

		r.运动.speed.baseValue = readFloat(tempRef_content);
		r.运动.speedDirection.baseValue = readFloat(tempRef_content);

		r.运动.acceleration.baseValue = readFloat(tempRef_content);
		r.运动.accelerationDirection.baseValue = readFloat(tempRef_content);

		r.碰撞事件组 = ReflexBoardAction.parseActions(readString(tempRef_content));

		r.运动.speed.randValue = readFloat(tempRef_content);
		r.运动.speedDirection.randValue = readFloat(tempRef_content);

		r.运动.acceleration.randValue = readFloat(tempRef_content);
		r.运动.accelerationDirection.randValue = readFloat(tempRef_content);

		content = tempRef_content.argValue;
		if (!content.equals(""))
		{
			throw new RuntimeException("反弹板解析后字符串剩余：" + content);
		}

		return r;
	}

	public ReflexBoard clone()
	{
		ReflexBoard varCopy = new ReflexBoard();

		varCopy.ID = this.ID;
		varCopy.层ID = this.层ID;
		varCopy.位置坐标 = this.位置坐标;
		varCopy.生命 = this.生命;
		varCopy.长度 = this.长度;
		varCopy.角度 = this.角度;
		varCopy.次数 = this.次数;
		varCopy.运动 = this.运动;
		varCopy.碰撞事件组 = this.碰撞事件组;

		return varCopy;
	}
}