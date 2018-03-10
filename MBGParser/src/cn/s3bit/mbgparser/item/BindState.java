package cn.s3bit.mbgparser.item;

import java.io.Serializable;

public class BindState implements Serializable
{
	private static final long serialVersionUID = 1979943698149811061L;
	public interface IBindable
	{
	}

	public BulletEmitter Parent;
	public IBindable Child;
	public boolean Depth, Relative;
}