package com.surfm.adapterexample.powsqrt;

import java.util.ArrayList;
import java.util.List;

import com.surfm.adapterexample.R;
import com.surfm.adapterexample.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PowSqrtAdapter extends BaseAdapter {

	private List<Integer> list = new ArrayList<Integer>();
	private int selectIndex = -1;
	private boolean editable = false;

	public void add(Integer i) {
		list.add(i);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) parent.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.powsqrtlist_item, null);
		}
		PowSqrtItem item = new PowSqrtItem(convertView);
		item.setData(list.get(position), editable);
		item.setSelectBackground(selectIndex == position);
		return convertView;
	}

	public void deleteByIndex(int position) {
		list.remove(position);
		notifyDataSetChanged();
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		notifyDataSetChanged();
	}

	public int getSelectIndex() {
		return selectIndex;
	}

	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
		notifyDataSetChanged();
	}

	public void moveSelect(int i) {
		Integer selectValue = list.get(selectIndex);
		list.remove(selectIndex);
		list.add(selectIndex + i, selectValue);
		selectIndex = selectIndex + i;
		notifyDataSetChanged();
	}

}
