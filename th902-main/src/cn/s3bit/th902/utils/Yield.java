package cn.s3bit.th902.utils;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Yield {
	public ArrayList<Integer> times;
	public ArrayList<Callable<?>> callables;
	public int current = 0;
	public int repeatCounter = 0;
	
	/**
	 * Repetition default to 1.
	 * @see #append(Runnable, int)
	 */
	public void append(final Runnable runnable) {
		append(runnable, 1);
	}
	
	/**
	 * Repetition default to 1.
	 * @see #append(Callable, int)
	 */
	public void append(final Callable<?> callable) {
		append(callable, 1);
	}
	
	/**
	 * @param runnable
	 * Code to run when yield. The yield will return null.
	 * @param repetition
	 * The repetition (Times Executed).
	 */
	public void append(final Runnable runnable, int repetition) {
		append(() -> {
			runnable.run();
			return null;
		}, repetition);
	}
	/**
	 * @param callable
	 * Code to run and return when yield.
	 * @param repetition
	 * The repetition (Times Executed).
	 */
	public void append(final Callable<?> callable, int repetition) {
		if (repetition <= 0) {
			throw new IllegalArgumentException("Execution Times (repitition) should be positive!");
		}
		callables.add(callable);
		times.add(repetition);
	}
	/**
	 * Calls the current {@link Callable}.
	 * @return
	 * The object returned or null if the current one in the queue is a {@link Runnable}.
	 */
	public Object yield() {
		if (current >= callables.size()) {
			throw new IllegalStateException("Yield Finished!");
		}
		Object ret;
		try {
			ret = callables.get(current).call();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		repeatCounter++;
		if (repeatCounter >= times.get(current)) {
			repeatCounter = 0;
			current++;
		}
		return ret;
	}
	
	/**
	 * Returns true if yield has already come to an end.
	 */
	public boolean isFinished() {
		return current >= callables.size();
	}
}
