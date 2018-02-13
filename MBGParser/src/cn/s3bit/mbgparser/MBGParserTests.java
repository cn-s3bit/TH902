package cn.s3bit.mbgparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MBGParserTests {

	public static void main(String[] args) {
		try {
			parseExamples("D:/ResavedExample");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void parseExamples(String folder) throws IOException {
		File dir = new File(folder);
		for (File file : dir.listFiles()) {
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] b = new byte[fileInputStream.available()];
			fileInputStream.read(b);
			fileInputStream.close();
			MBGData.parseFrom(new String(b));
		}
	}
}
