package com.joyfulmath.androidstudy.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AndroidRuntimeException;
import android.util.LruCache;
import android.widget.ImageView;

public class GraphicsUtils {
	
	
	/*DiskLruCache can be another case to using
	 * */
	private static LruCache<String, Bitmap> mMemoryCache = null;
	
	
	 /**
     * Get the bitmap size info
     * @param res  		the resource
     * @param resId		image id
     * @return 			the BitmapDisplay of bitmap
     */
	public static BitmapDisplay readBitmapDimensionsandType(Resources res,int resId)
	{
		BitmapDisplay display = new BitmapDisplay();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		display.imageHeight = options.outHeight;
		display.imageWidth = options.outWidth;
		display.imageType = options.outMimeType;
				
		return display;
	}

	 /**
     * Get the scale size with the special image
     * @param display  		the image info
     * @param reqWidth		request width
     * @param reqHeight		request height
     * @return 				the scale size
     */
	public static int calculateInSampleSize(BitmapDisplay display,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = display.imageHeight;
		final int width = display.imageWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapDisplay display = readBitmapDimensionsandType(res,resId);

	    // Calculate inSampleSize
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inSampleSize = calculateInSampleSize(display, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
	
	public static void loadBitmapAsync(Context context,int resId, ImageView imageView) {
		final String imageKey = String.valueOf(resId);
		final Bitmap bitmap = getBitmapFromMemCache(imageKey);
		if(null!=bitmap)
		{
			imageView.setImageBitmap(bitmap);
		}
		else
		{
		    BitmapWorkerTask task = new BitmapWorkerTask(imageView,context);
		    task.execute(resId);
		}
	}
	
	public static void initMemoryCache()
	{
	    // Get max available VM memory, exceeding this amount will throw an
	    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
	    // int in its constructor.
	    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

	    // Use 1/8th of the available memory for this memory cache.
	    final int cacheSize = maxMemory / 8;
	    
	    mMemoryCache = new LruCache<String, Bitmap>(cacheSize){

			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
	            // The cache size will be measured in kilobytes rather than
	            // number of items.
	            return bitmap.getByteCount() / 1024;
			}
	    	
	    };
	}
	
	public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if(null == mMemoryCache)
		{
			throw new AndroidRuntimeException("mMemoryCache has not been inited");
		}
		
	    if (getBitmapFromMemCache(key) == null) {
	        mMemoryCache.put(key, bitmap);
	    }
	}

	public static Bitmap getBitmapFromMemCache(String key) {
		
		if(null == mMemoryCache)
		{
			throw new AndroidRuntimeException("mMemoryCache has not been inited");
		}
		
	    return mMemoryCache.get(key);
	}
}
