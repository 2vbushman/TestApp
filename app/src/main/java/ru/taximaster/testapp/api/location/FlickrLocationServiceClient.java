package ru.taximaster.testapp.api.location;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.taximaster.testapp.data.Location;

public interface FlickrLocationServiceClient {
	@GET("rest/")
	Call<Location> getPhotos(
			@Query("method") String method,
			@Query("api_key") String api_key,
			@Query("photo_id") String photo_id,
			@Query("format") String format
	);

	@GET("rest/")
	Call<Location> getPhotos(@QueryMap Map<String, String> options);
}
