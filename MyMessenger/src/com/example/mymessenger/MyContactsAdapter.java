package com.example.mymessenger;

import java.util.List;

import com.example.mymessenger.services.MessageService;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyContactsAdapter extends BaseAdapter {
	LayoutInflater lInflater;
	List<mContact> data;
	Context context;
	
	MyContactsAdapter(Context context, List<mContact> showing_contacts) {
	    lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    data = showing_contacts;
	    this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//View view = convertView; // ������������ �������� View, ������ ��� ������ � �������
		View view = null;
		
	    if (view == null) {
	      view = lInflater.inflate(R.layout.contactlist_row_layout, parent, false);
	    }
	    
	    mContact cnt = data.get(position);
	    
	    //boolean left = msg.sender == ((MyApplication) context.getApplicationContext()).getService( MessageService.SMS ).getMyName();
	    
    	TextView textLabel = (TextView) view.findViewById(R.id.cntview_cntname);
    	textLabel.setText( cnt.getName() );
    	
    	if(cnt.icon_100 != null){
	    	ImageView iv = (ImageView) view.findViewById(R.id.cntview_iconmain);
	    	iv.setImageBitmap( cnt.icon_100 );
    	}

    	//Log.d("MyDialogsAdapter", data.size() + " : " + position + " : " + dlg.getParticipantsNames());
		return view;
	}

}
