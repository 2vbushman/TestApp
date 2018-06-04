package ru.taximaster.testapp.api.images;

import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import ru.taximaster.testapp.api.common.CustomGsonBodyConverter;

public final class CustomGsonBodyPhotoConverter<T>
		extends CustomGsonBodyConverter<T> {

	CustomGsonBodyPhotoConverter(Gson gson, TypeAdapter<T> adapter) {
		super(gson, adapter);
	}

	@VisibleForTesting
	protected String substringResponse(final String response) {
		if (response == null || response.equals("")) {
			return null;
		}
		final int start = response.indexOf("[");
		final int end = response.indexOf("]") + 1;
		return response.substring(start, end);
	}
}
