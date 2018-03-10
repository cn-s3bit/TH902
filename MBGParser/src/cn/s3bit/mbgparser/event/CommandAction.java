package cn.s3bit.mbgparser.event;

import java.io.Serializable;
import java.util.*;

public final class CommandAction implements IAction, Serializable
{
	private static final long serialVersionUID = -1880878601938661893L;
	public String Command;
	public ArrayList<String> Arguments;

	public static CommandAction parseFrom(String c)
	{
		String[] s = c.split("[，]", -1);
		CommandAction a = new CommandAction();
		a.Arguments = null;
		a.Command = s[0];
		if (s.length > 1)
		{
			a.Arguments = new ArrayList<String>();
			for (int i = 1; i < s.length; i++)
			{
				a.Arguments.add(s[i]);
			}
		}

		return a;
	}

	public CommandAction clone()
	{
		CommandAction varCopy = new CommandAction();

		varCopy.Command = this.Command;
		varCopy.Arguments = this.Arguments;

		return varCopy;
	}
}