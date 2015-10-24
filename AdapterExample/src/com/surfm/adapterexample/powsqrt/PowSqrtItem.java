package com.surfm.adapterexample.powsqrt;

import java.text.DecimalFormat;

import com.surfm.adapterexample.R;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PowSqrtItem {
	
	private static final   DecimalFormat SQRT_NUMBER_FORMAT = new DecimalFormat("##.####");
	
	private View view;
	
	private TextView numberView;
	private EditText numberEdit;
	private TextView powView;
	private TextView sqrtView;
	
	PowSqrtItem(View view){
		this.view = view;
		injectViews();
	}

	private void injectViews() {
		numberEdit = (EditText) view.findViewById(R.id.numberEdit);
		numberView = (TextView) view.findViewById(R.id.number);
		powView = (TextView) view.findViewById(R.id.pow);
		sqrtView = (TextView) view.findViewById(R.id.sqrt);
	}
	
	void setData(Integer n, boolean editable){
		double pow = Math.pow(n, 2);
		double sqrt = Math.sqrt(n);
		numberView.setText(n+"");
		numberEdit.setText(n+"");
		powView.setText(pow+"");
		sqrtView.setText(SQRT_NUMBER_FORMAT.format(sqrt));
		setupEditMode(editable);
	}
	
	void setSelectBackground(boolean select){
		int colorI =0;
		if(select){
			colorI = Color.GREEN;
		}else{
			colorI = Color.BLACK;
		}
		view.setBackgroundColor(colorI);
	}
	
	private void setupEditMode(boolean editable){		
		if(editable){
			numberView.setVisibility(View.GONE);
			numberEdit.setVisibility(View.VISIBLE);
		}else{
			numberView.setVisibility(View.VISIBLE);
			numberEdit.setVisibility(View.GONE);
		}
	}

}
