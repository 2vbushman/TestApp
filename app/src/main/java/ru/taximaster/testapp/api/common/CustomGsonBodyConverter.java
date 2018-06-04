package ru.taximaster.testapp.api.common;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public abstract class CustomGsonBodyConverter<T>
		implements Converter<ResponseBody, T> {

	private final Gson GSON;
	private final TypeAdapter<T> ADAPTER;

	protected CustomGsonBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		GSON = gson;
		ADAPTER = adapter;
	}

	@Override
	public T convert(@NonNull ResponseBody value) throws IOException {
		String response = value.string();
		JsonReader jsonReader;

		final String newResponse = substringResponse(response);
		if (newResponse == null) throw new IOException("Data corrupted");

		jsonReader = GSON.newJsonReader(new StringReader(newResponse));
		try {
			return ADAPTER.read(jsonReader);
		} finally {
			value.close();
		}
	}

	protected abstract String substringResponse(final String response);
}
