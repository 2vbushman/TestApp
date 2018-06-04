package ru.taximaster.testapp.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.data.Photo;
import ru.taximaster.testapp.utils.DialogUtil;

public final class MainFragment extends Fragment implements MainView {
	private MainFragmentPresenter mFragmentPresenter;
	private EditText mSearch;
	private Button mButton;
	private ProgressDialog mDialog;
	private RecyclerView mRecyclerView;
	private static final String LIST = "list";
	private static final String PAGE = "page";
	private static final String SEARCH_STRING = "search";
	private ArrayList<Photo> mPhotosList = new ArrayList<>();
	private int mPage = 1;
	private boolean mScrollEventLock;
	private boolean mInitFragment = true;

	@SuppressWarnings("unused")
	public static Fragment newInstance() {
		return new MainFragment();
	}

	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		mFragmentPresenter = new MainFragmentPresenter(getContext());
		mFragmentPresenter.attacheView(this);
		mDialog = mFragmentPresenter.prepareProgressDialog();

		mButton = rootView.findViewById(R.id.button);
		mButton.setEnabled(false);
		mButton.setOnClickListener(this::onClick);

		mSearch = rootView.findViewById(R.id.editText);
		canSearch();

		mRecyclerView = rootView.findViewById(R.id.recycler_view);
		bindRecyclerVieOnScrollEvent();

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			mPage = savedInstanceState.getInt(PAGE);
			mPhotosList = savedInstanceState.getParcelableArrayList(LIST);

			final String search = savedInstanceState.getString(SEARCH_STRING, "");

			if (!search.equals("") && mPhotosList != null && mPhotosList.size() > 0) {
				mSearch.setText(search);
				mInitFragment = false;
				mFragmentPresenter.onStateRestored();
			}
		}
	}

	private void onClick(View view) {
		startImageLoad();
	}

	private void startImageLoad() {
		final String search = mSearch.getText().toString().trim();
		if (!search.equals("")) {
			//DebugUtil.print(mPage);
			mFragmentPresenter.loadPhotos(search, mPage);
		} else {
			DialogUtil.getDialog(getString(R.string.dialog_header_warning),
					getString(R.string.dialog_empty_search_string),
					getContext());
		}
	}

	private void canSearch() {
		final TextWatcher watcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mButton.setEnabled(!mSearch.getText().toString().trim().equals(""));
				if (!mInitFragment) {
					mPage = 1;
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		};
		mSearch.addTextChangedListener(watcher);
	}

	@Override
	public ProgressDialog getDialog() {
		return mDialog;
	}

	@Override
	public RecyclerView getRecyclerView() {
		return mRecyclerView;
	}

	@Override
	public void setPage(int page) {
		mPage = page;
	}

	@Override
	public List<Photo> getList() {
		return mPhotosList;
	}

	@Override
	public void setList(List<Photo> list) {
		mPhotosList = new ArrayList<>(list);
	}

	@Override
	public void scrollEventUnlock() {
		mScrollEventLock = false;
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		if (mPhotosList != null && mPhotosList.size() > 0) {
			outState.putParcelableArrayList(LIST, mPhotosList);
		}
		outState.putInt(PAGE, mPage);

		final String search = mSearch.getText().toString().trim();
		if (!search.equals("")) {
			outState.putString(SEARCH_STRING, search);
		}
		super.onSaveInstanceState(outState);
	}

	private void bindRecyclerVieOnScrollEvent() {
		mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (!recyclerView.canScrollVertically(1)) {
					if (!mScrollEventLock) {
						mScrollEventLock = true;
						startImageLoad();
					}
				}
			}
		});
	}
}
