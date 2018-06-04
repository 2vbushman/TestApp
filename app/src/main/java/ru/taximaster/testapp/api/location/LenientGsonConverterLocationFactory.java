package ru.taximaster.testapp.api.location;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class LenientGsonConverterLocationFactory extends Converter.Factory {
	private final Gson GSON;

	@SuppressWarnings("unused")
	public static LenientGsonConverterLocationFactory create() {
		return create(new Gson());
	}

	public static LenientGsonConverterLocationFactory create(Gson gson) {
		return new LenientGsonConverterLocationFactory(gson);
	}

	private LenientGsonConverterLocationFactory(Gson gson) {
		if (gson == null) throw new NullPointerException("gson object is null");
		GSON = gson;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Converter<ResponseBody, ?> responseBodyConverter(Type type,
	                                                        Annotation[] annotations,
	                                                        Retrofit retrofit) {
		TypeAdapter<?> adapter = GSON.getAdapter(TypeToken.get(type));
		return new CustomGsonBodyLocationConverter(GSON, adapter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Converter<?, RequestBody> requestBodyConverter(Type type,
	                                                      Annotation[] parameterAnnotations,
	                                                      Annotation[] methodAnnotations,
	                                                      Retrofit retrofit) {
		TypeAdapter<?> adapter = GSON.getAdapter(TypeToken.get(type));
		return new CustomGsonBodyLocationConverter(GSON, adapter);
	}

}