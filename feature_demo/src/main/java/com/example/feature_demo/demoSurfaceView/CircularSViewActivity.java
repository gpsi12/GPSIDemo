package com.example.feature_demo.demoSurfaceView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

public class CircularSViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去Activity标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置布局
        this.setContentView(new MyCircularSurfaceView(this));

    }


}
