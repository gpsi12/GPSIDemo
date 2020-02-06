package com.example.feature_demo.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feature_demo.MyReceiver;
import com.example.feature_demo.R;

import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GoFragment extends GtFragment {

    private Button bt_insert_cp;
    private Button bt_threadpe;
    private Button bt_handler;

    private MyHandler myHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("GPSI_Fragment：", "当Fragment和Activity建立关联时调用");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("GPSI_Fragment：", "正在创建");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_go, null);
        bt_insert_cp = view.findViewById(R.id.bt_insert_cp);
        bt_threadpe = view.findViewById(R.id.bt_threadpe);
        bt_handler = view.findViewById(R.id.bt_handler);
        myHandler = new MyHandler(getActivity());
        Log.i("GPSI_Fragment：", "创建视图");
        inits();
        return view;
    }

    @Override
    protected void loadDataStart2() {
        super.loadDataStart2();
        Log.i("GPSI_Fragment", "页面--");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("GPSI_Fragment：", "与关联的Activity完成onCreate()之后调用");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("GPSI_Fragment：", "正在启动");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("GPSI_Fragment：", "获得焦点");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("GPSI_Fragment：", "正在停止");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("GPSI_Fragment：", "即将停止");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("GPSI_Fragment：", "在Fragment中的布局被移除时");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("GPSI_Fragment：", "即将销毁");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("GPSI_Fragment：", "Fragment和Activity接触关联");
    }

    public void inits() {
        bt_insert_cp.setOnClickListener(this);
        bt_threadpe.setOnClickListener(this);
        bt_handler.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.bt_insert_cp) {
            insertTest();
        }
        if (view.getId() == R.id.bt_threadpe) {
            for (int i = 0; i < 30; i++) {
                final int finali = i;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.i("GPSI", "Thread:" + finali);
                        Log.i("GPSI", "线程：" + Thread.currentThread().getName());
                    }
                };
                threadPoolExecutor.execute(runnable);
            }
        }
        if (view.getId() == R.id.bt_handler) {
            initstart();
        }
    }

    /**
     *
     */
    public void insertTest() {
        ContentResolver resolver = getContext().getContentResolver();
        Uri uri = Uri.parse("content://com.gpsidemo.person/insert");
        ContentValues values = new ContentValues();
        values.put("name", "葛太师");
        values.put("age", "20");
        values.put("phone", "15236633096");
        values.put("address", "河南");

        Uri insert = resolver.insert(uri, values);
        Log.i("GPSI", insert.toString());
        long id = ContentUris.parseId(insert);
        Log.i("GPSI", "插入数据产生的id+" + id);
    }

    final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5
            , 7, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(100));

    /**
     * 定义Handler子类，设置为静态内部类防制内存泄漏
     * 同时，还可以使用WeakReference弱引用持有Activity实例
     * 原因：弱引用的对象拥有短暂的生命周期，在垃圾回收器线程扫描时，一旦发现了只具有弱引用的对象
     * 不管当前内存空间是否足够，都会回收它的内存
     * */
    private static class MyHandler extends Handler{
        //定义弱引用实例
        private WeakReference<Activity> weakReference;
        //在构造方法中传入需持有的Activity实例
        public MyHandler(Activity activity){
            //使用WeakReference弱引用持有Activity实例
            weakReference = new WeakReference<Activity>(activity); }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.i("GPSI", "接收到GDemoloadThread的发送的消息：" + msg.arg1);
                    break;
                default:
                    break;
            }
        }
    }

    class GDemoloadThread extends Thread {

        @Override
        public void run() {
            super.run();
            Message message = new Message();    //实例化消息对象
            message.what = 1;   //  消息标示
            message.arg1 = 1214;   //
            myHandler.sendMessage(message);
            myHandler.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }

    private void initstart() {
        GDemoloadThread gDemoloadThread = new GDemoloadThread();
        gDemoloadThread.start();
    }



}
