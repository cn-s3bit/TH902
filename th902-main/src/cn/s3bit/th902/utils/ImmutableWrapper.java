package cn.s3bit.th902.utils;

import com.badlogic.gdx.math.MathUtils;

/**
 * In order to use changeable objects like Vector2 as Key in the HashMap,
 * we should create an immutable object.
 */
public class ImmutableWrapper<T> {
	public static <T> ImmutableWrapper<T> wrap(T data) {
		return new ImmutableWrapper<T>(data);
	}
	
	private final Integer mSeed;
	private final T mData;
	
	public ImmutableWrapper(T data) {
		mSeed = MathUtils.randomSign();
		mData = data;
	}
	
	@Override
	public int hashCode() {
		return mSeed;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ImmutableWrapper ? ((ImmutableWrapper<?>) obj).mData.equals(mData) : obj.equals(mData);
	}

	public T getData() {
		return mData;
	}
}