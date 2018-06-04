package ru.taximaster.testapp.utils;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;

public final class RotationUtil {
	public static void rotationLock(AppCompatActivity activity) {
		if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}

	public static void rotationUnLock(AppCompatActivity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
	}
}
