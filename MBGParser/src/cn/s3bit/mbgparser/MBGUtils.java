package cn.s3bit.mbgparser;

public final class MBGUtils {
	public static boolean isNullOrWhiteSpace(String str) {
		return str == null || str.trim().isEmpty();
	}
	public static String readString(MRef<String> line) {
		return readString(line, ',');
	}
	public static String readString(MRef<String> line, char splitter) {
		if (line == null || isNullOrWhiteSpace(line.data))
            throw new IllegalArgumentException("无法从空字符串读取信息。");

        int spl = line.data.indexOf(splitter);
        if (spl != -1)
        {
            String ret = line.data.substring(0, spl);
            line.data = line.data.substring(1 + spl);
            return ret;
        }
        else
        {
            String ret = line.data;
            line.data = "";
            return ret;
        }
	}
	public static boolean readBool(MRef<String> line) {
		return readBool(line, ',');
	}
	public static boolean readBool(MRef<String> line, char splitter) {
        return Boolean.parseBoolean(readString(line, splitter));
    }
	
	public static long readUInt(MRef<String> line) {
        return readUInt(line, ',');
    }
	public static long readUInt(MRef<String> line, char splitter) {
        return Long.parseLong(readString(line, splitter));
    }

	public static int readInt(MRef<String> line) {
        return readInt(line, ',');
    }
    public static int readInt(MRef<String> line, char splitter) {
        return Integer.parseInt(readString(line, splitter));
    }

    public static float readFloat(MRef<String> line) {
        return readFloat(line, ',');
    }
    public static float readFloat(MRef<String> line, char splitter) {
        return Float.parseFloat(readString(line, splitter));
    }

    public static Position<Float> readPosition(MRef<String> line) {
    	return readPosition(line, ',');
    }
    public static Position<Float> readPosition(MRef<String> line, char splitter) {
        String content = readString(line, splitter);

        int px1 = content.indexOf(':') + 1;
        int px2 = content.indexOf('Y');

        int py1 = content.lastIndexOf(':') + 1;
        int py2 = content.lastIndexOf('}');

        Position<Float> p = new Position<>();
        p.x = Float.parseFloat(content.substring(px1, px2).trim());
        p.y = Float.parseFloat(content.substring(py1, py2).trim());

        return p;
    }
}
