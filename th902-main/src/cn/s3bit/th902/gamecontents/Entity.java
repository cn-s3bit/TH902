package cn.s3bit.th902.gamecontents;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingQueue;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.components.Component;

public class Entity {
	public static TreeSet<Entity> instances = new TreeSet<>(new Comparator<Entity>() {

		@Override
		public int compare(Entity o1, Entity o2) {
			if (o1 == o2)
				return 0;
			return Integer.compare(o1.updateOrder, o2.updateOrder) | 1;
		}
	});
	public static LinkedBlockingQueue<Runnable> postUpdate = new LinkedBlockingQueue<>();
	
	private HashMap<Class<?>, Component> mComponents;
	public int updateOrder;
	private LinkedBlockingQueue<Class<?>> toDel;
	private Entity() {
		
	}

	public static Entity Create() {
		return Create(0);
	}
	
	public static Entity Create(int updateOrder) {
		final Entity entity = new Entity();
		entity.mComponents = new HashMap<>();
		entity.toDel = new LinkedBlockingQueue<>();
		entity.updateOrder = updateOrder;
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
	public <T> T GetComponent(Class<T> type) {
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
			if (!component.isDead()) {
				component.Kill();
			}
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
