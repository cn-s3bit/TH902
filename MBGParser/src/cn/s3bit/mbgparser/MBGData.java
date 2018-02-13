package cn.s3bit.mbgparser;

import cn.s3bit.mbgparser.event.*;
import static cn.s3bit.mbgparser.MBGUtils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class MBGData {
	public String version;

	public int totalFrame;

	public Center center;

	public Layer layer1, layer2, layer3, layer4;

	public List<Sound> sounds;

	public List<GlobalEvents> globalEvents;

	private void _processNormalTitle(String title, String content, BufferedReader mbg) throws IOException
	{
		switch (title)
		{
			case "Center":
				center = Center.parseFromContent(content);
				break;

			case "Totalframe":
				totalFrame = Integer.parseInt(content);
				break;

			case "Layer1":
				layer1 = Layer.parseFrom(content, mbg);
				break;

			case "Layer2":
				layer2 = Layer.parseFrom(content, mbg);
				break;

			case "Layer3":
				layer3 = Layer.parseFrom(content, mbg);
				break;

			case "Layer4":
				layer4 = Layer.parseFrom(content, mbg);
				break;

			default:
				throw new IllegalArgumentException("未知的标签:" + title);
		}
	}

	private boolean _processNumberTitle(String title, BufferedReader mbg) throws IOException
	{
		if (title.contains("Sounds"))
		{
			sounds = Sound.parseSounds(title, mbg);
			return true;
		}
		else if (title.contains("GlobalEvents"))
		{
			globalEvents = GlobalEvents.parseEvents(title, mbg);
			return true;
		}

		return false;
	}

	@Deprecated
	List<Sound> GlobalEvent(String title, BufferedReader mbg)
	{
		throw new AssertionError();
	}

	public static MBGData parseFrom(String mbgData) throws IOException
	{
		BufferedReader mbg = new BufferedReader(new StringReader(mbgData));

		MBGData data = new MBGData()
		{
			{
				version = mbg.readLine();
			}
		};

		if (!data.version.equals("Crazy Storm Data 1.01"))
			throw new RuntimeException("未知版本的CrazyStorm数据。");

		while (mbg.ready())
		{
			String content = mbg.readLine();
			if (content == null) break;
			else if (isNullOrWhiteSpace(content)) continue;

			MRef<String> ref = new MRef<String>(content);
			String title = readString(ref, ':');
			content = ref.argValue;

			boolean processed = data._processNumberTitle(title, mbg);
			if (!processed)
				data._processNormalTitle(title, content, mbg);
		}

		return data;
	}
}
