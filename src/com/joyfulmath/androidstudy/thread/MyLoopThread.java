package com.joyfulmath.androidstudy.thread;

import com.joyfulmath.androidstudy.TraceLog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MyLoopThread extends Thread {
		
	private Looper myLooper = null;
	private MyHandler mHandler = null;
	private Handler mHandler2 = null;
	public MyLoopThread()
	{
		super();
	}
	
	@Override
	public void run() {
		TraceLog.i("MyLoopThread looper prepare");
		Looper.prepare();
//		myLooper = Looper.getMainLooper(); /*using this can be set as main handler*/
		myLooper = Looper.myLooper();
		mHandler =  new MyHandler(myLooper);
		mHandler2 = new Handler(myLooper){

			@Override
			public void handleMessage(Message msg) {
				Bundle bundle = msg.getData();
				String params = bundle.getString("key");
				TraceLog.i("Handler2 "+params);
				switch(msg.what)
				{
				case ThreadConstant.INDEX_1:
					TraceLog.d("Handler2 INDEX_1");
					break;
				case ThreadConstant.INDEX_2:
					TraceLog.d("Handler2 INDEX_2");
					break;
				case ThreadConstant.INDEX_3:
					TraceLog.d("Handler2 INDEX_3");
					break;	
				}
			}
			
			
		};
		TraceLog.i("MyLoopThread looper loop");
		Looper.loop();
	}
	
	
	public void doAction(int index,String params)
	{
		if(index>0 && index <=3)
		{
			Message msg = mHandler.obtainMessage(index);
			Bundle bundle = new Bundle();
			bundle.putString("key", params);
			msg.setData(bundle);
			mHandler.sendMessage(msg);
		}
		else
		{
			TraceLog.w(index+"");
		}
	}
	
	
	public void doAction2(int index,String params)
	{
		if(index>0 && index <=3)
		{
			Message msg = mHandler2.obtainMessage(index);
			Bundle bundle = new Bundle();
			bundle.putString("key", params);
			msg.setData(bundle);
			mHandler2.sendMessage(msg);
		}
		else
		{
			TraceLog.w(index+"");
		}
	}
	
	public void exit()
	{
		myLooper.quit();
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
