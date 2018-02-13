package cn.s3bit.mbgparser.item;

public class BindState
{
	public interface IBindable
	{
	}

	public BulletEmitter Parent;
	public IBindable Child;
	public boolean Depth, Relative;
}