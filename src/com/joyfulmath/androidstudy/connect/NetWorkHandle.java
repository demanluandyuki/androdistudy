package com.joyfulmath.androidstudy.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.joyfulmath.androidstudy.TraceLog;

import android.os.AsyncTask;

public class NetWorkHandle {
	
	private onDownLoadResult mResultListener = null;
	
	public class DownloadWebpageTask extends AsyncTask<String, Void, String>{

		public DownloadWebpageTask(onDownLoadResult resultListener )
		{
			mResultListener = resultListener;
		}
		
		@Override
		protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
		}
		
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            mResultListener.onResult(result);
       }
	}



	private String downloadUrl(String myurl) throws IOException{
		
		InputStream is = null;
	    // Only display the first 500 characters of the retrieved
	    // web page content.
	    int len = 500;
	        
	    try {
	        URL url = new URL(myurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept-Charset", "utf-8");
	        conn.setRequestProperty("contentType", "utf-8");
	        conn.setDoInput(true);
	        // Starts the query
	        conn.connect();
	        int response = conn.getResponseCode();
	        TraceLog.d("The response is: " + response);
	        is = conn.getInputStream();

	        // Convert the InputStream into a string
//	        String contentAsString = readIt(is, len);
	        StringBuilder builder  = inputStreamToStringBuilder(is);
	        conn.disconnect();
	        return builder.toString();
	        
	    // Makes sure that the InputStream is closed after the app is
	    // finished using it.
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
	}


	// 将InputStream 格式转化为StringBuilder 格式
	private StringBuilder inputStreamToStringBuilder(InputStream is) throws IOException {
		// 定义空字符串
		String line = "";
		// 定义StringBuilder 的实例total
		StringBuilder total = new StringBuilder();
		// 定义BufferedReader，载入InputStreamReader
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		// readLine 是一个阻塞的方法，当没有断开连接的时候就会一直等待，直到有数据返回
		// 返回null 表示读到数据流最末尾
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}
		// 以StringBuilder 形式返回数据内容
		return total;
	}
	
	// 将InputStream 格式数据流转换为String 类型
	private String inputStreamToString(InputStream is) throws IOException {
		// 定义空字符串
		String s = "";
		String line = "";
		// 定义BufferedReader，载入InputStreamReader
		BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		// 读取到字符串中
		while ((line = rd.readLine()) != null) {
			s += line;
		}
		// 以字符串方式返回信息
		return s;
	}
	
	
	// Reads an InputStream and converts it to a String.
	public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}
	
	public interface onDownLoadResult{
		void onResult(String result);
	}
}
