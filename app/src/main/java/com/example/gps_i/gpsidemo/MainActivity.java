package com.example.gps_i.gpsidemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.feature_chat.ChatActivity;
import com.example.feature_demo.BaseActivity;
import com.example.feature_demo.DemoActivity;
import com.example.feature_list.RecyclerViewDemoActivity;
import com.example.feature_login.LoginActivity;

/**
 * 主模块
 */
public class MainActivity extends BaseActivity {

    private Button bt_001;
    private Button bt_002;
    private Button bt_003;
    private Button bt_004;

    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        //StatusBarUtil.setGradientColor(this, mToolbar);
        //状态导航栏透明
//        setNavigationStatusColor(Color.TRANSPARENT);
//        setStatusBarHeight();
        setStatusBarHeight(findViewById(R.id.activity_main_app));

    }

    public void openLogin(View view) {
        LoginActivity.newIsntance(this);
    }

    /**
     * 设置状态栏背景颜色
     * 透明：Color.TRANSPARENT
     *
     * @param color
     */
    public void setNavigationStatusColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setNavigationBarColor(color);
            getWindow().setStatusBarColor(color);
        }
    }

    //反射获取状态栏高度，设置padding
    public void gsetStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
//        ViewGroup rootView = getWindow().getDecorView().findViewById(R.id.activity_main_app);
//        rootView.setPadding(0, result, 0, 0);

    }
}
