package com.joyfulmath.androidstudy.thread.messagemachine;

import com.joyfulmath.androidstudy.TraceLog;

public class MessageHandlerThread extends Thread {
	private Object objsync = new Object();
	
	private boolean mQuite = false;
	ThreadLocal<MessageQueue> mThreadLocakMsgQueue = new ThreadLocal<MessageQueue>();
	@Override
	public void run() {
		TraceLog.d("MessageHandlerThread start running");
		prepare();
		final MessageQueue mQueue = getMessageQueue();
		while(true)
		{
			synchronized (objsync) {
				if(mQuite)
				{
					TraceLog.i("quite msg queue");
					break;
				}
			}
			Message msg = mQueue.next();
			TraceLog.i("get next msg:"+msg);
			if(msg == null)
			{
				// No message indicates that the message queue is quitting.
				return;
			}
			msg.target.dispatchHandlerMessage(msg);
		}
		
		TraceLog.i("thread is done");
	}
	
	private void prepare()
	{
		if(mThreadLocakMsgQueue.get()!=null)
		{
			throw new RuntimeException("message queue should only be one for pre thread");
		}
		mThreadLocakMsgQueue.set(new MessageQueue());
		onPrepared();
	}
	
	public MessageQueue getMessageQueue()
	{
		return mThreadLocakMsgQueue.get();
	}
	
	public void quit()
	{
		synchronized (objsync) {
			mQuite = true;
			getMessageQueue().quit();
		}
	}
	
	protected void onPrepared()
	{
		
	}
}
