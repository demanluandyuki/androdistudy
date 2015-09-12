package com.joyfulmath.androidstudy.bind.worm;

import java.io.Serializable;
import java.util.Random;

import com.joyfulmath.androidstudy.TraceLog;

public class Worm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7922476832346651738L;
	static Random rand = new Random(47);
	Data[] d = {
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10))
	};
	
	private Worm next;
	private char c;
	
	public Worm(int i, char x)
	{
		TraceLog.i("Worm construct:"+i);
		c = x;
		if(--i>0)
		{
			next = new Worm(i,(char) (x+1));
		}
	}
	
	public Worm()
	{
		TraceLog.i("default Worm construct");
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for(Data dat:d)
		{
			result.append(dat+" ");
		}
		result.append(")");
		if(next!=null)
		{
			result.append(next);
		}
		return result.toString();
	}
	
	
}
