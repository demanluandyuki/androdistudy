package com.joyfulmath.androidstudy.bind.worm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Intent;

import com.joyfulmath.androidstudy.TraceLog;

public class WormSample {
	
	static final String path = "/mnt/sdcard/worm.out";
	public void doAction()
	{
		TraceLog.i();
		Worm w = new Worm(6, 'a');
		TraceLog.i("\n"+w.toString());
		
		try {
			ObjectOutputStream opt = new ObjectOutputStream(new FileOutputStream(path));
			opt.writeObject("Worm object\n");
			opt.writeObject(w);
			opt.close();
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
			String s = (String) in.readObject();
			Worm w2 = (Worm) in.readObject();
			TraceLog.i(s+w);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TraceLog.i("end");
	}
	
	public void doActionP()
	{
		TraceLog.i();
		byte a = 'a';
		WormP w = new WormP(6, a);
		TraceLog.i(w.toString());
		Intent intent = new Intent();
		intent.putExtra("wormp", w);
		
		///...
		
		Intent newIntent = new Intent(intent);
		
		WormP w2 = newIntent.getParcelableExtra("wormp");
		TraceLog.i(w2.toString());
		
		TraceLog.i("end");
	}
}
