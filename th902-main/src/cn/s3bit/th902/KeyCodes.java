package cn.s3bit.th902;

import cn.s3bit.th902.utils.SerializableBitSet;

public final class KeyCodes {
	public static final int positiveKey = 0;
	public static final int negativeKey = 1;
	public static final int up = 2;
	public static final int down = 3;
	public static final int left = 4;
	public static final int right = 5;
	public static final int shift = 6;
	
	public static boolean mask(final SerializableBitSet actionBits, final int keyCode) {
		return actionBits.get(keyCode);
	}
	
	public static void setOn(final SerializableBitSet actionBits, final int keyCode) {
		actionBits.set(keyCode);
	}
	
	public static void setOff(final SerializableBitSet actionBits, final int keyCode) {
		actionBits.clear(keyCode);
	}
	
	public static void set(final SerializableBitSet actionBits, final int keyCode, final boolean isOn) {
		if (isOn)
			actionBits.set(keyCode);
		else
			actionBits.clear(keyCode);
	}
}
