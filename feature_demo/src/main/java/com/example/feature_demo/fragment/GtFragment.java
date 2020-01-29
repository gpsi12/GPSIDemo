package com.example.feature_demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feature_demo.R;

public class GtFragment extends Fragment {

    private boolean mLoadData;
    private boolean isFirstStart;

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
        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mLoadData = isVisibleToUser;
        if (isFirstStart){
            loadDataStart();
        }
        Log.i("GPSI_LOAD", "页面2-是否可见--" + isVisibleToUser);
    }

    protected void loadDataStart() {
        if (mLoadData) {
           loadDataStart2();
        }
    }

    protected void loadDataStart2(){
        Log.i("GPSI_LOAD", "页面2-加载数据!!!!!!");
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
        Log.i("GPSI_Fragment：", "页面2-即将销毁");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("GPSI_Fragment：", "页面2-Fragment和Activity接触关联");
    }
}
