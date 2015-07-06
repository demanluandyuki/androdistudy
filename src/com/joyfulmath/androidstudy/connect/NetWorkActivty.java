package com.joyfulmath.androidstudy.connect;

import com.joyfulmath.androidstudy.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class NetWorkActivty extends Activity {

	EditText mEdit = null;
	TextView mContent = null;
	NetWorkHandle netHandle = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_layout);
		mEdit = (EditText) findViewById(R.id.url_edit);
		mContent = (TextView) findViewById(R.id.content_view);
		netHandle = new NetWorkHandle();
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem connect = menu.add(0, 0, 0, "");
		connect.setIcon(R.drawable.connect_menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == 0)
		{
			stratConnect();			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void stratConnect() {
		String stringurl = mEdit.getText().toString();
		ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conMgr.getActiveNetworkInfo();
		if(info!=null && info.isConnected())
		{
			netHandle.new DownloadWebpageTask().execute(stringurl);
		}
		else
		{
			mContent.setText("No network connection available");
		}
	}
	
	
	
}
