package ru.taximaster.testapp.loaders;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.taximaster.testapp.BuildConfig;
import ru.taximaster.testapp.api.images.FlickrServiceClient;
import ru.taximaster.testapp.api.images.LenientGsonConverterFactory;
import ru.taximaster.testapp.data.Photo;
import ru.taximaster.testapp.ui.fragments.MainFragmentPresenter;
import ru.taximaster.testapp.ui.fragments.MainView;

public final class ImagesLoader {
	private final Retrofit RETROFIT;
	private String mQuery;
	private static final String BASE = "https://api.flickr.com/services/";
	private final MainFragmentPresenter PRESENTER;
	private int mPage;

	public ImagesLoader(MainFragmentPresenter presenter) {
		PRESENTER = presenter;
		RETROFIT = buildRetrofit();
	}

	public void loadPhotos(String query, int page) {
		mQuery = query;
		mPage = page;
		buildAPIServiceClient();
	}

	private Retrofit buildRetrofit() {
		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit.Builder builder = new Retrofit.Builder();
		builder.baseUrl(BASE);
		builder.addConverterFactory(LenientGsonConverterFactory.create(gson));
		return builder.build();
	}

	private void buildAPIServiceClient() {
		if (RETROFIT == null) return;
		if (mQuery == null || mQuery.equals("")) return;

		FlickrServiceClient client = RETROFIT.create(FlickrServiceClient.class);
		Call<List<Photo>> call = client.getPhotos(getQueryMap());
		setEnqueue(call);
	}

	private Map<String, String> getQueryMap() {
		return Collections.unmodifiableMap(new HashMap<String, String>() {{
			put("method", "flickr.photos.search");
			put("api_key", BuildConfig.FLICKR_KEY);
			put("sort", "relevance");
			put("content_type", "1");
			put("per_page", "20");
			put("page", String.valueOf(mPage));
			put("media", "photos");
			put("format", "json");
			put("text", mQuery);
		}});
	}

	private void setEnqueue(Call<List<Photo>> call) {
		if (call == null) return;
		call.enqueue(new Callback<List<Photo>>() {
			@Override
			public void onResponse(@NonNull Call<List<Photo>> call,
			                       @NonNull Response<List<Photo>> response) {
				if (response.isSuccessful()) {
					List<Photo> list = response.body();
					if (list != null && list.size() > 0) {
						//for (Photo photo : list) DebugUtil.print(photo.getImageUrl());
						incrementPage();
						PRESENTER.onImageLoaded(list);
					}
					closeDialog();
					unlockRecycleViewScrollEvents();
				}
			}

			@Override
			public void onFailure(@NonNull Call<List<Photo>> call,
			                      @NonNull Throwable t) {
				closeDialog();
				unlockRecycleViewScrollEvents();
				PRESENTER.showFinishDialog();
			}
		});
	}

	private void closeDialog() {
		MainView view = PRESENTER.getMainView();
		if (view != null) {
			view.getDialog().cancel();
		}
	}

	private void unlockRecycleViewScrollEvents() {
		MainView view = PRESENTER.getMainView();
		if (view != null) {
			new Handler().postDelayed(view::scrollEventUnlock, 1000L);
		}
	}

	private void incrementPage() {
		MainView view = PRESENTER.getMainView();
		if (view != null) {
			view.setPage(mPage += 1);
		}
	}
}
