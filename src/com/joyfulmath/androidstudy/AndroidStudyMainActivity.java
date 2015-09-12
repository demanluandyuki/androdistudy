package com.joyfulmath.androidstudy;

import com.joyfulmath.androidstudy.bind.worm.WormSample;
import com.joyfulmath.androidstudy.connect.NetWorkActivty;
import com.joyfulmath.androidstudy.graphics.GraphicsActivity;
import com.joyfulmath.androidstudy.opgles.OpenGLES20Activity;
import com.joyfulmath.androidstudy.selfviewgrop.CustomViewGroupActivity;
import com.joyfulmath.androidstudy.thread.ThreadSampleActivity;
import com.joyfulmath.androidstudy.transition.TransitionActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AndroidStudyMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_study_main);
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		TraceLog.i(String.valueOf(am.getMemoryClass()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_study_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id)
		{
		case R.id.action_settings:
			startActivity(new Intent(this,GraphicsActivity.class));
			break;
		case R.id.action_opengl:
			startActivity(new Intent(this,OpenGLES20Activity.class));
			break;
		case R.id.action_transition:
			startActivity(new Intent(this,TransitionActivity.class));
			break;
		case R.id.action_network:
			startActivity(new Intent(this,NetWorkActivty.class));
			break;
		case R.id.action_thread:
			startActivity(new Intent(this,ThreadSampleActivity.class));
			break;
		case R.id.action_worm:
			WormSample sample = new WormSample();
			sample.doAction();
			sample.doActionP();
			sample = null;
			break;
		case R.id.action_selfview:
			startActivity(new Intent(this,CustomViewGroupActivity.class));
			break;	
		}

		return super.onOptionsItemSelected(item);
	}
}
