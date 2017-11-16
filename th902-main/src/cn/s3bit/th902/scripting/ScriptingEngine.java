package cn.s3bit.th902.scripting;

import java.util.concurrent.LinkedBlockingQueue;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8ScriptException;

public class ScriptingEngine {
	public static Thread scriptThread;
	public static LinkedBlockingQueue<Runnable> messages;
	public static V8 v8;
	static
	{
		messages = new LinkedBlockingQueue<>();
		scriptThread = new Thread(() -> {
			boolean isInterrupted = false;
			while (!isInterrupted) {
				try {
					messages.take().run();
				} catch (InterruptedException e) {
					isInterrupted = true;
				} catch (V8ScriptException e) {
					System.err.println(e.toString());
				}
			}
		}, "TH902-Script");
		messages.add(() -> {
			v8 = V8.createV8Runtime();
			ScriptInterfaces.load();
			//System.out.println(v8.executeStringScript("'Hello, World!'"));
		});
		scriptThread.start();
	}
}
