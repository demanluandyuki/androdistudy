package com.joyfulmath.androidstudy.thread.messagemachine;

import com.joyfulmath.androidstudy.TraceLog;

public class MessageHandlerThread extends Thread {
	private Object objsync = new Object();
	
	private boolean mQuite = false;
	private Message msg = null;
	@Override
	public void run() {
		TraceLog.d("MessageHandlerThread start running");
		while(true)
		{
			synchronized (objsync) {
				if(mQuite)
				{
					break;
				}
			}
			
		}
	}
	
	
	public void quit()
	{
		synchronized (objsync) {
			mQuite = true;
		}
	}
}
