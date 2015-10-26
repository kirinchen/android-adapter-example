package com.surfm.adapterexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleListViewActivity extends Activity {

	private ListView listView;
	private SimpleAdapter simpleAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_listview_activity);
		injectView();
		initAdapter();
	}

	private void injectView() {
		listView = (ListView) findViewById(R.id.listView);
	}

	private void initAdapter() {
		String[] strs = { "¦Ñ¤ý", "¤p¤ý", "¹j¾À¦Ñ¤ý" };
		int[] images = { R.drawable.wang1, R.drawable.wang2, R.drawable.wang3 };
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < strs.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("avatar", images[i]);
			map.put("name", strs[i]);
			data.add(map);
		}

		simpleAdapter = new SimpleAdapter(this, data,
				R.layout.simple_list_view_adapter, new String[] { "name","avatar" },
				new int[] { R.id.itemTitle, R.id.itemAvatar });
		listView.setAdapter(simpleAdapter);
	}

}
