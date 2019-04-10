package com.example.gps_i.gpsidemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

public class DemoActivity extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState,  PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_demo);
    }
}
