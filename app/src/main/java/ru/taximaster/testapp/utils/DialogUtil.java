package ru.taximaster.testapp.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import ru.taximaster.testapp.R;

public final class DialogUtil {
	public static void getDialog(String title, String message, Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNegativeButton(R.string.dialog_close_button, null);
		builder.show();
	}
}
