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

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

public class GeoUtils {

	public static List<Address> getStringFromLocation(double lat, double lng)
			throws ClientProtocolException, IOException, JSONException {

		String address = String
				.format(Locale.TAIWAN,
						"http://maps.googleapis.com/maps/api/geocode/json?latlng=%1$f,%2$f&sensor=true&language=zh_tw",
						lat, lng);
		HttpGet httpGet = new HttpGet(address);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();

		List<Address> retList = null;

		response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream stream = entity.getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(stream,
				"UTF8"));
		String str;
		while ((str = in.readLine()) != null) {
			stringBuilder.append(str);
		}

		JSONObject jsonObject = new JSONObject(stringBuilder.toString());

		retList = new ArrayList<Address>();

		if ("OK".equalsIgnoreCase(jsonObject.getString("status"))) {
			JSONArray results = jsonObject.getJSONArray("results");
			for (int i = 0; i < results.length(); i++) {
				JSONObject result = results.getJSONObject(i);
				String indiStr = result.getString("formatted_address");
				Address addr = new Address(Locale.getDefault());
				addr.setAddressLine(0, indiStr);
				retList.add(addr);
			}
		}

		return retList;
	}

	public static LatLng getLocationFromString(String address)
			throws JSONException, UnsupportedEncodingException {

		HttpGet httpGet = new HttpGet(
				"http://maps.google.com/maps/api/geocode/json?address="
						+ URLEncoder.encode(address, "UTF-8")
						+ "&ka&sensor=false");
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}

		JSONObject jsonObject = new JSONObject(stringBuilder.toString());

		double lng = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
				.getJSONObject("geometry").getJSONObject("location")
				.getDouble("lng");

		double lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
				.getJSONObject("geometry").getJSONObject("location")
				.getDouble("lat");

		return new LatLng(lat, lng);
	}

}
