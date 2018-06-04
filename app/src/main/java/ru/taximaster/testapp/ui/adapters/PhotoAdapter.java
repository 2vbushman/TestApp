package ru.taximaster.testapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.taximaster.testapp.FlickrApplication;
import ru.taximaster.testapp.R;
import ru.taximaster.testapp.data.Photo;
import ru.taximaster.testapp.loaders.LocationLoader;
import ru.taximaster.testapp.ui.dialogs.ImageViewDialog;
import ru.taximaster.testapp.ui.dialogs.MapViewDialog;
import ru.taximaster.testapp.utils.ImageUtil;
import ru.taximaster.testapp.utils.MenuUtil;

public final class PhotoAdapter
		extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
	private List<Photo> mPhotos;
	private final LayoutInflater INFLATER;
	private final Context CONTEXT;
	private final LocationLoader LOCATION_LOADER;

	@SuppressWarnings("unused")
	public PhotoAdapter(Context context) {
		CONTEXT = context;
		INFLATER = LayoutInflater.from(context);
		LOCATION_LOADER = new LocationLoader();
	}

	public PhotoAdapter(List<Photo> photos, Context context) {
		mPhotos = photos;
		CONTEXT = context;
		INFLATER = LayoutInflater.from(context);
		LOCATION_LOADER = new LocationLoader();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		private final ImageButton PHOTO;
		private final ImageView COORDINATES;
		private final TextView TITLE;

		ViewHolder(View itemView) {
			super(itemView);
			PHOTO = itemView.findViewById(R.id.inv_photo);
			COORDINATES = itemView.findViewById(R.id.coordinates);
			TITLE = itemView.findViewById(R.id.photo_title);
		}
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = INFLATER.inflate(R.layout.photos_list_item, parent, false);
		return new PhotoAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		final Photo photo = mPhotos.get(position);
		if (photo != null) {
			int size = (int) (CONTEXT.getResources().getDimension(R.dimen.image_size));
			ImageUtil.loadImageBitmap(photo.getImageUrl(), holder.PHOTO, CONTEXT, size);
			holder.TITLE.setText(photo.getTitle());

			holder.COORDINATES.setImageResource(R.drawable.ic_no_coordinates);
			loadLocation(holder.COORDINATES, photo);


			holder.PHOTO.setOnLongClickListener(view -> {
				showPopupMenu(view, photo);
				return false;
			});

			holder.PHOTO.setOnClickListener(v -> showImageViewDialog(photo.getImageUrl()));
		}
	}

	private void loadLocation(ImageView view, Photo photo) {
		LOCATION_LOADER.loadLocation(view, photo);
	}

	private void showPopupMenu(View v, Photo photo) {
		final PopupMenu popupMenu = new PopupMenu(CONTEXT, v);
		popupMenu.inflate(R.menu.photo_menu);
		MenuUtil.showContextMenuIcons(popupMenu);

		popupMenu.setOnMenuItemClickListener(item -> {
			int id = item.getItemId();
			switch (id) {
				case R.id.menu_show_photo:
					showImageViewDialog(photo.getImageUrl());
					break;
				case R.id.menu_show_map:
					showImageMapDialog(photo);
					break;
			}
			return false;
		});
		popupMenu.show();
	}

	private void showImageMapDialog(Photo photo) {
		MapViewDialog dialog = new MapViewDialog();
		dialog.setLocation(photo.getLocation());

		AppCompatActivity activity = getActivity();
		if (activity != null) {
			dialog.show(activity.getSupportFragmentManager(), null);
		}
	}

	private void showImageViewDialog(String imageUrl) {
		ImageViewDialog dialog = new ImageViewDialog();
		dialog.setImageUrl(imageUrl);

		AppCompatActivity activity = getActivity();
		if (activity != null) {
			dialog.show(activity.getSupportFragmentManager(), null);
		}
	}

	private AppCompatActivity getActivity() {
		if (CONTEXT.getApplicationContext() instanceof FlickrApplication) {
			FlickrApplication application = (FlickrApplication) CONTEXT.getApplicationContext();
			if (application != null) {
				return application.getActivity();
			}
		}
		return null;
	}

	@Override
	public int getItemCount() {
		return mPhotos.size();
	}


	public void setModel(List<Photo> model) {
		mPhotos = new ArrayList<>(model);
	}

	public List<Photo> getModel() {
		return mPhotos;
	}
}
