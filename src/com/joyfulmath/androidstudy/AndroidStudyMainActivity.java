package com.joyfulmath.androidstudy;

import com.joyfulmath.androidstudy.graphics.GraphicsActivity;
import com.joyfulmath.androidstudy.graphics.GraphicsUtils;
import com.joyfulmath.androidstudy.opgles.OpenGLES20Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AndroidStudyMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_study_main);
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
		}

		return super.onOptionsItemSelected(item);
	}
}
