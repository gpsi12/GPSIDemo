package com.example.gps_i.gpsidemo;

import android.app.Application;

import com.example.feature_net.Mokgo;

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        Mokgo.init(this);
    }
}
