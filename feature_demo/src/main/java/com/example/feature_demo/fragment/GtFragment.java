package com.example.feature_demo.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feature_demo.AloneActivity;
import com.example.feature_demo.MyReceiver;
import com.example.feature_demo.ProviderPNumberActivity;
import com.example.feature_demo.R;
import com.example.feature_demo.service.DomeService;

public class GtFragment extends Fragment implements View.OnClickListener {

    private boolean mLoadData;
    private boolean isFirstStart;
    private MyReceiver myReceiver;

    private Button sbt_starts,sbt_binds,sbt_stops,sbt_unbind,bt_OPrecess,bt_bdgb,bt_hqlxr;

    private String TAG = "GPSI";

    private DomeService.DomeBinder mBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"onServiceConnected:"+name.toString());
            mBinder = (DomeService.DomeBinder) service;
            mBinder.doTask();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //进程被不正常kill掉后调用
            Log.i(TAG,"onServiceDisconnected:"+name.toString());
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("GPSI_Fragment：", "页面2-当Fragment和Activity建立关联时调用");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("GPSI_Fragment：", "页面2-正在创建");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gt, null);
        Log.i("GPSI_Fragment：", "页面2-创建视图");
//        Intent intent = new Intent(getActivity(),DomeService.class);
//        localBroadcastManager = new MyReceiver();
//        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        //动态注册俄本地广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.gpsidemo.MY_BROADCAST_BD");//自定义广播
        intentFilter.addAction("com.example.gps.ALONEDEMO");//单开启进程的
        intentFilter.addAction("android.intent.action.SCREEN_OFF");//关屏幕
        intentFilter.addAction("android.intent.action.SCREEN_ON");//点亮屏幕
        myReceiver = new MyReceiver();
        getActivity().registerReceiver(myReceiver, intentFilter);
//        localBroadcastManager.registerReceiver(myReceiver,intentFilter);

        sbt_starts = view.findViewById(R.id.sbt_starts);
        sbt_binds = view.findViewById(R.id.sbt_binds);
        sbt_stops = view.findViewById(R.id.sbt_stops);
        sbt_unbind = view.findViewById(R.id.sbt_unbind);
        bt_bdgb = view.findViewById(R.id.bt_bdgb);
        bt_OPrecess = view.findViewById(R.id.bt_OPrecess);
        bt_hqlxr = view.findViewById(R.id.bt_hqlxr);
        init();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mLoadData = isVisibleToUser;
        if (isFirstStart) {
            loadDataStart();
        }
        Log.i("GPSI_Fragment", "页面2-是否可见--" + isVisibleToUser);
    }

    protected void loadDataStart() {
        if (mLoadData) {
            loadDataStart2();
        }
    }

    protected void loadDataStart2() {
        Log.i("GPSI_Fragment", "页面2-加载数据!!!!!!");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("GPSI_Fragment：", "页面2-与关联的Activity完成onCreate()之后调用");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("GPSI_Fragment：", "页面2-正在启动");
    }

    @Override
    public void onResume() {
        super.onResume();
        isFirstStart = true;
        loadDataStart();
        Log.i("GPSI_Fragment：", "页面2-获得焦点");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("GPSI_Fragment：", "页面2-正在停止");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("GPSI_Fragment：", "页面2-即将停止");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("GPSI_Fragment：", "页面2-在Fragment中的布局被移除时");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myReceiver != null && getActivity() != null) {
            getActivity().unregisterReceiver(myReceiver);
        }
        Log.i("GPSI_Fragment：", "页面2-即将销毁");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("GPSI_Fragment：", "页面2-Fragment和Activity接触关联");
    }

    public void init() {
        sbt_starts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(new Intent(getActivity(), DomeService.class));
            }
        });
        sbt_binds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DomeService.class);
                getActivity().bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
            }
        });
        sbt_stops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(new Intent(getActivity(), DomeService.class));
            }
        });
        sbt_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().unbindService(mConnection);
            }
        });
        bt_bdgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.gpsidemo.MY_BROADCAST_BD");
                getActivity().sendBroadcast(intent);
            }
        });
        bt_OPrecess.setOnClickListener(this);
        bt_hqlxr.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.bt_hqlxr) {
            startActivity(new Intent(getActivity(), ProviderPNumberActivity.class));
        }
        if (view.getId() == R.id.bt_OPrecess) {
            //单开一个进程
            startActivity(new Intent(getActivity(), AloneActivity.class));
        }
    }
}
