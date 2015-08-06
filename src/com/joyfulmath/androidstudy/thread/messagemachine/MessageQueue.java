package com.joyfulmath.androidstudy.thread.messagemachine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.joyfulmath.androidstudy.TraceLog;

import android.util.AndroidRuntimeException;

/*message queue
 * 
 * */
public class MessageQueue {
	BlockingQueue<Message> mQueue = null;
	private boolean mQuit = false;
	public MessageQueue()
	{
		mQueue = new LinkedBlockingQueue<Message>();
		mQueue.clear();
	}
	
	public boolean enqueueMessage(Message msg, long when)
	{
		TraceLog.i();
        if (msg.target == null) {
            throw new AndroidRuntimeException("Message must have a target.");
        }
        
		try {
			mQueue.put(msg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TraceLog.i("done");
		return true;
	}
	
	public Message next()
	{
		TraceLog.i();
		Message msg = null;
		if(mQuit)
		{
			return null;
		}
		

		//wait mQueue msg util we can get one.
		try {
			msg = mQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TraceLog.i(msg.toString());
		return msg;
	}
	
	public synchronized void quit()
	{
		mQuit = true;
	}
}
