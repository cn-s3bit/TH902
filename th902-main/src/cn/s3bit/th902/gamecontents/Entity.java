package cn.s3bit.th902.gamecontents;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;

import cn.s3bit.th902.gamecontents.components.Component;

public class Entity {
	public static HashSet<Entity> instances = new HashSet<>();
	public static LinkedBlockingQueue<Runnable> postUpdate = new LinkedBlockingQueue<>();
	
	private HashMap<Class<?>, Component> mComponents;
	private LinkedBlockingQueue<Class<?>> toDel;
	private Entity() {
		
	}
	
	public static Entity Create() {
		Entity entity = new Entity();
		entity.mComponents = new HashMap<>();
		entity.toDel = new LinkedBlockingQueue<>();
		postUpdate.add(() -> { instances.add(entity); });
		return entity;
	}
	
	public void Update() {
		for (Entry<Class<?>, Component> entry : mComponents.entrySet())
		{
			entry.getValue().Update();
			if (entry.getValue().isDead()) {
				toDel.add(entry.getKey());
			}
		}
		while (!toDel.isEmpty()) {
			mComponents.remove(toDel.poll());
		}
		if (mComponents.isEmpty()) {
			instances.remove(this);
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
		for (Component component : mComponents.values())
			component.Kill();
		mComponents.clear();
		postUpdate.add(() -> { instances.remove(this); });
	}
	
	public static void Reset() {
		Entity[] entities = (Entity[]) instances.toArray(new Entity[instances.size()]);
		for (int i = 0; i < entities.length; i++) {
			entities[i].Destroy();
		}
	}
	
	public static void UpdateAll() {
		for (Iterator<Entity> iterator = instances.iterator(); iterator.hasNext();) {
			Entity entity = iterator.next();
			entity.Update();
		}
		while (!postUpdate.isEmpty()) {
			postUpdate.poll().run();
		}
	}
}
