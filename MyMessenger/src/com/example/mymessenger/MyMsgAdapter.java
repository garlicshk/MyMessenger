package com.example.mymessenger;

import java.util.List;

import com.example.mymessenger.services.MessageService;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyMsgAdapter extends BaseAdapter {
	LayoutInflater lInflater;
	List<mMessage> data;
	Context context;
	
	public MyMsgAdapter(Context context, List<mMessage> msgs) {
	    lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    data = msgs;
	    this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
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
	    	view = lInflater.inflate(R.layout.list_row_layout, parent, false);
	    }
	    
	    mMessage msg = data.get(position);
	    MessageService ser = ((MyApplication) context.getApplicationContext()).getActiveService();
	    boolean left = msg.getFlag(mMessage.OUT);
	    
	    RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.msg_container);
	    
	    if(!msg.getFlag(mMessage.READED))rl.setBackgroundColor(context.getResources().getColor(R.color.msg_notreaded));
	    
    	//TextView textLabel = (TextView) view.findViewById(R.id.author_text);
    	//if(left)textLabel.setText( ser.getMyContact().getName() );
    	//else textLabel.setText( msg.respondent.getName() );
	    TextView textLabel_date = (TextView) view.findViewById(R.id.msg_date);
	    WrapWidthTextView textLabel_text = (WrapWidthTextView) view.findViewById(R.id.msg_text);
	    
	    
	    textLabel_date.setText( msg.sendTime.format("%H:%M\n%d.%m.%Y") );
	    textLabel_date.setGravity(left ? Gravity.LEFT : Gravity.RIGHT);
	    
	    RelativeLayout.LayoutParams lp_date = (RelativeLayout.LayoutParams) textLabel_date.getLayoutParams();
	    //lp_date.addRule(left ? RelativeLayout.ALIGN_PARENT_RIGHT : RelativeLayout.ALIGN_PARENT_LEFT);
	    lp_date.addRule(left ? RelativeLayout.RIGHT_OF : RelativeLayout.LEFT_OF, textLabel_text.getId());
	    textLabel_date.setLayoutParams(lp_date);
	    
	    
	    textLabel_text.setText( ChatMessageFormatter.getSmiledText(context, msg.text, textLabel_text.getLineHeight()) );
    	
	    textLabel_text.setBackgroundResource(left ? R.drawable.bubble_green : R.drawable.bubble_yellow);
	    textLabel_text.setGravity(left ? Gravity.RIGHT : Gravity.LEFT);
    	    	    	
    	RelativeLayout.LayoutParams lp_text = (RelativeLayout.LayoutParams) textLabel_text.getLayoutParams();
    	lp_text.addRule(left ? RelativeLayout.ALIGN_PARENT_LEFT : RelativeLayout.ALIGN_PARENT_RIGHT);
    	//lp_text.addRule(left ? RelativeLayout.LEFT_OF : RelativeLayout.RIGHT_OF, textLabel_date.getId());

    	textLabel_text.setLayoutParams(lp_text);
    	
    	DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    	textLabel_date.measure(metrics.widthPixels, metrics.heightPixels);
    	//textLabel_text.setMaxWidth((int) convertPixelsToDp( metrics.widthPixels - textLabel_date.getMeasuredWidth() ));
    	textLabel_text.setWidth(metrics.widthPixels - textLabel_date.getMeasuredWidth() - 20);
    	//textLabel_text.setCustomMax(metrics.widthPixels - textLabel_date.getMeasuredWidth() - 20);
    	
    	
    	//Log.d("MyAdapter", data.size() + " : " + position + " : " + msg.text);
	
	
		return view;
	}
	
	public float convertPixelsToDp(float px){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;
	}
	
	public float convertDpToPixel(float dp){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}

}
