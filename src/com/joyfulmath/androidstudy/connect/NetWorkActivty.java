package com.joyfulmath.androidstudy.connect;

import com.joyfulmath.androidstudy.R;
import com.joyfulmath.androidstudy.connect.NetWorkHandle.onDownLoadResult;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class NetWorkActivty extends Activity implements onDownLoadResult{

	EditText mEdit = null;
	WebView mContent = null;
	WebView mWebView = null;
	NetWorkHandle netHandle = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_layout);
		mEdit = (EditText) findViewById(R.id.url_edit);
		mEdit.setText("http://www.163.com");
		mContent = (WebView) findViewById(R.id.content_view);
		mWebView = (WebView) findViewById(R.id.webview_id);
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
		MenuItem connect = menu.add(0, 0, 0, "connect");
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
			netHandle.new DownloadWebpageTask(this).execute(stringurl);
		}
		else
		{
			String mimeType = "text/html";
			mContent.loadData("No network connection available", mimeType, null);
		}
		
		mWebView.loadUrl(stringurl);
	}

	@Override
	public void onResult(String result) {
		String mimeType = "text/html";
		mContent.loadData(result,mimeType,null);
	}
	
	
	
}
