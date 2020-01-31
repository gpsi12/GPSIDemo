package com.example.feature_demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

/**
 * 单开进程的一个Activity
 */
public class AloneActivity extends Activity {

    private Button abt_alone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alone);
        abt_alone = findViewById(R.id.abt_alone);
        abt_alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.gps.ALONEDEMO");
//                LocalBroadcastManager.getInstance(AloneActivity.this).sendBroadcast(intent);
                sendBroadcast(intent);
            }
        });
    }
}
