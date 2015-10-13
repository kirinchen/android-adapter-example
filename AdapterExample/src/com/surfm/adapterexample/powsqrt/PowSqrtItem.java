package com.surfm.adapterexample.powsqrt;

import java.text.DecimalFormat;

import com.surfm.adapterexample.R;

import android.view.View;
import android.widget.TextView;

public class PowSqrtItem {
	
	private static final   DecimalFormat SQRT_NUMBER_FORMAT = new DecimalFormat("##.####");
	
	private View view;
	
	private TextView numberView;
	private TextView powView;
	private TextView sqrtView;
	
	PowSqrtItem(View view){
		this.view = view;
		injectViews();
	}

	private void injectViews() {
		numberView = (TextView) view.findViewById(R.id.number);
		powView = (TextView) view.findViewById(R.id.pow);
		sqrtView = (TextView) view.findViewById(R.id.sqrt);
	}
	
	void setData(Integer n){
		double pow = Math.pow(n, 2);
		double sqrt = Math.sqrt(n);
		numberView.setText(n+"");
		powView.setText(pow+"");
		sqrtView.setText(SQRT_NUMBER_FORMAT.format(sqrt));
	}

}
