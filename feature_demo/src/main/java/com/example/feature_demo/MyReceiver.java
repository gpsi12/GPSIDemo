package com.example.feature_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 广播接收者
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.SCREEN_ON")){
            Log.i("GPSI","点亮屏幕");
        }if (action.equals("android.intent.action.SCREEN_OFF")){
            Log.i("GPSI","关闭屏幕");
        }if (action.equals("com.gpsidemo.MY_BROADCAST_BD")){
            Log.i("GPSI","接收到自定义广播。");
        }

    }
}
