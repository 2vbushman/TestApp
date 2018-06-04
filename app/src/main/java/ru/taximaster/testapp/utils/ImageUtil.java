package ru.taximaster.testapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import ru.taximaster.testapp.loaders.ImageFileLoader;

public final class ImageUtil {
	private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
		if (image != null) {
			if (maxHeight > 0 && maxWidth > 0) {
				int width = image.getWidth();
				int height = image.getHeight();
				float ratioBitmap = (float) width / (float) height;
				float ratioMax = (float) maxWidth / (float) maxHeight;

				int finalWidth = maxWidth;
				int finalHeight = maxHeight;
				if (ratioMax > 1) {
					finalWidth = (int) ((float) maxHeight * ratioBitmap);
				} else {
					finalHeight = (int) ((float) maxWidth / ratioBitmap);
				}
				image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
				return image;
			} else {
				return image;
			}
		} else {
			return null;
		}
	}

	public static Bitmap resize(Bitmap image, int size) {
		return resize(image, size, size);
	}

	public static void loadImageBitmap(String url,
	                                   ImageView button,
	                                   Context context,
	                                   int size) {

		ImageFileLoader loader = new ImageFileLoader(url,
				new WeakReference<>(button),
				new WeakReference<>(context),
				size);

		loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	public static int convertDpToPx(int dp) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		return (int) (dp * displaymetrics.density);
	}
}
