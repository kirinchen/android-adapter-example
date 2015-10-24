package com.surfm.adapterexample.powsqrt;

import com.surfm.adapterexample.R;
import com.surfm.adapterexample.R.id;
import com.surfm.adapterexample.R.layout;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class PowSqrtListActivity extends Activity implements OnClickListener,OnItemLongClickListener {
	
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
		listView.setOnItemLongClickListener(this);
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
		try {
			int inputN = Integer.parseInt( inputNumberText.getText().toString());
			adapter.add(inputN);
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		//adapter.deleteByIndex(position);
		DialogKit.showConfirmDialog(this, "¬O§_­n§R°£", android.R.string.ok, android.R.string.no, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				adapter.deleteByIndex(position);
				dialog.dismiss();
			}
		}, null);
		
		return false;
	}

}
