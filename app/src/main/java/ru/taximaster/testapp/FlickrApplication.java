package ru.taximaster.testapp;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class FlickrApplication extends Application {
	private AppCompatActivity mActivity;
	private SharedPreferences mPreferences;
	private static final String PREF_FILE_NAME = "prefs";

	public enum PreferencesKeys {
		INFO("info"),
		OTHER("other");
		public final String val;

		PreferencesKeys(final String v) {
			this.val = v;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initSharedPreferences();
	}

	public AppCompatActivity getActivity() {
		return mActivity;
	}

	public void setActivity(AppCompatActivity activity) {
		mActivity = activity;
	}

	private void initSharedPreferences() {
		if (mPreferences == null) {
			mPreferences = getSharedPreferences(PREF_FILE_NAME, Activity.MODE_PRIVATE);
		}
	}

	public SharedPreferences getPreferences() {
		return mPreferences;
	}
}
