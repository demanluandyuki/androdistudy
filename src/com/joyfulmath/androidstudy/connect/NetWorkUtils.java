package com.joyfulmath.androidstudy.connect;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joyfulmath.androidstudy.TraceLog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {
	
	public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
	public enum NetWorkStatus{
		INVALID,GPRS,WIFI,BOTH
	}
	private WeakReference<Context> mWeakContext = null;
	private ConnectReceiver mConReceiver = null;
//	private NetWorkStatus mNetWorkStatus = NetWorkStatus.INVALID;
	
	public static NetWorkStatus traceConnectStatus(Context context) {
		
		NetWorkStatus mStatus = NetWorkStatus.INVALID;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean isWifiConn = networkInfo.isConnected();
		networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isMobileConn = networkInfo.isConnected();
		
		if (isMobileConn) {
			if (isWifiConn) {
				mStatus = NetWorkStatus.BOTH;
			} else {
				mStatus = NetWorkStatus.GPRS;
			}
		} else {
			if (isWifiConn) {
				mStatus = NetWorkStatus.WIFI;
			} else {
				mStatus = NetWorkStatus.INVALID;
			}
		}
		return mStatus;
	}
	
	public static boolean isOnline(Context context)
	{
	    ConnectivityManager connMgr = (ConnectivityManager) 
	    		context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    return (networkInfo != null && networkInfo.isConnected());
	}
	
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	
	public NetWorkUtils(Context context)
	{
		mWeakContext = new WeakReference<Context>(context);
	}
	

	public void registerConnectReceiver() {
		if (mWeakContext.get() != null) {
			
			if (mConReceiver == null) {
				mConReceiver = new ConnectReceiver();
			}
			IntentFilter filter = new IntentFilter();
			filter.addAction(CONNECTIVITY_CHANGE_ACTION);
			filter.setPriority(1000);
			mWeakContext.get().registerReceiver(mConReceiver, filter);
		}

	}
	
	public void unRegisterConnectReceiver()
	{
		if(mWeakContext.get()!=null && mConReceiver!=null)
		{
			mWeakContext.get().unregisterReceiver(mConReceiver);
			mConReceiver = null;
		}

	}
	
	private void connectChanged()
	{
		if(mWeakContext.get()!=null)
		{
			NetWorkStatus status = traceConnectStatus(mWeakContext.get());
			TraceLog.i("status:"+status);
		}
	}
	
	public class ConnectReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action!=null && action.equals(CONNECTIVITY_CHANGE_ACTION))
			{
				connectChanged();
			}
		}
		
	}

}
