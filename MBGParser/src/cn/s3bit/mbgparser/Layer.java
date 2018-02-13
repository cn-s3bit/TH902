package cn.s3bit.mbgparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.s3bit.mbgparser.item.*;
import static cn.s3bit.mbgparser.MBGUtils.*;

public class Layer
{
	public String name;
	public Life life;

	public List<BulletEmitter> BulletEmitters;
	public List<ReflexBoard> ReflexBoards;
	public List<ForceField> ForceFields;
	public List<Mask> Masks;
	public List<LazerEmitter> LazerEmitters;

	private void LoadContent(
		BufferedReader mbg,
		int bulletEmitterCount,
		int lazerEmitterCount,
		int maskEmitterCount,
		int reflexBoardCount,
		int forceFieldCount) throws IOException
	{
		List<Action> linkers = new ArrayList<Action>();

		BulletEmitters = new ArrayList<BulletEmitter>();
		for (int i = 0; i < bulletEmitterCount; ++i)
		{
			Tuple<BulletEmitter, Action> result = BulletEmitter.parseFrom(mbg.readLine(), this);
			linkers.add(result.Item2);
			BulletEmitters.add(result.Item1);
		}


		LazerEmitters = new ArrayList<LazerEmitter>();
		for (int i = 0; i < lazerEmitterCount; ++i)
		{
			Tuple<LazerEmitter, Action> result = LazerEmitter.parseFrom(mbg.readLine(),this);
			linkers.add(result.Item2);
			LazerEmitters.add(result.Item1);
		}

		Masks = new ArrayList<Mask>();
		for (int i = 0; i < maskEmitterCount; ++i)
		{
			Tuple<Mask, Action> result = Mask.parseFrom(mbg.readLine(),this);
			linkers.add(result.Item2);
			Masks.add(result.Item1);
		}

		ReflexBoards = new ArrayList<ReflexBoard>();
		for (int i = 0; i < reflexBoardCount; ++i)
			ReflexBoards.add(ReflexBoard.parseFrom(mbg.readLine()));

		ForceFields = new ArrayList<ForceField>();
		for (int i = 0; i < forceFieldCount; ++i)
			ForceFields.add(ForceField.parseFrom(mbg.readLine()));

		for (Action l : linkers)
			l.invoke();
	}

	public BulletEmitter findBulletEmitterByID(int id)
	{
		for (BulletEmitter i : BulletEmitters)
			if (i.ID == id)
				return i;
		throw new IllegalArgumentException("找不到子弹发射器" + id);
	}

	public static Layer parseFrom(String contentRaw, BufferedReader mbg) throws IOException
	{
		if (contentRaw.equals("empty"))
			return null;
		else
		{
			MRef<String> content = new MRef<String>(contentRaw);
			Layer layer = new Layer();
			layer.name = readString(content);
			layer.life.begin = Integer.parseInt(readString(content));
			layer.life.lifeTime = Integer.parseInt(readString(content));
			int bulletEmitterCount = Integer.parseInt(readString(content));
			int lazerEmitterCount = Integer.parseInt(readString(content));
			int maskEmitterCount = Integer.parseInt(readString(content));
			int reflexBoardCount = Integer.parseInt(readString(content));
			int forceFieldCount = Integer.parseInt(readString(content));

			layer.LoadContent(
				mbg,
				bulletEmitterCount,
				lazerEmitterCount,
				maskEmitterCount,
				reflexBoardCount,
				forceFieldCount);

			return layer;
		}
	}
}
