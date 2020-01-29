package com.example.feature_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class DomeService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();
        lLog("onCreate()-第一次被创建时调用");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        lLog("onStartCommand()-服务启动时调用");
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        lLog("onBind()-绑定时调用");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        lLog("onUnbind()-接触绑定时调用");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lLog("onDestroy()-停止时调用");
    }

    public void lLog(String s){
        Log.i("GPSI_Service-",s);
    }

}
