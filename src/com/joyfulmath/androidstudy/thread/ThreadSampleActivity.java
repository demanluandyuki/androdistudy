package com.joyfulmath.androidstudy.thread;


import com.joyfulmath.androidstudy.R;
import com.joyfulmath.androidstudy.thread.messagemachine.MyMsgMachineSample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThreadSampleActivity extends Activity {
	
	private MyLoopThread myThread = null;
	private MyHandlerThread myHandlerThread = null;
	MyMsgMachineSample mMsgMachineSample = null;
	Button btnStart = null;
	Button btnStart2 = null;
	Button btnStart3 = null;
	Button btnStart4 = null;
	Button btnStart5 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.looperthread_activity);
		initView();

	}

	private void initView() {
		btnStart = (Button) findViewById(R.id.thread_start_id);
		myThread = new MyLoopThread();
		myThread.start();
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myThread.doAction((int)(Math.random()*3)+1, "time millseconds one");
			}
		});
		btnStart2 = (Button) findViewById(R.id.thread_start_id2);
		btnStart2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myThread.doAction2((int)(Math.random()*3)+1, "time millseconds two");
			}
		});
		
		myHandlerThread = new MyHandlerThread("myhandlerthread");
		myHandlerThread.start();
		
		btnStart3 = (Button) findViewById(R.id.thread_start_id3);
		btnStart3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myHandlerThread.doAction((int)(Math.random()*3)+1, "handlerthread time millseconds");
			}
		});
		
		btnStart4 = (Button) findViewById(R.id.thread_start_id4);
		btnStart4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ThreadSampleActivity.this,MyIntentService.class);
				int index = (int)(Math.random()*3)+1;
				String params = "service intent handler";
				intent.putExtra("key", params);
				intent.putExtra("index", index);
				startService(intent);
			}
		});
		
		mMsgMachineSample = new MyMsgMachineSample();
		mMsgMachineSample.start();
		
		btnStart5 = (Button) findViewById(R.id.thread_start_id5);
		btnStart5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMsgMachineSample.doAction((int)(Math.random()*0x10));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(myThread!=null)
		{
			myThread.exit();
		}
		
		if(myHandlerThread!=null)
		{
			myHandlerThread.quit();
		}
		
		if(mMsgMachineSample!=null)
		{
			mMsgMachineSample.quit();
		}
	}
	
}
