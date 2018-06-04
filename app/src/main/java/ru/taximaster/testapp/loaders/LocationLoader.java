package ru.taximaster.testapp.loaders;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.taximaster.testapp.BuildConfig;
import ru.taximaster.testapp.R;
import ru.taximaster.testapp.api.location.FlickrLocationServiceClient;
import ru.taximaster.testapp.api.location.LenientGsonConverterLocationFactory;
import ru.taximaster.testapp.data.Location;
import ru.taximaster.testapp.data.Photo;

public final class LocationLoader {
	private final Retrofit RETROFIT;
	private static final String BASE = "https://api.flickr.com/services/";

	public LocationLoader() {
		RETROFIT = buildRetrofit();
	}

	private Retrofit buildRetrofit() {
//		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit.Builder builder = new Retrofit.Builder();
		builder.baseUrl(BASE);
		builder.addConverterFactory(LenientGsonConverterLocationFactory.create(gson));
//		builder.client(client);
		return builder.build();
	}

	public void loadLocation(ImageView image, Photo photo) {
		if (RETROFIT == null) return;
		if (photo == null) return;

		FlickrLocationServiceClient client = RETROFIT.create(FlickrLocationServiceClient.class);
		Call<Location> call = client.getPhotos(getQueryMap(photo.getId()));
		setEnqueue(image, call, photo);
	}

	private void setEnqueue(ImageView image, Call<Location> call, Photo photo) {
		if (call == null) return;
		call.enqueue(new Callback<Location>() {
			@Override
			public void onResponse(@NonNull Call<Location> call, @NonNull Response<Location> response) {
				if (response.isSuccessful()) {
					Location location = response.body();
					if (location != null) {
						image.setImageResource(R.drawable.ic_coordinates);
						photo.setLocation(location);
					}
				}
			}

			@Override
			public void onFailure(@NonNull Call<Location> call, @NonNull Throwable t) {

			}
		});
	}

	private Map<String, String> getQueryMap(String id) {
		return Collections.unmodifiableMap(new HashMap<String, String>() {{
			put("method", "flickr.photos.geo.getLocation");
			put("api_key", BuildConfig.FLICKR_KEY);
			put("photo_id", id);
			put("format", "json");
		}});
	}
}
