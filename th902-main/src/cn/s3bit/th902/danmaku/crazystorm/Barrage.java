package cn.s3bit.th902.danmaku.crazystorm;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.ResourceManager;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.utils.RandomPool;

public class Barrage
{
	public boolean IsLase;

	public boolean IsRay;

	public int id = -1;

	public int parentid = -2;

	public int shatime;
	
	public boolean NeedDelete;

	public boolean Dis;

	public List<Integer> Covered = new ArrayList<>();

	public int life;

	public int time;

	public int type;

	public float x;

	public float y;

	public float dscale = 0.9f;

	public float wscale;

	public float rwscale;

	public float hscale;

	public float longs;

	public float rlongs;

	public float randf;

	public float R;

	public float G;

	public float B;

	public float alpha;

	public float head;

	public float speed;

	public float speedx;

	public float speedy;

	public float bspeedx;

	public float bspeedy;

	public float speedd;

	public float vf;

	public float aspeed;

	public float aspeedx;

	public float aspeedy;

	public float aspeedd;

	public boolean Withspeedd;

	public float fdirection;

	public float sonaspeedd;

	public float fx;

	public float fy;

	public Vector2 fdirections;

	public Vector2 sonaspeedds;

	public float randfdirection;

	public float randsonaspeedd;

	public int g;

	public int tiaos;

	public int range;

	public int randrange;

	public float bindspeedd;

	public boolean Bindwithspeedd;

	public float xscale;

	public float yscale;

	public boolean Mist;

	public boolean Dispel;

	public boolean Blend;

	public boolean Afterimage;

	public boolean Outdispel;

	public boolean Invincible;

	public boolean Cover;

	public boolean Rebound;

	public boolean Force;

	public boolean Alreadylong;

	public int reboundtime;

	public int fadeout;

	public Batch batch;

	// public Lase lase;

	// public Cover cover;

	public List<Event> Events;

	public List<BExecution> Eventsexe;

	// public List<BLExecution> LEventsexe;

	private float[] conditions = new float[3];

	private float[] results = new float[21];

	public Barrage()
	{
		NeedDelete = false;
		Events = new ArrayList<Event>();
		Eventsexe = new ArrayList<BExecution>();
		// LEventsexe = new ArrayList<BLExecution>();
	}

	public void Update()
	{
		if (!IsLase & (type != -2))
		{
			// float x = this.x;
			// float y = this.y;
			float form = JudgingSystem.playerJudge.x;
			float i8 = JudgingSystem.playerJudge.y;
			int k11 = 0;
			if (Mist)
			{
				k11 = 15;
			}
			time++;
			if (type <= -1)
			{
				type = -1;
			}
			if (type >= ResourceManager.barrages.size())
			{
				type = ResourceManager.barrages.size() - 1;
			}
			if (time > 15 || !Mist)
			{
				if ((Mist & (time == 16)) || (!Mist & (time == 1)))
				{
					/*if (type > -1 && type < Main.bgset.Count && Main.bgset[type].sd != null)
					{
						Main.bgset[type].sd.Play();
					}*/
					if (fdirection == -99998f)
					{
						fdirection = GameHelper.twoPointAngle(fx, fy, this.x, this.y);
					}
					else if (fdirection == -99999f)
					{
						fdirection = GameHelper.twoPointAngle(form, i8, this.x, this.y);
					}
					else if (fdirection == -100000f)
					{
						fdirection = GameHelper.twoPointAngle(fdirections.x, fdirections.y, this.x, this.y);
					}
					if (Bindwithspeedd)
					{
						speedd = fdirection + randfdirection + ((float)g - ((float)tiaos - 1f) / 2f) * (float)(range + randrange) / (float)tiaos + bindspeedd;
					}
					else
					{
						speedd = fdirection + randfdirection + ((float)g - ((float)tiaos - 1f) / 2f) * (float)(range + randrange) / (float)tiaos;
					}
					if (sonaspeedd == -99998f)
					{
						sonaspeedd = GameHelper.twoPointAngle(fx, fy, this.x, this.y);
					}
					else if (sonaspeedd == -99999f)
					{
						sonaspeedd = GameHelper.twoPointAngle(form, i8, this.x, this.y);
					}
					else if (sonaspeedd == -100000f)
					{
						sonaspeedd = GameHelper.twoPointAngle(sonaspeedds.x, sonaspeedds.y, this.x, this.y);
					}
					aspeedd = sonaspeedd + randsonaspeedd;
					speedx = xscale * speed * MathUtils.cosDeg(speedd);
					speedy = yscale * speed * MathUtils.sinDeg(speedd);
					aspeedx = xscale * aspeed * MathUtils.cosDeg(aspeedd);
					aspeedy = yscale * aspeed * MathUtils.sinDeg(aspeedd);
					if (Withspeedd)
					{
						head = speedd + 90f;
					}
				}
				if (!Dis)
				{
					speedx += aspeedx * Time.stop;
					speedy += aspeedy * Time.stop;
					this.x += speedx * Time.stop;
					this.y += speedy * Time.stop;
				}
				if (speed != 0f)
				{
					if (speedy != 0f)
					{
						vf = 1.57079637f - (float)Math.atan((double)(speedx / xscale / (speedy / yscale)));
						if (speedy < 0f)
						{
							vf += 3.14159274f;
						}
					}
					else
					{
						if (speedx >= 0f)
						{
							vf = 0f;
						}
						if (speedx < 0f)
						{
							vf = 3.14159274f;
						}
					}
					if (speed > 0f)
					{
						speedd = MathUtils.radiansToDegrees * (vf);
						if (Withspeedd)
						{
							head = speedd;
						}
					}
					else if (Withspeedd)
					{
						head = MathUtils.radiansToDegrees * (vf);
					}
				}
				if (Afterimage & (time <= k11 + life))
				{
					shatime++;
					if (shatime >= 49)
					{
						shatime = 0;
					}
				}
				else
				{
					shatime = 0;
				}
				conditions[0] = (float)(time - k11);
				conditions[1] = this.x;
				conditions[2] = this.y;
				results[0] = (float)life;
				results[1] = (float)type;
				results[2] = wscale;
				results[3] = hscale;
				results[4] = R;
				results[5] = G;
				results[6] = B;
				results[7] = alpha;
				results[8] = head;
				results[9] = speed;
				results[10] = speedd;
				results[11] = aspeed;
				results[12] = aspeedd;
				results[13] = xscale;
				results[14] = yscale;
				results[15] = 0f;
				results[16] = 0f;
				results[17] = 0f;
				results[18] = 0f;
				results[19] = 0f;
				results[20] = 0f;
				for (Event event : Events)
				{
					if (event.t <= 0)
					{
						event.t = 1;
					}
					if ((time - k11) % event.t == 0)
					{
						event.loop++;
					}
					for (EventRead result : event.results)
					{
						if (result.special2 == 1)
						{
							conditions[0] = (float)Time.now;
						}
						if (result.opreator.equals(">"))
						{
							if (result.opreator2.equals(">"))
							{
								if (result.collector.equals("且"))
								{
									if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution copy4 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										copy4.change = result.change;
										copy4.changetype = result.changetype;
										copy4.changevalue = result.changevalue;
										copy4.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										copy4.region = Float.toString(results[result.changename]);
										copy4.time = result.times;
										copy4.ctime = copy4.time;
										Eventsexe.add(copy4);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution k9 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									k9.change = result.change;
									k9.changetype = result.changetype;
									k9.changevalue = result.changevalue;
									k9.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									k9.region = Float.toString(results[result.changename]);
									k9.time = result.times;
									k9.ctime = k9.time;
									Eventsexe.add(k9);
								}
							}
							else if (result.opreator2 == "=")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution copy3 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										copy3.change = result.change;
										copy3.changetype = result.changetype;
										copy3.changevalue = result.changevalue;
										copy3.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, (float)RandomPool.get(5).random());
										copy3.region = Float.toString(results[result.changename]);
										copy3.time = result.times;
										copy3.ctime = copy3.time;
										Eventsexe.add(copy3);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution k8 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									k8.change = result.change;
									k8.changetype = result.changetype;
									k8.changevalue = result.changevalue;
									k8.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									k8.region = Float.toString(results[result.changename]);
									k8.time = result.times;
									k8.ctime = k8.time;
									Eventsexe.add(k8);
								}
							}
							else if (result.opreator2 == "<")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution copy2 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										copy2.change = result.change;
										copy2.changetype = result.changetype;
										copy2.changevalue = result.changevalue;
										copy2.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										copy2.region = Float.toString(results[result.changename]);
										copy2.time = result.times;
										copy2.ctime = copy2.time;
										Eventsexe.add(copy2);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution k7 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									k7.change = result.change;
									k7.changetype = result.changetype;
									k7.changevalue = result.changevalue;
									k7.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									k7.region = Float.toString(results[result.changename]);
									k7.time = result.times;
									k7.ctime = k7.time;
									Eventsexe.add(k7);
								}
							}
							else if (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime))
							{
								if (result.special == 4)
								{
									if (result.changevalue == 10)
									{
										result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
									}
									if (result.changevalue == 12)
									{
										result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
									}
								}
								BExecution copy = new BExecution();
								if (result.noloop)
								{
									continue;
								}
								if (result.time > 0)
								{
									result.time--;
									if (result.time == 0)
									{
										result.noloop = true;
									}
								}
								copy.change = result.change;
								copy.changetype = result.changetype;
								copy.changevalue = result.changevalue;
								copy.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
								copy.region = Float.toString(results[result.changename]);
								copy.time = result.times;
								copy.ctime = copy.time;
								Eventsexe.add(copy);
							}
						}
						if (result.opreator == "=")
						{
							if (result.opreator2 == ">")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution total = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										total.change = result.change;
										total.changetype = result.changetype;
										total.changevalue = result.changevalue;
										total.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										total.region = Float.toString(results[result.changename]);
										total.time = result.times;
										total.ctime = total.time;
										Eventsexe.add(total);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution num = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									num.change = result.change;
									num.changetype = result.changetype;
									num.changevalue = result.changevalue;
									num.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									num.region = Float.toString(results[result.changename]);
									num.time = result.times;
									num.ctime = num.time;
									Eventsexe.add(num);
								}
							}
							else if (result.opreator2 == "=")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution i7 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										i7.change = result.change;
										i7.changetype = result.changetype;
										i7.changevalue = result.changevalue;
										i7.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										i7.region = Float.toString(results[result.changename]);
										i7.time = result.times;
										i7.ctime = i7.time;
										Eventsexe.add(i7);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution k6 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									k6.change = result.change;
									k6.changetype = result.changetype;
									k6.changevalue = result.changevalue;
									k6.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									k6.region = Float.toString(results[result.changename]);
									k6.time = result.times;
									k6.ctime = k6.time;
									Eventsexe.add(k6);
								}
							}
							else if (result.opreator2 == "<")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution i6 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										i6.change = result.change;
										i6.changetype = result.changetype;
										i6.changevalue = result.changevalue;
										i6.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										i6.region = Float.toString(results[result.changename]);
										i6.time = result.times;
										i6.ctime = i6.time;
										Eventsexe.add(i6);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution k5 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									k5.change = result.change;
									k5.changetype = result.changetype;
									k5.changevalue = result.changevalue;
									k5.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									k5.region = Float.toString(results[result.changename]);
									k5.time = result.times;
									k5.ctime = k5.time;
									Eventsexe.add(k5);
								}
							}
							else if (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime))
							{
								if (result.special == 4)
								{
									if (result.changevalue == 10)
									{
										result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
									}
									if (result.changevalue == 12)
									{
										result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
									}
								}
								BExecution i5 = new BExecution();
								if (result.noloop)
								{
									continue;
								}
								if (result.time > 0)
								{
									result.time--;
									if (result.time == 0)
									{
										result.noloop = true;
									}
								}
								i5.change = result.change;
								i5.changetype = result.changetype;
								i5.changevalue = result.changevalue;
								i5.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
								i5.region = Float.toString(results[result.changename]);
								i5.time = result.times;
								i5.ctime = i5.time;
								Eventsexe.add(i5);
							}
						}
						if (result.opreator == "<")
						{
							if (result.opreator2 == ">")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution k4 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										k4.change = result.change;
										k4.changetype = result.changetype;
										k4.changevalue = result.changevalue;
										k4.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										k4.region = Float.toString(results[result.changename]);
										k4.time = result.times;
										k4.ctime = k4.time;
										Eventsexe.add(k4);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution i4 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									i4.change = result.change;
									i4.changetype = result.changetype;
									i4.changevalue = result.changevalue;
									i4.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									i4.region = Float.toString(results[result.changename]);
									i4.time = result.times;
									i4.ctime = i4.time;
									Eventsexe.add(i4);
								}
							}
							else if (result.opreator2 == "=")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution k3 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										k3.change = result.change;
										k3.changetype = result.changetype;
										k3.changevalue = result.changevalue;
										k3.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										k3.region = Float.toString(results[result.changename]);
										k3.time = result.times;
										k3.ctime = k3.time;
										Eventsexe.add(k3);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution i3 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									i3.change = result.change;
									i3.changetype = result.changetype;
									i3.changevalue = result.changevalue;
									i3.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									i3.region = Float.toString(results[result.changename]);
									i3.time = result.times;
									i3.ctime = i3.time;
									Eventsexe.add(i3);
								}
							}
							else if (result.opreator2 == "<")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 10)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
											if (result.changevalue == 12)
											{
												result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
											}
										}
										BExecution k2 = new BExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										k2.change = result.change;
										k2.changetype = result.changetype;
										k2.changevalue = result.changevalue;
										k2.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										k2.region = Float.toString(results[result.changename]);
										k2.time = result.times;
										k2.ctime = k2.time;
										Eventsexe.add(k2);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 10)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
										if (result.changevalue == 12)
										{
											result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
										}
									}
									BExecution i2 = new BExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									i2.change = result.change;
									i2.changetype = result.changetype;
									i2.changevalue = result.changevalue;
									i2.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									i2.region = Float.toString(results[result.changename]);
									i2.time = result.times;
									i2.ctime = i2.time;
									Eventsexe.add(i2);
								}
							}
							else if (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime))
							{
								if (result.special == 4)
								{
									if (result.changevalue == 10)
									{
										result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
									}
									if (result.changevalue == 12)
									{
										result.res = GameHelper.twoPointAngle(form, i8, this.x, this.y);
									}
								}
								BExecution n = new BExecution();
								if (result.noloop)
								{
									continue;
								}
								if (result.time > 0)
								{
									result.time--;
									if (result.time == 0)
									{
										result.noloop = true;
									}
								}
								n.change = result.change;
								n.changetype = result.changetype;
								n.changevalue = result.changevalue;
								n.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
								n.region = Float.toString(results[result.changename]);
								n.time = result.times;
								n.ctime = n.time;
								Eventsexe.add(n);
							}
						}
						/*if (result.special == 5)
						{
							this.x = Center.ox;
							this.y = Center.oy;
						}*/
						if (result.special2 == 1)
						{
							conditions[0] = (float)Time.now;
						}
					}
				}
				for (int m = 0; m < Eventsexe.size(); m++)
				{
					if (!Eventsexe.get(m).NeedDelete)
					{
						Eventsexe.get(m).Update(this);
					}
					else
					{
						Eventsexe.remove(m);
						m--;
					}
				}
				/*if ((Main.Missable & !Dis & !Player.Dis & (alpha > 95f) & (type >= 0)) && Judge(x, y, this.x, this.y, form, i8, Player.position.X, Player.position.Y, wscale, hscale, Main.bgset[type].pdr0, head))
				{
					if (!Invincible)
					{
						time = 1 + k11 + life;
						Dis = true;
						Blend = true;
						randf = 10f * RandomPool.get(5).random();
					}
					Player.Dis = true;
				}*/
				/*if (!Dis && ((double)((this.x - form) * (this.x - form) + (this.y - i8) * (this.y - i8))) < 225.0 && !Invincible)
				{
					time = 1 + k11 + life;
					Dis = true;
					Blend = true;
					randf = 10f * RandomPool.get(5).random();
				}*/
				if (time > k11 + life)
				{
					if (Dispel & (type >= 0))
					{
						if (ResourceManager.barrages.get(type).getWidth() <= 32)
						{
							fadeout += 5;
							alpha -= 5f;
							if (alpha <= 0f)
							{
								alpha = 0f;
							}
							wscale = MathUtils.clamp(wscale - 0.06f, 0f, 100f);
							hscale = MathUtils.clamp(hscale - 0.06f, 0f, 100f);
							if (time - (k11 + life) >= 20)
							{
								NeedDelete = true;
							}
						}
						else
						{
							fadeout += 5;
							alpha -= 5f;
							if (alpha <= 0f)
							{
								alpha = 0f;
							}
							wscale += 0.06f;
							hscale += 0.06f;
							if (time - (k11 + life) >= 20)
							{
								NeedDelete = true;
							}
						}
					}
					else
					{
						NeedDelete = true;
					}
				}
			}
			else if (!Invincible && ((double)((this.x - form) * (this.x - form) + (this.y - i8) * (this.y - i8))) <= 100.0)
			{
				NeedDelete = true;
			}
			// int l = 0;
			/*Shadows[] k = savesha;
			foreach (Shadows i in k)
			{
				if (i.alpha <= 0f)
				{
					l++;
				}
			}*/
			/*if (Outdispel)
			{
				if (l == savesha.Length)
				{
					if (Main.WideScreen)
					{
						if (this.x < -50f || this.x > 680f || this.y < -50f || this.y > 530f)
						{
							NeedDelete = true;
						}
					}
					else if (this.x < 90f || this.x > 540f || this.y < -50f || this.y > 530f)
					{
						NeedDelete = true;
					}
				}
			}
			else if (l == savesha.Length)
			{
				if (Main.WideScreen)
				{
					if (this.x < -250f || this.x > 880f || this.y < -250f || this.y > 730f)
					{
						NeedDelete = true;
					}
				}
				else if (this.x < -110f || this.x > 740f || this.y < -250f || this.y > 730f)
				{
					NeedDelete = true;
				}
			}*/
		}
		if (!IsLase & (type == -2))
		{
			NeedDelete = true;
		}
	}

	/*public void Draw(SpriteBatch s)
	{
		if (!IsLase && type != -1)
		{
			if ((time <= 15) & Mist)
			{
				if (Main.bgset[type].rect.Width <= 48)
				{
					if (Main.bgset[type].color != -1)
					{
						s.Draw(Main.mist, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), new Rectangle(Main.bgset[type].color * 32, 0, 32, 30), new Color(R / 255f, G / 255f, B / 255f, (float)time / 15f * (alpha / 100f)), 0f, new Vector2(16f, 15f), (float)Main.bgset[type].rect.Width / 30f + 1.5f * (15f - (float)time) / 15f, SpriteEffects.None, 0f);
					}
					else if (type < 228)
					{
						s.Draw(Main.barrages, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, (float)time / 15f * (alpha / 100f)), MathHelper.ToRadians(head) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale, hscale), SpriteEffects.None, 0f);
					}
					else
					{
						s.Draw(Main.barrages2, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, (float)time / 15f * (alpha / 100f)), MathHelper.ToRadians(head) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale, hscale), SpriteEffects.None, 0f);
					}
				}
				else if (type < 228)
				{
					s.Draw(Main.barrages, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, (float)time / 15f * (alpha / 100f)), MathHelper.ToRadians(head) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale + (15f - (float)time) / 15f, hscale + (15f - (float)time) / 15f), SpriteEffects.None, 0f);
				}
				else
				{
					s.Draw(Main.barrages2, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, (float)time / 15f * (alpha / 100f)), MathHelper.ToRadians(head) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale + (15f - (float)time) / 15f, hscale + (15f - (float)time) / 15f), SpriteEffects.None, 0f);
				}
			}
			else
			{
				if (type < 228)
				{
					s.Draw(Main.barrages, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, alpha / 100f), MathHelper.ToRadians(head) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale, hscale), SpriteEffects.None, 0f);
				}
				else
				{
					s.Draw(Main.barrages2, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, alpha / 100f), MathHelper.ToRadians(head) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale, hscale), SpriteEffects.None, 0f);
				}
				if (Afterimage)
				{
					Shadows[] array = savesha;
					foreach (Shadows shadows in array)
					{
						if (shadows.alpha > 0f)
						{
							shadows.alpha -= 0.02f;
							if (type < 228)
							{
								s.Draw(Main.barrages, new Vector2(170f + shadows.x + Time.quake.X, 22f + shadows.y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, shadows.alpha), MathHelper.ToRadians(shadows.d) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale, hscale), SpriteEffects.None, 0f);
							}
							else
							{
								s.Draw(Main.barrages2, new Vector2(170f + shadows.x + Time.quake.X, 22f + shadows.y + Time.quake.Y), Main.bgset[type].rect, new Color(R / 255f, G / 255f, B / 255f, shadows.alpha), MathHelper.ToRadians(shadows.d) + 1.57079637f, Main.bgset[type].origin, new Vector2(wscale, hscale), SpriteEffects.None, 0f);
							}
						}
					}
				}
				if (Dis & (Main.bgset[type].rect.Width <= 48))
				{
					s.Draw(Main.dis, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), new Rectangle(Main.bgset[type].color * 32, 0, 32, 32), new Color(R / 255f, G / 255f, B / 255f, alpha / 100f), randf, new Vector2(16f, 16f), dscale, SpriteEffects.None, 0f);
				}
			}
		}
	}*/

	/*public void LUpdate()
	{
		if (IsLase & (type != -1))
		{
			float num = x;
			float num2 = y;
			float bpx = Player.position.X;
			float bpy = Player.position.Y;
			time++;
			if (time <= life)
			{
				conditions[0] = (float)time;
				results[0] = (float)life;
				results[1] = (float)type;
				results[2] = wscale;
				results[3] = longs;
				results[4] = alpha;
				results[5] = speed;
				results[6] = speedd;
				results[7] = aspeed;
				results[8] = aspeedd;
				results[9] = xscale;
				results[10] = yscale;
				results[11] = 0f;
				results[12] = 0f;
				results[13] = 0f;
				foreach (Event event in Events)
				{
					if (event.t <= 0)
					{
						event.t = 1;
					}
					if (time % event.t == 0)
					{
						event.loop++;
					}
					foreach (EventRead result in event.results)
					{
						if (result.opreator == ">")
						{
							if (result.opreator2 == ">")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution.change = result.change;
										bLExecution.changetype = result.changetype;
										bLExecution.changevalue = result.changevalue;
										bLExecution.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution.region = Float.toString(results[result.changename]);
										bLExecution.time = result.times;
										bLExecution.ctime = bLExecution.time;
										LEventsexe.Add(bLExecution);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution2 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution2.change = result.change;
									bLExecution2.changetype = result.changetype;
									bLExecution2.changevalue = result.changevalue;
									bLExecution2.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution2.region = Float.toString(results[result.changename]);
									bLExecution2.time = result.times;
									bLExecution2.ctime = bLExecution2.time;
									LEventsexe.Add(bLExecution2);
								}
							}
							else if (result.opreator2 == "=")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution3 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution3.change = result.change;
										bLExecution3.changetype = result.changetype;
										bLExecution3.changevalue = result.changevalue;
										bLExecution3.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution3.region = Float.toString(results[result.changename]);
										bLExecution3.time = result.times;
										bLExecution3.ctime = bLExecution3.time;
										LEventsexe.Add(bLExecution3);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution4 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution4.change = result.change;
									bLExecution4.changetype = result.changetype;
									bLExecution4.changevalue = result.changevalue;
									bLExecution4.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution4.region = Float.toString(results[result.changename]);
									bLExecution4.time = result.times;
									bLExecution4.ctime = bLExecution4.time;
									LEventsexe.Add(bLExecution4);
								}
							}
							else if (result.opreator2 == "<")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution5 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution5.change = result.change;
										bLExecution5.changetype = result.changetype;
										bLExecution5.changevalue = result.changevalue;
										bLExecution5.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution5.region = Float.toString(results[result.changename]);
										bLExecution5.time = result.times;
										bLExecution5.ctime = bLExecution5.time;
										LEventsexe.Add(bLExecution5);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution6 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution6.change = result.change;
									bLExecution6.changetype = result.changetype;
									bLExecution6.changevalue = result.changevalue;
									bLExecution6.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution6.region = Float.toString(results[result.changename]);
									bLExecution6.time = result.times;
									bLExecution6.ctime = bLExecution6.time;
									LEventsexe.Add(bLExecution6);
								}
							}
							else if (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(event.loop * event.addtime))
							{
								if (result.special == 4)
								{
									if (result.changevalue == 6)
									{
										result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
									}
									if (result.changevalue == 8)
									{
										result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
									}
								}
								BLExecution bLExecution7 = new BLExecution();
								if (result.noloop)
								{
									continue;
								}
								if (result.time > 0)
								{
									result.time--;
									if (result.time == 0)
									{
										result.noloop = true;
									}
								}
								bLExecution7.change = result.change;
								bLExecution7.changetype = result.changetype;
								bLExecution7.changevalue = result.changevalue;
								bLExecution7.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
								bLExecution7.region = Float.toString(results[result.changename]);
								bLExecution7.time = result.times;
								bLExecution7.ctime = bLExecution7.time;
								LEventsexe.Add(bLExecution7);
							}
						}
						if (result.opreator == "=")
						{
							if (result.opreator2 == ">")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution8 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution8.change = result.change;
										bLExecution8.changetype = result.changetype;
										bLExecution8.changevalue = result.changevalue;
										bLExecution8.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution8.region = Float.toString(results[result.changename]);
										bLExecution8.time = result.times;
										bLExecution8.ctime = bLExecution8.time;
										LEventsexe.Add(bLExecution8);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution9 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution9.change = result.change;
									bLExecution9.changetype = result.changetype;
									bLExecution9.changevalue = result.changevalue;
									bLExecution9.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution9.region = Float.toString(results[result.changename]);
									bLExecution9.time = result.times;
									bLExecution9.ctime = bLExecution9.time;
									LEventsexe.Add(bLExecution9);
								}
							}
							else if (result.opreator2 == "=")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution10 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution10.change = result.change;
										bLExecution10.changetype = result.changetype;
										bLExecution10.changevalue = result.changevalue;
										bLExecution10.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution10.region = Float.toString(results[result.changename]);
										bLExecution10.time = result.times;
										bLExecution10.ctime = bLExecution10.time;
										LEventsexe.Add(bLExecution10);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution11 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution11.change = result.change;
									bLExecution11.changetype = result.changetype;
									bLExecution11.changevalue = result.changevalue;
									bLExecution11.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution11.region = Float.toString(results[result.changename]);
									bLExecution11.time = result.times;
									bLExecution11.ctime = bLExecution11.time;
									LEventsexe.Add(bLExecution11);
								}
							}
							else if (result.opreator2 == "<")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution12 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution12.change = result.change;
										bLExecution12.changetype = result.changetype;
										bLExecution12.changevalue = result.changevalue;
										bLExecution12.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution12.region = Float.toString(results[result.changename]);
										bLExecution12.time = result.times;
										bLExecution12.ctime = bLExecution12.time;
										LEventsexe.Add(bLExecution12);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution13 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution13.change = result.change;
									bLExecution13.changetype = result.changetype;
									bLExecution13.changevalue = result.changevalue;
									bLExecution13.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution13.region = Float.toString(results[result.changename]);
									bLExecution13.time = result.times;
									bLExecution13.ctime = bLExecution13.time;
									LEventsexe.Add(bLExecution13);
								}
							}
							else if (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(event.loop * event.addtime))
							{
								if (result.special == 4)
								{
									if (result.changevalue == 6)
									{
										result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
									}
									if (result.changevalue == 8)
									{
										result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
									}
								}
								BLExecution bLExecution14 = new BLExecution();
								if (result.noloop)
								{
									continue;
								}
								if (result.time > 0)
								{
									result.time--;
									if (result.time == 0)
									{
										result.noloop = true;
									}
								}
								bLExecution14.change = result.change;
								bLExecution14.changetype = result.changetype;
								bLExecution14.changevalue = result.changevalue;
								bLExecution14.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
								bLExecution14.region = Float.toString(results[result.changename]);
								bLExecution14.time = result.times;
								bLExecution14.ctime = bLExecution14.time;
								LEventsexe.Add(bLExecution14);
							}
						}
						if (result.opreator == "<")
						{
							if (result.opreator2 == ">")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution15 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution15.change = result.change;
										bLExecution15.changetype = result.changetype;
										bLExecution15.changevalue = result.changevalue;
										bLExecution15.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution15.region = Float.toString(results[result.changename]);
										bLExecution15.time = result.times;
										bLExecution15.ctime = bLExecution15.time;
										LEventsexe.Add(bLExecution15);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution16 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution16.change = result.change;
									bLExecution16.changetype = result.changetype;
									bLExecution16.changevalue = result.changevalue;
									bLExecution16.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution16.region = Float.toString(results[result.changename]);
									bLExecution16.time = result.times;
									bLExecution16.ctime = bLExecution16.time;
									LEventsexe.Add(bLExecution16);
								}
							}
							else if (result.opreator2 == "=")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution17 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution17.change = result.change;
										bLExecution17.changetype = result.changetype;
										bLExecution17.changevalue = result.changevalue;
										bLExecution17.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution17.region = Float.toString(results[result.changename]);
										bLExecution17.time = result.times;
										bLExecution17.ctime = bLExecution17.time;
										LEventsexe.Add(bLExecution17);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution18 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution18.change = result.change;
									bLExecution18.changetype = result.changetype;
									bLExecution18.changevalue = result.changevalue;
									bLExecution18.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution18.region = Float.toString(results[result.changename]);
									bLExecution18.time = result.times;
									bLExecution18.ctime = bLExecution18.time;
									LEventsexe.Add(bLExecution18);
								}
							}
							else if (result.opreator2 == "<")
							{
								if (result.collector == "且")
								{
									if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
									{
										if (result.special == 4)
										{
											if (result.changevalue == 6)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
											if (result.changevalue == 8)
											{
												result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
											}
										}
										BLExecution bLExecution19 = new BLExecution();
										if (result.noloop)
										{
											continue;
										}
										if (result.time > 0)
										{
											result.time--;
											if (result.time == 0)
											{
												result.noloop = true;
											}
										}
										bLExecution19.change = result.change;
										bLExecution19.changetype = result.changetype;
										bLExecution19.changevalue = result.changevalue;
										bLExecution19.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										bLExecution19.region = Float.toString(results[result.changename]);
										bLExecution19.time = result.times;
										bLExecution19.ctime = bLExecution19.time;
										LEventsexe.Add(bLExecution19);
									}
								}
								else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(event.loop * event.addtime)))
								{
									if (result.special == 4)
									{
										if (result.changevalue == 6)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
										if (result.changevalue == 8)
										{
											result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
										}
									}
									BLExecution bLExecution20 = new BLExecution();
									if (result.noloop)
									{
										continue;
									}
									if (result.time > 0)
									{
										result.time--;
										if (result.time == 0)
										{
											result.noloop = true;
										}
									}
									bLExecution20.change = result.change;
									bLExecution20.changetype = result.changetype;
									bLExecution20.changevalue = result.changevalue;
									bLExecution20.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
									bLExecution20.region = Float.toString(results[result.changename]);
									bLExecution20.time = result.times;
									bLExecution20.ctime = bLExecution20.time;
									LEventsexe.Add(bLExecution20);
								}
							}
							else if (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(event.loop * event.addtime))
							{
								if (result.special == 4)
								{
									if (result.changevalue == 6)
									{
										result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
									}
									if (result.changevalue == 8)
									{
										result.res = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, x, y));
									}
								}
								BLExecution bLExecution21 = new BLExecution();
								if (result.noloop)
								{
									continue;
								}
								if (result.time > 0)
								{
									result.time--;
									if (result.time == 0)
									{
										result.noloop = true;
									}
								}
								bLExecution21.change = result.change;
								bLExecution21.changetype = result.changetype;
								bLExecution21.changevalue = result.changevalue;
								bLExecution21.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
								bLExecution21.region = Float.toString(results[result.changename]);
								bLExecution21.time = result.times;
								bLExecution21.ctime = bLExecution21.time;
								LEventsexe.Add(bLExecution21);
							}
						}
						if (result.special == 5)
						{
							x = Center.ox;
							y = Center.oy;
						}
					}
				}
				for (int i = 0; i < LEventsexe.Count; i++)
				{
					if (!LEventsexe[i].NeedDelete)
					{
						LEventsexe[i].Update(this);
					}
					else
					{
						LEventsexe.RemoveAt(i);
						i--;
					}
				}
				rwscale = wscale;
				if (!IsRay)
				{
					if (speedy != 0f)
					{
						vf = 1.57079637f - (float)Math.Atan((double)(speedx / speedy));
						if (speedy < 0f)
						{
							vf += 3.14159274f;
						}
					}
					else
					{
						if (speedx >= 0f)
						{
							vf = 0f;
						}
						if (speedx < 0f)
						{
							vf = 3.14159274f;
						}
					}
					head = MathHelper.ToDegrees(vf);
					if ((rlongs < longs) & !Alreadylong)
					{
						rlongs += speed;
					}
					if (rlongs >= longs)
					{
						Alreadylong = true;
					}
					if (rlongs >= longs || Alreadylong)
					{
						rlongs = longs;
						speedx += aspeedx * Time.stop;
						speedy += aspeedy * Time.stop;
						x += speedx * Time.stop;
						y += speedy * Time.stop;
						if (Outdispel)
						{
							if (Main.WideScreen)
							{
								if (x < -50f || x > 680f || y < -50f || y > 530f)
								{
									NeedDelete = true;
								}
							}
							else if (x < 90f || x > 540f || y < -50f || y > 530f)
							{
								NeedDelete = true;
							}
						}
						else if (Main.WideScreen)
						{
							if (x < -250f || x > 880f || y < -250f || y > 730f)
							{
								NeedDelete = true;
							}
						}
						else if (x < -110f || x > 740f || y < -250f || y > 730f)
						{
							NeedDelete = true;
						}
					}
					if (Main.Missable & !Player.Dis & (alpha > 95f))
					{
						num = (num + num + rlongs * (float)Math.Cos((double)MathHelper.ToRadians(speedd))) / 2f;
						num2 = (num2 + num2 + rlongs * (float)Math.Sin((double)MathHelper.ToRadians(speedd))) / 2f;
						float num3 = (x + x + rlongs * (float)Math.Cos((double)MathHelper.ToRadians(speedd))) / 2f;
						float num4 = (y + y + rlongs * (float)Math.Sin((double)MathHelper.ToRadians(speedd))) / 2f;
						float hs = rlongs / 6f;
						if (Judge(num, num2, num3, num4, bpx, bpy, Player.position.X, Player.position.Y, wscale, hs, 2f, head) & (wscale >= 0.5f))
						{
							if (!Invincible)
							{
								time = 1 + life;
								Dis = true;
								randf = 10f * RandomPool.get(5).random();
							}
							Player.Dis = true;
						}
					}
					if ((Main.Missable & !Dis) && Math.Sqrt((double)((x - Player.position.X) * (x - Player.position.X) + (y - Player.position.Y) * (y - Player.position.Y))) < (double)Math.Abs(Player.time * 15) && !Invincible)
					{
						time = 1 + life;
						Dis = true;
						randf = 10f * RandomPool.get(5).random();
					}
				}
				else
				{
					rlongs = 792f;
					head = speedd;
					speedx += aspeedx * Time.stop;
					speedy += aspeedy * Time.stop;
					x += speedx * Time.stop;
					y += speedy * Time.stop;
					if (Main.Missable & !Dis & !Player.Dis & (alpha > 95f))
					{
						num = (num + num + rlongs * (float)Math.Cos((double)MathHelper.ToRadians(speedd))) / 2f;
						num2 = (num2 + num2 + rlongs * (float)Math.Sin((double)MathHelper.ToRadians(speedd))) / 2f;
						float num5 = (x + x + rlongs * (float)Math.Cos((double)MathHelper.ToRadians(speedd))) / 2f;
						float num6 = (y + y + rlongs * (float)Math.Sin((double)MathHelper.ToRadians(speedd))) / 2f;
						float hs2 = rlongs / 6f;
						if (Judge(num, num2, num5, num6, bpx, bpy, Player.position.X, Player.position.Y, wscale, hs2, 2f, head) & (wscale >= 0.5f))
						{
							if (!Invincible)
							{
								time = 1 + life;
								Dis = true;
								randf = 10f * RandomPool.get(5).random();
							}
							Player.Dis = true;
						}
					}
					if ((Main.Missable & !Dis) && Math.Sqrt((double)((x - Player.position.X) * (x - Player.position.X) + (y - Player.position.Y) * (y - Player.position.Y))) < (double)Math.Abs(Player.time * 15) && !Invincible)
					{
						time = 1 + life;
						Dis = true;
						randf = 10f * RandomPool.get(5).random();
					}
				}
			}
			else
			{
				if (!IsRay)
				{
					speedx += aspeedx;
					speedy += aspeedy;
					x += speedx;
					y += speedy;
					rlongs -= speed;
					wscale -= 0.1f * rwscale;
					if (wscale < 0f)
					{
						wscale = 0f;
					}
					if (rlongs < 0f)
					{
						rlongs = 0f;
					}
					if (rlongs == 0f)
					{
						NeedDelete = true;
					}
				}
				else
				{
					head = speedd;
					wscale -= 0.1f * rwscale;
					if (wscale < 0f)
					{
						wscale = 0f;
					}
					if (wscale == 0f)
					{
						NeedDelete = true;
					}
				}
				for (int j = 0; j < LEventsexe.Count; j++)
				{
					if (!LEventsexe[j].NeedDelete)
					{
						LEventsexe[j].Update(this);
					}
					else
					{
						LEventsexe.RemoveAt(j);
						j--;
					}
				}
			}
		}
		if (IsLase & (type == -1))
		{
			NeedDelete = true;
		}
	}

	public void LDraw(SpriteBatch s)
	{
		if (IsLase & (type != -1))
		{
			if ((time <= life) & (((rlongs < longs) & !Alreadylong) || IsRay))
			{
				s.Draw(Main.mist, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), new Rectangle(Main.bgset[32 + type].color * 32, 0, 32, 30), new Color(1f, 1f, 1f, 0.8f), MathHelper.ToDegrees((float)(time * 5)), new Vector2(16f, 15f), 1f, SpriteEffects.None, 0f);
			}
			s.Draw(Main.barrages, new Vector2(170f + x + Time.quake.X, 22f + y + Time.quake.Y), Main.bgset[32 + type].rect, new Color(1f, 1f, 1f, alpha / 100f), MathHelper.ToRadians(head) - 1.57079637f, new Vector2((float)(Main.bgset[32 + type].rect.Width / 2), 0f), new Vector2(wscale, rlongs / (float)Main.bgset[32 + type].rect.Height), SpriteEffects.None, 0f);
		}
	}

	private boolean Judge(float bx, float by, float x, float y, float bpx, float bpy, float px, float py, float ws, float hs, float pdr, float dr)
	{
		pdr += 1f;
		bpx = x + bpx - bx;
		bpy = y + bpy - by;
		float num = px - bpx;
		float num2 = py - bpy;
		float num4;
		float num5;
		if (num != 0f)
		{
			float num3 = num2 / num;
			if (num3 != 0f)
			{
				num4 = (y - bpy + 1f / num3 * x + num3 * bpx) / (num3 + 1f / num3);
				num5 = bpy + num3 * (num4 - bpx);
			}
			else
			{
				num4 = x;
				num5 = py;
			}
			if (Math.Abs(Math.Abs(px - num4) + Math.Abs(bpx - num4) - Math.Abs(px - bpx)) > 0f)
			{
				num4 = px;
				num5 = py;
			}
		}
		else if (num2 != 0f)
		{
			num4 = px;
			num5 = y;
			if (Math.Abs(Math.Abs(py - num5) + Math.Abs(bpy - num5) - Math.Abs(py - bpy)) > 0f)
			{
				num4 = px;
				num5 = py;
			}
		}
		else
		{
			num4 = px;
			num5 = py;
		}
		dr = MathHelper.ToRadians(dr);
		double num6;
		if (num4 - x == 0f)
		{
			num6 = ((!(num5 - y > 0f)) ? (-1.5707963705062866) : 1.5707963705062866);
		}
		else
		{
			num6 = Math.Atan((double)((num5 - y) / (num4 - x)));
			if (num4 - x < 0f)
			{
				num6 += 3.1415927410125732;
			}
		}
		float num7 = (float)Math.Sqrt((double)((x - num4) * (x - num4) + (y - num5) * (y - num5)));
		num4 = x + num7 * (float)Math.Cos(num6 - (double)dr);
		num5 = y + num7 * (float)Math.Sin(num6 - (double)dr);
		x = (x - num4) * (x - num4);
		y = (y - num5) * (y - num5);
		float num8 = pdr * ws * (pdr * ws);
		float num9 = pdr * hs * (pdr * hs);
		if (x / num9 + y / num8 <= 1f)
		{
			return true;
		}
		return false;
	}*/
}
