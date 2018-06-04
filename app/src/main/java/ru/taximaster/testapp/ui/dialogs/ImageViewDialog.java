package ru.taximaster.testapp.ui.dialogs;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.ui.views.TouchImageView;
import ru.taximaster.testapp.utils.ImageUtil;

public final class ImageViewDialog extends ViewDialog {
	private String mImageUrl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.image_view_dialog, container, false);
		prepareDialogImage(rootView);
		return rootView;
	}

	public void setImageUrl(String imageUrl) {
		mImageUrl = imageUrl;
	}

	private void prepareDialogImage(View dialogView) {
		if (dialogView != null) {
			TouchImageView view = dialogView.findViewById(R.id.photo);
			view.setScaleType(ImageView.ScaleType.MATRIX);
			view.setMaxZoom(4f);
			view.setMinZoom(1f);
			loadImage(view);

			dialogView.findViewById(R.id.close).setOnClickListener(v -> dismiss());
		}
	}

	private void loadImage(TouchImageView view) {
		ImageUtil.loadImageBitmap(mImageUrl, view, mActivity, getDialogMinSize());
	}

	private int getDialogMinSize() {
		Dialog dialog = getDialog();
		if (dialog != null) {
			Window window = getDialog().getWindow();
			if (window != null) {
				Display display = mActivity.getWindowManager().getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				int width = size.x;
				int height = size.y;
				return Math.min(width, height);
			}
		}
		return 0;
	}
}
