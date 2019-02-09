package cn.s3bit.th902.danmaku.crazystorm;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import cn.s3bit.mbgparser.Layer;
import cn.s3bit.th902.GameHelper;
import cn.s3bit.th902.danmaku.mbg.MBGScene;
import cn.s3bit.th902.gamecontents.JudgingSystem;
import cn.s3bit.th902.utils.RandomPool;

public class Batch
{
	public MBGScene mbgScene;

	private static int record;

	private int clcount;

	private int clwait;

	public boolean Selecting;

	public int Searched;

	public boolean NeedDelete;

	public int id;

	public int parentid;

	public int parentcolor;

	public boolean Binding;

	public int bindid = -1;

	public boolean Bindwithspeedd;

	public boolean Deepbind;

	public boolean Deepbinded;

	public float x;

	public float y;

	public int time;

	public int begin;

	public int life;

	public float fx;

	public float fy;

	public float r;

	public float rdirection;

	public Vector2 rdirections;

	public int tiao;

	public int t;

	public float fdirection;

	public float bfdirection;

	public Vector2 fdirections;

	public int range;

	public float speed;

	public float speedd;

	public float speedx;

	public float speedy;

	public Vector2 speedds;

	public float aspeed;

	public float aspeedx;

	public float aspeedy;

	public float aspeedd;

	public Vector2 aspeedds;

	public int sonlife;

	public float type;

	public float wscale;

	public float hscale;

	public float colorR;

	public float colorG;

	public float colorB;

	public float alpha;

	public float head;

	public Vector2 heads;

	public boolean Withspeedd;

	public float sonspeed;

	public float sonspeedd;

	public Vector2 sonspeedds;

	public float sonaspeed;

	public float sonaspeedd;

	public float bsonaspeedd;

	public Vector2 sonaspeedds;

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

	public Batch rand;

	public List<Event> Parentevents;

	public List<Execution> Eventsexe;

	public List<Event> Sonevents;

	public Batch copys;

	private float[] conditions = new float[13];

	private float[] results = new float[33];

	public Batch(MBGScene scene)
	{
		mbgScene = scene;
	}
	
	public Layer getLayerById(int id)
	{
		switch (id) {
		case 0:
			return mbgScene.mbgData.layer1;
		case 1:
			return mbgScene.mbgData.layer2;
		case 2:
			return mbgScene.mbgData.layer3;
		case 3:
			return mbgScene.mbgData.layer4;
		}
		throw new IllegalArgumentException("" + id);
	}

	public Batch(MBGScene scene, float xs, float ys, int pc)
	{
		rand = new Batch(scene);
		Parentevents = new ArrayList<Event>();
		Sonevents = new ArrayList<Event>();
		Eventsexe = new ArrayList<Execution>();
		x = xs;
		y = ys;
		parentcolor = pc;
		begin = getLayerById(parentid).life.begin;
		life = getLayerById(parentid).life.lifeTime + 1;
		fx = -99998f;
		fy = -99998f;
		r = 0f;
		rdirection = 0f;
		tiao = 1;
		t = 5;
		fdirection = 0f;
		range = 360;
		speed = 0f;
		speedd = 0f;
		aspeed = 0f;
		aspeedd = 0f;
		sonlife = 200;
		type = 1f;
		wscale = 1f;
		hscale = 1f;
		colorR = 255f;
		colorG = 255f;
		colorB = 255f;
		alpha = 100f;
		head = 0f;
		Withspeedd = true;
		sonspeed = 5f;
		sonspeedd = 0f;
		sonaspeed = 0f;
		sonaspeedd = 0f;
		xscale = 1f;
		yscale = 1f;
		Mist = true;
		Dispel = true;
		Blend = false;
		Afterimage = false;
		Outdispel = true;
		Invincible = false;
		Cover = true;
		Rebound = true;
		Force = true;
	}

	public void Update()
	{
		if (clcount == 1)
		{
			clwait++;
			if (clwait > 15)
			{
				clwait = 0;
				clcount = 0;
			}
		}
		if (!Time.Playing)
		{
			aspeedx = aspeed * MathUtils.cosDeg(aspeedd);
			aspeedy = aspeed * MathUtils.sinDeg(aspeedd);
			speedx = speed * MathUtils.cosDeg(speedd);
			speedy = speed * MathUtils.sinDeg(speedd);
			// begin = (int)MathUtils.clamp((float)begin, (float)Layer.LayerArray[parentid].begin, (float)(1 + Layer.LayerArray[parentid].end - Layer.LayerArray[parentid].begin));
			// life = (int)MathUtils.clamp((float)life, 1f, (float)(Layer.LayerArray[parentid].end - Layer.LayerArray[parentid].begin + 2 - begin));
		}
		if (bindid == id)
		{
			bindid = -1;
			Deepbind = false;
			Bindwithspeedd = false;
		}
		if (bindid != -1 && bindid >= getLayerById(parentid).BulletEmitters.size())
		{
			bindid = -1;
			Deepbind = false;
			Bindwithspeedd = false;
		}
		if (Time.Playing)
		{
			if (Deepbinded)
			{
				time++;
			}
			if ((!Deepbinded & (Time.now >= begin) & (Time.now <= begin + life - 1)) || (Deepbinded & (time >= begin) & (time <= begin + life - 1)) || Deepbind)
			{
				if (!Deepbind & !Deepbinded)
				{
					time = Time.now - begin + 1;
				}
				if (!Deepbind)
				{
					speedx += aspeedx;
					speedy += aspeedy;
					x += speedx;
					y += speedy;
					fx += speedx;
					fy += speedy;
					if (Deepbinded)
					{
						conditions[0] = (float)(time - begin + 1);
					}
					else
					{
						conditions[0] = (float)time;
					}
					conditions[1] = fx;
					conditions[2] = fy;
					conditions[3] = r;
					conditions[4] = rdirection;
					conditions[5] = (float)tiao;
					conditions[6] = (float)t;
					conditions[7] = fdirection;
					conditions[8] = (float)range;
					conditions[9] = wscale;
					conditions[10] = hscale;
					conditions[11] = alpha;
					conditions[12] = head;
					results[0] = fx;
					results[1] = fy;
					results[2] = r;
					results[3] = rdirection;
					results[4] = (float)tiao;
					results[5] = (float)t;
					results[6] = fdirection;
					results[7] = (float)range;
					results[8] = speed;
					results[9] = speedd;
					results[10] = aspeed;
					results[11] = aspeedd;
					results[12] = (float)life;
					results[13] = type;
					results[14] = wscale;
					results[15] = hscale;
					results[16] = colorR;
					results[17] = colorG;
					results[18] = colorB;
					results[19] = alpha;
					results[20] = head;
					results[21] = sonspeed;
					results[22] = sonspeedd;
					results[23] = sonaspeed;
					results[24] = sonaspeedd;
					results[25] = xscale;
					results[26] = yscale;
					results[27] = 0f;
					results[28] = 0f;
					results[29] = 0f;
					results[30] = 0f;
					results[31] = 0f;
					results[32] = 0f;
					for (Event parentevent2 : Parentevents)
					{
						if (parentevent2.t <= 0)
						{
							parentevent2.t = 1;
						}
						if (time % parentevent2.t == 0)
						{
							parentevent2.loop++;
						}
						for (EventRead result : parentevent2.results)
						{
							if (result.special == 3)
							{
								if (result.changevalue == 0)
								{
									result.res = x - 4f;
								}
								if (result.changevalue == 1)
								{
									result.res = y + 16f;
								}
								if (result.changevalue == 6)
								{
									result.res = GameHelper.twoPointAngle(x - 4f, y + 16f, fx, fy);
								}
								if (result.changevalue == 24)
								{
									result.res = GameHelper.twoPointAngle(x - 4f, y + 16f, fx, fy);
								}
							}
							if (result.special == 4)
							{
								if (result.changevalue == 0)
								{
									result.res = JudgingSystem.playerJudge.x;
								}
								if (result.changevalue == 1)
								{
									result.res = JudgingSystem.playerJudge.y;
								}
								if (result.changevalue == 3)
								{
									result.res = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, fx, fy);
								}
								if (result.changevalue == 6)
								{
									result.res = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, fx, fy);
								}
								if (result.changevalue == 9)
								{
									result.res = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, fx, fy);
								}
								if (result.changevalue == 11)
								{
									result.res = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, fx, fy);
								}
								if (result.changevalue == 24)
								{
									result.res = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, fx, fy);
								}
							}
							if (result.opreator == ">")
							{
								if (result.opreator2 == ">")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution = new Execution();
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
												execution.parentid = parentid;
												execution.id = id;
												execution.change = result.change;
												execution.changetype = result.changetype;
												execution.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution.value = result.res;
												}
												execution.region = Float.toString(results[result.changename]);
												execution.time = result.times;
												execution.ctime = execution.time;
												Eventsexe.add(execution);
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution2 = new Execution();
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
											execution2.parentid = parentid;
											execution2.id = id;
											execution2.change = result.change;
											execution2.changetype = result.changetype;
											execution2.changevalue = result.changevalue;
											if (result.rand != 0f)
											{
												execution2.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
											}
											else
											{
												execution2.value = result.res;
											}
											execution2.region = Float.toString(results[result.changename]);
											execution2.time = result.times;
											execution2.ctime = execution2.time;
											Eventsexe.add(execution2);
										}
									}
								}
								else if (result.opreator2 == "=")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution3 = new Execution();
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
												execution3.parentid = parentid;
												execution3.id = id;
												execution3.change = result.change;
												execution3.changetype = result.changetype;
												execution3.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution3.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution3.value = result.res;
												}
												execution3.region = Float.toString(results[result.changename]);
												execution3.time = result.times;
												execution3.ctime = execution3.time;
												Eventsexe.add(execution3);
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution4 = new Execution();
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
											execution4.parentid = parentid;
											execution4.id = id;
											execution4.change = result.change;
											execution4.changetype = result.changetype;
											execution4.changevalue = result.changevalue;
											if (result.rand != 0f)
											{
												execution4.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
											}
											else
											{
												execution4.value = result.res;
											}
											execution4.region = Float.toString(results[result.changename]);
											execution4.time = result.times;
											execution4.ctime = execution4.time;
											Eventsexe.add(execution4);
										}
									}
								}
								else if (result.opreator2 == "<")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] > Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution5 = new Execution();
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
												execution5.parentid = parentid;
												execution5.id = id;
												execution5.change = result.change;
												execution5.changetype = result.changetype;
												execution5.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution5.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution5.value = result.res;
												}
												execution5.region = Float.toString(results[result.changename]);
												execution5.time = result.times;
												execution5.ctime = execution5.time;
												Eventsexe.add(execution5);
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution6 = new Execution();
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
											execution6.parentid = parentid;
											execution6.id = id;
											execution6.change = result.change;
											execution6.changetype = result.changetype;
											execution6.changevalue = result.changevalue;
											if (result.rand != 0f)
											{
												execution6.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
											}
											else
											{
												execution6.value = result.res;
											}
											execution6.region = Float.toString(results[result.changename]);
											execution6.time = result.times;
											execution6.ctime = execution6.time;
											Eventsexe.add(execution6);
										}
									}
								}
								else if (conditions[result.contype] > Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime))
								{
									if (result.special == 1)
									{
										// Recover();
									}
									else if (result.special == 2)
									{
										Shoot();
									}
									else
									{
										Execution execution7 = new Execution();
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
										execution7.parentid = parentid;
										execution7.id = id;
										execution7.change = result.change;
										execution7.changetype = result.changetype;
										execution7.changevalue = result.changevalue;
										if (result.rand != 0f)
										{
											execution7.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										}
										else
										{
											execution7.value = result.res;
										}
										execution7.region = Float.toString(results[result.changename]);
										execution7.time = result.times;
										execution7.ctime = execution7.time;
										Eventsexe.add(execution7);
									}
								}
							}
							if (result.opreator == "=")
							{
								if (result.opreator2 == ">")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution8 = new Execution();
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
												execution8.parentid = parentid;
												execution8.id = id;
												execution8.change = result.change;
												execution8.changetype = result.changetype;
												execution8.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution8.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution8.value = result.res;
												}
												execution8.region = Float.toString(results[result.changename]);
												execution8.time = result.times;
												execution8.ctime = execution8.time;
												Eventsexe.add(execution8);
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution9 = new Execution();
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
											execution9.parentid = parentid;
											execution9.id = id;
											execution9.change = result.change;
											execution9.changetype = result.changetype;
											execution9.changevalue = result.changevalue;
											if (result.rand != 0f)
											{
												execution9.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
											}
											else
											{
												execution9.value = result.res;
											}
											execution9.region = Float.toString(results[result.changename]);
											execution9.time = result.times;
											execution9.ctime = execution9.time;
											Eventsexe.add(execution9);
										}
									}
								}
								else if (result.opreator2 == "=")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution10 = new Execution();
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
												execution10.parentid = parentid;
												execution10.id = id;
												execution10.change = result.change;
												execution10.changetype = result.changetype;
												execution10.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution10.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution10.value = result.res;
												}
												execution10.region = Float.toString(results[result.changename]);
												execution10.time = result.times;
												execution10.ctime = execution10.time;
												Eventsexe.add(execution10);
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution11 = new Execution();
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
											execution11.parentid = parentid;
											execution11.id = id;
											execution11.change = result.change;
											execution11.changetype = result.changetype;
											execution11.changevalue = result.changevalue;
											if (result.rand != 0f)
											{
												execution11.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
											}
											else
											{
												execution11.value = result.res;
											}
											execution11.region = Float.toString(results[result.changename]);
											execution11.time = result.times;
											execution11.ctime = execution11.time;
											Eventsexe.add(execution11);
										}
									}
								}
								else if (result.opreator2 == "<")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] == Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution12 = new Execution();
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
												execution12.parentid = parentid;
												execution12.id = id;
												execution12.change = result.change;
												execution12.changetype = result.changetype;
												execution12.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution12.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution12.value = result.res;
												}
												execution12.region = Float.toString(results[result.changename]);
												execution12.time = result.times;
												execution12.ctime = execution12.time;
												Eventsexe.add(execution12);
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution13 = new Execution();
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
											execution13.parentid = parentid;
											execution13.id = id;
											execution13.change = result.change;
											execution13.changetype = result.changetype;
											execution13.changevalue = result.changevalue;
											if (result.rand != 0f)
											{
												execution13.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
											}
											else
											{
												execution13.value = result.res;
											}
											execution13.region = Float.toString(results[result.changename]);
											execution13.time = result.times;
											execution13.ctime = execution13.time;
											Eventsexe.add(execution13);
										}
									}
								}
								else if (conditions[result.contype] == Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime))
								{
									if (result.special == 1)
									{
										// Recover();
									}
									else if (result.special == 2)
									{
										Shoot();
									}
									else
									{
										Execution execution14 = new Execution();
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
										execution14.parentid = parentid;
										execution14.id = id;
										execution14.change = result.change;
										execution14.changetype = result.changetype;
										execution14.changevalue = result.changevalue;
										if (result.rand != 0f)
										{
											execution14.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
										}
										else
										{
											execution14.value = result.res;
										}
										execution14.region = Float.toString(results[result.changename]);
										execution14.time = result.times;
										execution14.ctime = execution14.time;
										Eventsexe.add(execution14);
									}
								}
							}
							if (result.opreator == "<")
							{
								if (result.opreator2 == ">")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution15 = new Execution();
												if (!result.noloop)
												{
													if (result.time > 0)
													{
														result.time--;
														if (result.time == 0)
														{
															result.noloop = true;
														}
													}
													execution15.parentid = parentid;
													execution15.id = id;
													execution15.change = result.change;
													execution15.changetype = result.changetype;
													execution15.changevalue = result.changevalue;
													if (result.rand != 0f)
													{
														execution15.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
													}
													else
													{
														execution15.value = result.res;
													}
													execution15.region = Float.toString(results[result.changename]);
													execution15.time = result.times;
													execution15.ctime = execution15.time;
													Eventsexe.add(execution15);
												}
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] > Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution16 = new Execution();
											if (!result.noloop)
											{
												if (result.time > 0)
												{
													result.time--;
													if (result.time == 0)
													{
														result.noloop = true;
													}
												}
												execution16.parentid = parentid;
												execution16.id = id;
												execution16.change = result.change;
												execution16.changetype = result.changetype;
												execution16.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution16.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution16.value = result.res;
												}
												execution16.region = Float.toString(results[result.changename]);
												execution16.time = result.times;
												execution16.ctime = execution16.time;
												Eventsexe.add(execution16);
											}
										}
									}
								}
								else if (result.opreator2 == "=")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// // Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution17 = new Execution();
												if (!result.noloop)
												{
													if (result.time > 0)
													{
														result.time--;
														if (result.time == 0)
														{
															result.noloop = true;
														}
													}
													execution17.parentid = parentid;
													execution17.id = id;
													execution17.change = result.change;
													execution17.changetype = result.changetype;
													execution17.changevalue = result.changevalue;
													if (result.rand != 0f)
													{
														execution17.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
													}
													else
													{
														execution17.value = result.res;
													}
													execution17.region = Float.toString(results[result.changename]);
													execution17.time = result.times;
													execution17.ctime = execution17.time;
													Eventsexe.add(execution17);
												}
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] == Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution18 = new Execution();
											if (!result.noloop)
											{
												if (result.time > 0)
												{
													result.time--;
													if (result.time == 0)
													{
														result.noloop = true;
													}
												}
												execution18.parentid = parentid;
												execution18.id = id;
												execution18.change = result.change;
												execution18.changetype = result.changetype;
												execution18.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution18.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution18.value = result.res;
												}
												execution18.region = Float.toString(results[result.changename]);
												execution18.time = result.times;
												execution18.ctime = execution18.time;
												Eventsexe.add(execution18);
											}
										}
									}
								}
								else if (result.opreator2 == "<")
								{
									if (result.collector == "且")
									{
										if ((conditions[result.contype] < Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime)) & (conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
										{
											if (result.special == 1)
											{
												// Recover();
											}
											else if (result.special == 2)
											{
												Shoot();
											}
											else
											{
												Execution execution19 = new Execution();
												if (!result.noloop)
												{
													if (result.time > 0)
													{
														result.time--;
														if (result.time == 0)
														{
															result.noloop = true;
														}
													}
													execution19.parentid = parentid;
													execution19.id = id;
													execution19.change = result.change;
													execution19.changetype = result.changetype;
													execution19.changevalue = result.changevalue;
													if (result.rand != 0f)
													{
														execution19.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
													}
													else
													{
														execution19.value = result.res;
													}
													execution19.region = Float.toString(results[result.changename]);
													execution19.time = result.times;
													execution19.ctime = execution19.time;
													Eventsexe.add(execution19);
												}
											}
										}
									}
									else if (result.collector == "或" && (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime) || conditions[result.contype2] < Float.parseFloat(result.condition2) + (float)(parentevent2.loop * parentevent2.addtime)))
									{
										if (result.special == 1)
										{
											// Recover();
										}
										else if (result.special == 2)
										{
											Shoot();
										}
										else
										{
											Execution execution20 = new Execution();
											if (!result.noloop)
											{
												if (result.time > 0)
												{
													result.time--;
													if (result.time == 0)
													{
														result.noloop = true;
													}
												}
												execution20.parentid = parentid;
												execution20.id = id;
												execution20.change = result.change;
												execution20.changetype = result.changetype;
												execution20.changevalue = result.changevalue;
												if (result.rand != 0f)
												{
													execution20.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
												}
												else
												{
													execution20.value = result.res;
												}
												execution20.region = Float.toString(results[result.changename]);
												execution20.time = result.times;
												execution20.ctime = execution20.time;
												Eventsexe.add(execution20);
											}
										}
									}
								}
								else if (conditions[result.contype] < Float.parseFloat(result.condition) + (float)(parentevent2.loop * parentevent2.addtime))
								{
									if (result.special == 1)
									{
										// Recover();
									}
									else if (result.special == 2)
									{
										Shoot();
									}
									else
									{
										Execution execution21 = new Execution();
										if (!result.noloop)
										{
											if (result.time > 0)
											{
												result.time--;
												if (result.time == 0)
												{
													result.noloop = true;
												}
											}
											execution21.parentid = parentid;
											execution21.id = id;
											execution21.change = result.change;
											execution21.changetype = result.changetype;
											execution21.changevalue = result.changevalue;
											if (result.rand != 0f)
											{
												execution21.value = result.res + MathUtils.lerp(0f - result.rand, result.rand, RandomPool.get(5).random());
											}
											else
											{
												execution21.value = result.res;
											}
											execution21.region = Float.toString(results[result.changename]);
											execution21.time = result.times;
											execution21.ctime = execution21.time;
											Eventsexe.add(execution21);
										}
									}
								}
							}
						}
					}
					for (int num3 = 0; num3 < Eventsexe.size(); num3++)
					{
						if (!Eventsexe.get(num3).NeedDelete)
						{
							Eventsexe.get(num3).Update(this);
						}
						else
						{
							Eventsexe.remove(num3);
							num3--;
						}
					}
				}
				if (t > 0)
				{
					if (Deepbind)
					{
						Shoot();
					}
					else if (time % t + (int)MathUtils.lerp((float)(-rand.t), (float)rand.t, RandomPool.get(5).random()) == 0)
					{
						Shoot();
					}
				}
			}
		}
	}

	private void Shoot()
	{
		int i = tiao + (int)MathUtils.lerp((float)(-rand.tiao), (float)rand.tiao, RandomPool.get(5).random());
		float item = (float)(int)MathUtils.lerp(0f - rand.fx, rand.fx, RandomPool.get(5).random());
		float num = (float)(int)MathUtils.lerp(0f - rand.fy, rand.fy, RandomPool.get(5).random());
		int num2 = (int)MathUtils.lerp(0f - rand.r, rand.r, RandomPool.get(5).random());
		float num3 = MathUtils.lerp(0f - rand.rdirection, rand.rdirection, RandomPool.get(5).random());
		float num4 = (float)(int)MathUtils.lerp(0f - rand.head, rand.head, RandomPool.get(5).random());
		int num5 = (int)MathUtils.lerp((float)(-rand.range), (float)rand.range, RandomPool.get(5).random());
		float num6 = MathUtils.lerp(0f - rand.sonspeed, rand.sonspeed, RandomPool.get(5).random());
		float randfdirection = MathUtils.lerp(0f - rand.fdirection, rand.fdirection, RandomPool.get(5).random());
		float num7 = MathUtils.lerp(0f - rand.sonaspeed, rand.sonaspeed, RandomPool.get(5).random());
		float randsonaspeedd = MathUtils.lerp(0f - rand.sonaspeedd, rand.sonaspeedd, RandomPool.get(5).random());
		float val = MathUtils.lerp(0f - rand.wscale, rand.wscale, RandomPool.get(5).random());
		float val2 = MathUtils.lerp(0f - rand.hscale, rand.hscale, RandomPool.get(5).random());
		if (bindid == -1)
		{
			for (int j = 0; j < i; j++)
			{
				Barrage barrage = new Barrage();
				float num8 = 0f;
				if (getLayerById(parentid).BulletEmitters.get(id).半径方向.baseValue == -99999f)
				{
					rdirection = GameHelper.twoPointAngle(JudgingSystem.playerJudge.x, JudgingSystem.playerJudge.y, fx, fy);
				}
				num8 = rdirection + ((float)j - ((float)i - 1f) / 2f) * (float)(range + num5) / (float)i + num3;
				// barrage.x = fx + (r + (float)num2) * MathUtils.cosDeg(num8) + item + Center.ox - Center.x;
				// barrage.y = fy + (r + (float)num2) * MathUtils.sinDeg(num8) + num + Center.oy - Center.y;
				// TODO: Center
				barrage.life = sonlife;
				barrage.type = (int)type - 1;
				barrage.wscale = wscale + Math.max(val, val2);
				barrage.hscale = hscale + Math.max(val, val2);
				barrage.head = head + num4;
				barrage.alpha = alpha;
				barrage.R = colorR;
				barrage.G = colorG;
				barrage.B = colorB;
				barrage.speed = sonspeed + num6;
				barrage.aspeed = sonaspeed + num7;
				barrage.fx = x - 4f;
				barrage.fy = y + 16f;
				if (bfdirection >= -99997f)
				{
					barrage.fdirection = fdirection;
				}
				else
				{
					barrage.fdirection = bfdirection;
				}
				barrage.fdirections = fdirections;
				barrage.randfdirection = randfdirection;
				barrage.g = j;
				barrage.tiaos = i;
				barrage.range = range;
				barrage.randrange = num5;
				if (bsonaspeedd >= -99997f)
				{
					barrage.sonaspeedd = sonaspeedd;
				}
				else
				{
					barrage.sonaspeedd = bsonaspeedd;
				}
				barrage.sonaspeedds = sonaspeedds;
				barrage.randsonaspeedd = randsonaspeedd;
				barrage.Withspeedd = Withspeedd;
				barrage.xscale = xscale;
				barrage.yscale = yscale;
				barrage.Mist = Mist;
				barrage.Dispel = Dispel;
				barrage.Blend = Blend;
				barrage.Outdispel = Outdispel;
				barrage.Afterimage = Afterimage;
				barrage.Invincible = Invincible;
				barrage.Cover = Cover;
				barrage.Rebound = Rebound;
				barrage.Force = Force;
				for (int k = 0; k < Sonevents.size(); k++)
				{
					Event event = new Event(k);
					event.t = Sonevents.get(k).t;
					event.addtime = Sonevents.get(k).addtime;
					event.events = Sonevents.get(k).events;
					for (int l = 0; l < Sonevents.get(k).results.size(); l++)
					{
						event.results.add(Sonevents.get(k).results.get(l).Copy());
					}
					event.index = Sonevents.get(k).index;
					barrage.Events.add(event);
				}
				barrage.parentid = id;
				// Layer.LayerArray[parentid].Barrages.Add(barrage);
				// TODO: Emit Code
			}
		}
		else
		{
			/*for (int m = 0; m < Layer.LayerArray[parentid].Barrages.Count; m++)
			{
				if (!Layer.LayerArray[parentid].Barrages[m].IsLase & (Layer.LayerArray[parentid].Barrages[m].parentid == bindid) & (Layer.LayerArray[parentid].Barrages[m].time > 15 || !Layer.LayerArray[parentid].Barrages[m].Mist) & !Layer.LayerArray[parentid].Barrages[m].NeedDelete)
				{
					if (Deepbind)
					{
						if (Layer.LayerArray[parentid].Barrages[m].batch != null)
						{
							Layer.LayerArray[parentid].Barrages[m].batch.x = Layer.LayerArray[parentid].Barrages[m].x;
							Layer.LayerArray[parentid].Barrages[m].batch.y = Layer.LayerArray[parentid].Barrages[m].y;
							Layer.LayerArray[parentid].Barrages[m].batch.fx = Layer.LayerArray[parentid].Barrages[m].x;
							Layer.LayerArray[parentid].Barrages[m].batch.fy = Layer.LayerArray[parentid].Barrages[m].y;
							Layer.LayerArray[parentid].Barrages[m].batch.Update();
						}
						else
						{
							Layer.LayerArray[parentid].Barrages[m].batch = BindClone();
							Layer.LayerArray[parentid].Barrages[m].batch.Deepbind = false;
							Layer.LayerArray[parentid].Barrages[m].batch.Deepbinded = true;
							Layer.LayerArray[parentid].Barrages[m].batch.bindid = -1;
							Layer.LayerArray[parentid].Barrages[m].batch.time = 0;
							if (Bindwithspeedd)
							{
								Layer.LayerArray[parentid].Barrages[m].batch.fdirection += Layer.LayerArray[parentid].Barrages[m].fdirection;
							}
							Layer.LayerArray[parentid].Barrages[m].batch.Bindwithspeedd = false;
						}
					}
					else
					{
						for (int n = 0; n < i; n++)
						{
							Barrage barrage2 = new Barrage();
							float num9 = 0f;
							if (Layer.LayerArray[parentid].BatchArray[id].rdirection == -99999f)
							{
								rdirection = MathHelper.ToDegrees(Main.Twopointangle(Player.position.X, Player.position.Y, Layer.LayerArray[parentid].Barrages[m].x, Layer.LayerArray[parentid].Barrages[m].y));
							}
							num9 = rdirection + ((float)n - ((float)i - 1f) / 2f) * (float)(range + num5) / (float)i + num3;
							barrage2.x = Layer.LayerArray[parentid].Barrages[m].x + (r + (float)num2) * (float)Math.Cos((double)MathHelper.ToRadians(num9)) + item;
							barrage2.y = Layer.LayerArray[parentid].Barrages[m].y + (r + (float)num2) * (float)Math.Sin((double)MathHelper.ToRadians(num9)) + num;
							barrage2.life = sonlife;
							barrage2.type = (int)type - 1;
							barrage2.wscale = wscale + Math.Max(val, val2);
							barrage2.hscale = hscale + Math.Max(val, val2);
							if (Layer.LayerArray[parentid].BatchArray[id].head == -100000f)
							{
								head = MathHelper.ToDegrees(Main.Twopointangle(heads.X, heads.Y, barrage2.x, barrage2.y));
							}
							barrage2.head = head + num4;
							barrage2.alpha = alpha;
							barrage2.R = colorR;
							barrage2.G = colorG;
							barrage2.B = colorB;
							barrage2.speed = sonspeed + num6;
							barrage2.aspeed = sonaspeed + num7;
							barrage2.fx = x - 4f;
							barrage2.fy = y + 16f;
							if (bfdirection >= -99997f)
							{
								barrage2.fdirection = fdirection;
							}
							else
							{
								barrage2.fdirection = bfdirection;
							}
							barrage2.bindspeedd = Layer.LayerArray[parentid].Barrages[m].speedd;
							barrage2.Bindwithspeedd = Bindwithspeedd;
							barrage2.fdirections = fdirections;
							barrage2.randfdirection = randfdirection;
							barrage2.g = n;
							barrage2.tiaos = i;
							barrage2.range = range;
							barrage2.randrange = num5;
							if (bsonaspeedd >= -99997f)
							{
								barrage2.sonaspeedd = sonaspeedd;
							}
							else
							{
								barrage2.sonaspeedd = bsonaspeedd;
							}
							barrage2.sonaspeedds = sonaspeedds;
							barrage2.randsonaspeedd = randsonaspeedd;
							barrage2.Withspeedd = Withspeedd;
							barrage2.xscale = xscale;
							barrage2.yscale = yscale;
							barrage2.Mist = Mist;
							barrage2.Dispel = Dispel;
							barrage2.Blend = Blend;
							barrage2.Outdispel = Outdispel;
							barrage2.Afterimage = Afterimage;
							barrage2.Invincible = Invincible;
							barrage2.Cover = Cover;
							barrage2.Rebound = Rebound;
							barrage2.Force = Force;
							for (int num10 = 0; num10 < Sonevents.Count; num10++)
							{
								Event event2 = new Event(num10);
								event2.t = Sonevents[num10].t;
								event2.addtime = Sonevents[num10].addtime;
								event2.events = Sonevents[num10].events;
								for (int num11 = 0; num11 < Sonevents[num10].results.Count; num11++)
								{
									event2.results.Add((EventRead)Sonevents[num10].results[num11].Copy());
								}
								event2.index = Sonevents[num10].index;
								barrage2.Events.Add(event2);
							}
							barrage2.parentid = id;
							Layer.LayerArray[parentid].Barrages.Add(barrage2);
						}
					}
				}
			}*/
		}
	}

	/*public void Draw(SpriteBatch s)
	{
		if (Searched != 0)
		{
			s.Draw(Main.layercolor, new Vector2(150f + x - 1f - 4f, 22f + y - 1f - 4f), new Rectangle(14 * parentcolor, 0, 14, 14), Color.White, 0f, Vector2.Zero, 3f, SpriteEffects.None, 1f);
		}
		else
		{
			s.Draw(Main.layercolor, new Vector2(150f + x - 1f, 22f + y - 1f), new Rectangle(14 * parentcolor, 0, 14, 14), Color.White, 0f, Vector2.Zero, 2.4f, SpriteEffects.None, 1f);
		}
		s.Draw(Main.item, new Vector2(150f + x + 1f, 22f + y + 1f), new Rectangle(0, 0, 30, 30), Color.White, 0f, Vector2.Zero, 1f, SpriteEffects.None, 1f);
		if (id <= 8)
		{
			Main.font.Draw(s, "0" + (id + 1).ToString(), new Vector2(150f + x + 18f, 22f + y + 21f), Color.Black);
		}
		else
		{
			Main.font.Draw(s, (id + 1).ToString(), new Vector2(150f + x + 18f, 22f + y + 21f), Color.Black);
		}
		if (Selecting)
		{
			s.Draw(Main.create, new Vector2(150f + x - 1f, 22f + y - 1f), Color.White);
		}
	}

	public Batch BindClone()
	{
		Batch batch = Copy() as Batch;
		batch.Parentevents = new List<Event>();
		foreach (Event parentevent in Parentevents)
		{
			batch.Parentevents.Add((Event)parentevent.Clone());
		}
		batch.Eventsexe = new List<Execution>();
		foreach (Execution item in Eventsexe)
		{
			batch.Eventsexe.add((Execution)item.Clone());
		}
		batch.Sonevents = new List<Event>();
		foreach (Event sonevent in Sonevents)
		{
			batch.Sonevents.Add((Event)sonevent.Clone());
		}
		return batch;
	}

	public object Clone()
	{
		MemoryStream memoryStream = new MemoryStream();
		BinaryFormatter binaryFormatter = new BinaryFormatter();
		binaryFormatter.Serialize(memoryStream, this);
		memoryStream.Seek(0L, SeekOrigin.Begin);
		object result = binaryFormatter.Deserialize(memoryStream);
		memoryStream.Close();
		return result;
	}

	public object Copy()
	{
		return MemberwiseClone();
	}

	public void Recover()
	{
		x = Layer.LayerArray[parentid].BatchArray[id].x;
		y = Layer.LayerArray[parentid].BatchArray[id].y;
		parentcolor = Layer.LayerArray[parentid].BatchArray[id].parentcolor;
		begin = Layer.LayerArray[parentid].BatchArray[id].begin;
		life = Layer.LayerArray[parentid].BatchArray[id].life;
		fx = Layer.LayerArray[parentid].BatchArray[id].fx;
		fy = Layer.LayerArray[parentid].BatchArray[id].fy;
		r = Layer.LayerArray[parentid].BatchArray[id].r;
		rdirection = Layer.LayerArray[parentid].BatchArray[id].rdirection;
		tiao = Layer.LayerArray[parentid].BatchArray[id].tiao;
		t = Layer.LayerArray[parentid].BatchArray[id].t;
		fdirection = Layer.LayerArray[parentid].BatchArray[id].fdirection;
		range = Layer.LayerArray[parentid].BatchArray[id].range;
		speed = Layer.LayerArray[parentid].BatchArray[id].speed;
		speedd = Layer.LayerArray[parentid].BatchArray[id].speedd;
		aspeed = Layer.LayerArray[parentid].BatchArray[id].aspeed;
		aspeedd = Layer.LayerArray[parentid].BatchArray[id].aspeedd;
		sonlife = Layer.LayerArray[parentid].BatchArray[id].sonlife;
		type = Layer.LayerArray[parentid].BatchArray[id].type;
		wscale = Layer.LayerArray[parentid].BatchArray[id].wscale;
		hscale = Layer.LayerArray[parentid].BatchArray[id].hscale;
		colorR = Layer.LayerArray[parentid].BatchArray[id].colorR;
		colorG = Layer.LayerArray[parentid].BatchArray[id].colorG;
		colorB = Layer.LayerArray[parentid].BatchArray[id].colorB;
		alpha = Layer.LayerArray[parentid].BatchArray[id].alpha;
		head = Layer.LayerArray[parentid].BatchArray[id].head;
		Withspeedd = Layer.LayerArray[parentid].BatchArray[id].Withspeedd;
		sonspeed = Layer.LayerArray[parentid].BatchArray[id].sonspeed;
		sonspeedd = Layer.LayerArray[parentid].BatchArray[id].sonspeedd;
		sonaspeed = Layer.LayerArray[parentid].BatchArray[id].sonaspeed;
		sonaspeedd = Layer.LayerArray[parentid].BatchArray[id].sonaspeedd;
		xscale = Layer.LayerArray[parentid].BatchArray[id].xscale;
		yscale = Layer.LayerArray[parentid].BatchArray[id].yscale;
		Mist = Layer.LayerArray[parentid].BatchArray[id].Mist;
		Dispel = Layer.LayerArray[parentid].BatchArray[id].Dispel;
		Blend = Layer.LayerArray[parentid].BatchArray[id].Blend;
		Afterimage = Layer.LayerArray[parentid].BatchArray[id].Afterimage;
		Outdispel = Layer.LayerArray[parentid].BatchArray[id].Outdispel;
		Invincible = Layer.LayerArray[parentid].BatchArray[id].Invincible;
	}

	public void PreviewUpdate()
	{
		if ((Time.now >= begin) & (Time.now <= begin + life - 1))
		{
			int now = Time.now;
			speedx += aspeedx;
			speedy += aspeedy;
			x += speedx;
			y += speedy;
			fx += speedx;
			fy += speedy;
		}
	}*/
}
