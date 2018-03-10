package cn.s3bit.mbgparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static cn.s3bit.mbgparser.MBGUtils.*;

public class Sound implements Serializable {
	private static final long serialVersionUID = -6187293338097279439L;
	public int BulletType;
	public String FileName;
	public float Volume;

	private static Sound _parseFrom(MRef<String> c) {
		Sound s = new Sound();
		s.BulletType = readInt(c, '_');
		s.FileName = readString(c, '_');
		s.Volume = readFloat(c, '_');

		if (!c.argValue.equals(""))
			throw new AssertionError("音效字符串解析后剩余：" + c);

		return s;
	}

	public static List<Sound> parseSounds(String title, BufferedReader mbg) throws IOException {
		int soundCount = Integer.parseInt(title.substring(0, title.indexOf("Sounds")).trim());
		List<Sound> ret = new ArrayList<>();
		for (int i = 0; i < soundCount; ++i)
			ret.add(_parseFrom(new MRef<String>(mbg.readLine())));
		return ret;
	}
}
