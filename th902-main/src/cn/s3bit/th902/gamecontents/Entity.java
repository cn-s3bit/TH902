package cn.s3bit.th902.gamecontents;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.components.Component;

public class Entity {
	public static HashSet<Entity> instances = new HashSet<>();
	public static LinkedBlockingQueue<Runnable> postUpdate = new LinkedBlockingQueue<>();
	
	private HashMap<Class<?>, Component> mComponents;
	private LinkedBlockingQueue<Class<?>> toDel;
	private Entity() {
		
	}
	
	public static Entity Create() {
		final Entity entity = new Entity();
		entity.mComponents = new HashMap<>();
		entity.toDel = new LinkedBlockingQueue<>();
		postUpdate.add(() -> { instances.add(entity); });
		return entity;
	}
	
	public void Update() {
		Object[] entries =  mComponents.entrySet().toArray();
		for (Object obj : entries)
		{
			@SuppressWarnings("unchecked")
			Entry<Class<?>, Component> entry = (Entry<Class<?>, Component>) obj;
			entry.getValue().Update();
			if (entry.getValue().isDead()) {
				toDel.add(entry.getKey());
			}
		}
		while (!toDel.isEmpty()) {
			mComponents.remove(toDel.poll());
		}
		if (mComponents.isEmpty()) {
			this.Destroy();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T GetComponent(Class<T> type) {
		T c = (T) mComponents.get(type);
		if (c != null)
			return c;
		for (Class<?> typec : mComponents.keySet())
		{
			if (type.isAssignableFrom(typec)) {
				return (T) mComponents.get(typec);
			}
		}
		return null;
	}
	
	public void AddComponent(Component component) {
		if (component == null)
			throw new NullPointerException();
		Class<?> type = component.getClass();
		if (mComponents.containsKey(type))
			throw new IllegalArgumentException();
		mComponents.put(type, component);
		component.Initialize(this);
	}
	
	public void Destroy() {
		Component[] components = (Component[]) mComponents.values().toArray(new Component[mComponents.values().size()]);
		for (Component component : components)
			component.Kill();
		mComponents.clear();
		instances.remove(this);
	}
	
	public static void Reset() {
		Entity[] entities = (Entity[]) instances.toArray(new Entity[instances.size()]);
		for (int i = 0; i < entities.length; i++) {
			entities[i].Destroy();
		}
	}
	
	public static void UpdateAll() {
		GameMain.instance.activeStage.act();
		Entity[] entities = (Entity[]) instances.toArray(new Entity[instances.size()]);
		for (int i = 0; i < entities.length; i++) {
			entities[i].Update();
		}
		while (!postUpdate.isEmpty()) {
			postUpdate.poll().run();
		}
	}
}
