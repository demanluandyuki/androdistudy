package com.joyfulmath.androidstudy.bind.worm;

import java.util.Random;

import com.joyfulmath.androidstudy.TraceLog;

import android.os.Parcel;
import android.os.Parcelable;

public class WormP implements Parcelable {

	static Random rand = new Random(47);
	public DataP[] d = { new DataP(rand.nextInt(10)), new DataP(rand.nextInt(10)),
			new DataP(rand.nextInt(10)) };

	private WormP next;
	public byte c;

	public WormP(int i,byte x)
	{
		TraceLog.i("Wormp construct:"+i);
		c = x;
		if(--i>0)
		{
			next = new WormP(i,(byte) (x+1));
		}
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte(c);
		dest.writeParcelableArray(d, 0);
		if (next != null) {
			dest.writeParcelable(next, 0);
		}
	}

	public static final Parcelable.Creator<WormP> CREATOR = new Parcelable.Creator<WormP>() {
		public WormP createFromParcel(Parcel in) {
			return new WormP(in);
		}

		public WormP[] newArray(int size) {
			return new WormP[size];
		}
	};

	private WormP(Parcel in) {
		c = in.readByte();
		d = (DataP[]) in.readParcelableArray(DataP.class.getClassLoader());
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for(DataP dat:d)
		{
			result.append(dat+" ");
		}
		result.append(")");
		if(next!=null)
		{
			result.append(next);
		}
		return result.toString();
	}
}
