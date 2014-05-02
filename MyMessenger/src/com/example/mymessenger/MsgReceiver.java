package com.example.mymessenger;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MsgReceiver extends BroadcastReceiver {
	public static final String ACTION_RECEIVE = "android.mymessenger.MSG_RECEIVED";
	public static final String ACTION_UPDATE = "android.mymessenger.MSG_UPDATED";

	@Override
	public void onReceive(Context context, Intent intent) {
		int service_type = intent.getIntExtra("service_type", 0);
		mMessage msg = (mMessage) intent.getParcelableExtra("msg");
		MyApplication app = (MyApplication) context.getApplicationContext();
		
		if(intent.getAction().equals(ACTION_RECEIVE)){
			Toast.makeText(context, "New MSG! " + app.getService(service_type).getServiceName() + " " + msg.text, Toast.LENGTH_LONG).show();
		}

		List<mMessage> msgs = new ArrayList<mMessage>();
		msgs.add(msg);
		app.triggerMsgsUpdaters(msgs);
		
		//if(app.getCurrentActivity() != null && app.getCurrentActivity().getClass() == ActivityTwo.class){
		
	}

}
