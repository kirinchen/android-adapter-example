package com.surfm.adapterexample.powsqrt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.widget.TextView;




public class DialogKit {

	static class NegativeClickListener implements OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
	}
	
	static class PositiveClickListener implements OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	}
	
	public static Dialog showConfirmDialog(Context context, String msg,int positiveId,int negativeId,
			OnClickListener positiveListener, OnClickListener negativeListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if (negativeListener == null) {
			negativeListener = new NegativeClickListener();
		}
		if(positiveListener == null){
			positiveListener = new PositiveClickListener();
		}
		builder.setMessage(msg);
		builder.setPositiveButton(positiveId, positiveListener);
		builder.setNegativeButton(negativeId, negativeListener);
		
		
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
        dialog.show();

		TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
		messageView.setGravity(Gravity.LEFT);
		
		return dialog;
	}
	
}
