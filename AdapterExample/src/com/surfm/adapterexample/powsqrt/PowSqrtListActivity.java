package com.surfm.adapterexample.powsqrt;

import com.surfm.adapterexample.R;
import com.surfm.adapterexample.R.id;
import com.surfm.adapterexample.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PowSqrtListActivity extends Activity {
	
	private ListView listView;
	private PowSqrtAdapter adapter = new PowSqrtAdapter();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.powsqrtlist_activity);
		initUi();
	}

	private void initUi() {
		listView = (ListView) findViewById(R.id.list);
		initAdapter();
		listView.setAdapter(adapter);
	}

	private void initAdapter() {
		for(int i=1;i<=30;i++){
			adapter.add(i);
		}
	}

}
