package com.joyfulmath.androidstudy.graphics;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {

	private final WeakReference<ImageView> imageViewReference;
    private int data = 0;
	private Context mContext;

    public BitmapWorkerTask(ImageView imageView,Context mContext) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
        this.mContext = mContext;
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(Integer... params) {
        data = params[0];
        return GraphicsUtils.decodeSampledBitmapFromResource(mContext.getResources(), data, 100, 100);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

}
