package cn.s3bit.mbgparser;

public class Tuple <A, B> {
	public A Item1;
	public B Item2;
	public Tuple(A a, B b) {
		Item1 = a;
		Item2 = b;
	}
	public static <A, B> Tuple<A, B> Create(A a, B b) {
		return new Tuple<A, B>(a, b);
	}
}
