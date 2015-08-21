package com.joyfulmath.androidstudy.bind.worm;

import android.os.Parcel;
import android.os.Parcelable;

public class DataP implements Parcelable {

	public int n;
	
	public DataP(int n) {
		this.n = n;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(n);
	}

	
    public static final Parcelable.Creator<DataP> CREATOR = new Parcelable.Creator<DataP>() 
    {
        public DataP createFromParcel(Parcel in) 
        {
            return new DataP(in);
        }

        public DataP[] newArray(int size) 
        {
            return new DataP[size];
        }
    };
    
    private DataP(Parcel in) 
    {
        n = in.readInt();
    }

	@Override
	public String toString() {
		return Integer.toString(n);
	}
	
    
}
