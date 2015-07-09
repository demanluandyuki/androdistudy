package com.joyfulmath.androidstudy.graphics;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapWorkerTask extends AsyncTask<Integer, Integer, Bitmap> {

	private final WeakReference<ImageView> imageViewReference;
    private int data = 0;
	private Context mContext;

    public BitmapWorkerTask(ImageView imageView,Context mContext) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
        this.mContext = mContext;
    }

    // Decode image in background.
    //work thread
    @Override
    protected Bitmap doInBackground(Integer... params) {
    	publishProgress(0);
        data = params[0];
        publishProgress(1);
        Bitmap mBitmap = GraphicsUtils.decodeSampledBitmapFromResource(mContext.getResources(), data, 100, 100);
        GraphicsUtils.addBitmapToMemoryCache(String.valueOf(data), mBitmap);
        return mBitmap;
    }

    // Once complete, see if ImageView is still around and set bitmap.
    //UI thread
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
        publishProgress(100);
    }

    //UI thread
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	//UI thread
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}
    
    
}

