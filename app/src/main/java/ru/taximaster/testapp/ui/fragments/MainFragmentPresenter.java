package ru.taximaster.testapp.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.taximaster.testapp.FlickrApplication;
import ru.taximaster.testapp.R;
import ru.taximaster.testapp.data.Photo;
import ru.taximaster.testapp.loaders.ImagesLoader;
import ru.taximaster.testapp.ui.adapters.PhotoAdapter;
import ru.taximaster.testapp.ui.dialogs.InfoDialog;
import ru.taximaster.testapp.utils.DialogUtil;
import ru.taximaster.testapp.utils.NetworkUtil;

public final class MainFragmentPresenter {
	private final Context CONTEXT;
	private MainView mMainView;
	private final ImagesLoader IMAGES_LOADER;

	MainFragmentPresenter(Context context) {
		CONTEXT = context;
		IMAGES_LOADER = new ImagesLoader(this);
	}

	void attacheView(MainView view) {
		mMainView = view;
	}

	public void loadPhotos(String search, int page) {
		if (!NetworkUtil.isNetworkConnected(CONTEXT)) {
			showNetworkErrorDialog();
			return;
		}
		if (mMainView != null) {
			ProgressDialog dialog = mMainView.getDialog();
			if (dialog != null && !dialog.isShowing()) dialog.show();
		}

		IMAGES_LOADER.loadPhotos(search, page);
	}

	public ProgressDialog prepareProgressDialog() {
		ProgressDialog dialog = new ProgressDialog(CONTEXT);
		dialog.setTitle(R.string.dialog_header);
		dialog.setMessage(CONTEXT.getString(R.string.dialog_message));
		dialog.setCancelable(false);
		dialog.setIndeterminate(true);
		return dialog;
	}

	public void onImageLoaded(List<Photo> list) {
		if (mMainView != null && list != null && list.size() > 0) {
			final RecyclerView recyclerView = mMainView.getRecyclerView();
			initRecycleView(recyclerView);

			if (recyclerView.getAdapter() instanceof PhotoAdapter) {
				PhotoAdapter adapter = (PhotoAdapter) recyclerView.getAdapter();
				if (adapter != null) {
					List<Photo> oldList = adapter.getModel();
					if (oldList != null && oldList.size() > 0) {
						final int oldSize = oldList.size() - 1;
						oldList.addAll(list);
						adapter.setModel(oldList);
						mMainView.setList(oldList);
						recyclerView.scrollToPosition(oldSize);
					} else {
						mMainView.setList(list);
						adapter.setModel(list);
					}
					adapter.notifyDataSetChanged();
				}
			}
		}
		showHintDialog();
	}

	private void showHintDialog() {
		SharedPreferences preferences = getSharedPreferences();
		AppCompatActivity activity = getActivity();

		if (!preferences.getBoolean(FlickrApplication.PreferencesKeys.INFO.val, false)) {
			new Handler().postDelayed(() -> {
				InfoDialog infoDialog = new InfoDialog();
				infoDialog.setTitle(R.string.dialog_header_info);
				infoDialog.setMessage(R.string.dialog_hind_message);
				infoDialog.show(activity.getSupportFragmentManager(), null);
			}, 1000L);
		}
	}

	private SharedPreferences getSharedPreferences() {
		return ((FlickrApplication) CONTEXT.getApplicationContext()).getPreferences();
	}

	private AppCompatActivity getActivity() {
		return ((FlickrApplication) CONTEXT.getApplicationContext()).getActivity();
	}

	public void onStateRestored() {
		if (!NetworkUtil.isNetworkConnected(CONTEXT)) {
			showNetworkErrorDialog();
			return;
		}
		if (mMainView != null) {
			final RecyclerView recyclerView = mMainView.getRecyclerView();
			initRecycleView(recyclerView);

			if (recyclerView.getAdapter() instanceof PhotoAdapter) {
				PhotoAdapter adapter = (PhotoAdapter) recyclerView.getAdapter();
				if (adapter != null) {
					List<Photo> list = mMainView.getList();
					if (list != null && list.size() > 0) {
						adapter.setModel(list);
						adapter.notifyDataSetChanged();
					}
				}
			}
		}
	}

	private void initRecycleView(RecyclerView recyclerView) {
		if (mMainView != null) {
			List<Photo> photos = mMainView.getList();
			final int columns = CONTEXT.getResources().getInteger(R.integer.columns);

			recyclerView.setLayoutManager(new GridLayoutManager(CONTEXT, columns));
			PhotoAdapter adapter = new PhotoAdapter(photos, CONTEXT);
			recyclerView.setAdapter(adapter);
		}
	}

	public MainView getMainView() {
		return mMainView;
	}

	public void showFinishDialog() {
		DialogUtil.getDialog(CONTEXT.getString(R.string.dialog_header_warning),
				CONTEXT.getString(R.string.dialog_empty_result_error),
				CONTEXT);
	}

	private void showNetworkErrorDialog() {
		DialogUtil.getDialog(CONTEXT.getString(R.string.dialog_header_error),
				CONTEXT.getString(R.string.dialog_network_error),
				CONTEXT);
	}
}
