package com.surfm.gmaptest;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class GPSTracker implements LocationListener {

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

	private final Context mContext;

	// flag for GPS status
	private boolean isGPSEnabled = false;

	// flag for network status
	private boolean isNetworkEnabled = false;

	private Location location; // location
	private double latitude; // latitude
	private double longitude; // longitude

	// Declaring a Location Manager
	protected LocationManager locationManager;

	public GPSTracker(Context context) {
		this.mContext = context;
	}

	private void initFeasibility() {
		// getting GPS status
		isGPSEnabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// getting network status
		isNetworkEnabled = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}

	private boolean setupLocationManagerAndLocation(String PROVIDER) {
		locationManager.requestLocationUpdates(PROVIDER, MIN_TIME_BW_UPDATES,
				MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
		location = locationManager.getLastKnownLocation(PROVIDER);
		return location != null;
	}

	public void refreshLocation()  {
		locationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);
		initFeasibility();
		if (!isGPSEnabled && !isNetworkEnabled) {
			//throw new NoGpsProviderException();
		} else {
			if (isGPSEnabled) {
				if(!setupLocationManagerAndLocation(LocationManager.GPS_PROVIDER)){
					setupLocationManagerAndLocation(LocationManager.NETWORK_PROVIDER);
				}
			} else if (isNetworkEnabled) {
				setupLocationManagerAndLocation(LocationManager.NETWORK_PROVIDER);
			}
		}
		setupLocationValue(location);
	}

	/**
	 * Stop using GPS listener Calling this function will stop using GPS in your
	 * app
	 * */
	public void stopUsingGPS() {
		if (locationManager != null) {
			locationManager.removeUpdates(GPSTracker.this);
		}
	}

	/**
	 * Function to get latitude
	 * */
	public double getLatitude() {
		if (location != null) {
			latitude = location.getLatitude();
		}
		// return latitude
		return latitude;
	}

	/**
	 * Function to get longitude
	 * */
	public double getLongitude() {
		if (location != null) {
			longitude = location.getLongitude();
		}

		// return longitude
		return longitude;
	}

	/**
	 * Function to check GPS/wifi enabled
	 * 
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return isGPSEnabled || isNetworkEnabled;
	}

	private void setupLocationValue(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
	}

	@Override
	public void onLocationChanged(Location location) {
		setupLocationValue(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		try {
			refreshLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		try {
			refreshLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		try {
			refreshLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}