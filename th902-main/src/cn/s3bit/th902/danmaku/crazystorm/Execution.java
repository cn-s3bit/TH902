package cn.s3bit.th902.danmaku.crazystorm;

import com.badlogic.gdx.math.MathUtils;

public class Execution
{
	public int parentid;

	public int id;

	public int change;

	public int changetype;

	public int changevalue;

	public String region;

	public float value;

	public int time;

	public int ctime;

	public boolean NeedDelete;

	public void Update(Batch objects)
	{
		if (changetype == 0)
		{
			switch (changevalue)
			{
			case 0:
				if (change == 0)
				{
					objects.fx = (objects.fx * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.fx += value / (float)time;
				}
				else if (change == 2)
				{
					objects.fx -= value / (float)time;
				}
				break;
			case 1:
				if (change == 0)
				{
					objects.fy = (objects.fy * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.fy += value / (float)time;
				}
				else if (change == 2)
				{
					objects.fy -= value / (float)time;
				}
				break;
			case 2:
				if (change == 0)
				{
					objects.r = (objects.r * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.r += value / (float)time;
				}
				else if (change == 2)
				{
					objects.r -= value / (float)time;
				}
				break;
			case 3:
				if (change == 0)
				{
					objects.rdirection = (objects.rdirection * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.rdirection += value / (float)time;
				}
				else if (change == 2)
				{
					objects.rdirection -= value / (float)time;
				}
				break;
			case 4:
				if (change == 0)
				{
					objects.tiao = (int)(((float)objects.tiao * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.tiao += (int)(value / (float)time);
				}
				else if (change == 2)
				{
					objects.tiao -= (int)(value / (float)time);
				}
				break;
			case 5:
				if (change == 0)
				{
					objects.t = (int)(((float)objects.t * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.t += (int)(value / (float)time);
				}
				else if (change == 2)
				{
					objects.t -= (int)(value / (float)time);
				}
				break;
			case 6:
				if (change == 0)
				{
					objects.fdirection = (objects.fdirection * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.fdirection += value / (float)time;
				}
				else if (change == 2)
				{
					objects.fdirection -= value / (float)time;
				}
				break;
			case 7:
				if (change == 0)
				{
					objects.range = (int)(((float)objects.range * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.range += (int)(value / (float)time);
				}
				else if (change == 2)
				{
					objects.range -= (int)(value / (float)time);
				}
				break;
			case 8:
				if (change == 0)
				{
					objects.speed = (objects.speed * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.speed += value / (float)time;
				}
				else if (change == 2)
				{
					objects.speed -= value / (float)time;
				}
				objects.speedx = objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 9:
				if (change == 0)
				{
					objects.speedd = (objects.speedd * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.speedd += value / (float)time;
				}
				else if (change == 2)
				{
					objects.speedd -= value / (float)time;
				}
				objects.speedx = objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 10:
				if (change == 0)
				{
					objects.aspeed = (objects.aspeed * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.aspeed += value / (float)time;
				}
				else if (change == 2)
				{
					objects.aspeed -= value / (float)time;
				}
				objects.aspeedx = objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 11:
				if (change == 0)
				{
					objects.aspeedd = (float)(int)((objects.aspeedd * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.aspeedd += value / (float)time;
				}
				else if (change == 2)
				{
					objects.aspeedd -= value / (float)time;
				}
				objects.aspeedx = objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 12:
				if (change == 0)
				{
					objects.sonlife = (int)(((float)objects.sonlife * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.sonlife += (int)(value / (float)time);
				}
				else if (change == 2)
				{
					objects.sonlife -= (int)(value / (float)time);
				}
				break;
			case 13:
				if (change == 0)
				{
					objects.type = (objects.type * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.type += value / (float)time;
				}
				else if (change == 2)
				{
					objects.type -= value / (float)time;
				}
				break;
			case 14:
				if (change == 0)
				{
					objects.wscale = (objects.wscale * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.wscale += value / (float)time;
				}
				else if (change == 2)
				{
					objects.wscale -= value / (float)time;
				}
				break;
			case 15:
				if (change == 0)
				{
					objects.hscale = (objects.hscale * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.hscale += value / (float)time;
				}
				else if (change == 2)
				{
					objects.hscale -= value / (float)time;
				}
				break;
			case 16:
				if (change == 0)
				{
					objects.colorR = (float)(int)((objects.colorR * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.colorR += value / (float)time;
				}
				else if (change == 2)
				{
					objects.colorR -= value / (float)time;
				}
				break;
			case 17:
				if (change == 0)
				{
					objects.colorG = (float)(int)((objects.colorG * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.colorG += value / (float)time;
				}
				else if (change == 2)
				{
					objects.colorG -= value / (float)time;
				}
				break;
			case 18:
				if (change == 0)
				{
					objects.colorB = (float)(int)((objects.colorB * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.colorB += value / (float)time;
				}
				else if (change == 2)
				{
					objects.colorB -= value / (float)time;
				}
				break;
			case 19:
				if (change == 0)
				{
					objects.alpha = (objects.alpha * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.alpha += value / (float)time;
				}
				else if (change == 2)
				{
					objects.alpha -= value / (float)time;
				}
				break;
			case 20:
				if (change == 0)
				{
					objects.head = (objects.head * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.head += value / (float)time;
				}
				else if (change == 2)
				{
					objects.head -= value / (float)time;
				}
				break;
			case 21:
				if (change == 0)
				{
					objects.sonspeed = (objects.sonspeed * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.sonspeed += value / (float)time;
				}
				else if (change == 2)
				{
					objects.sonspeed -= value / (float)time;
				}
				break;
			case 22:
				if (change == 0)
				{
					objects.sonspeedd = (float)(int)((objects.sonspeedd * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.sonspeedd += value / (float)time;
				}
				else if (change == 2)
				{
					objects.sonspeedd -= value / (float)time;
				}
				break;
			case 23:
				if (change == 0)
				{
					objects.sonaspeed = (objects.sonaspeed * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.sonaspeed += value / (float)time;
				}
				else if (change == 2)
				{
					objects.sonaspeed -= value / (float)time;
				}
				break;
			case 24:
				if (change == 0)
				{
					objects.sonaspeedd = (float)(int)((objects.sonaspeedd * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.sonaspeedd += value / (float)time;
				}
				else if (change == 2)
				{
					objects.sonaspeedd -= value / (float)time;
				}
				break;
			case 25:
				if (change == 0)
				{
					objects.xscale = (objects.xscale * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.xscale += value / (float)time;
				}
				else if (change == 2)
				{
					objects.xscale -= value / (float)time;
				}
				break;
			case 26:
				if (change == 0)
				{
					objects.yscale = (objects.yscale * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.yscale += value / (float)time;
				}
				else if (change == 2)
				{
					objects.yscale -= value / (float)time;
				}
				break;
			case 27:
				if (value > 0f)
				{
					objects.Mist = true;
				}
				if (value <= 0f)
				{
					objects.Mist = false;
				}
				break;
			case 28:
				if (value > 0f)
				{
					objects.Dispel = true;
				}
				if (value <= 0f)
				{
					objects.Dispel = false;
				}
				break;
			case 29:
				if (value > 0f)
				{
					objects.Blend = true;
				}
				if (value <= 0f)
				{
					objects.Blend = false;
				}
				break;
			case 30:
				if (value > 0f)
				{
					objects.Afterimage = true;
				}
				if (value <= 0f)
				{
					objects.Afterimage = false;
				}
				break;
			case 31:
				if (value > 0f)
				{
					objects.Outdispel = true;
				}
				if (value <= 0f)
				{
					objects.Outdispel = false;
				}
				break;
			case 32:
				if (value > 0f)
				{
					objects.Invincible = true;
				}
				if (value <= 0f)
				{
					objects.Invincible = false;
				}
				break;
			}
		}
		else if (changetype == 1)
		{
			switch (changevalue)
			{
			case 0:
				if (change == 0)
				{
					objects.fx = value;
				}
				else if (change == 1)
				{
					objects.fx += value;
				}
				else if (change == 2)
				{
					objects.fx -= value;
				}
				break;
			case 1:
				if (change == 0)
				{
					objects.fy = value;
				}
				else if (change == 1)
				{
					objects.fy += value;
				}
				else if (change == 2)
				{
					objects.fy -= value;
				}
				break;
			case 2:
				if (change == 0)
				{
					objects.r = value;
				}
				else if (change == 1)
				{
					objects.r += value;
				}
				else if (change == 2)
				{
					objects.r -= value;
				}
				break;
			case 3:
				if (change == 0)
				{
					objects.rdirection = value;
				}
				else if (change == 1)
				{
					objects.rdirection += value;
				}
				else if (change == 2)
				{
					objects.rdirection -= value;
				}
				break;
			case 4:
				if (change == 0)
				{
					objects.tiao = (int)value;
				}
				else if (change == 1)
				{
					objects.tiao += (int)value;
				}
				else if (change == 2)
				{
					objects.tiao -= (int)value;
				}
				break;
			case 5:
				if (change == 0)
				{
					objects.t = (int)value;
				}
				else if (change == 1)
				{
					objects.t += (int)value;
				}
				else if (change == 2)
				{
					objects.t -= (int)value;
				}
				break;
			case 6:
				if (change == 0)
				{
					objects.fdirection = value;
				}
				else if (change == 1)
				{
					objects.fdirection += value;
				}
				else if (change == 2)
				{
					objects.fdirection -= value;
				}
				break;
			case 7:
				if (change == 0)
				{
					objects.range = (int)value;
				}
				else if (change == 1)
				{
					objects.range += (int)value;
				}
				else if (change == 2)
				{
					objects.range -= (int)value;
				}
				break;
			case 8:
				if (change == 0)
				{
					objects.speed = value;
				}
				else if (change == 1)
				{
					objects.speed += value;
				}
				else if (change == 2)
				{
					objects.speed -= value;
				}
				objects.speedx = objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 9:
				if (change == 0)
				{
					objects.speedd = value;
				}
				else if (change == 1)
				{
					objects.speedd += value;
				}
				else if (change == 2)
				{
					objects.speedd -= value;
				}
				objects.speedx = objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 10:
				if (change == 0)
				{
					objects.aspeed = value;
				}
				else if (change == 1)
				{
					objects.aspeed += value;
				}
				else if (change == 2)
				{
					objects.aspeed -= value;
				}
				objects.aspeedx = objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 11:
				if (change == 0)
				{
					objects.aspeedd = value;
				}
				else if (change == 1)
				{
					objects.aspeedd += value;
				}
				else if (change == 2)
				{
					objects.aspeedd -= value;
				}
				objects.aspeedx = objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 12:
				if (change == 0)
				{
					objects.sonlife = (int)value;
				}
				else if (change == 1)
				{
					objects.sonlife += (int)value;
				}
				else if (change == 2)
				{
					objects.sonlife -= (int)value;
				}
				break;
			case 13:
				if (change == 0)
				{
					objects.type = (float)(int)value;
				}
				else if (change == 1)
				{
					objects.type += (float)(int)value;
				}
				else if (change == 2)
				{
					objects.type -= (float)(int)value;
				}
				break;
			case 14:
				if (change == 0)
				{
					objects.wscale = value;
				}
				else if (change == 1)
				{
					objects.wscale += value;
				}
				else if (change == 2)
				{
					objects.wscale -= value;
				}
				break;
			case 15:
				if (change == 0)
				{
					objects.hscale = value;
				}
				else if (change == 1)
				{
					objects.hscale += value;
				}
				else if (change == 2)
				{
					objects.hscale -= value;
				}
				break;
			case 16:
				if (change == 0)
				{
					objects.colorR = value;
				}
				else if (change == 1)
				{
					objects.colorR += value;
				}
				else if (change == 2)
				{
					objects.colorR -= value;
				}
				break;
			case 17:
				if (change == 0)
				{
					objects.colorG = value;
				}
				else if (change == 1)
				{
					objects.colorG += value;
				}
				else if (change == 2)
				{
					objects.colorG -= value;
				}
				break;
			case 18:
				if (change == 0)
				{
					objects.colorB = value;
				}
				else if (change == 1)
				{
					objects.colorB += value;
				}
				else if (change == 2)
				{
					objects.colorB -= value;
				}
				break;
			case 19:
				if (change == 0)
				{
					objects.alpha = value;
				}
				else if (change == 1)
				{
					objects.alpha += value;
				}
				else if (change == 2)
				{
					objects.alpha -= value;
				}
				break;
			case 20:
				if (change == 0)
				{
					objects.head = value;
				}
				else if (change == 1)
				{
					objects.head += value;
				}
				else if (change == 2)
				{
					objects.head -= value;
				}
				break;
			case 21:
				if (change == 0)
				{
					objects.sonspeed = value;
				}
				else if (change == 1)
				{
					objects.sonspeed += value;
				}
				else if (change == 2)
				{
					objects.sonspeed -= value;
				}
				break;
			case 22:
				if (change == 0)
				{
					objects.sonspeedd = value;
				}
				else if (change == 1)
				{
					objects.sonspeedd += value;
				}
				else if (change == 2)
				{
					objects.sonspeedd -= value;
				}
				break;
			case 23:
				if (change == 0)
				{
					objects.sonaspeed = value;
				}
				else if (change == 1)
				{
					objects.sonaspeed += value;
				}
				else if (change == 2)
				{
					objects.sonaspeed -= value;
				}
				break;
			case 24:
				if (change == 0)
				{
					objects.sonaspeedd = value;
				}
				else if (change == 1)
				{
					objects.sonaspeedd += value;
				}
				else if (change == 2)
				{
					objects.sonaspeedd -= value;
				}
				break;
			case 25:
				if (change == 0)
				{
					objects.xscale = value;
				}
				else if (change == 1)
				{
					objects.xscale += value;
				}
				else if (change == 2)
				{
					objects.xscale -= value;
				}
				break;
			case 26:
				if (change == 0)
				{
					objects.yscale = value;
				}
				else if (change == 1)
				{
					objects.yscale += value;
				}
				else if (change == 2)
				{
					objects.yscale -= value;
				}
				break;
			case 27:
				if (value > 0f)
				{
					objects.Mist = true;
				}
				if (value <= 0f)
				{
					objects.Mist = false;
				}
				break;
			case 28:
				if (value > 0f)
				{
					objects.Dispel = true;
				}
				if (value <= 0f)
				{
					objects.Dispel = false;
				}
				break;
			case 29:
				if (value > 0f)
				{
					objects.Blend = true;
				}
				if (value <= 0f)
				{
					objects.Blend = false;
				}
				break;
			case 30:
				if (value > 0f)
				{
					objects.Afterimage = true;
				}
				if (value <= 0f)
				{
					objects.Afterimage = false;
				}
				break;
			case 31:
				if (value > 0f)
				{
					objects.Outdispel = true;
				}
				if (value <= 0f)
				{
					objects.Outdispel = false;
				}
				break;
			case 32:
				if (value > 0f)
				{
					objects.Invincible = true;
				}
				if (value <= 0f)
				{
					objects.Invincible = false;
				}
				break;
			}
		}
		else if (changetype == 2)
		{
			switch (changevalue)
			{
			case 0:
				if (change == 0)
				{
					objects.fx = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.fx = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.fx = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 1:
				if (change == 0)
				{
					objects.fy = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.fy = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.fy = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 2:
				if (change == 0)
				{
					objects.r = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.r = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.r = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 3:
				if (change == 0)
				{
					objects.rdirection = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.rdirection = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.rdirection = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 4:
				if (change == 0)
				{
					objects.tiao = (int)(Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 1)
				{
					objects.tiao = (int)(Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 2)
				{
					objects.tiao = (int)(Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				break;
			case 5:
				if (change == 0)
				{
					objects.t = (int)(Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 1)
				{
					objects.t = (int)(Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 2)
				{
					objects.t = (int)(Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				break;
			case 6:
				if (change == 0)
				{
					objects.fdirection = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * (float)(time - ctime)));
				}
				else if (change == 1)
				{
					objects.fdirection = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * (float)(time - ctime)));
				}
				else if (change == 2)
				{
					objects.fdirection = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * (float)(time - ctime)));
				}
				break;
			case 7:
				if (change == 0)
				{
					objects.range = (int)(Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 1)
				{
					objects.range = (int)(Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 2)
				{
					objects.range = (int)(Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				break;
			case 8:
				if (change == 0)
				{
					objects.speed = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.speed = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.speed = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				objects.speedx = objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 9:
				if (change == 0)
				{
					objects.speedd = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.speedd = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.speedd = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				objects.speedx = objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 10:
				if (change == 0)
				{
					objects.aspeed = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.aspeed = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.aspeed = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				objects.aspeedx = objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 11:
				if (change == 0)
				{
					objects.aspeedd = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.aspeedd = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.aspeedd = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				objects.aspeedx = objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 12:
				if (change == 0)
				{
					objects.sonlife = (int)(Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 1)
				{
					objects.sonlife = (int)(Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 2)
				{
					objects.sonlife = (int)(Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				break;
			case 13:
				if (change == 0)
				{
					objects.type = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.type = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.type = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 14:
				if (change == 0)
				{
					objects.wscale = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.wscale = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.wscale = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 15:
				if (change == 0)
				{
					objects.hscale = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.hscale = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.hscale = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 16:
				if (change == 0)
				{
					objects.colorR = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.colorR = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.colorR = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 17:
				if (change == 0)
				{
					objects.colorG = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.colorG = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.colorG = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 18:
				if (change == 0)
				{
					objects.colorB = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.colorB = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.colorB = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 19:
				if (change == 0)
				{
					objects.alpha = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.alpha = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.alpha = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 20:
				if (change == 0)
				{
					objects.head = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.head = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.head = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 21:
				if (change == 0)
				{
					objects.sonspeed = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.sonspeed = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.sonspeed = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 22:
				if (change == 0)
				{
					objects.sonspeedd = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.sonspeedd = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.sonspeedd = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 23:
				if (change == 0)
				{
					objects.sonaspeed = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.sonaspeed = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.sonaspeed = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 24:
				if (change == 0)
				{
					objects.sonaspeedd = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.sonaspeedd = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.sonaspeedd = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 25:
				if (change == 0)
				{
					objects.xscale = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.xscale = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.xscale = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 26:
				if (change == 0)
				{
					objects.yscale = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.yscale = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.yscale = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 27:
				if (value > 0f)
				{
					objects.Mist = true;
				}
				if (value <= 0f)
				{
					objects.Mist = false;
				}
				break;
			case 28:
				if (value > 0f)
				{
					objects.Dispel = true;
				}
				if (value <= 0f)
				{
					objects.Dispel = false;
				}
				break;
			case 29:
				if (value > 0f)
				{
					objects.Blend = true;
				}
				if (value <= 0f)
				{
					objects.Blend = false;
				}
				break;
			case 30:
				if (value > 0f)
				{
					objects.Afterimage = true;
				}
				if (value <= 0f)
				{
					objects.Afterimage = false;
				}
				break;
			case 31:
				if (value > 0f)
				{
					objects.Outdispel = true;
				}
				if (value <= 0f)
				{
					objects.Outdispel = false;
				}
				break;
			case 32:
				if (value > 0f)
				{
					objects.Invincible = true;
				}
				if (value <= 0f)
				{
					objects.Invincible = false;
				}
				break;
			}
		}
		ctime--;
		if ((changetype == 2) & (ctime == -1))
		{
			NeedDelete = true;
		}
		else if ((changetype != 2) & (ctime == 0))
		{
			NeedDelete = true;
		}
	}

	/*public object Clone()
	{
		MemoryStream memoryStream = new MemoryStream();
		BinaryFormatter binaryFormatter = new BinaryFormatter();
		binaryFormatter.Serialize(memoryStream, this);
		memoryStream.Seek(0L, SeekOrigin.Begin);
		object result = binaryFormatter.Deserialize(memoryStream);
		memoryStream.Close();
		return result;
	}*/
}
