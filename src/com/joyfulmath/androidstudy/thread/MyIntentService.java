package com.joyfulmath.androidstudy.thread;

import com.joyfulmath.androidstudy.TraceLog;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

	public MyIntentService() {
		super("MyIntentService");
	}
	
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		TraceLog.i();
	}



	@Override
	protected void onHandleIntent(Intent intent) {
		TraceLog.i();
		doAction(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		TraceLog.i();
	}
	
	private void doAction(Intent intent)
	{
		String params = intent.getStringExtra("key");
		TraceLog.i(params);
		int index = intent.getIntExtra("index", -1);
		TraceLog.i(index+"");
	}
}
