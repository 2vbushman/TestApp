package ru.taximaster.testapp.ui.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

import ru.taximaster.testapp.utils.RotationUtil;

public class ViewDialog extends DialogFragment {
	protected FragmentActivity mActivity;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public void onResume() {
		RotationUtil.rotationLock((AppCompatActivity) Objects.requireNonNull(getActivity()));
		super.onResume();
	}

	@Override
	public void onPause() {
		RotationUtil.rotationUnLock((AppCompatActivity) Objects.requireNonNull(getActivity()));
		super.onPause();
	}
}
