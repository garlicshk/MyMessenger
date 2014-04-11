package com.example.mymessenger;

import java.util.ArrayList;
import java.util.List;
import com.example.mymessenger.services.MessageService;
import com.example.mymessenger.services.Sms;
import com.example.mymessenger.services.Vk;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.HandlerThread;

public class MyApplication extends Application {
	public List<MessageService> myMsgServices;
	public int active_service;
	PendingIntent pi;
	private SharedPreferences sPref;
	
	@Override
	public void onCreate() {
		super.onCreate();

		myMsgServices = new ArrayList<MessageService>();
		
		sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String using_services[] = sPref.getString("usingservices", "sms,vk").split(",");

        for(String i : using_services){        	
        	if(i.equals("sms"))
        		addMsgService(new Sms(this));
        	if(i.equals("vk"))
        		addMsgService(new Vk(this));
        }
        
        Intent intent1 = new Intent(this, UpdateService.class);

		startService(intent1);

	}
	
	public void addMsgService(MessageService mServive){
		myMsgServices.add(mServive);
	}

	public MessageService getService(int typeId) {
		for(MessageService ms : myMsgServices){
			if (ms.getServiceType() == typeId ) return ms;
		}
		return null;
	}

	public MessageService getActiveService() {
		return getService(active_service);
	}

	public boolean isServisesLoaded() {
		return myMsgServices.size() > 0;
	}
	
	private Activity mCurrentActivity = null;
	
    public Activity getCurrentActivity(){
          return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
          this.mCurrentActivity = mCurrentActivity;
    }

	public void requestLastDialogs(int offset, int count,
			AsyncTaskCompleteListener<List<mDialog>> cb) {
		for(MessageService msg : myMsgServices){
			msg.requestDialogs(offset, count, cb);
		}
	}

	public void setActiveService(int msgService) {
		active_service = msgService;
	}

}
