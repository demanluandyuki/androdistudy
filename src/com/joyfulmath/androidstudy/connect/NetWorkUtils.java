package com.joyfulmath.androidstudy.connect;

import com.joyfulmath.androidstudy.TraceLog;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {
	
	
	public static void traceConnectStatus(Context context)
	{
		ConnectivityManager connMgr = (ConnectivityManager) 
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
		boolean isWifiConn = networkInfo.isConnected();
		networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isMobileConn = networkInfo.isConnected();
		TraceLog.d("Wifi connected: " + isWifiConn);
		TraceLog.d("Mobile connected: " + isMobileConn);
	}
	
	public static boolean isOnline(Context context)
	{
	    ConnectivityManager connMgr = (ConnectivityManager) 
	    		context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    return (networkInfo != null && networkInfo.isConnected());
	}
}
