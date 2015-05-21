package com.joyfulmath.androidstudy.graphics;

import com.joyfulmath.androidstudy.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class GraphicsActivity extends Activity {

	private ImageView mImage = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphics_activity_layout);
		mImage = (ImageView) findViewById(R.id.graphics_imageView1);
		final int mSize  = (int) getResources().getDimension(R.dimen.graphics_image_width);
		Bitmap mBitmap = GraphicsUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.jellyfish, mSize, mSize);
		mImage.setImageBitmap(mBitmap);
	}

	@Override
	protected void onStart() {
		super.onStart();
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
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	

}
