package cn.s3bit.mbgparser;

import java.io.Serializable;

public class Motion<T> implements Serializable {
	private static final long serialVersionUID = -5191575372238099177L;
	public T speed, acceleration;
	public T speedDirection, accelerationDirection;
}
