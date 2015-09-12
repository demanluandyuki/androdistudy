package com.joyfulmath.androidstudy.bind.worm;

import java.io.Serializable;

public class Data implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1335198108706772640L;
	private int n;

	public Data(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return Integer.toString(n);
	}
	
	
}
