package cn.s3bit.mbgparser;

public class MotionWithRand extends Motion<ValueWithRand> {
	private static final long serialVersionUID = 9126840292962330341L;

	{
		acceleration = new ValueWithRand();
		accelerationDirection = new ValueWithRand();
		speed = new ValueWithRand();
		speedDirection = new ValueWithRand();
	}
}
