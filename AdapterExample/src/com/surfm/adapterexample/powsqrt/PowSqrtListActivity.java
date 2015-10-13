package com.surfm.adapterexample.powsqrt;

import com.surfm.adapterexample.R;
import com.surfm.adapterexample.R.id;
import com.surfm.adapterexample.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class PowSqrtListActivity extends Activity implements OnClickListener {
	
	private ListView listView;
	private PowSqrtAdapter adapter = new PowSqrtAdapter();
	private EditText inputNumberText;
	private Button addNumberButton;
	
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
		inputNumberText = (EditText) findViewById(R.id.inputNumber);
		addNumberButton = (Button) findViewById(R.id.addNumberButton);
		addNumberButton.setOnClickListener(this);
	}

	private void initAdapter() {
		for(int i=1;i<=30;i++){
			adapter.add(i);
		}
	}

	@Override
	public void onClick(View v) {
		int inputN = Integer.parseInt( inputNumberText.getText().toString());
		adapter.add(inputN);
		adapter.notifyDataSetChanged();
	}

}
