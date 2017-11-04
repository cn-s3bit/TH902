# 合作开发约定  
## 命名  
1. 所有非private变量用首字母小写，第二个以及以后单词的首字母大写。  
Example:  
```java
public int positionIndex;
```
2. 所有private变量用m开头，然后首字母均大写。  
Example:  
```java
private boolean mIsDead;
private int mIndex;
```
3. 所有类名、接口名首字母均大写。  
Example:  
```java
public class MyExampleClass {

}
```
  
## 代码风格
类、接口、方法之后的大括号前有一个空格并且不换行。  
Example:  
```java
public class MyExampleClass {
	public void ExampleMethod() {
		
	}
}
```
if、for后大括号是否换行没有限制。
方法间空行没有限制。

## 一些说明
正常注释是
```java
//
/* */
```  
如果是Javadoc就是  
```java
/**  
 *  
 */
```  
Javadoc会显示在自动补全中和Javadoc下面。  
当你认为你的参数可能会引起问题的时候可以加Javadoc说明，便于合作开发。  
Javadoc中普通换行不会显示为换行。可以将一段用
```html
<p></p>
```包围起来，或者在换行处加入
```html
<br />
```，这样就能显示为换行。
