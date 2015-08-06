package com.joyfulmath.androidstudy.thread.messagemachine;

import com.joyfulmath.androidstudy.TraceLog;

public abstract class MsgHandler {
	/*
	 * message queue is only one in thread
	 * */
	final MessageQueue mQueue;
	
	public MsgHandler(MessageQueue mQueue)
	{
		this.mQueue = mQueue;
	}
	
	
	public void dispatchHandlerMessage(Message msg)
	{
		TraceLog.i();
		onHandleMessage(msg);
	}
	
	public void sendMessage(Message msg)
	{
		enqueueMessage(msg,0L);
	}
	
	private boolean enqueueMessage(Message msg, long when)
	{
		msg.target = this;
		return mQueue.enqueueMessage(msg, 0);
	}
	
	protected abstract void onHandleMessage(Message msg);
}
