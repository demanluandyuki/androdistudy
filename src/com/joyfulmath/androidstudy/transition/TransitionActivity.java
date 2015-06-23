package com.joyfulmath.androidstudy.transition;

import com.joyfulmath.androidstudy.R;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class TransitionActivity extends Activity {

	Scene mAScene;
	Scene mAnotherScene;
	private ViewGroup mSceneRoot;
	private TransitionManager  mTransitionManager;
	View mTitle = null;
	int currentId = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scene_layout);
		mSceneRoot = (ViewGroup) findViewById(R.id.scene_root);
		
		mTitle = findViewById(R.id.title);

		
		mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.a_scene, this);
		mAnotherScene =
		    Scene.getSceneForLayout(mSceneRoot, R.layout.another_scene, this);
		TransitionInflater inflater = TransitionInflater.from(this);
        mTransitionManager = inflater.inflateTransitionManager(R.transition.transitions_mgr,
                mSceneRoot);
        
		mTitle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setScene(3-currentId);
			}
		});
	}
	
	public void setScene(int id)
	{
		if(id == currentId)
		{
			return;
		}
		currentId = id;
		switch (id) {
        case 1:
            mTransitionManager.transitionTo(mAScene);
            break;
        case 2:
            mTransitionManager.transitionTo(mAnotherScene);
            break;
		}
	}
}
