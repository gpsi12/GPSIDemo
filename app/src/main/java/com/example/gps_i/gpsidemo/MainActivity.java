package com.example.gps_i.gpsidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.feature_chat.ChatActivity;
import com.example.feature_demo.DemoActivity;
import com.example.feature_list.RecyclerViewDemoActivity;
import com.example.feature_login.LoginActivity;

/**
 * 主模块
 */
public class MainActivity extends Activity {

    private Button bt_001;
    private Button bt_002;
    private Button bt_003;
    private Button bt_004;

    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_001 = findViewById(R.id.bt_001);
        bt_001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DemoActivity.class);
                startActivity(intent);
//                finish();
            }
        });
        bt_002 = findViewById(R.id.bt_002);
        bt_002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewDemoActivity.class);
                startActivity(intent);
            }
        });
        bt_003 = findViewById(R.id.bt_003);
        bt_003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        bt_003 = findViewById(R.id.bt_004);
        bt_003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openLogin(View view) {
        LoginActivity.newIsntance(this);
    }
}
