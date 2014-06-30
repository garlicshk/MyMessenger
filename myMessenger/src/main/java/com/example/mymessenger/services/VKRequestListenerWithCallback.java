package com.example.mymessenger.services;

import android.util.Log;

import com.example.mymessenger.AsyncTaskCompleteListener;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKRequest.VKRequestListener;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

public abstract class VKRequestListenerWithCallback<T> extends VKRequestListener {
    AsyncTaskCompleteListener<T> callback;
    Vk vk;
    List<Object> params;

    public VKRequestListenerWithCallback(AsyncTaskCompleteListener<T> cb, Vk vk) {
        super();
        this.callback = cb;
        this.vk = vk;
    }

    @Override
    public void onComplete(VKResponse response) {
        Log.d("VKRequestListener", response.request.methodName +  " :: onComplete");
    }

    @Override
    public void onError(VKError error) {
        if(error.apiError != null) {
            Log.w("VKRequestListener", error.request.methodName +  " :: onError " + error.errorCode + ", " + error.errorMessage + ", " + error.errorReason + ", ApiError");
            vk.HandleApiError(error);
        } else {
            Log.w("VKRequestListener", error.request.methodName +  " :: onError " + error.errorCode + ", " + error.errorMessage + ", " + error.errorReason);
            if(error.errorCode == -105){
                error.request.repeat();
            }
        }
        // Ошибка. Сообщаем пользователю об error.
    }

    @Override
    public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
        Log.d("VKRequestListener", "attemptFailed" );
        // Неудачная попытка. В аргументах имеется номер попытки и общее их количество.
    }

    public void setParams(Object...objects){
        params = new ArrayList<Object>();
        for(Object o : objects)params.add(o);
    }
}
