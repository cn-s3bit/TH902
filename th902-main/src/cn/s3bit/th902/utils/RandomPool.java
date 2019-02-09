package cn.s3bit.th902.utils;

import java.util.Random;

import com.badlogic.gdx.utils.IntMap;

public class RandomPool {
	
	public static class RandomXS128Wrapper {
		public Random random = new Random();
		public long timeStamp = 0;
		public long timeSeed = 0;

		/** Returns a random number between 0 (inclusive) and the specified value (inclusive). */
		public int random (int range) {
			return random.nextInt(range + 1);
		}

		/** Returns a random number between start (inclusive) and end (inclusive). */
		public int random (int start, int end) {
			return start + random.nextInt(end - start + 1);
		}

		/** Returns a random number between 0 (inclusive) and the specified value (inclusive). */
		public long random (long range) {
			return (long)(random.nextDouble() * range);
		}

		/** Returns a random number between start (inclusive) and end (inclusive). */
		public long random (long start, long end) {
			return start + (long)(random.nextDouble() * (end - start));
		}

		/** Returns a random boolean value. */
		public boolean randomBoolean () {
			return random.nextBoolean();
		}

		/** Returns true if a random integer between 0 and 100 is less than the specified value. */
		public boolean randomBoolean (int chance) {
			return random(0, 99) < chance;
		}

		/** Returns random number between 0.0 (inclusive) and 1.0 (exclusive). */
		public float random () {
			return random.nextFloat();
		}

		/** Returns a random number between 0 (inclusive) and the specified value (exclusive). */
		public float random (float range) {
			return random.nextFloat() * range;
		}

		/** Returns a random number between start (inclusive) and end (exclusive). */
		public float random (float start, float end) {
			return start + random.nextFloat() * (end - start);
		}

		/** Returns -1 or 1, randomly. */
		public int randomSign () {
			return 1 | (random.nextInt() >> 31);
		}

		/** Returns a triangularly distributed random number between -1.0 (exclusive) and 1.0 (exclusive), where values around zero are
		 * more likely.
		 * <p>
		 * This is an optimized version of {@link #randomTriangular(float, float, float) randomTriangular(-1, 1, 0)} */
		public float randomTriangular () {
			return random.nextFloat() - random.nextFloat();
		}

		/** Returns a triangularly distributed random number between {@code -max} (exclusive) and {@code max} (exclusive), where values
		 * around zero are more likely.
		 * <p>
		 * This is an optimized version of {@link #randomTriangular(float, float, float) randomTriangular(-max, max, 0)}
		 * @param max the upper limit */
		public float randomTriangular (float max) {
			return (random.nextFloat() - random.nextFloat()) * max;
		}

		/** Returns a triangularly distributed random number between {@code min} (inclusive) and {@code max} (exclusive), where the
		 * {@code mode} argument defaults to the midpoint between the bounds, giving a symmetric distribution.
		 * <p>
		 * This method is equivalent of {@link #randomTriangular(float, float, float) randomTriangular(min, max, (min + max) * .5f)}
		 * @param min the lower limit
		 * @param max the upper limit */
		public float randomTriangular (float min, float max) {
			return randomTriangular(min, max, (min + max) * 0.5f);
		}

		/** Returns a triangularly distributed random number between {@code min} (inclusive) and {@code max} (exclusive), where values
		 * around {@code mode} are more likely.
		 * @param min the lower limit
		 * @param max the upper limit
		 * @param mode the point around which the values are more likely */
		public float randomTriangular (float min, float max, float mode) {
			float u = random.nextFloat();
			float d = max - min;
			if (u <= (mode - min) / d) return min + (float)Math.sqrt(u * d * (mode - min));
			return max - (float)Math.sqrt((1 - u) * d * (max - mode));
		}
	}
	
	protected static IntMap<RandomXS128Wrapper> pool = new IntMap<>();
	
	public static RandomXS128Wrapper get(int index) {
		if (!pool.containsKey(index))
			pool.put(index, new RandomXS128Wrapper());
		return pool.get(index);
	}
}
