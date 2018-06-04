package ru.taximaster.testapp.api.images;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.taximaster.testapp.data.Photo;

public interface FlickrServiceClient {
	@GET("rest/")
	Call<List<Photo>> getPhotos(
			@Query("method") String method,
			@Query("api_key") String api_key,
			@Query("sort") String sort,
			@Query("content_type") String content_type,
			@Query("per_page") String per_page,
			@Query("page") String page,
			@Query("media") String media,
			@Query("format") String format,
			@Query("text") String text);

	@GET("rest/")
	Call<List<Photo>> getPhotos(@QueryMap Map<String, String> options);
}
