package cn.s3bit.th902.tests;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class TestGdxBuggyHashmap {
	public static class IllHashProducer {
		@Override
		public int hashCode() {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		ObjectMap<IllHashProducer, Integer> testMap = new ObjectMap<>();
		// We now have a map of base capacity 64 and a small amount of stash capacity
		IllHashProducer key1 = new IllHashProducer();
		IllHashProducer key2 = new IllHashProducer();
		IllHashProducer key3 = new IllHashProducer();
		IllHashProducer key4 = new IllHashProducer();
		IllHashProducer key5 = new IllHashProducer();
		testMap.put(key1, 10);  // No conflicts
		testMap.put(key2, 12);  // No conflicts
		testMap.put(key3, 13);  // No conflicts
		
		testMap.put(key4, 14);  // Should be in stash
		testMap.put(key5, 15);  // Should be in stash
		System.out.println(testMap.remove(key4));
		// Remove from stash, now key5 is moved to the front
		System.out.println(testMap.remove(key5));
		// Remove from stash, but keyTable[lastIndex] is still storing key5
		System.out.println(testMap.remove(key2));
		// Control group
		
		try {
			Field field = ClassReflection.getDeclaredField(ObjectMap.class, "stashSize");
			field.setAccessible(true);
			field.set(testMap, 10);
			// Use an arbitary size to see whether key5 exists in the stash
			System.out.println(testMap.get(key5, 20));
			// Should give 20, but outputs null
			// So we can conclude that the key5 still exists in the keyTable
			System.out.println(testMap.get(key2, 20));
			// Gives 20 correctly
		} catch (ReflectionException e) {
			e.printStackTrace();
		}
	}
}
