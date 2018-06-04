package ru.taximaster.testapp.loaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.taximaster.testapp.utils.ImageUtil;

public final class ImageFileLoader extends AsyncTask<Void, Void, Bitmap> {
	private final WeakReference<ImageView> REFERENCE;
	private final String IMAGE_URL;
	private final WeakReference<Context> CONTEXT;
	private final int IMAGE_SIZE;

	public ImageFileLoader(String url,
	                       WeakReference<ImageView> reference,
	                       WeakReference<Context> context,
	                       int size) {
		IMAGE_URL = url;
		REFERENCE = reference;
		CONTEXT = context;
		IMAGE_SIZE = size;
	}

	@Override
	protected Bitmap doInBackground(Void... strings) {
		return getPhoto();
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (bitmap != null) {
			if (CONTEXT.get() != null && REFERENCE.get() != null) {
				Bitmap resizedBitmap = ImageUtil.resize(bitmap, IMAGE_SIZE);
				REFERENCE.get().setImageBitmap(resizedBitmap);
			}
		}
		super.onPostExecute(bitmap);
	}

	private Bitmap getPhoto() {
		try {
			URL url = new URL(IMAGE_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			return BitmapFactory.decodeStream(input);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
