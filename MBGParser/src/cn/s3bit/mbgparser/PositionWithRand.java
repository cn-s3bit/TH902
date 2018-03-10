package cn.s3bit.mbgparser;

public class PositionWithRand extends Position<ValueWithRand> {
	private static final long serialVersionUID = -2631577873556976114L;

	{
		x = new ValueWithRand();
		y = new ValueWithRand();
	}
}
