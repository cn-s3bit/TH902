package cn.s3bit.th902.scripting;

import java.lang.reflect.Method;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.eclipsesource.v8.V8Object;

/**
 * @author Obsidianss
 * Interfaces between JavaScript and Java. Singleton Class.
 */
public class ScriptInterfaces {
	private static ScriptInterfaces mInstance = null;
	public synchronized static ScriptInterfaces getInstance() {
		if (mInstance == null)
			mInstance = new ScriptInterfaces();
		return mInstance;
	}
	
	public final V8Object scriptObj;
	public static void load() {
		ScriptingEngine.v8.add("bridge", getInstance().scriptObj);
	}
	
	private ScriptInterfaces() {
		scriptObj = new V8Object(ScriptingEngine.v8);
		try {
			Class<?> thiz = ClassReflection.forName(this.getClass().getName());
			Method[] methods = thiz.getMethods();
			for (Method method : methods) {
				scriptObj.registerJavaMethod(getInstance(), method.getName(), method.getName(), method.getParameterTypes());
			}
		} catch (ReflectionException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public Object getStaticJavaObject(String jClassFullName, String field) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ReflectionException {
		return ClassReflection.forName(jClassFullName).getField(field).get(null);
	}
}
