package cn.s3bit.th902.danmaku.crazystorm;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.mbgparser.Layer;
import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.danmaku.mbg.MBGScene;
import cn.s3bit.th902.gamecontents.JudgingSystem;

class Time
{
	// private static WindowInputCapturer wic;

	private static int clcount = 0;

	private static int clwait = 0;

	public static int total = 200;

	public static int now = 1;

	public static int left = 1;

	private static boolean Aim1;

	private static boolean Aim2;

	private static boolean Aim3;

	private static int mouseleft;

	private static int mousex;

	private static boolean search = false;

	private static String text = "";

	private static String textsave = "";

	private static int time = 0;

	public static Vector2 quake = new Vector2(0f, 0f);

	public static float stop = 1f;

	public static boolean Playing = false;

	public static boolean Pause = false;

	// public static ArrayList<GlobalEvent> GE = new ArrayList<GlobalEvent>();

	// public static ArrayList<Inte> GEcount = new ArrayList<int>();

	public static void Clear()
	{
		total = 200;
		now = 1;
		left = 1;
		text = "";
		textsave = "";
		time = 0;
		Playing = false;
		Pause = false;
		// GE.Clear();
		// GEcount.Clear();
	}
    
    /*public static void Initialize(MBGScene scene)
    {
        Playing = true;
        ArrayList<Layer> layerList = new ArrayList<>();
        layerList.add(scene.mbgData.layer1);
        layerList.add(scene.mbgData.layer2);
        layerList.add(scene.mbgData.layer3);
        layerList.add(scene.mbgData.layer4);
        if (!Pause)
        {
            Iterator<Layer> enumerator = layerList.iterator();
            try
            {
                while (enumerator.hasNext())
                {
                    Layer current = enumerator.next();
                    enumerator2 = current.BulletEmitters.GetEnumerator();
                    ArrayList<String>.Enumerator enumerator4;
                    try
                    {
                        while (enumerator2.MoveNext())
                        {
                            Batch current2 = enumerator2.Current;
                            current2.Selecting = false;
                            current2.copys = (current2.Copy() as Batch);
                            enumerator3 = current2.copys.Parentevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current3 = enumerator3.Current;
                                    current3.loop = 0;
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                            float num = MathUtils.lerp(0f - current2.copys.rand.speed, current2.copys.rand.speed, RandomPool.get(5).random());
                            int num2 = (int)MathUtils.lerp(0f - current2.copys.rand.speedd, current2.copys.rand.speedd, RandomPool.get(5).random());
                            float num3 = MathUtils.lerp(0f - current2.copys.rand.aspeed, current2.copys.rand.aspeed, RandomPool.get(5).random());
                            int num4 = (int)MathUtils.lerp(0f - current2.copys.rand.aspeedd, current2.copys.rand.aspeedd, RandomPool.get(5).random());
                            if (current2.fx == -99998f)
                            {
                                current2.copys.fx = current2.x - 4f;
                            }
                            if (current2.fx == -99999f)
                            {
                                current2.copys.fx = JudgingSystem.playerJudge.x;
                            }
                            if (current2.fy == -99998f)
                            {
                                current2.copys.fy = current2.y + 16f;
                            }
                            if (current2.fy == -99999f)
                            {
                                current2.copys.fy = JudgingSystem.playerJudge.y;
                            }
                            if (current2.speedd == -99999f)
                            {
                                current2.copys.speedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current2.copys.fx, current2.copys.fy));
                            }
                            if (current2.aspeedd == -99999f)
                            {
                                current2.copys.aspeedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current2.copys.fx, current2.copys.fy));
                            }
                            current2.copys.aspeed += num3;
                            current2.copys.aspeedd += (float)num4;
                            current2.copys.speed += num;
                            current2.copys.speedd += (float)num2;
                            current2.copys.aspeedx = current2.copys.aspeed * MathUtils.cosDeg((current2.copys.aspeedd));
                            current2.copys.aspeedy = current2.copys.aspeed * MathUtils.sinDeg((current2.copys.aspeedd));
                            current2.copys.speedx = current2.copys.speed * MathUtils.cosDeg((current2.copys.speedd));
                            current2.copys.speedy = current2.copys.speed * MathUtils.sinDeg((current2.copys.speedd));
                            current2.copys.bfdirection = current2.fdirection;
                            current2.copys.bsonaspeedd = current2.sonaspeedd;
                            if (current2.fdirection == -99998f)
                            {
                                current2.copys.fdirection = GameHelper.twoPointAngle(current2.x - 4f, current2.y + 16f, current2.copys.fx, current2.copys.fy));
                            }
                            else if (current2.fdirection == -99999f)
                            {
                                current2.copys.fdirection = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current2.copys.fx, current2.copys.fy));
                            }
                            else if (current2.fdirection == -100000f)
                            {
                                current2.copys.fdirection = GameHelper.twoPointAngle(current2.fdirections.X, current2.fdirections.Y, current2.copys.fx, current2.copys.fy));
                            }
                            if (current2.sonaspeedd == -99998f)
                            {
                                current2.copys.sonaspeedd = GameHelper.twoPointAngle(current2.x - 4f, current2.y + 16f, current2.copys.fx, current2.copys.fy));
                            }
                            else if (current2.sonaspeedd == -99999f)
                            {
                                current2.copys.sonaspeedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current2.copys.fx, current2.copys.fy));
                            }
                            else if (current2.sonaspeedd == -100000f)
                            {
                                current2.copys.sonaspeedd = GameHelper.twoPointAngle(current2.sonaspeedds.X, current2.sonaspeedds.Y, current2.copys.fx, current2.copys.fy));
                            }
                            if (current2.head == -100000f)
                            {
                                current2.copys.head = GameHelper.twoPointAngle(current2.heads.X, current2.heads.Y, current2.copys.fx, current2.copys.fx));
                            }
                            enumerator3 = current2.Parentevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current4 = enumerator3.Current;
                                    enumerator4 = current4.events.GetEnumerator();
                                    try
                                    {
                                        while (enumerator4.MoveNext())
                                        {
                                            String current5 = enumerator4.Current;
                                            String text = current5.Split('：')[0];
                                            String text2 = "";
                                            String key = "";
                                            String opreator = "";
                                            String opreator2 = "";
                                            String collector = "";
                                            String text3 = current5.Split('：')[1];
                                            int num5 = 0;
                                            String text4 = "";
                                            int num6 = 0;
                                            String text5 = "";
                                            float res = 0f;
                                            int num7 = 0;
                                            int special = 0;
                                            if (current5.Contains("且"))
                                            {
                                                collector = "且";
                                                text2 = text.Split("且".ToCharArray())[1];
                                                text = text.Split("且".ToCharArray())[0];
                                            }
                                            else if (current5.Contains("或"))
                                            {
                                                collector = "或";
                                                text2 = text.Split("或".ToCharArray())[1];
                                                text = text.Split("或".ToCharArray())[0];
                                            }
                                            if (text.Contains(">"))
                                            {
                                                key = text.Split('>')[0];
                                                opreator = ">";
                                                text = text.Split('>')[1];
                                            }
                                            if (text.Contains("="))
                                            {
                                                key = text.Split('=')[0];
                                                opreator = "=";
                                                text = text.Split('=')[1];
                                            }
                                            if (text.Contains("<"))
                                            {
                                                key = text.Split('<')[0];
                                                opreator = "<";
                                                text = text.Split('<')[1];
                                            }
                                            if (text2.Contains(">"))
                                            {
                                                String text31 = text2.Split('>')[0];
                                                opreator2 = ">";
                                                text2 = text2.Split('>')[1];
                                            }
                                            if (text2.Contains("="))
                                            {
                                                String text32 = text2.Split('=')[0];
                                                opreator2 = "=";
                                                text2 = text2.Split('=')[1];
                                            }
                                            if (text2.Contains("<"))
                                            {
                                                String text33 = text2.Split('<')[0];
                                                opreator2 = "<";
                                                text2 = text2.Split('<')[1];
                                            }
                                            if (current5.Contains("变化到"))
                                            {
                                                num5 = 0;
                                                String[] array = text3.Split("变".ToCharArray())[1].Split("，".ToCharArray());
                                                num6 = (int)Main.results[text3.Split("变".ToCharArray())[0]];
                                                text5 = text3.Split("变".ToCharArray())[0];
                                                if (array[0].Replace("化到", "").Contains('+'))
                                                {
                                                    if (array[0].Replace("化到", "").Split('+')[0] == "自身")
                                                    {
                                                        special = 3;
                                                    }
                                                    else if (array[0].Replace("化到", "").Split('+')[0] == "自机")
                                                    {
                                                        special = 4;
                                                    }
                                                    else
                                                    {
                                                        res = Float.parseFloat(array[0].Replace("化到", "").Split('+')[0]);
                                                    }
                                                }
                                                else if (array[0].Replace("化到", "") == "自身")
                                                {
                                                    special = 3;
                                                }
                                                else if (array[0].Replace("化到", "") == "自机")
                                                {
                                                    special = 4;
                                                }
                                                else
                                                {
                                                    res = Float.parseFloat(array[0].Replace("化到", ""));
                                                }
                                                text4 = array[1];
                                                num7 = int.Parse(array[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead = new EventRead();
                                                eventRead.condition = text;
                                                eventRead.result = text3;
                                                eventRead.condition2 = text2;
                                                eventRead.contype = (int)Main.conditions[key];
                                                eventRead.contype2 = (int)Main.conditions[key];
                                                eventRead.opreator = opreator;
                                                eventRead.opreator2 = opreator2;
                                                eventRead.collector = collector;
                                                eventRead.change = num5;
                                                eventRead.changetype = (int)Main.type[text4];
                                                eventRead.changevalue = num6;
                                                eventRead.changename = (int)Main.results[text5];
                                                eventRead.res = res;
                                                eventRead.special = special;
                                                if (array[0].Replace("化到", "").Contains('+'))
                                                {
                                                    eventRead.rand = Float.parseFloat(array[0].Replace("化到", "").Split('+')[1]);
                                                }
                                                eventRead.times = num7;
                                                if (array[2].Contains("("))
                                                {
                                                    eventRead.time = int.Parse(array[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current4.results.Add(eventRead);
                                            }
                                            else if (current5.Contains("增加"))
                                            {
                                                num5 = 1;
                                                String[] array2 = text3.Split("增".ToCharArray())[1].Split("，".ToCharArray());
                                                array2[0] = array2[0].Replace("加", "");
                                                num6 = (int)Main.results[text3.Split("增".ToCharArray())[0]];
                                                text5 = text3.Split("增".ToCharArray())[0];
                                                if (array2[0].Contains('+'))
                                                {
                                                    if (array2[0].Split('+')[0] == "自身")
                                                    {
                                                        special = 3;
                                                    }
                                                    else if (array2[0].Split('+')[0] == "自机")
                                                    {
                                                        special = 4;
                                                    }
                                                    else
                                                    {
                                                        res = Float.parseFloat(array2[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array2[0] == "自身")
                                                {
                                                    special = 3;
                                                }
                                                else if (array2[0] == "自机")
                                                {
                                                    special = 4;
                                                }
                                                else
                                                {
                                                    res = Float.parseFloat(array2[0]);
                                                }
                                                text4 = array2[1];
                                                num7 = int.Parse(array2[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead2 = new EventRead();
                                                eventRead2.condition = text;
                                                eventRead2.result = text3;
                                                eventRead2.condition2 = text2;
                                                eventRead2.contype = (int)Main.conditions[key];
                                                eventRead2.contype2 = (int)Main.conditions[key];
                                                eventRead2.opreator = opreator;
                                                eventRead2.opreator2 = opreator2;
                                                eventRead2.collector = collector;
                                                eventRead2.change = num5;
                                                eventRead2.changetype = (int)Main.type[text4];
                                                eventRead2.changevalue = num6;
                                                eventRead2.changename = (int)Main.results[text5];
                                                eventRead2.res = res;
                                                eventRead2.special = special;
                                                if (array2[0].Contains('+'))
                                                {
                                                    eventRead2.rand = Float.parseFloat(array2[0].Split('+')[1]);
                                                }
                                                eventRead2.times = num7;
                                                if (array2[2].Contains("("))
                                                {
                                                    eventRead2.time = int.Parse(array2[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current4.results.Add(eventRead2);
                                            }
                                            else if (current5.Contains("减少"))
                                            {
                                                num5 = 2;
                                                String[] array3 = text3.Split("减少".ToCharArray())[2].Split("，".ToCharArray());
                                                num6 = (int)Main.results[text3.Split("减少".ToCharArray())[0]];
                                                text5 = text3.Split("减少".ToCharArray())[0];
                                                if (array3[0].Contains('+'))
                                                {
                                                    if (array3[0].Split('+')[0] == "自身")
                                                    {
                                                        special = 3;
                                                    }
                                                    else if (array3[0].Split('+')[0] == "自机")
                                                    {
                                                        special = 4;
                                                    }
                                                    else
                                                    {
                                                        res = Float.parseFloat(array3[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array3[0] == "自身")
                                                {
                                                    special = 3;
                                                }
                                                else if (array3[0] == "自机")
                                                {
                                                    special = 4;
                                                }
                                                else
                                                {
                                                    res = Float.parseFloat(array3[0]);
                                                }
                                                text4 = array3[1];
                                                num7 = int.Parse(array3[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead3 = new EventRead();
                                                eventRead3.condition = text;
                                                eventRead3.result = text3;
                                                eventRead3.condition2 = text2;
                                                eventRead3.contype = (int)Main.conditions[key];
                                                eventRead3.contype2 = (int)Main.conditions[key];
                                                eventRead3.opreator = opreator;
                                                eventRead3.opreator2 = opreator2;
                                                eventRead3.collector = collector;
                                                eventRead3.change = num5;
                                                eventRead3.changetype = (int)Main.type[text4];
                                                eventRead3.changevalue = num6;
                                                eventRead3.changename = (int)Main.results[text5];
                                                eventRead3.res = res;
                                                eventRead3.special = special;
                                                if (array3[0].Contains('+'))
                                                {
                                                    eventRead3.rand = Float.parseFloat(array3[0].Split('+')[1]);
                                                }
                                                eventRead3.times = num7;
                                                if (array3[2].Contains("("))
                                                {
                                                    eventRead3.time = int.Parse(array3[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current4.results.Add(eventRead3);
                                            }
                                            else if (current5.Contains("恢复"))
                                            {
                                                EventRead eventRead4 = new EventRead();
                                                eventRead4.special = 1;
                                                eventRead4.opreator = opreator;
                                                eventRead4.opreator2 = opreator2;
                                                eventRead4.condition = text;
                                                eventRead4.condition2 = text2;
                                                eventRead4.contype = (int)Main.conditions[key];
                                                eventRead4.contype2 = (int)Main.conditions[key];
                                                eventRead4.collector = collector;
                                                current4.results.Add(eventRead4);
                                            }
                                            else if (current5.Contains("发射"))
                                            {
                                                EventRead eventRead5 = new EventRead();
                                                eventRead5.special = 2;
                                                eventRead5.opreator = opreator;
                                                eventRead5.opreator2 = opreator2;
                                                eventRead5.condition = text;
                                                eventRead5.condition2 = text2;
                                                eventRead5.contype = (int)Main.conditions[key];
                                                eventRead5.contype2 = (int)Main.conditions[key];
                                                eventRead5.collector = collector;
                                                current4.results.Add(eventRead5);
                                            }
                                        }
                                    }
                                    finally
                                    {
                                        ((IDisposable)enumerator4).Dispose();
                                    }
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                            enumerator3 = current2.Sonevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current6 = enumerator3.Current;
                                    enumerator4 = current6.events.GetEnumerator();
                                    try
                                    {
                                        while (enumerator4.MoveNext())
                                        {
                                            String current7 = enumerator4.Current;
                                            String text6 = current7.Split('：')[0];
                                            String text7 = "";
                                            String key2 = "";
                                            String key3 = "";
                                            String opreator3 = "";
                                            String opreator4 = "";
                                            String collector2 = "";
                                            String text8 = current7.Split('：')[1];
                                            int num8 = 0;
                                            String text9 = "";
                                            int num9 = 0;
                                            String text10 = "";
                                            float res2 = 0f;
                                            int num10 = 0;
                                            int special2 = 0;
                                            if (current7.Contains("且"))
                                            {
                                                collector2 = "且";
                                                text7 = text6.Split("且".ToCharArray())[1];
                                                text6 = text6.Split("且".ToCharArray())[0];
                                            }
                                            else if (current7.Contains("或"))
                                            {
                                                collector2 = "或";
                                                text7 = text6.Split("或".ToCharArray())[1];
                                                text6 = text6.Split("或".ToCharArray())[0];
                                            }
                                            if (text6.Contains(">"))
                                            {
                                                key2 = text6.Split('>')[0];
                                                opreator3 = ">";
                                                text6 = text6.Split('>')[1];
                                            }
                                            if (text6.Contains("="))
                                            {
                                                key2 = text6.Split('=')[0];
                                                opreator3 = "=";
                                                text6 = text6.Split('=')[1];
                                            }
                                            if (text6.Contains("<"))
                                            {
                                                key2 = text6.Split('<')[0];
                                                opreator3 = "<";
                                                text6 = text6.Split('<')[1];
                                            }
                                            if (text7.Contains(">"))
                                            {
                                                key3 = text7.Split('>')[0];
                                                opreator4 = ">";
                                                text7 = text7.Split('>')[1];
                                            }
                                            if (text7.Contains("="))
                                            {
                                                key3 = text7.Split('=')[0];
                                                opreator4 = "=";
                                                text7 = text7.Split('=')[1];
                                            }
                                            if (text7.Contains("<"))
                                            {
                                                key3 = text7.Split('<')[0];
                                                opreator4 = "<";
                                                text7 = text7.Split('<')[1];
                                            }
                                            if (current7.Contains("变化到"))
                                            {
                                                num8 = 0;
                                                String[] array4 = text8.Split("变".ToCharArray())[1].Split("，".ToCharArray());
                                                num9 = (int)Main.results2[text8.Split("变".ToCharArray())[0]];
                                                text10 = text8.Split("变".ToCharArray())[0];
                                                if (array4[0].Replace("化到", "").Contains('+'))
                                                {
                                                    if (array4[0].Replace("化到", "").Split('+')[0] == "自身")
                                                    {
                                                        special2 = 3;
                                                    }
                                                    else if (array4[0].Replace("化到", "").Split('+')[0] == "自机")
                                                    {
                                                        special2 = 4;
                                                    }
                                                    else if (array4[0].Replace("化到", "").Split('+')[0] == "中心")
                                                    {
                                                        special2 = 5;
                                                        opreator3 = "";
                                                    }
                                                    else
                                                    {
                                                        res2 = Float.parseFloat(array4[0].Replace("化到", "").Split('+')[0]);
                                                    }
                                                }
                                                else if (array4[0].Replace("化到", "") == "自身")
                                                {
                                                    special2 = 3;
                                                }
                                                else if (array4[0].Replace("化到", "") == "自机")
                                                {
                                                    special2 = 4;
                                                }
                                                else if (array4[0].Replace("化到", "") == "中心")
                                                {
                                                    special2 = 5;
                                                    opreator3 = "";
                                                }
                                                else
                                                {
                                                    res2 = Float.parseFloat(array4[0].Replace("化到", ""));
                                                }
                                                text9 = array4[1];
                                                num10 = int.Parse(array4[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead6 = new EventRead();
                                                eventRead6.condition = text6;
                                                eventRead6.result = text8;
                                                eventRead6.condition2 = text7;
                                                eventRead6.contype = (int)Main.conditions2[key2];
                                                eventRead6.contype2 = (int)Main.conditions2[key3];
                                                eventRead6.opreator = opreator3;
                                                eventRead6.opreator2 = opreator4;
                                                eventRead6.collector = collector2;
                                                eventRead6.change = num8;
                                                eventRead6.changetype = (int)Main.type[text9];
                                                eventRead6.changevalue = num9;
                                                eventRead6.changename = (int)Main.results2[text10];
                                                eventRead6.res = res2;
                                                eventRead6.special = special2;
                                                if (array4[0].Replace("化到", "").Contains('+'))
                                                {
                                                    eventRead6.rand = Float.parseFloat(array4[0].Replace("化到", "").Split('+')[1]);
                                                }
                                                eventRead6.times = num10;
                                                if (array4[2].Contains("("))
                                                {
                                                    eventRead6.time = int.Parse(array4[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current6.results.Add(eventRead6);
                                            }
                                            else if (current7.Contains("增加"))
                                            {
                                                num8 = 1;
                                                String[] array5 = text8.Split("增".ToCharArray())[1].Split("，".ToCharArray());
                                                array5[0] = array5[0].Replace("加", "");
                                                num9 = (int)Main.results2[text8.Split("增".ToCharArray())[0]];
                                                text10 = text8.Split("增".ToCharArray())[0];
                                                if (array5[0].Contains('+'))
                                                {
                                                    if (array5[0].Split('+')[0] == "自身")
                                                    {
                                                        special2 = 3;
                                                    }
                                                    else if (array5[0].Split('+')[0] == "自机")
                                                    {
                                                        special2 = 4;
                                                    }
                                                    else if (array5[0].Split('+')[0] == "中心")
                                                    {
                                                        special2 = 5;
                                                        opreator3 = "";
                                                    }
                                                    else
                                                    {
                                                        res2 = Float.parseFloat(array5[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array5[0] == "自身")
                                                {
                                                    special2 = 3;
                                                }
                                                else if (array5[0] == "自机")
                                                {
                                                    special2 = 4;
                                                }
                                                else if (array5[0] == "中心")
                                                {
                                                    special2 = 5;
                                                    opreator3 = "";
                                                }
                                                else
                                                {
                                                    res2 = Float.parseFloat(array5[0]);
                                                }
                                                text9 = array5[1];
                                                num10 = int.Parse(array5[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead7 = new EventRead();
                                                eventRead7.condition = text6;
                                                eventRead7.result = text8;
                                                eventRead7.condition2 = text7;
                                                eventRead7.contype = (int)Main.conditions2[key2];
                                                eventRead7.contype2 = (int)Main.conditions2[key3];
                                                eventRead7.opreator = opreator3;
                                                eventRead7.opreator2 = opreator4;
                                                eventRead7.collector = collector2;
                                                eventRead7.change = num8;
                                                eventRead7.changetype = (int)Main.type[text9];
                                                eventRead7.changevalue = num9;
                                                eventRead7.changename = (int)Main.results2[text10];
                                                eventRead7.res = res2;
                                                eventRead7.special = special2;
                                                if (array5[0].Contains('+'))
                                                {
                                                    eventRead7.rand = Float.parseFloat(array5[0].Split('+')[1]);
                                                }
                                                eventRead7.times = num10;
                                                if (array5[2].Contains("("))
                                                {
                                                    eventRead7.time = int.Parse(array5[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current6.results.Add(eventRead7);
                                            }
                                            else if (current7.Contains("减少"))
                                            {
                                                num8 = 2;
                                                String[] array6 = text8.Split("减少".ToCharArray())[2].Split("，".ToCharArray());
                                                num9 = (int)Main.results2[text8.Split("减少".ToCharArray())[0]];
                                                text10 = text8.Split("减少".ToCharArray())[0];
                                                if (array6[0].Contains('+'))
                                                {
                                                    if (array6[0].Split('+')[0] == "自身")
                                                    {
                                                        special2 = 3;
                                                    }
                                                    else if (array6[0].Split('+')[0] == "自机")
                                                    {
                                                        special2 = 4;
                                                    }
                                                    else if (array6[0].Split('+')[0] == "中心")
                                                    {
                                                        special2 = 5;
                                                        opreator3 = "";
                                                    }
                                                    else
                                                    {
                                                        res2 = Float.parseFloat(array6[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array6[0] == "自身")
                                                {
                                                    special2 = 3;
                                                }
                                                else if (array6[0] == "自机")
                                                {
                                                    special2 = 4;
                                                }
                                                else if (array6[0] == "中心")
                                                {
                                                    special2 = 5;
                                                    opreator3 = "";
                                                }
                                                else
                                                {
                                                    res2 = Float.parseFloat(array6[0]);
                                                }
                                                text9 = array6[1];
                                                num10 = int.Parse(array6[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead8 = new EventRead();
                                                eventRead8.condition = text6;
                                                eventRead8.result = text8;
                                                eventRead8.condition2 = text7;
                                                eventRead8.contype = (int)Main.conditions2[key2];
                                                eventRead8.contype2 = (int)Main.conditions2[key3];
                                                eventRead8.opreator = opreator3;
                                                eventRead8.opreator2 = opreator4;
                                                eventRead8.collector = collector2;
                                                eventRead8.change = num8;
                                                eventRead8.changetype = (int)Main.type[text9];
                                                eventRead8.changevalue = num9;
                                                eventRead8.changename = (int)Main.results2[text10];
                                                eventRead8.res = res2;
                                                eventRead8.special = special2;
                                                if (array6[0].Contains('+'))
                                                {
                                                    eventRead8.rand = Float.parseFloat(array6[0].Split('+')[1]);
                                                }
                                                eventRead8.times = num10;
                                                if (array6[2].Contains("("))
                                                {
                                                    eventRead8.time = int.Parse(array6[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current6.results.Add(eventRead8);
                                            }
                                        }
                                    }
                                    finally
                                    {
                                        ((IDisposable)enumerator4).Dispose();
                                    }
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                        }
                    }
                    finally
                    {
                        ((IDisposable)enumerator2).Dispose();
                    }
                    enumerator5 = current.LaseArray.GetEnumerator();
                    try
                    {
                        while (enumerator5.MoveNext())
                        {
                            Lase current8 = enumerator5.Current;
                            current8.Selecting = false;
                            current8.copys = (current8.Copy() as Lase);
                            enumerator3 = current8.copys.Parentevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current9 = enumerator3.Current;
                                    current9.loop = 0;
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                            float num11 = MathUtils.lerp(0f - current8.copys.rand.speed, current8.copys.rand.speed, RandomPool.get(5).random());
                            int num12 = (int)MathUtils.lerp(0f - current8.copys.rand.speedd, current8.copys.rand.speedd, RandomPool.get(5).random());
                            float num13 = MathUtils.lerp(0f - current8.copys.rand.aspeed, current8.copys.rand.aspeed, RandomPool.get(5).random());
                            int num14 = (int)MathUtils.lerp(0f - current8.copys.rand.aspeedd, current8.copys.rand.aspeedd, RandomPool.get(5).random());
                            current8.copys.aspeed += num13;
                            current8.copys.aspeedd += (float)num14;
                            current8.copys.speed += num11;
                            current8.copys.speedd += (float)num12;
                            current8.copys.aspeedx = current8.copys.aspeed * MathUtils.cosDeg((current8.copys.aspeedd));
                            current8.copys.aspeedy = current8.copys.aspeed * MathUtils.sinDeg((current8.copys.aspeedd));
                            current8.copys.speedx = current8.copys.speed * MathUtils.cosDeg((current8.copys.speedd));
                            current8.copys.speedy = current8.copys.speed * MathUtils.sinDeg((current8.copys.speedd));
                            if (current8.fdirection == -99999f)
                            {
                                current8.copys.fdirection = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current8.copys.x - 4f, current8.copys.y + 16f));
                            }
                            else if (current8.fdirection == -100000f)
                            {
                                current8.copys.fdirection = GameHelper.twoPointAngle(current8.fdirections.X, current8.fdirections.Y, current8.copys.x - 4f, current8.copys.y + 16f));
                            }
                            if (current8.sonaspeedd == -99998f)
                            {
                                current8.copys.sonaspeedd = GameHelper.twoPointAngle(current8.x - 4f, current8.y + 16f, current8.copys.x - 4f, current8.copys.y + 16f));
                            }
                            else if (current8.sonaspeedd == -99999f)
                            {
                                current8.copys.sonaspeedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current8.copys.x - 4f, current8.copys.y + 16f));
                            }
                            else if (current8.sonaspeedd == -100000f)
                            {
                                current8.copys.sonaspeedd = GameHelper.twoPointAngle(current8.sonaspeedds.X, current8.sonaspeedds.Y, current8.copys.x - 4f, current8.copys.y + 16f));
                            }
                            enumerator3 = current8.Parentevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current10 = enumerator3.Current;
                                    enumerator4 = current10.events.GetEnumerator();
                                    try
                                    {
                                        while (enumerator4.MoveNext())
                                        {
                                            String current11 = enumerator4.Current;
                                            String text11 = current11.Split('：')[0];
                                            String text12 = "";
                                            String key4 = "";
                                            String key5 = "";
                                            String opreator5 = "";
                                            String opreator6 = "";
                                            String collector3 = "";
                                            String text13 = current11.Split('：')[1];
                                            int num15 = 0;
                                            String text14 = "";
                                            int num16 = 0;
                                            String text15 = "";
                                            float res3 = 0f;
                                            int num17 = 0;
                                            int special3 = 0;
                                            if (current11.Contains("且"))
                                            {
                                                collector3 = "且";
                                                text12 = text11.Split("且".ToCharArray())[1];
                                                text11 = text11.Split("且".ToCharArray())[0];
                                            }
                                            else if (current11.Contains("或"))
                                            {
                                                collector3 = "或";
                                                text12 = text11.Split("或".ToCharArray())[1];
                                                text11 = text11.Split("或".ToCharArray())[0];
                                            }
                                            if (text11.Contains(">"))
                                            {
                                                key4 = text11.Split('>')[0];
                                                opreator5 = ">";
                                                text11 = text11.Split('>')[1];
                                            }
                                            if (text11.Contains("="))
                                            {
                                                key4 = text11.Split('=')[0];
                                                opreator5 = "=";
                                                text11 = text11.Split('=')[1];
                                            }
                                            if (text11.Contains("<"))
                                            {
                                                key4 = text11.Split('<')[0];
                                                opreator5 = "<";
                                                text11 = text11.Split('<')[1];
                                            }
                                            if (text12.Contains(">"))
                                            {
                                                key5 = text12.Split('>')[0];
                                                opreator6 = ">";
                                                text12 = text12.Split('>')[1];
                                            }
                                            if (text12.Contains("="))
                                            {
                                                key5 = text12.Split('=')[0];
                                                opreator6 = "=";
                                                text12 = text12.Split('=')[1];
                                            }
                                            if (text12.Contains("<"))
                                            {
                                                key5 = text12.Split('<')[0];
                                                opreator6 = "<";
                                                text12 = text12.Split('<')[1];
                                            }
                                            if (current11.Contains("变化到"))
                                            {
                                                num15 = 0;
                                                String[] array7 = text13.Split("变化到".ToCharArray())[3].Split("，".ToCharArray());
                                                num16 = (int)Main.lresults[text13.Split("变化到".ToCharArray())[0]];
                                                text15 = text13.Split("变化到".ToCharArray())[0];
                                                if (array7[0].Contains('+'))
                                                {
                                                    if (array7[0].Split('+')[0] == "自机")
                                                    {
                                                        special3 = 4;
                                                    }
                                                    else
                                                    {
                                                        res3 = Float.parseFloat(array7[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array7[0] == "自机")
                                                {
                                                    special3 = 4;
                                                }
                                                else
                                                {
                                                    res3 = Float.parseFloat(array7[0]);
                                                }
                                                text14 = array7[1];
                                                num17 = int.Parse(array7[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead9 = new EventRead();
                                                eventRead9.condition = text11;
                                                eventRead9.result = text13;
                                                eventRead9.condition2 = text12;
                                                eventRead9.contype = (int)Main.lconditions[key4];
                                                eventRead9.contype2 = (int)Main.lconditions[key5];
                                                eventRead9.opreator = opreator5;
                                                eventRead9.opreator2 = opreator6;
                                                eventRead9.collector = collector3;
                                                eventRead9.change = num15;
                                                eventRead9.changetype = (int)Main.type[text14];
                                                eventRead9.changevalue = num16;
                                                eventRead9.changename = (int)Main.lresults[text15];
                                                eventRead9.res = res3;
                                                eventRead9.special = special3;
                                                if (array7[0].Contains('+'))
                                                {
                                                    eventRead9.rand = Float.parseFloat(array7[0].Split('+')[1]);
                                                }
                                                eventRead9.times = num17;
                                                if (array7[2].Contains("("))
                                                {
                                                    eventRead9.time = int.Parse(array7[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current10.results.Add(eventRead9);
                                            }
                                            else if (current11.Contains("增加"))
                                            {
                                                num15 = 1;
                                                String[] array8 = text13.Split("增".ToCharArray())[1].Split("，".ToCharArray());
                                                array8[0] = array8[0].Replace("加", "");
                                                num16 = (int)Main.lresults[text13.Split("增".ToCharArray())[0]];
                                                text15 = text13.Split("增".ToCharArray())[0];
                                                if (array8[0].Contains('+'))
                                                {
                                                    if (array8[0].Split('+')[0] == "自机")
                                                    {
                                                        special3 = 4;
                                                    }
                                                    else
                                                    {
                                                        res3 = Float.parseFloat(array8[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array8[0] == "自机")
                                                {
                                                    special3 = 4;
                                                }
                                                else
                                                {
                                                    res3 = Float.parseFloat(array8[0]);
                                                }
                                                text14 = array8[1];
                                                num17 = int.Parse(array8[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead10 = new EventRead();
                                                eventRead10.condition = text11;
                                                eventRead10.result = text13;
                                                eventRead10.condition2 = text12;
                                                eventRead10.contype = (int)Main.lconditions[key4];
                                                eventRead10.contype2 = (int)Main.lconditions[key5];
                                                eventRead10.opreator = opreator5;
                                                eventRead10.opreator2 = opreator6;
                                                eventRead10.collector = collector3;
                                                eventRead10.change = num15;
                                                eventRead10.changetype = (int)Main.type[text14];
                                                eventRead10.changevalue = num16;
                                                eventRead10.changename = (int)Main.lresults[text15];
                                                eventRead10.res = res3;
                                                eventRead10.special = special3;
                                                if (array8[0].Contains('+'))
                                                {
                                                    eventRead10.rand = Float.parseFloat(array8[0].Split('+')[1]);
                                                }
                                                eventRead10.times = num17;
                                                if (array8[2].Contains("("))
                                                {
                                                    eventRead10.time = int.Parse(array8[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current10.results.Add(eventRead10);
                                            }
                                            else if (current11.Contains("减少"))
                                            {
                                                num15 = 2;
                                                String[] array9 = text13.Split("减少".ToCharArray())[2].Split("，".ToCharArray());
                                                num16 = (int)Main.lresults[text13.Split("减少".ToCharArray())[0]];
                                                text15 = text13.Split("减少".ToCharArray())[0];
                                                if (array9[0].Contains('+'))
                                                {
                                                    if (array9[0].Split('+')[0] == "自机")
                                                    {
                                                        special3 = 4;
                                                    }
                                                    else
                                                    {
                                                        res3 = Float.parseFloat(array9[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array9[0] == "自机")
                                                {
                                                    special3 = 4;
                                                }
                                                else
                                                {
                                                    res3 = Float.parseFloat(array9[0]);
                                                }
                                                text14 = array9[1];
                                                num17 = int.Parse(array9[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead11 = new EventRead();
                                                eventRead11.condition = text11;
                                                eventRead11.result = text13;
                                                eventRead11.condition2 = text12;
                                                eventRead11.contype = (int)Main.lconditions[key4];
                                                eventRead11.contype2 = (int)Main.lconditions[key5];
                                                eventRead11.opreator = opreator5;
                                                eventRead11.opreator2 = opreator6;
                                                eventRead11.collector = collector3;
                                                eventRead11.change = num15;
                                                eventRead11.changetype = (int)Main.type[text14];
                                                eventRead11.changevalue = num16;
                                                eventRead11.changename = (int)Main.lresults[text15];
                                                eventRead11.res = res3;
                                                eventRead11.special = special3;
                                                if (array9[0].Contains('+'))
                                                {
                                                    eventRead11.rand = Float.parseFloat(array9[0].Split('+')[1]);
                                                }
                                                eventRead11.times = num17;
                                                if (array9[2].Contains("("))
                                                {
                                                    eventRead11.time = int.Parse(array9[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current10.results.Add(eventRead11);
                                            }
                                            else if (current11.Contains("恢复"))
                                            {
                                                EventRead eventRead12 = new EventRead();
                                                eventRead12.special = 1;
                                                eventRead12.opreator = opreator5;
                                                eventRead12.opreator2 = opreator6;
                                                eventRead12.condition = text11;
                                                eventRead12.condition2 = text12;
                                                eventRead12.contype = (int)Main.lconditions[key4];
                                                eventRead12.contype2 = (int)Main.lconditions[key5];
                                                eventRead12.collector = collector3;
                                                current10.results.Add(eventRead12);
                                            }
                                            else if (current11.Contains("发射"))
                                            {
                                                EventRead eventRead13 = new EventRead();
                                                eventRead13.special = 2;
                                                eventRead13.opreator = opreator5;
                                                eventRead13.opreator2 = opreator6;
                                                eventRead13.condition = text11;
                                                eventRead13.condition2 = text12;
                                                eventRead13.contype = (int)Main.lconditions[key4];
                                                eventRead13.contype2 = (int)Main.lconditions[key5];
                                                eventRead13.collector = collector3;
                                                current10.results.Add(eventRead13);
                                            }
                                        }
                                    }
                                    finally
                                    {
                                        ((IDisposable)enumerator4).Dispose();
                                    }
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                            enumerator3 = current8.Sonevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current12 = enumerator3.Current;
                                    enumerator4 = current12.events.GetEnumerator();
                                    try
                                    {
                                        while (enumerator4.MoveNext())
                                        {
                                            String current13 = enumerator4.Current;
                                            String text16 = current13.Split('：')[0];
                                            String text17 = "";
                                            String key6 = "";
                                            String key7 = "";
                                            String opreator7 = "";
                                            String opreator8 = "";
                                            String collector4 = "";
                                            String text18 = current13.Split('：')[1];
                                            int num18 = 0;
                                            String text19 = "";
                                            int num19 = 0;
                                            String text20 = "";
                                            float res4 = 0f;
                                            int num20 = 0;
                                            int special4 = 0;
                                            if (current13.Contains("且"))
                                            {
                                                collector4 = "且";
                                                text17 = text16.Split("且".ToCharArray())[1];
                                                text16 = text16.Split("且".ToCharArray())[0];
                                            }
                                            else if (current13.Contains("或"))
                                            {
                                                collector4 = "或";
                                                text17 = text16.Split("或".ToCharArray())[1];
                                                text16 = text16.Split("或".ToCharArray())[0];
                                            }
                                            if (text16.Contains(">"))
                                            {
                                                key6 = text16.Split('>')[0];
                                                opreator7 = ">";
                                                text16 = text16.Split('>')[1];
                                            }
                                            if (text16.Contains("="))
                                            {
                                                key6 = text16.Split('=')[0];
                                                opreator7 = "=";
                                                text16 = text16.Split('=')[1];
                                            }
                                            if (text16.Contains("<"))
                                            {
                                                key6 = text16.Split('<')[0];
                                                opreator7 = "<";
                                                text16 = text16.Split('<')[1];
                                            }
                                            if (text17.Contains(">"))
                                            {
                                                key7 = text17.Split('>')[0];
                                                opreator8 = ">";
                                                text17 = text17.Split('>')[1];
                                            }
                                            if (text17.Contains("="))
                                            {
                                                key7 = text17.Split('=')[0];
                                                opreator8 = "=";
                                                text17 = text17.Split('=')[1];
                                            }
                                            if (text17.Contains("<"))
                                            {
                                                key7 = text17.Split('<')[0];
                                                opreator8 = "<";
                                                text17 = text17.Split('<')[1];
                                            }
                                            if (current13.Contains("变化到"))
                                            {
                                                num18 = 0;
                                                String[] array10 = text18.Split("变化到".ToCharArray())[3].Split("，".ToCharArray());
                                                num19 = (int)Main.lresults2[text18.Split("变化到".ToCharArray())[0]];
                                                text20 = text18.Split("变化到".ToCharArray())[0];
                                                if (array10[0].Contains('+'))
                                                {
                                                    if (array10[0].Split('+')[0] == "自机")
                                                    {
                                                        special4 = 4;
                                                    }
                                                    else if (array10[0].Split('+')[0] == "中心")
                                                    {
                                                        special4 = 5;
                                                        opreator7 = "";
                                                    }
                                                    else
                                                    {
                                                        res4 = Float.parseFloat(array10[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array10[0] == "自机")
                                                {
                                                    special4 = 4;
                                                }
                                                else if (array10[0] == "中心")
                                                {
                                                    special4 = 5;
                                                    opreator7 = "";
                                                }
                                                else
                                                {
                                                    res4 = Float.parseFloat(array10[0]);
                                                }
                                                text19 = array10[1];
                                                num20 = int.Parse(array10[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead14 = new EventRead();
                                                eventRead14.condition = text16;
                                                eventRead14.result = text18;
                                                eventRead14.condition2 = text17;
                                                eventRead14.contype = (int)Main.lconditions[key6];
                                                eventRead14.contype2 = (int)Main.lconditions[key7];
                                                eventRead14.opreator = opreator7;
                                                eventRead14.opreator2 = opreator8;
                                                eventRead14.collector = collector4;
                                                eventRead14.change = num18;
                                                eventRead14.changetype = (int)Main.type[text19];
                                                eventRead14.changevalue = num19;
                                                eventRead14.changename = (int)Main.lresults2[text20];
                                                eventRead14.res = res4;
                                                eventRead14.special = special4;
                                                if (array10[0].Contains('+'))
                                                {
                                                    eventRead14.rand = Float.parseFloat(array10[0].Split('+')[1]);
                                                }
                                                eventRead14.times = num20;
                                                if (array10[2].Contains("("))
                                                {
                                                    eventRead14.time = int.Parse(array10[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current12.results.Add(eventRead14);
                                            }
                                            else if (current13.Contains("增加"))
                                            {
                                                num18 = 1;
                                                String[] array11 = text18.Split("增".ToCharArray())[1].Split("，".ToCharArray());
                                                array11[0] = array11[0].Replace("加", "");
                                                num19 = (int)Main.lresults2[text18.Split("增".ToCharArray())[0]];
                                                text20 = text18.Split("增".ToCharArray())[0];
                                                if (array11[0].Contains('+'))
                                                {
                                                    if (array11[0].Split('+')[0] == "自机")
                                                    {
                                                        special4 = 4;
                                                    }
                                                    else if (array11[0].Split('+')[0] == "中心")
                                                    {
                                                        special4 = 5;
                                                        opreator7 = "";
                                                    }
                                                    else
                                                    {
                                                        res4 = Float.parseFloat(array11[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array11[0] == "自机")
                                                {
                                                    special4 = 4;
                                                }
                                                else if (array11[0] == "中心")
                                                {
                                                    special4 = 5;
                                                    opreator7 = "";
                                                }
                                                else
                                                {
                                                    res4 = Float.parseFloat(array11[0]);
                                                }
                                                text19 = array11[1];
                                                num20 = int.Parse(array11[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead15 = new EventRead();
                                                eventRead15.condition = text16;
                                                eventRead15.result = text18;
                                                eventRead15.condition2 = text17;
                                                eventRead15.contype = (int)Main.lconditions[key6];
                                                eventRead15.contype2 = (int)Main.lconditions[key7];
                                                eventRead15.opreator = opreator7;
                                                eventRead15.opreator2 = opreator8;
                                                eventRead15.collector = collector4;
                                                eventRead15.change = num18;
                                                eventRead15.changetype = (int)Main.type[text19];
                                                eventRead15.changevalue = num19;
                                                eventRead15.changename = (int)Main.lresults2[text20];
                                                eventRead15.res = res4;
                                                eventRead15.special = special4;
                                                if (array11[0].Contains('+'))
                                                {
                                                    eventRead15.rand = Float.parseFloat(array11[0].Split('+')[1]);
                                                }
                                                eventRead15.times = num20;
                                                if (array11[2].Contains("("))
                                                {
                                                    eventRead15.time = int.Parse(array11[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current12.results.Add(eventRead15);
                                            }
                                            else if (current13.Contains("减少"))
                                            {
                                                num18 = 2;
                                                String[] array12 = text18.Split("减少".ToCharArray())[2].Split("，".ToCharArray());
                                                num19 = (int)Main.lresults2[text18.Split("减少".ToCharArray())[0]];
                                                text20 = text18.Split("减少".ToCharArray())[0];
                                                if (array12[0].Contains('+'))
                                                {
                                                    if (array12[0].Split('+')[0] == "自机")
                                                    {
                                                        special4 = 4;
                                                    }
                                                    else if (array12[0].Split('+')[0] == "中心")
                                                    {
                                                        special4 = 5;
                                                        opreator7 = "";
                                                    }
                                                    else
                                                    {
                                                        res4 = Float.parseFloat(array12[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array12[0] == "自机")
                                                {
                                                    special4 = 4;
                                                }
                                                else if (array12[0] == "中心")
                                                {
                                                    special4 = 5;
                                                    opreator7 = "";
                                                }
                                                else
                                                {
                                                    res4 = Float.parseFloat(array12[0]);
                                                }
                                                text19 = array12[1];
                                                num20 = int.Parse(array12[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead16 = new EventRead();
                                                eventRead16.condition = text16;
                                                eventRead16.result = text18;
                                                eventRead16.condition2 = text17;
                                                eventRead16.contype = (int)Main.lconditions[key6];
                                                eventRead16.contype2 = (int)Main.lconditions[key7];
                                                eventRead16.opreator = opreator7;
                                                eventRead16.opreator2 = opreator8;
                                                eventRead16.collector = collector4;
                                                eventRead16.change = num18;
                                                eventRead16.changetype = (int)Main.type[text19];
                                                eventRead16.changevalue = num19;
                                                eventRead16.changename = (int)Main.lresults2[text20];
                                                eventRead16.res = res4;
                                                eventRead16.special = special4;
                                                if (array12[0].Contains('+'))
                                                {
                                                    eventRead16.rand = Float.parseFloat(array12[0].Split('+')[1]);
                                                }
                                                eventRead16.times = num20;
                                                if (array12[2].Contains("("))
                                                {
                                                    eventRead16.time = int.Parse(array12[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current12.results.Add(eventRead16);
                                            }
                                        }
                                    }
                                    finally
                                    {
                                        ((IDisposable)enumerator4).Dispose();
                                    }
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                        }
                    }
                    finally
                    {
                        ((IDisposable)enumerator5).Dispose();
                    }
                    enumerator6 = current.CoverArray.GetEnumerator();
                    try
                    {
                        while (enumerator6.MoveNext())
                        {
                            Cover current14 = enumerator6.Current;
                            current14.Selecting = false;
                            current14.copys = (current14.Copy() as Cover);
                            enumerator3 = current14.copys.Parentevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current15 = enumerator3.Current;
                                    current15.loop = 0;
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                            float num21 = MathUtils.lerp(0f - current14.copys.rand.speed, current14.copys.rand.speed, RandomPool.get(5).random());
                            int num22 = (int)MathUtils.lerp(0f - current14.copys.rand.speedd, current14.copys.rand.speedd, RandomPool.get(5).random());
                            float num23 = MathUtils.lerp(0f - current14.copys.rand.aspeed, current14.copys.rand.aspeed, RandomPool.get(5).random());
                            int num24 = (int)MathUtils.lerp(0f - current14.copys.rand.aspeedd, current14.copys.rand.aspeedd, RandomPool.get(5).random());
                            current14.copys.aspeed += num23;
                            current14.copys.aspeedd += (float)num24;
                            current14.copys.speed += num21;
                            current14.copys.speedd += (float)num22;
                            current14.copys.aspeedx = current14.copys.aspeed * MathUtils.cosDeg((current14.copys.aspeedd));
                            current14.copys.aspeedy = current14.copys.aspeed * MathUtils.sinDeg((current14.copys.aspeedd));
                            current14.copys.speedx = current14.copys.speed * MathUtils.cosDeg((current14.copys.speedd));
                            current14.copys.speedy = current14.copys.speed * MathUtils.sinDeg((current14.copys.speedd));
                            enumerator3 = current14.Parentevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current16 = enumerator3.Current;
                                    enumerator4 = current16.events.GetEnumerator();
                                    try
                                    {
                                        while (enumerator4.MoveNext())
                                        {
                                            String current17 = enumerator4.Current;
                                            String text21 = current17.Split('：')[0];
                                            String text22 = "";
                                            String key8 = "";
                                            String key9 = "";
                                            String opreator9 = "";
                                            String opreator10 = "";
                                            String collector5 = "";
                                            String text23 = current17.Split('：')[1];
                                            int num25 = 0;
                                            String text24 = "";
                                            int num26 = 0;
                                            String text25 = "";
                                            float res5 = 0f;
                                            int num27 = 0;
                                            int special5 = 0;
                                            if (current17.Contains("且"))
                                            {
                                                collector5 = "且";
                                                text22 = text21.Split("且".ToCharArray())[1];
                                                text21 = text21.Split("且".ToCharArray())[0];
                                            }
                                            else if (current17.Contains("或"))
                                            {
                                                collector5 = "或";
                                                text22 = text21.Split("或".ToCharArray())[1];
                                                text21 = text21.Split("或".ToCharArray())[0];
                                            }
                                            if (text21.Contains(">"))
                                            {
                                                key8 = text21.Split('>')[0];
                                                opreator9 = ">";
                                                text21 = text21.Split('>')[1];
                                            }
                                            if (text21.Contains("="))
                                            {
                                                key8 = text21.Split('=')[0];
                                                opreator9 = "=";
                                                text21 = text21.Split('=')[1];
                                            }
                                            if (text21.Contains("<"))
                                            {
                                                key8 = text21.Split('<')[0];
                                                opreator9 = "<";
                                                text21 = text21.Split('<')[1];
                                            }
                                            if (text22.Contains(">"))
                                            {
                                                key9 = text22.Split('>')[0];
                                                opreator10 = ">";
                                                text22 = text22.Split('>')[1];
                                            }
                                            if (text22.Contains("="))
                                            {
                                                key9 = text22.Split('=')[0];
                                                opreator10 = "=";
                                                text22 = text22.Split('=')[1];
                                            }
                                            if (text22.Contains("<"))
                                            {
                                                key9 = text22.Split('<')[0];
                                                opreator10 = "<";
                                                text22 = text22.Split('<')[1];
                                            }
                                            if (current17.Contains("变化到"))
                                            {
                                                num25 = 0;
                                                String[] array13 = text23.Split("变化到".ToCharArray())[3].Split("，".ToCharArray());
                                                num26 = (int)Main.cresults[text23.Split("变化到".ToCharArray())[0]];
                                                text25 = text23.Split("变化到".ToCharArray())[0];
                                                if (array13[0].Contains('+'))
                                                {
                                                    if (array13[0].Split('+')[0] == "自机")
                                                    {
                                                        special5 = 4;
                                                    }
                                                    else
                                                    {
                                                        res5 = Float.parseFloat(array13[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array13[0] == "自机")
                                                {
                                                    special5 = 4;
                                                }
                                                else
                                                {
                                                    res5 = Float.parseFloat(array13[0]);
                                                }
                                                text24 = array13[1];
                                                num27 = int.Parse(array13[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead17 = new EventRead();
                                                eventRead17.condition = text21;
                                                eventRead17.result = text23;
                                                eventRead17.condition2 = text22;
                                                eventRead17.contype = (int)Main.cconditions[key8];
                                                eventRead17.contype2 = (int)Main.cconditions[key9];
                                                eventRead17.opreator = opreator9;
                                                eventRead17.opreator2 = opreator10;
                                                eventRead17.collector = collector5;
                                                eventRead17.change = num25;
                                                eventRead17.changetype = (int)Main.type[text24];
                                                eventRead17.changevalue = num26;
                                                eventRead17.changename = (int)Main.cresults[text25];
                                                eventRead17.res = res5;
                                                eventRead17.special = special5;
                                                if (array13[0].Contains('+'))
                                                {
                                                    eventRead17.rand = Float.parseFloat(array13[0].Split('+')[1]);
                                                }
                                                eventRead17.times = num27;
                                                if (array13[2].Contains("("))
                                                {
                                                    eventRead17.time = int.Parse(array13[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current16.results.Add(eventRead17);
                                            }
                                            else if (current17.Contains("增加"))
                                            {
                                                num25 = 1;
                                                String[] array14 = text23.Split("增".ToCharArray())[1].Split("，".ToCharArray());
                                                array14[0] = array14[0].Replace("加", "");
                                                num26 = (int)Main.cresults[text23.Split("增".ToCharArray())[0]];
                                                text25 = text23.Split("增".ToCharArray())[0];
                                                if (array14[0].Contains('+'))
                                                {
                                                    if (array14[0].Split('+')[0] == "自机")
                                                    {
                                                        special5 = 4;
                                                    }
                                                    else
                                                    {
                                                        res5 = Float.parseFloat(array14[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array14[0] == "自机")
                                                {
                                                    special5 = 4;
                                                }
                                                else
                                                {
                                                    res5 = Float.parseFloat(array14[0]);
                                                }
                                                text24 = array14[1];
                                                num27 = int.Parse(array14[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead18 = new EventRead();
                                                eventRead18.condition = text21;
                                                eventRead18.result = text23;
                                                eventRead18.condition2 = text22;
                                                eventRead18.contype = (int)Main.cconditions[key8];
                                                eventRead18.contype2 = (int)Main.cconditions[key9];
                                                eventRead18.opreator = opreator9;
                                                eventRead18.opreator2 = opreator10;
                                                eventRead18.collector = collector5;
                                                eventRead18.change = num25;
                                                eventRead18.changetype = (int)Main.type[text24];
                                                eventRead18.changevalue = num26;
                                                eventRead18.changename = (int)Main.cresults[text25];
                                                eventRead18.res = res5;
                                                eventRead18.special = special5;
                                                if (array14[0].Contains('+'))
                                                {
                                                    eventRead18.rand = Float.parseFloat(array14[0].Split('+')[1]);
                                                }
                                                eventRead18.times = num27;
                                                if (array14[2].Contains("("))
                                                {
                                                    eventRead18.time = int.Parse(array14[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current16.results.Add(eventRead18);
                                            }
                                            else if (current17.Contains("减少"))
                                            {
                                                num25 = 2;
                                                String[] array15 = text23.Split("减少".ToCharArray())[2].Split("，".ToCharArray());
                                                num26 = (int)Main.cresults[text23.Split("减少".ToCharArray())[0]];
                                                text25 = text23.Split("减少".ToCharArray())[0];
                                                if (array15[0].Contains('+'))
                                                {
                                                    if (array15[0].Split('+')[0] == "自机")
                                                    {
                                                        special5 = 4;
                                                    }
                                                    else
                                                    {
                                                        res5 = Float.parseFloat(array15[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array15[0] == "自机")
                                                {
                                                    special5 = 4;
                                                }
                                                else
                                                {
                                                    res5 = Float.parseFloat(array15[0]);
                                                }
                                                text24 = array15[1];
                                                num27 = int.Parse(array15[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead19 = new EventRead();
                                                eventRead19.condition = text21;
                                                eventRead19.result = text23;
                                                eventRead19.condition2 = text22;
                                                eventRead19.contype = (int)Main.cconditions[key8];
                                                eventRead19.contype2 = (int)Main.cconditions[key9];
                                                eventRead19.opreator = opreator9;
                                                eventRead19.opreator2 = opreator10;
                                                eventRead19.collector = collector5;
                                                eventRead19.change = num25;
                                                eventRead19.changetype = (int)Main.type[text24];
                                                eventRead19.changevalue = num26;
                                                eventRead19.changename = (int)Main.cresults[text25];
                                                eventRead19.res = res5;
                                                eventRead19.special = special5;
                                                if (array15[0].Contains('+'))
                                                {
                                                    eventRead19.rand = Float.parseFloat(array15[0].Split('+')[1]);
                                                }
                                                eventRead19.times = num27;
                                                if (array15[2].Contains("("))
                                                {
                                                    eventRead19.time = int.Parse(array15[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current16.results.Add(eventRead19);
                                            }
                                        }
                                    }
                                    finally
                                    {
                                        ((IDisposable)enumerator4).Dispose();
                                    }
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                            enumerator3 = current14.Sonevents.GetEnumerator();
                            try
                            {
                                while (enumerator3.MoveNext())
                                {
                                    Event current18 = enumerator3.Current;
                                    enumerator4 = current18.events.GetEnumerator();
                                    try
                                    {
                                        while (enumerator4.MoveNext())
                                        {
                                            String current19 = enumerator4.Current;
                                            String text26 = current19.Split('：')[0];
                                            String text27 = "";
                                            String key10 = "";
                                            String key11 = "";
                                            String opreator11 = "";
                                            String opreator12 = "";
                                            String collector6 = "";
                                            String text28 = current19.Split('：')[1];
                                            int num28 = 0;
                                            String text29 = "";
                                            int num29 = 0;
                                            String text30 = "";
                                            float res6 = 0f;
                                            int num30 = 0;
                                            int special6 = 0;
                                            if (current19.Contains("且"))
                                            {
                                                collector6 = "且";
                                                text27 = text26.Split("且".ToCharArray())[1];
                                                text26 = text26.Split("且".ToCharArray())[0];
                                            }
                                            else if (current19.Contains("或"))
                                            {
                                                collector6 = "或";
                                                text27 = text26.Split("或".ToCharArray())[1];
                                                text26 = text26.Split("或".ToCharArray())[0];
                                            }
                                            if (text26.Contains(">"))
                                            {
                                                key10 = text26.Split('>')[0];
                                                opreator11 = ">";
                                                text26 = text26.Split('>')[1];
                                            }
                                            if (text26.Contains("="))
                                            {
                                                key10 = text26.Split('=')[0];
                                                opreator11 = "=";
                                                text26 = text26.Split('=')[1];
                                            }
                                            if (text26.Contains("<"))
                                            {
                                                key10 = text26.Split('<')[0];
                                                opreator11 = "<";
                                                text26 = text26.Split('<')[1];
                                            }
                                            if (text27.Contains(">"))
                                            {
                                                key11 = text27.Split('>')[0];
                                                opreator12 = ">";
                                                text27 = text27.Split('>')[1];
                                            }
                                            if (text27.Contains("="))
                                            {
                                                key11 = text27.Split('=')[0];
                                                opreator12 = "=";
                                                text27 = text27.Split('=')[1];
                                            }
                                            if (text27.Contains("<"))
                                            {
                                                key11 = text27.Split('<')[0];
                                                opreator12 = "<";
                                                text27 = text27.Split('<')[1];
                                            }
                                            if (current19.Contains("变化到"))
                                            {
                                                num28 = 0;
                                                String[] array16 = text28.Split("变化到".ToCharArray())[3].Split("，".ToCharArray());
                                                num29 = (int)Main.results2[text28.Split("变化到".ToCharArray())[0]];
                                                text30 = text28.Split("变化到".ToCharArray())[0];
                                                if (array16[0].Contains('+'))
                                                {
                                                    if (array16[0].Split('+')[0] == "自身")
                                                    {
                                                        special6 = 3;
                                                    }
                                                    else if (array16[0].Split('+')[0] == "自机")
                                                    {
                                                        special6 = 4;
                                                    }
                                                    else if (array16[0].Split('+')[0] == "中心")
                                                    {
                                                        special6 = 5;
                                                        opreator11 = "";
                                                    }
                                                    else
                                                    {
                                                        res6 = Float.parseFloat(array16[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array16[0] == "自身")
                                                {
                                                    special6 = 3;
                                                }
                                                else if (array16[0] == "自机")
                                                {
                                                    special6 = 4;
                                                }
                                                else if (array16[0] == "中心")
                                                {
                                                    special6 = 5;
                                                    opreator11 = "";
                                                }
                                                else
                                                {
                                                    res6 = Float.parseFloat(array16[0]);
                                                }
                                                text29 = array16[1];
                                                num30 = int.Parse(array16[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead20 = new EventRead();
                                                eventRead20.condition = text26;
                                                eventRead20.result = text28;
                                                eventRead20.condition2 = text27;
                                                eventRead20.contype = (int)Main.conditions2[key10];
                                                eventRead20.contype2 = (int)Main.conditions2[key11];
                                                eventRead20.opreator = opreator11;
                                                eventRead20.opreator2 = opreator12;
                                                eventRead20.collector = collector6;
                                                eventRead20.change = num28;
                                                eventRead20.changetype = (int)Main.type[text29];
                                                eventRead20.changevalue = num29;
                                                eventRead20.changename = (int)Main.results2[text30];
                                                eventRead20.res = res6;
                                                eventRead20.special = special6;
                                                eventRead20.special2 = 1;
                                                if (array16[0].Contains('+'))
                                                {
                                                    eventRead20.rand = Float.parseFloat(array16[0].Split('+')[1]);
                                                }
                                                eventRead20.times = num30;
                                                if (array16[2].Contains("("))
                                                {
                                                    eventRead20.time = int.Parse(array16[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current18.results.Add(eventRead20);
                                            }
                                            else if (current19.Contains("增加"))
                                            {
                                                num28 = 1;
                                                String[] array17 = text28.Split("增".ToCharArray())[1].Split("，".ToCharArray());
                                                array17[0] = array17[0].Replace("加", "");
                                                num29 = (int)Main.results2[text28.Split("增".ToCharArray())[0]];
                                                text30 = text28.Split("增".ToCharArray())[0];
                                                if (array17[0].Contains('+'))
                                                {
                                                    if (array17[0].Split('+')[0] == "自身")
                                                    {
                                                        special6 = 3;
                                                    }
                                                    else if (array17[0].Split('+')[0] == "自机")
                                                    {
                                                        special6 = 4;
                                                    }
                                                    else if (array17[0].Split('+')[0] == "中心")
                                                    {
                                                        special6 = 5;
                                                        opreator11 = "";
                                                    }
                                                    else
                                                    {
                                                        res6 = Float.parseFloat(array17[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array17[0] == "自身")
                                                {
                                                    special6 = 3;
                                                }
                                                else if (array17[0] == "自机")
                                                {
                                                    special6 = 4;
                                                }
                                                else if (array17[0] == "中心")
                                                {
                                                    special6 = 5;
                                                    opreator11 = "";
                                                }
                                                else
                                                {
                                                    res6 = Float.parseFloat(array17[0]);
                                                }
                                                text29 = array17[1];
                                                num30 = int.Parse(array17[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead21 = new EventRead();
                                                eventRead21.condition = text26;
                                                eventRead21.result = text28;
                                                eventRead21.condition2 = text27;
                                                eventRead21.contype = (int)Main.conditions2[key10];
                                                eventRead21.contype2 = (int)Main.conditions2[key11];
                                                eventRead21.opreator = opreator11;
                                                eventRead21.opreator2 = opreator12;
                                                eventRead21.collector = collector6;
                                                eventRead21.change = num28;
                                                eventRead21.changetype = (int)Main.type[text29];
                                                eventRead21.changevalue = num29;
                                                eventRead21.changename = (int)Main.results2[text30];
                                                eventRead21.res = res6;
                                                eventRead21.special = special6;
                                                eventRead21.special2 = 1;
                                                if (array17[0].Contains('+'))
                                                {
                                                    eventRead21.rand = Float.parseFloat(array17[0].Split('+')[1]);
                                                }
                                                eventRead21.times = num30;
                                                if (array17[2].Contains("("))
                                                {
                                                    eventRead21.time = int.Parse(array17[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current18.results.Add(eventRead21);
                                            }
                                            else if (current19.Contains("减少"))
                                            {
                                                num28 = 2;
                                                String[] array18 = text28.Split("减少".ToCharArray())[2].Split("，".ToCharArray());
                                                num29 = (int)Main.results2[text28.Split("减少".ToCharArray())[0]];
                                                text30 = text28.Split("减少".ToCharArray())[0];
                                                if (array18[0].Contains('+'))
                                                {
                                                    if (array18[0].Split('+')[0] == "自身")
                                                    {
                                                        special6 = 3;
                                                    }
                                                    else if (array18[0].Split('+')[0] == "自机")
                                                    {
                                                        special6 = 4;
                                                    }
                                                    else if (array18[0].Split('+')[0] == "中心")
                                                    {
                                                        special6 = 5;
                                                        opreator11 = "";
                                                    }
                                                    else
                                                    {
                                                        res6 = Float.parseFloat(array18[0].Split('+')[0]);
                                                    }
                                                }
                                                else if (array18[0] == "自身")
                                                {
                                                    special6 = 3;
                                                }
                                                else if (array18[0] == "自机")
                                                {
                                                    special6 = 4;
                                                }
                                                else if (array18[0] == "中心")
                                                {
                                                    special6 = 5;
                                                    opreator11 = "";
                                                }
                                                else
                                                {
                                                    res6 = Float.parseFloat(array18[0]);
                                                }
                                                text29 = array18[1];
                                                num30 = int.Parse(array18[2].Split("帧".ToCharArray())[0]);
                                                EventRead eventRead22 = new EventRead();
                                                eventRead22.condition = text26;
                                                eventRead22.result = text28;
                                                eventRead22.condition2 = text27;
                                                eventRead22.contype = (int)Main.conditions2[key10];
                                                eventRead22.contype2 = (int)Main.conditions2[key11];
                                                eventRead22.opreator = opreator11;
                                                eventRead22.opreator2 = opreator12;
                                                eventRead22.collector = collector6;
                                                eventRead22.change = num28;
                                                eventRead22.changetype = (int)Main.type[text29];
                                                eventRead22.changevalue = num29;
                                                eventRead22.changename = (int)Main.results2[text30];
                                                eventRead22.res = res6;
                                                eventRead22.special = special6;
                                                eventRead22.special2 = 1;
                                                if (array18[0].Contains('+'))
                                                {
                                                    eventRead22.rand = Float.parseFloat(array18[0].Split('+')[1]);
                                                }
                                                eventRead22.times = num30;
                                                if (array18[2].Contains("("))
                                                {
                                                    eventRead22.time = int.Parse(array18[2].Split('(')[1].Split(')')[0]);
                                                }
                                                current18.results.Add(eventRead22);
                                            }
                                        }
                                    }
                                    finally
                                    {
                                        ((IDisposable)enumerator4).Dispose();
                                    }
                                }
                            }
                            finally
                            {
                                ((IDisposable)enumerator3).Dispose();
                            }
                        }
                    }
                    finally
                    {
                        ((IDisposable)enumerator6).Dispose();
                    }
                    enumerator7 = current.ReboundArray.GetEnumerator();
                    try
                    {
                        while (enumerator7.MoveNext())
                        {
                            Rebound current20 = enumerator7.Current;
                            current20.Selecting = false;
                            current20.copys = (current20.Copy() as Rebound);
                            float num31 = MathUtils.lerp(0f - current20.copys.rand.speed, current20.copys.rand.speed, RandomPool.get(5).random());
                            int num32 = (int)MathUtils.lerp(0f - current20.copys.rand.speedd, current20.copys.rand.speedd, RandomPool.get(5).random());
                            float num33 = MathUtils.lerp(0f - current20.copys.rand.aspeed, current20.copys.rand.aspeed, RandomPool.get(5).random());
                            int num34 = (int)MathUtils.lerp(0f - current20.copys.rand.aspeedd, current20.copys.rand.aspeedd, RandomPool.get(5).random());
                            current20.copys.aspeed += num33;
                            current20.copys.aspeedd += (float)num34;
                            current20.copys.speed += num31;
                            current20.copys.speedd += (float)num32;
                            current20.copys.aspeedx = current20.copys.aspeed * MathUtils.cosDeg((current20.copys.aspeedd));
                            current20.copys.aspeedy = current20.copys.aspeed * MathUtils.sinDeg((current20.copys.aspeedd));
                            current20.copys.speedx = current20.copys.speed * MathUtils.cosDeg((current20.copys.speedd));
                            current20.copys.speedy = current20.copys.speed * MathUtils.sinDeg((current20.copys.speedd));
                        }
                    }
                    finally
                    {
                        ((IDisposable)enumerator7).Dispose();
                    }
                    enumerator8 = current.ForceArray.GetEnumerator();
                    try
                    {
                        while (enumerator8.MoveNext())
                        {
                            Force current21 = enumerator8.Current;
                            current21.Selecting = false;
                            current21.copys = (current21.Copy() as Force);
                            float num35 = MathUtils.lerp(0f - current21.copys.rand.speed, current21.copys.rand.speed, RandomPool.get(5).random());
                            int num36 = (int)MathUtils.lerp(0f - current21.copys.rand.speedd, current21.copys.rand.speedd, RandomPool.get(5).random());
                            float num37 = MathUtils.lerp(0f - current21.copys.rand.aspeed, current21.copys.rand.aspeed, RandomPool.get(5).random());
                            int num38 = (int)MathUtils.lerp(0f - current21.copys.rand.aspeedd, current21.copys.rand.aspeedd, RandomPool.get(5).random());
                            current21.copys.aspeed += num37;
                            current21.copys.aspeedd += (float)num38;
                            current21.copys.speed += num35;
                            current21.copys.speedd += (float)num36;
                            current21.copys.aspeedx = current21.copys.aspeed * MathUtils.cosDeg((current21.copys.aspeedd));
                            current21.copys.aspeedy = current21.copys.aspeed * MathUtils.sinDeg((current21.copys.aspeedd));
                            current21.copys.speedx = current21.copys.speed * MathUtils.cosDeg((current21.copys.speedd));
                            current21.copys.speedy = current21.copys.speed * MathUtils.sinDeg((current21.copys.speedd));
                        }
                    }
                    finally
                    {
                        ((IDisposable)enumerator8).Dispose();
                    }
                }
            }
            finally
            {
                ((IDisposable)enumerator).Dispose();
            }
            int num39 = now;
            now = 1;
            for (int j = 0; j < num39; j++)
            {
                enumerator = Layer.LayerArray.GetEnumerator();
                try
                {
                    while (enumerator.MoveNext())
                    {
                        Layer current22 = enumerator.Current;
                        enumerator2 = current22.BatchArray.GetEnumerator();
                        try
                        {
                            while (enumerator2.MoveNext())
                            {
                                Batch current23 = enumerator2.Current;
                                current23.copys.PreviewUpdate();
                            }
                        }
                        finally
                        {
                            ((IDisposable)enumerator2).Dispose();
                        }
                    }
                }
                finally
                {
                    ((IDisposable)enumerator).Dispose();
                }
                now++;
            }
            now = 1;
            for (int k = 0; k < num39; k++)
            {
                enumerator = Layer.LayerArray.GetEnumerator();
                try
                {
                    while (enumerator.MoveNext())
                    {
                        Layer current24 = enumerator.Current;
                        enumerator5 = current24.LaseArray.GetEnumerator();
                        try
                        {
                            while (enumerator5.MoveNext())
                            {
                                Lase current25 = enumerator5.Current;
                                current25.copys.PreviewUpdate();
                            }
                        }
                        finally
                        {
                            ((IDisposable)enumerator5).Dispose();
                        }
                    }
                }
                finally
                {
                    ((IDisposable)enumerator).Dispose();
                }
                now++;
            }
            now = 1;
            for (int l = 0; l < num39; l++)
            {
                enumerator = Layer.LayerArray.GetEnumerator();
                try
                {
                    while (enumerator.MoveNext())
                    {
                        Layer current26 = enumerator.Current;
                        enumerator6 = current26.CoverArray.GetEnumerator();
                        try
                        {
                            while (enumerator6.MoveNext())
                            {
                                Cover current27 = enumerator6.Current;
                                current27.copys.PreviewUpdate();
                            }
                        }
                        finally
                        {
                            ((IDisposable)enumerator6).Dispose();
                        }
                    }
                }
                finally
                {
                    ((IDisposable)enumerator).Dispose();
                }
                now++;
            }
            now = 1;
            for (int m = 0; m < num39; m++)
            {
                enumerator = Layer.LayerArray.GetEnumerator();
                try
                {
                    while (enumerator.MoveNext())
                    {
                        Layer current28 = enumerator.Current;
                        enumerator7 = current28.ReboundArray.GetEnumerator();
                        try
                        {
                            while (enumerator7.MoveNext())
                            {
                                Rebound current29 = enumerator7.Current;
                                current29.copys.PreviewUpdate();
                            }
                        }
                        finally
                        {
                            ((IDisposable)enumerator7).Dispose();
                        }
                    }
                }
                finally
                {
                    ((IDisposable)enumerator).Dispose();
                }
                now++;
            }
            now = 1;
            for (int n = 0; n < num39; n++)
            {
                enumerator = Layer.LayerArray.GetEnumerator();
                try
                {
                    while (enumerator.MoveNext())
                    {
                        Layer current30 = enumerator.Current;
                        enumerator8 = current30.ForceArray.GetEnumerator();
                        try
                        {
                            while (enumerator8.MoveNext())
                            {
                                Force current31 = enumerator8.Current;
                                current31.copys.PreviewUpdate();
                            }
                        }
                        finally
                        {
                            ((IDisposable)enumerator8).Dispose();
                        }
                    }
                }
                finally
                {
                    ((IDisposable)enumerator).Dispose();
                }
                now++;
            }
            now = num39 - 1;
        }
    }

	public static void Update()
	{
		ArrayList<Layer>. enumerator;
		ArrayList<Batch>.Enumerator enumerator2;
		ArrayList<Event>.Enumerator enumerator3;
		ArrayList<Lase>.Enumerator enumerator5;
		ArrayList<Cover>.Enumerator enumerator6;
		ArrayList<Rebound>.Enumerator enumerator7;
		ArrayList<Force>.Enumerator enumerator8;
		ArrayList<GlobalEvent>.Enumerator enumerator9;
		
		if (Playing)
		{
			now++;
			if (now > total)
			{
				now = 1;
				left = 1;
				Center.Eventsexe.Clear();
				enumerator = Layer.LayerArray.GetEnumerator();
				try
				{
					while (enumerator.MoveNext())
					{
						Layer current43 = enumerator.Current;
						enumerator2 = current43.BatchArray.GetEnumerator();
						try
						{
							while (enumerator2.MoveNext())
							{
								Batch current44 = enumerator2.Current;
								current44.Eventsexe.Clear();
								current44.copys = (current44.Copy() as Batch);
								enumerator3 = current44.copys.Parentevents.GetEnumerator();
								try
								{
									while (enumerator3.MoveNext())
									{
										Event current45 = enumerator3.Current;
										current45.loop = 0;
									}
								}
								finally
								{
									((IDisposable)enumerator3).Dispose();
								}
								float num40 = MathUtils.lerp(0f - current44.copys.rand.speed, current44.copys.rand.speed, RandomPool.get(5).random());
								int num41 = (int)MathUtils.lerp(0f - current44.copys.rand.speedd, current44.copys.rand.speedd, RandomPool.get(5).random());
								float num42 = MathUtils.lerp(0f - current44.copys.rand.aspeed, current44.copys.rand.aspeed, RandomPool.get(5).random());
								int num43 = (int)MathUtils.lerp(0f - current44.copys.rand.aspeedd, current44.copys.rand.aspeedd, RandomPool.get(5).random());
								if (current44.fx == -99998f)
								{
									current44.copys.fx = current44.x - 4f;
								}
								if (current44.fx == -99999f)
								{
									current44.copys.fx = JudgingSystem.playerJudge.x;
								}
								if (current44.fy == -99998f)
								{
									current44.copys.fy = current44.y + 16f;
								}
								if (current44.fy == -99999f)
								{
									current44.copys.fy = JudgingSystem.playerJudge.y;
								}
								if (current44.speedd == -99999f)
								{
									current44.copys.speedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current44.copys.fx, current44.copys.fy));
								}
								if (current44.aspeedd == -99999f)
								{
									current44.copys.aspeedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current44.copys.fx, current44.copys.fy));
								}
								current44.copys.aspeed += num42;
								current44.copys.aspeedd += (float)num43;
								current44.copys.speed += num40;
								current44.copys.speedd += (float)num41;
								current44.copys.aspeedx = current44.copys.aspeed * MathUtils.cosDeg((current44.copys.aspeedd));
								current44.copys.aspeedy = current44.copys.aspeed * MathUtils.sinDeg((current44.copys.aspeedd));
								current44.copys.speedx = current44.copys.speed * MathUtils.cosDeg((current44.copys.speedd));
								current44.copys.speedy = current44.copys.speed * MathUtils.sinDeg((current44.copys.speedd));
								current44.copys.bfdirection = current44.fdirection;
								current44.copys.bsonaspeedd = current44.sonaspeedd;
								if (current44.fdirection == -99998f)
								{
									current44.copys.fdirection = GameHelper.twoPointAngle(current44.x - 4f, current44.y + 16f, current44.copys.fx, current44.copys.fy));
								}
								else if (current44.fdirection == -99999f)
								{
									current44.copys.fdirection = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current44.copys.fx, current44.copys.fy));
								}
								else if (current44.fdirection == -100000f)
								{
									current44.copys.fdirection = GameHelper.twoPointAngle(current44.fdirections.X, current44.fdirections.Y, current44.copys.fx, current44.copys.fy));
								}
								if (current44.sonaspeedd == -99998f)
								{
									current44.copys.sonaspeedd = GameHelper.twoPointAngle(current44.x - 4f, current44.y + 16f, current44.copys.fx, current44.copys.fy));
								}
								else if (current44.sonaspeedd == -99999f)
								{
									current44.copys.sonaspeedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current44.copys.fx, current44.copys.fy));
								}
								else if (current44.sonaspeedd == -100000f)
								{
									current44.copys.sonaspeedd = GameHelper.twoPointAngle(current44.sonaspeedds.X, current44.sonaspeedds.Y, current44.copys.fx, current44.copys.fy));
								}
								if (current44.head == -100000f)
								{
									current44.copys.head = GameHelper.twoPointAngle(current44.heads.X, current44.heads.Y, current44.copys.fx, current44.copys.fx));
								}
							}
						}
						finally
						{
							((IDisposable)enumerator2).Dispose();
						}
						enumerator5 = current43.LaseArray.GetEnumerator();
						try
						{
							while (enumerator5.MoveNext())
							{
								Lase current46 = enumerator5.Current;
								current46.Eventsexe.Clear();
								current46.copys = (current46.Copy() as Lase);
								enumerator3 = current46.copys.Parentevents.GetEnumerator();
								try
								{
									while (enumerator3.MoveNext())
									{
										Event current47 = enumerator3.Current;
										current47.loop = 0;
									}
								}
								finally
								{
									((IDisposable)enumerator3).Dispose();
								}
								float num44 = MathUtils.lerp(0f - current46.copys.rand.speed, current46.copys.rand.speed, RandomPool.get(5).random());
								int num45 = (int)MathUtils.lerp(0f - current46.copys.rand.speedd, current46.copys.rand.speedd, RandomPool.get(5).random());
								float num46 = MathUtils.lerp(0f - current46.copys.rand.aspeed, current46.copys.rand.aspeed, RandomPool.get(5).random());
								int num47 = (int)MathUtils.lerp(0f - current46.copys.rand.aspeedd, current46.copys.rand.aspeedd, RandomPool.get(5).random());
								current46.copys.aspeed += num46;
								current46.copys.aspeedd += (float)num47;
								current46.copys.speed += num44;
								current46.copys.speedd += (float)num45;
								current46.copys.aspeedx = current46.copys.aspeed * MathUtils.cosDeg((current46.copys.aspeedd));
								current46.copys.aspeedy = current46.copys.aspeed * MathUtils.sinDeg((current46.copys.aspeedd));
								current46.copys.speedx = current46.copys.speed * MathUtils.cosDeg((current46.copys.speedd));
								current46.copys.speedy = current46.copys.speed * MathUtils.sinDeg((current46.copys.speedd));
								if (current46.fdirection == -99999f)
								{
									current46.copys.fdirection = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current46.copys.x - 4f, current46.copys.y + 16f));
								}
								else if (current46.fdirection == -100000f)
								{
									current46.copys.fdirection = GameHelper.twoPointAngle(current46.fdirections.X, current46.fdirections.Y, current46.copys.x - 4f, current46.copys.y + 16f));
								}
								if (current46.sonaspeedd == -99998f)
								{
									current46.copys.sonaspeedd = GameHelper.twoPointAngle(current46.x - 4f, current46.y + 16f, current46.copys.x - 4f, current46.copys.y + 16f));
								}
								else if (current46.sonaspeedd == -99999f)
								{
									current46.copys.sonaspeedd = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, current46.copys.x - 4f, current46.copys.y + 16f));
								}
								else if (current46.sonaspeedd == -100000f)
								{
									current46.copys.sonaspeedd = GameHelper.twoPointAngle(current46.sonaspeedds.X, current46.sonaspeedds.Y, current46.copys.x - 4f, current46.copys.y + 16f));
								}
							}
						}
						finally
						{
							((IDisposable)enumerator5).Dispose();
						}
						enumerator6 = current43.CoverArray.GetEnumerator();
						try
						{
							while (enumerator6.MoveNext())
							{
								Cover current48 = enumerator6.Current;
								current48.Eventsexe.Clear();
								current48.copys = (current48.Copy() as Cover);
								enumerator3 = current48.copys.Parentevents.GetEnumerator();
								try
								{
									while (enumerator3.MoveNext())
									{
										Event current49 = enumerator3.Current;
										current49.loop = 0;
									}
								}
								finally
								{
									((IDisposable)enumerator3).Dispose();
								}
								float num48 = MathUtils.lerp(0f - current48.copys.rand.speed, current48.copys.rand.speed, RandomPool.get(5).random());
								int num49 = (int)MathUtils.lerp(0f - current48.copys.rand.speedd, current48.copys.rand.speedd, RandomPool.get(5).random());
								float num50 = MathUtils.lerp(0f - current48.copys.rand.aspeed, current48.copys.rand.aspeed, RandomPool.get(5).random());
								int num51 = (int)MathUtils.lerp(0f - current48.copys.rand.aspeedd, current48.copys.rand.aspeedd, RandomPool.get(5).random());
								current48.copys.aspeed += num50;
								current48.copys.aspeedd += (float)num51;
								current48.copys.speed += num48;
								current48.copys.speedd += (float)num49;
								current48.copys.aspeedx = current48.copys.aspeed * MathUtils.cosDeg((current48.copys.aspeedd));
								current48.copys.aspeedy = current48.copys.aspeed * MathUtils.sinDeg((current48.copys.aspeedd));
								current48.copys.speedx = current48.copys.speed * MathUtils.cosDeg((current48.copys.speedd));
								current48.copys.speedy = current48.copys.speed * MathUtils.sinDeg((current48.copys.speedd));
							}
						}
						finally
						{
							((IDisposable)enumerator6).Dispose();
						}
						enumerator7 = current43.ReboundArray.GetEnumerator();
						try
						{
							while (enumerator7.MoveNext())
							{
								Rebound current50 = enumerator7.Current;
								current50.copys = (current50.Copy() as Rebound);
								float num52 = MathUtils.lerp(0f - current50.copys.rand.speed, current50.copys.rand.speed, RandomPool.get(5).random());
								int num53 = (int)MathUtils.lerp(0f - current50.copys.rand.speedd, current50.copys.rand.speedd, RandomPool.get(5).random());
								float num54 = MathUtils.lerp(0f - current50.copys.rand.aspeed, current50.copys.rand.aspeed, RandomPool.get(5).random());
								int num55 = (int)MathUtils.lerp(0f - current50.copys.rand.aspeedd, current50.copys.rand.aspeedd, RandomPool.get(5).random());
								current50.copys.aspeed += num54;
								current50.copys.aspeedd += (float)num55;
								current50.copys.speed += num52;
								current50.copys.speedd += (float)num53;
								current50.copys.aspeedx = current50.copys.aspeed * MathUtils.cosDeg((current50.copys.aspeedd));
								current50.copys.aspeedy = current50.copys.aspeed * MathUtils.sinDeg((current50.copys.aspeedd));
								current50.copys.speedx = current50.copys.speed * MathUtils.cosDeg((current50.copys.speedd));
								current50.copys.speedy = current50.copys.speed * MathUtils.sinDeg((current50.copys.speedd));
							}
						}
						finally
						{
							((IDisposable)enumerator7).Dispose();
						}
						enumerator8 = current43.ForceArray.GetEnumerator();
						try
						{
							while (enumerator8.MoveNext())
							{
								Force current51 = enumerator8.Current;
								current51.copys = (current51.Copy() as Force);
								float num56 = MathUtils.lerp(0f - current51.copys.rand.speed, current51.copys.rand.speed, RandomPool.get(5).random());
								int num57 = (int)MathUtils.lerp(0f - current51.copys.rand.speedd, current51.copys.rand.speedd, RandomPool.get(5).random());
								float num58 = MathUtils.lerp(0f - current51.copys.rand.aspeed, current51.copys.rand.aspeed, RandomPool.get(5).random());
								int num59 = (int)MathUtils.lerp(0f - current51.copys.rand.aspeedd, current51.copys.rand.aspeedd, RandomPool.get(5).random());
								current51.copys.aspeed += num58;
								current51.copys.aspeedd += (float)num59;
								current51.copys.speed += num56;
								current51.copys.speedd += (float)num57;
								current51.copys.aspeedx = current51.copys.aspeed * MathUtils.cosDeg((current51.copys.aspeedd));
								current51.copys.aspeedy = current51.copys.aspeed * MathUtils.sinDeg((current51.copys.aspeedd));
								current51.copys.speedx = current51.copys.speed * MathUtils.cosDeg((current51.copys.speedd));
								current51.copys.speedy = current51.copys.speed * MathUtils.sinDeg((current51.copys.speedd));
							}
						}
						finally
						{
							((IDisposable)enumerator8).Dispose();
						}
					}
				}
				finally
				{
					((IDisposable)enumerator).Dispose();
				}
				enumerator9 = GE.GetEnumerator();
				try
				{
					while (enumerator9.MoveNext())
					{
						GlobalEvent current52 = enumerator9.Current;
						current52.qtcount = 0;
						current52.stcount = 0;
					}
				}
				finally
				{
					((IDisposable)enumerator9).Dispose();
				}
				stop = 1f;
				quake = new Vector2(0f, 0f);
			}
			if (now >= left + 105)
			{
				left++;
			}
			for (int num60 = 0; num60 < GE.Count; num60++)
			{
				if (num60 + 1 == now && GE[num60].isgoto)
				{
					GE[num60].gtcount++;
					if (GE[num60].gotowhere != 0 && (GE[num60].gototime == 0 || GE[num60].gtcount <= GE[num60].gototime))
					{
						now = GE[num60].gotowhere;
					}
				}
				if (GE[num60].isquake && now >= num60 + 1)
				{
					GE[num60].qtcount++;
					if (GE[num60].qtcount % 2 == 0 && (GE[num60].quaketime == 0 || GE[num60].qtcount <= GE[num60].quaketime))
					{
						quake = new Vector2(0f, (1f - (float)GE[num60].qtcount / (float)GE[num60].quaketime) * (float)GE[num60].quakelevel * (float)Math.Sin((double)GE[num60].qtcount));
					}
				}
				if (GE[num60].isstop && now >= num60 + 1)
				{
					GE[num60].stcount++;
					if (GE[num60].stoptime == 0 || GE[num60].stcount <= GE[num60].stoptime)
					{
						if (GE[num60].stoplevel == 0)
						{
							stop = (float)GE[num60].stcount / (float)GE[num60].stoptime * (float)GE[num60].stcount / (float)GE[num60].stoptime;
						}
						else if (GE[num60].stoplevel == 1)
						{
							stop = 0f;
						}
					}
					else
					{
						stop = 1f;
					}
				}
			}
		}
		int num61 = left + 106;
		if (num61 >= total)
		{
			num61 -= num61 - total;
		}
		for (int num62 = left; num62 < num61 + 1; num62++)
		{
			if (((x >= 169 + 6 * (num62 - left)) & (x <= 169 + 6 * (num62 - left) + 7) & (y >= 527) & (y <= 544)) && ((Main.mousestate.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed) & !Playing & Main.Available))
			{
				now = num62;
				Playing = false;
				Pause = false;
				enumerator = Layer.LayerArray.GetEnumerator();
				try
				{
					while (enumerator.MoveNext())
					{
						Layer current53 = enumerator.Current;
						enumerator2 = current53.BatchArray.GetEnumerator();
						try
						{
							while (enumerator2.MoveNext())
							{
								Batch current54 = enumerator2.Current;
								current54.Eventsexe.Clear();
							}
						}
						finally
						{
							((IDisposable)enumerator2).Dispose();
						}
						enumerator5 = current53.LaseArray.GetEnumerator();
						try
						{
							while (enumerator5.MoveNext())
							{
								Lase current55 = enumerator5.Current;
								current55.Eventsexe.Clear();
							}
						}
						finally
						{
							((IDisposable)enumerator5).Dispose();
						}
						current53.Barrages.Clear();
					}
				}
				finally
				{
					((IDisposable)enumerator).Dispose();
				}
			}
		}
		if (x >= 169 && x <= 800 && y >= 527 && y <= 544 && Main.mousestate.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed && ((Main.prostate.LeftButton != Microsoft.Xna.Framework.Input.ButtonState.Pressed) & !Playing & Main.Available))
		{
			clwait = 0;
			clcount++;
			if (clcount == 2)
			{
				clcount = 0;
				clwait = 0;
				TimeEvent timeEvent = new TimeEvent();
				timeEvent.GE = GE[now - 1];
				timeEvent.id = now - 1;
				timeEvent.Text = "当前帧：" + now.ToString();
				timeEvent.跳转启用.Checked = GE[now - 1].isgoto;
				timeEvent.跳转条件.SelectedIndex = GE[now - 1].gotocondition;
				timeEvent.跳转条件值.Text = GE[now - 1].gotocvalue.ToString();
				timeEvent.跳转执行次数.Text = GE[now - 1].gototime.ToString();
				timeEvent.跳转目标.Text = GE[now - 1].gotowhere.ToString();
				timeEvent.大于1.Checked = false;
				timeEvent.等于1.Checked = false;
				timeEvent.小于1.Checked = false;
				if (GE[now - 1].gotoopreator == ">")
				{
					timeEvent.大于1.Checked = true;
				}
				else if (GE[now - 1].gotoopreator == "<")
				{
					timeEvent.小于1.Checked = true;
				}
				else
				{
					timeEvent.等于1.Checked = true;
				}
				timeEvent.震屏启用.Checked = GE[now - 1].isquake;
				timeEvent.震屏条件.SelectedIndex = GE[now - 1].quakecondition;
				timeEvent.震屏条件值.Text = GE[now - 1].quakecvalue.ToString();
				timeEvent.震屏持续时间.Text = GE[now - 1].quaketime.ToString();
				timeEvent.震屏强度.Text = GE[now - 1].quakelevel.ToString();
				timeEvent.大于2.Checked = false;
				timeEvent.等于2.Checked = false;
				timeEvent.小于2.Checked = false;
				if (GE[now - 1].quakeopreator == ">")
				{
					timeEvent.大于2.Checked = true;
				}
				else if (GE[now - 1].quakeopreator == "<")
				{
					timeEvent.小于2.Checked = true;
				}
				else
				{
					timeEvent.等于2.Checked = true;
				}
				timeEvent.停滞启用.Checked = GE[now - 1].isstop;
				timeEvent.停滞条件.SelectedIndex = GE[now - 1].stopcondition;
				timeEvent.停滞条件值.Text = GE[now - 1].stopcvalue.ToString();
				timeEvent.停滞时间.Text = GE[now - 1].stoptime.ToString();
				timeEvent.停滞程度.SelectedIndex = GE[now - 1].stoplevel;
				timeEvent.大于3.Checked = false;
				timeEvent.等于3.Checked = false;
				timeEvent.小于3.Checked = false;
				if (GE[now - 1].stopopreator == ">")
				{
					timeEvent.大于3.Checked = true;
				}
				else if (GE[now - 1].stopopreator == "<")
				{
					timeEvent.小于3.Checked = true;
				}
				else
				{
					timeEvent.等于3.Checked = true;
				}
				timeEvent.Show();
			}
		}
		if (clcount == 1)
		{
			clwait++;
			if (clwait > 15)
			{
				clwait = 0;
				clcount = 0;
			}
		}
		if ((x >= 159) & (x <= 631) & (y >= 527) & (y <= 544))
		{
			if ((Main.mousestate.RightButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed) & !Playing & Main.Available)
			{
				if (Main.prostate.RightButton != Microsoft.Xna.Framework.Input.ButtonState.Pressed)
				{
					mouseleft = left;
					mousex = x;
				}
				left = (int)MathHelper.Clamp((float)(mouseleft - (x - mousex)), 1f, (float)(total - 105));
			}
			else
			{
				mouseleft = -1;
			}
		}
		if (Main.Available & search & !Playing)
		{
			ArrayList<Character> myCharacters = WindowInputCapturer.myCharacters;
			foreach (Character item in myCharacters)
			{
				if (!item.IsUsed)
				{
					if ((item.CharaterType == characterType.Char) & (Time.text.Length < 5))
					{
						Time.text += item.Chars.ToString();
					}
					else if (item.CharaterType == characterType.BackSpace)
					{
						Time.text = SubStringHelper.BackSpaceString(Time.text);
					}
					item.IsUsed = true;
				}
			}
			if (textsave != null)
			{
				Time.text = textsave;
				textsave = null;
			}
			if (((Main.mousestate.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed) & (Main.prostate.LeftButton != Microsoft.Xna.Framework.Input.ButtonState.Pressed) & !Playing & Main.Available) && ((x >= 48) & (x <= 62) & (y >= 481) & (y <= 497)))
			{
				try
				{
					now = (int)MathHelper.Clamp((float)int.Parse(Time.text), 1f, (float)total);
					if (now > left + 105 || now < left)
					{
						left = now;
					}
				}
				catch (Exception)
				{
					MessageBox.Show("只能输入数字", "错误", MessageBoxButtons.OK, MessageBoxIcon.Hand);
				}
			}
			time++;
			if (time >= 30)
			{
				time = 0;
			}
		}
		if ((Main.mousestate.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed) & (Main.prostate.LeftButton != Microsoft.Xna.Framework.Input.ButtonState.Pressed) & !Playing & Main.Available)
		{
			if ((x >= 6) & (x <= 62) & (y >= 481) & (y <= 497))
			{
				search = true;
			}
			else
			{
				textsave = Time.text;
				search = false;
			}
		}
	}

	/*public static void Draw(SpriteBatch s)
	{
		if (Main.Available)
		{
			if (Aim1)
			{
				s.Draw(Main.aimed, new Vector2(79f, 478f), new Rectangle(102, 0, 23, 23), Color.White);
			}
			if (Aim2)
			{
				s.Draw(Main.aimed, new Vector2(109f, 478f), new Rectangle(102, 0, 23, 23), Color.White);
			}
			if (Aim3)
			{
				s.Draw(Main.aimed, new Vector2(140f, 478f), new Rectangle(102, 0, 23, 23), Color.White);
			}
			if (now - left >= 0)
			{
				s.Draw(Main.graduation, new Vector2((float)(169 + 6 * (now - left)), 527f), new Rectangle(2, 0, 7, 17), Color.White);
			}
			int mx = left + 106;
			if (mx >= total)
			{
				mx -= mx - total;
			}
			for (int my = left; my < mx + 1; my++)
			{
				if (my % 5 == 0)
				{
					s.Draw(Main.graduation, new Vector2((float)(169 + 6 * (my - left)), 534f), new Rectangle(0, 0, 1, 10), Color.White);
				}
				else if ((my % 1 == 0) & (my % 5 != 0))
				{
					s.Draw(Main.graduation, new Vector2((float)(169 + 6 * (my - left)), 537f), new Rectangle(1, 3, 1, 7), Color.White);
				}
			}
			Main.font.Draw(s, "总帧数：" + total.ToString() + "     当前帧：" + now.ToString(), new Vector2(82f, 508f), Color.White);
			if (search)
			{
				if (time > 15)
				{
					Main.font.Draw(s, text + "|", new Vector2(9f, 483f), Color.Black);
				}
				else
				{
					Main.font.Draw(s, text, new Vector2(9f, 483f), Color.Black);
				}
			}
			else
			{
				Main.font.Draw(s, text, new Vector2(9f, 483f), Color.Black);
			}
		}
	}*/
}
