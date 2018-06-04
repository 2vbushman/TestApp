package ru.taximaster.testapp.utils;

import android.util.Log;

import ru.taximaster.testapp.BuildConfig;

public class DebugUtil {
	public static void print(final Object object) {
		int maxLogSize = 2048;
		if (BuildConfig.DEBUG) {
			if (object == null) {
				Log.d(BuildConfig.LOG_TAG, "--- attention object is null --- !!!!!!!!!!!!!");
				return;
			}
			if (!object.getClass().equals(String.class)) {
				Log.d(BuildConfig.LOG_TAG, object.toString());
			} else {
				if (((String) object).isEmpty()) {
					Log.d(BuildConfig.LOG_TAG, "--- string empty --- !!!!!!!!!!!!!");
					return;
				} else if (object.equals(" ")) {
					Log.d(BuildConfig.LOG_TAG, "--- string equals space --- !!!!!!!!!!!!!");
					return;
				}
				String str = (String) object;
				for (int i = 0; i <= str.length() / maxLogSize; i++) {
					int start = i * maxLogSize;
					int end = (i + 1) * maxLogSize;
					end = end > str.length() ? str.length() : end;
					Log.d(BuildConfig.LOG_TAG, str.substring(start, end));
				}
			}
		}
	}

	@SuppressWarnings("unused")
	public static void foreach(Iterable<?> collection) {
		if (collection == null) {
			print("--- attention collection is null --- !!!!!!!!!!!!!");
			print("++++++++++++++++++++++++++++++++++++");
			return;
		}

		for (Object object : collection) {
			print(object);
		}
		print("++++++++++++++++++++++++++++++++++++");
	}
}
