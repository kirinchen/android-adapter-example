package com.surfm.adapterexample;

import com.surfm.adapterexample.powsqrt.PowSqrtListActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button goPowSqrtButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		injectView();
	}

	private void injectView() {
		goPowSqrtButton = (Button) findViewById(R.id.goToPowSqrt);
		goPowSqrtButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		i.setClass(this, PowSqrtListActivity.class);
		startActivity(i);
	}



}
