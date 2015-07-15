package com.joyfulmath.androidstudy.connect;

import com.joyfulmath.androidstudy.R;
import com.joyfulmath.androidstudy.TraceLog;
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

public class NetWorkActivty extends Activity implements onDownLoadResult{

	EditText mEdit = null;
	WebView mContent = null;
	WebView mWebView = null;
	NetWorkHandle netHandle = null;
	NetWorkUtils mConnectMgr = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_layout);
		mEdit = (EditText) findViewById(R.id.url_edit);
		mEdit.setText("http://www.163.com");
		mContent = (WebView) findViewById(R.id.content_view);
		mWebView = (WebView) findViewById(R.id.webview_id);
		netHandle = new NetWorkHandle();
		mContent.getSettings().setDefaultTextEncodingName("UTF-8");
		mConnectMgr = new NetWorkUtils(getApplicationContext());
	}

	@Override
	protected void onResume() {
		super.onResume();
		NetWorkUtils.traceConnectStatus(getApplicationContext());
		TraceLog.d("isOnline "+NetWorkUtils.isOnline(getApplicationContext()));
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	
	
	@Override
	protected void onStart() {
		super.onStart();
		mConnectMgr.registerConnectReceiver();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mConnectMgr.unRegisterConnectReceiver();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem connect = menu.add(0, 0, 0, "connect");
		connect.setIcon(R.drawable.connect_menu);
		
		MenuItem xml = menu.add(0, 1, 1, "xml");
		xml.setIcon(R.drawable.connect_menu);
				
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == 0)
		{
			stratConnect();			
			return true;
		}else if(item.getItemId() == 1)
		{
			Student  su = XmlParserDemo.parserStudentXml(this, R.xml.student);
			TraceLog.i(su.toString());
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
		TraceLog.d("result length"+result.length());
		TraceLog.i(result);
//		String mimeType = "text/html";
		mContent.loadData(result, "text/html; charset=UTF-8", null);
	}
	
	
	
}
