package cn.s3bit.th902;

import cn.s3bit.th902.utils.IFont;

public abstract class PlatformRelatedInterfaces {
	public abstract IFont getFont(String fontName, boolean isBold, boolean isItalic, int size);
}
