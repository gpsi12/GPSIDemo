package com.example.feature_common;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 类描述：透明状态栏的Activity
 * * Created by Gpsi on 2020-03-25.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    /**
     * 利用反射获取状态栏高度
     * @param rootView 设定标题栏的Padding值
     */
    public void setStatusBarHeight(View rootView) {
        if (Build.VERSION.SDK_INT >= 23){
            int result = 0;
            //获取状态栏高度的资源id
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
//        ViewGroup rootView = getWindow().getDecorView().findViewById(R.id.activity_main_app);
            rootView.setPadding(0, result, 0, 0);
//        return result;
        }
    }
}
