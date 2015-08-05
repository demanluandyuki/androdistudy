package com.joyfulmath.androidstudy.thread;

import com.joyfulmath.androidstudy.TraceLog;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class MyHandlerThread extends HandlerThread{

	MyHandler myHandler = null;
	
	public MyHandlerThread(String name) {
		super(name);
	}
	
		
	@Override
	protected void onLooperPrepared() {
		super.onLooperPrepared();
		myHandler = new MyHandler(getLooper());
	}

	public void doAction(int index,String params)
	{
		if(index>0 && index <=3)
		{
			Message msg = myHandler.obtainMessage(index);
			Bundle bundle = new Bundle();
			bundle.putString("key", params);
			msg.setData(bundle);
			myHandler.sendMessage(msg);
		}
		else
		{
			TraceLog.w(index+"");
		}
	}

	public static  class MyHandler extends Handler{
		
		public MyHandler()
		{
			super();
		}
		
		public MyHandler(Looper loop)
		{
			super(loop);
		}
		
		/*make sure that the looper is main or not
		 *so you can update UI or send main handler to do it. 
		 * */
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			String params = bundle.getString("key");
			TraceLog.i(params);
			switch(msg.what)
			{
			case ThreadConstant.INDEX_1:
				TraceLog.d("INDEX_1");
				break;
			case ThreadConstant.INDEX_2:
				TraceLog.d("INDEX_2");
				break;
			case ThreadConstant.INDEX_3:
				TraceLog.d("INDEX_3");
				break;	
			}
		}
	}
}
