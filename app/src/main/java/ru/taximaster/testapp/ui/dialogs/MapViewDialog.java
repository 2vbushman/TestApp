package ru.taximaster.testapp.ui.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.data.Location;

public final class MapViewDialog extends ViewDialog {
	private Location mLocation;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map_view_dialog, container, false);

		if (mLocation != null) {
			SupportMapFragment fragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
			fragment.getMapAsync(this::onMapReady);
			rootView.findViewById(R.id.no_coordinates).setVisibility(View.GONE);
		} else {
			rootView.findViewById(R.id.no_coordinates).setVisibility(View.VISIBLE);
			rootView.findViewById(R.id.map).setVisibility(View.GONE);
		}
		rootView.findViewById(R.id.close).setOnClickListener(v -> dismiss());
		return rootView;
	}

	public void setLocation(Location location) {
		mLocation = location;
	}

	private void onMapReady(GoogleMap googleMap) {
		if (mLocation != null) {
			LatLng latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
			googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
			googleMap.addMarker(getMarkerOptions(latLng));
		}
	}

	private MarkerOptions getMarkerOptions(LatLng latLng){
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(latLng);
		markerOptions.title(mActivity.getString(R.string.dialog_current_location));
		markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		return markerOptions;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
	}

	@Override
	public void onPause() {
		SupportMapFragment fragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
		if (fragment != null) getFragmentManager().beginTransaction().remove(fragment).commit();
		super.onPause();
	}
}
