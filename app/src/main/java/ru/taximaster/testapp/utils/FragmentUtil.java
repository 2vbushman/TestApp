package ru.taximaster.testapp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Method;

import ru.taximaster.testapp.R;

public final class FragmentUtil {
	private final AppCompatActivity ACTIVITY;

	public FragmentUtil(AppCompatActivity activity) {
		ACTIVITY = activity;
	}

	public void getFragment(Fragment fragment) {
		if (fragment != null) {
			FragmentManager fragmentManager = ACTIVITY.getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.content_frame, fragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}

	@SuppressWarnings(value = "unchecked")
	public Fragment getFragmentClass(String className) {
		Class<Fragment> fragmentClass;
		Method method;
		Fragment fragment = null;
		try {
			fragmentClass = (Class<Fragment>) Class.forName(className);
			method = fragmentClass.getMethod("newInstance");
			fragment = (Fragment) method.invoke(null);

		} catch (Exception ignored) {
		}
		return fragment;
	}
}
