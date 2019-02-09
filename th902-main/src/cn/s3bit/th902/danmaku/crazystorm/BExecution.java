package cn.s3bit.th902.danmaku.crazystorm;

import com.badlogic.gdx.math.MathUtils;

// CrazyStorm_1._03.BExecution


public class BExecution
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

	public void Update(Barrage objects)
	{
		if (changetype == 1)
		{
			switch (changevalue)
			{
			case 0:
				if (change == 0)
				{
					objects.life = (int)value;
				}
				else if (change == 1)
				{
					objects.life += (int)value;
				}
				else if (change == 2)
				{
					objects.life -= (int)value;
				}
				break;
			case 1:
				if (change == 0)
				{
					objects.type = (int)value;
				}
				else if (change == 1)
				{
					objects.type += (int)value;
				}
				else if (change == 2)
				{
					objects.type -= (int)value;
				}
				break;
			case 2:
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
			case 3:
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
			case 4:
				if (change == 0)
				{
					objects.R = value;
				}
				else if (change == 1)
				{
					objects.R += value;
				}
				else if (change == 2)
				{
					objects.R -= value;
				}
				break;
			case 5:
				if (change == 0)
				{
					objects.G = value;
				}
				else if (change == 1)
				{
					objects.G += value;
				}
				else if (change == 2)
				{
					objects.G -= value;
				}
				break;
			case 6:
				if (change == 0)
				{
					objects.B = value;
				}
				else if (change == 1)
				{
					objects.B += value;
				}
				else if (change == 2)
				{
					objects.B -= value;
				}
				break;
			case 7:
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
			case 8:
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
			case 9:
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
				objects.speedx = objects.xscale * objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.yscale * objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 10:
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
				objects.speedx = objects.xscale * objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.yscale * objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 11:
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
				objects.aspeedx = objects.xscale * objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.yscale * objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 12:
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
				objects.aspeedx = objects.xscale * objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.yscale * objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 13:
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
			case 14:
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
			case 15:
				if (value > 0f)
				{
					objects.Mist = true;
				}
				if (value <= 0f)
				{
					objects.Mist = false;
				}
				break;
			case 16:
				if (value > 0f)
				{
					objects.Dispel = true;
				}
				if (value <= 0f)
				{
					objects.Dispel = false;
				}
				break;
			case 17:
				if (value > 0f)
				{
					objects.Blend = true;
				}
				if (value <= 0f)
				{
					objects.Blend = false;
				}
				break;
			case 18:
				if (value > 0f)
				{
					objects.Afterimage = true;
				}
				if (value <= 0f)
				{
					objects.Afterimage = false;
				}
				break;
			case 19:
				if (value > 0f)
				{
					objects.Outdispel = true;
				}
				if (value <= 0f)
				{
					objects.Outdispel = false;
				}
				break;
			case 20:
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
		else if (changetype == 0)
		{
			switch (changevalue)
			{
			case 0:
				if (change == 0)
				{
					objects.life = (int)(((float)objects.life * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.life += (int)(value / (float)time);
				}
				else if (change == 2)
				{
					objects.life -= (int)(value / (float)time);
				}
				break;
			case 1:
				if (change == 0)
				{
					objects.type = (int)(((float)objects.type * ((float)ctime - 1f) + value) / (float)ctime);
				}
				else if (change == 1)
				{
					objects.type += (int)(value / (float)time);
				}
				else if (change == 2)
				{
					objects.type -= (int)(value / (float)time);
				}
				break;
			case 2:
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
			case 3:
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
			case 4:
				if (change == 0)
				{
					objects.R = (objects.R * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.R += value / (float)time;
				}
				else if (change == 2)
				{
					objects.R -= value / (float)time;
				}
				break;
			case 5:
				if (change == 0)
				{
					objects.G = (objects.G * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.G += value / (float)time;
				}
				else if (change == 2)
				{
					objects.G -= value / (float)time;
				}
				break;
			case 6:
				if (change == 0)
				{
					objects.B = (objects.B * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.B += value / (float)time;
				}
				else if (change == 2)
				{
					objects.B -= value / (float)time;
				}
				break;
			case 7:
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
			case 8:
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
			case 9:
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
				objects.speedx = objects.xscale * objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.yscale * objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 10:
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
				objects.speedx = objects.xscale * objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.yscale * objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 11:
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
				objects.aspeedx = objects.xscale * objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.yscale * objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 12:
				if (change == 0)
				{
					objects.aspeedd = (objects.aspeedd * ((float)ctime - 1f) + value) / (float)ctime;
				}
				else if (change == 1)
				{
					objects.aspeedd += value / (float)time;
				}
				else if (change == 2)
				{
					objects.aspeedd -= value / (float)time;
				}
				objects.aspeedx = objects.xscale * objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.yscale * objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 13:
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
			case 14:
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
			case 15:
				if (value > 0f)
				{
					objects.Mist = true;
				}
				if (value <= 0f)
				{
					objects.Mist = false;
				}
				break;
			case 16:
				if (value > 0f)
				{
					objects.Dispel = true;
				}
				if (value <= 0f)
				{
					objects.Dispel = false;
				}
				break;
			case 17:
				if (value > 0f)
				{
					objects.Blend = true;
				}
				if (value <= 0f)
				{
					objects.Blend = false;
				}
				break;
			case 18:
				if (value > 0f)
				{
					objects.Afterimage = true;
				}
				if (value <= 0f)
				{
					objects.Afterimage = false;
				}
				break;
			case 19:
				if (value > 0f)
				{
					objects.Outdispel = true;
				}
				if (value <= 0f)
				{
					objects.Outdispel = false;
				}
				break;
			case 20:
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
					objects.life = (int)(Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 1)
				{
					objects.life = (int)(Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 2)
				{
					objects.life = (int)(Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				break;
			case 1:
				if (change == 0)
				{
					objects.type = (int)(Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 1)
				{
					objects.type = (int)(Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				else if (change == 2)
				{
					objects.type = (int)(Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime))));
				}
				break;
			case 2:
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
			case 3:
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
			case 4:
				if (change == 0)
				{
					objects.R = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.R = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.R = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 5:
				if (change == 0)
				{
					objects.G = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.G = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.G = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 6:
				if (change == 0)
				{
					objects.B = Float.parseFloat(region) + (value - Float.parseFloat(region)) * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 1)
				{
					objects.B = Float.parseFloat(region) + value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				else if (change == 2)
				{
					objects.B = Float.parseFloat(region) - value * MathUtils.sinDeg((360f / (float)time * ((float)time - (float)ctime)));
				}
				break;
			case 7:
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
			case 8:
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
			case 9:
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
				objects.speedx = objects.xscale * objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.yscale * objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 10:
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
				objects.speedx = objects.xscale * objects.speed * MathUtils.cosDeg((objects.speedd));
				objects.speedy = objects.yscale * objects.speed * MathUtils.sinDeg((objects.speedd));
				break;
			case 11:
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
				objects.aspeedx = objects.xscale * objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.yscale * objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 12:
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
				objects.aspeedx = objects.xscale * objects.aspeed * MathUtils.cosDeg((objects.aspeedd));
				objects.aspeedy = objects.yscale * objects.aspeed * MathUtils.sinDeg((objects.aspeedd));
				break;
			case 13:
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
			case 14:
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
			case 15:
				if (value > 0f)
				{
					objects.Mist = true;
				}
				if (value <= 0f)
				{
					objects.Mist = false;
				}
				break;
			case 16:
				if (value > 0f)
				{
					objects.Dispel = true;
				}
				if (value <= 0f)
				{
					objects.Dispel = false;
				}
				break;
			case 17:
				if (value > 0f)
				{
					objects.Blend = true;
				}
				if (value <= 0f)
				{
					objects.Blend = false;
				}
				break;
			case 18:
				if (value > 0f)
				{
					objects.Afterimage = true;
				}
				if (value <= 0f)
				{
					objects.Afterimage = false;
				}
				break;
			case 19:
				if (value > 0f)
				{
					objects.Outdispel = true;
				}
				if (value <= 0f)
				{
					objects.Outdispel = false;
				}
				break;
			case 20:
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
}
