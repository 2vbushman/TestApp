package ru.taximaster.testapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.Map;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.utils.FragmentUtil;

final class MainPresenter {
	private final Map<Integer, String> FRAGMENTS;
	private final FragmentUtil FRAGMENT_UTIL;

	MainPresenter(MainModel model, AppCompatActivity activity) {
		this.FRAGMENTS = model.getFragmentList();
		FRAGMENT_UTIL = new FragmentUtil(activity);
	}

	void loadMainFragment() {
		Fragment fragment = FRAGMENT_UTIL.getFragmentClass(FRAGMENTS.get(R.id.main_fragment));
		if (fragment != null) FRAGMENT_UTIL.getFragment(fragment);
	}
}
