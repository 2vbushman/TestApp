package ru.taximaster.testapp.ui.dialogs;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import ru.taximaster.testapp.FlickrApplication;
import ru.taximaster.testapp.R;

public final class InfoDialog extends DialogFragment {
	private int mMessageRecourse = -1;
	private int mTitleRecourse = -1;
	private CheckBox mDoNotShowAgain;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.info_dialog, container, false);
		prepareTitle();

		setDialogElements(rootView);
		return rootView;
	}

	private void prepareTitle() {
		Dialog dialog = getDialog();
		if (mTitleRecourse != -1 && dialog != null) {
			dialog.setTitle(mTitleRecourse);
		}
	}

	public void setMessage(@StringRes int messageRecourse) {
		mMessageRecourse = messageRecourse;
	}

	public void setTitle(@StringRes int titleRecourse) {
		mTitleRecourse = titleRecourse;
	}

	public void setDialogElements(View rootView) {
		if (rootView != null) {
			TextView message = rootView.findViewById(R.id.info_text);
			message.setText(mMessageRecourse);
			mDoNotShowAgain = rootView.findViewById(R.id.not_show_again);
			rootView.findViewById(R.id.close).setOnClickListener(v -> dismiss());
		}
	}

	@Override
	public void onPause() {
		if (mDoNotShowAgain.isChecked()) writePrefs();
		super.onPause();
	}

	private void writePrefs() {
		SharedPreferences.Editor editor
				= ((FlickrApplication) getActivity().getApplicationContext()).getPreferences().edit();

		editor.putBoolean(FlickrApplication.PreferencesKeys.INFO.val, true);
		editor.apply();
	}
}
