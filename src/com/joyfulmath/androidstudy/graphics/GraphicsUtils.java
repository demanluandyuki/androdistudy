package com.joyfulmath.androidstudy.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GraphicsUtils {
	
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
}
