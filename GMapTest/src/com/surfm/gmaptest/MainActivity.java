package com.surfm.gmaptest;

import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements OnMapReadyCallback,
		OnMapClickListener, OnClickListener {

	private Button clearBtton;
	private MapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		clearBtton = (Button) findViewById(R.id.clearBtn);
		clearBtton.setOnClickListener(this);
		mapFragment = (MapFragment) getFragmentManager().findFragmentById(
				R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		LatLng loc = new LatLng(24.137776, 120.608070);

		map.setMyLocationEnabled(true);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));
		putMark(loc);

		map.setOnMapClickListener(this);
	}

	private void putMark(LatLng loc) {
		mapFragment.getMap().addMarker(
				new MarkerOptions().title("HI").snippet(getAddress(loc))
						.position(loc));

	}

	public String getAddress(LatLng loc) {
		try {
			Geocoder geocoder = new Geocoder(this);
			List<Address> addresses = geocoder.getFromLocation(loc.latitude,
					loc.longitude, 1);
			String address = addresses.get(0).getAddressLine(0);
			String city = addresses.get(0).getAddressLine(1);
			String country = addresses.get(0).getAddressLine(2);
			return "address = " + address + ", city = " + city + ", country = "
					+ country;
		} catch (Exception e) {
			return e.toString();
		}
	}

	@Override
	public void onMapClick(LatLng arg0) {
		putMark(arg0);
	}

	@Override
	public void onClick(View v) {
		mapFragment.getMap().clear();
	}

}
