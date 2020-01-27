package com.example.feature_demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Demo模块
 */
public class DemoActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Log.i("GPSI","onCreate()-正在创建，此时Activity可见但不再前台");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("GPSI","onStart()-正在启动，此时Activity可见且在前台并开始活动");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("GPSI","onRestoreInstanceState()-在onStart()之后，将onSaveInstanceState()保存的Bundle取出进行恢复。在onCreate恢复前要判断是否有保存数据");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("GPSI","onPause()-正在停止,做数据存储、停止动画等操作");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("GPSI","onSaveInstanceState()-非人为终止Activity时，用来保存状态，方法调用在onStop之前，但是和onPsuse没有时序关系--人为退出时不会调用次方法");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("GPSI","onStop()-即将停止，可以做稍微重量级回收工作");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("GPSI","onDestroy()-即将销毁，通常用来做回收工作，资源释放");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("GPSI","onRestart()-后台切换到前台，不可见到可见-表示Activity重新启动");
    }
}
