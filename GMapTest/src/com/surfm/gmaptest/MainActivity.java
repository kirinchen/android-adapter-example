package com.surfm.gmaptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
		putAsyncMark(loc);

		map.setOnMapClickListener(this);

		map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

			@Override
			public View getInfoWindow(Marker arg0) {
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {

				LinearLayout info = new LinearLayout(MainActivity.this);
				info.setOrientation(LinearLayout.VERTICAL);

				TextView title = new TextView(MainActivity.this);
				title.setTextColor(Color.BLACK);
				title.setGravity(Gravity.CENTER);
				title.setTypeface(null, Typeface.BOLD);
				title.setText(marker.getTitle());

				TextView snippet = new TextView(MainActivity.this);
				snippet.setTextColor(Color.RED);
				snippet.setText(marker.getSnippet());

				info.addView(title);
				info.addView(snippet);

				return info;
			}
		});
	}

	private void putAsyncMark(LatLng loc) {
		new MyTask(loc).execute();
	}

	private void putMark(LatLng loc,String address) {

		mapFragment.getMap().addMarker(
				new MarkerOptions().title("HI").snippet(address).position(loc));

	}

	private class MyTask extends AsyncTask<Void, Void, String>{

		LatLng loc;
		
		public MyTask(LatLng loc) {
			this.loc = loc;
		}
		
		@Override
		protected String doInBackground(Void... params) {
			String address = null;
			try {
				List<Address> ad = GeoUtils.getStringFromLocation(loc.latitude,
						loc.longitude);
				address = ad.get(0).getAddressLine(0);
				LatLng geoLoc =GeoUtils.getLocationFromString("USA");
				address += "  loc="+geoLoc;
			} catch (Exception e) {
				address = e.toString();
			}
			return address;
		}
		
		@Override
		protected void onPostExecute(String result) {
			putMark(loc, result);
		}
		
	}
	
	
	

	@Override
	public void onMapClick(LatLng arg0) {
		putAsyncMark(arg0);
	}

	@Override
	public void onClick(View v) {
		mapFragment.getMap().clear();
	}

}
