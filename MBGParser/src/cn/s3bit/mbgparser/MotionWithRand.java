package cn.s3bit.mbgparser;

public class MotionWithRand extends Motion<ValueWithRand> {
	{
		acceleration = new ValueWithRand();
		accelerationDirection = new ValueWithRand();
		speed = new ValueWithRand();
		speedDirection = new ValueWithRand();
	}
}
