package com.example.feature_list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.feature_net.BaseResponse;
import com.example.feature_net.JsonCallback;
import com.example.feature_net.Mokgo;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemoActivity extends Activity {
    private RecyclerView recyclerView;
    private List<ItemBean> mList = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initRecyclerView();
    }

    private void initRecyclerView() {
        initItemBean();
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(mList);
        recyclerView.setAdapter(adapter);

    }

    private void initItemBean() {

        OkGo.<BaseResponse<ArrayList<ItemBean>>>get("https://wanandroid.com/wxarticle/chapters/json")
                .tag(this)
                .execute(new JsonCallback<BaseResponse<ArrayList<ItemBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<ArrayList<ItemBean>>> response) {
                        Log.i("OkGo", "成功" + response.body());
                        if (mList == null) {
                            Log.i("OkGo", "失败");
                        } else {
                            mList.addAll(response.body().data);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<ArrayList<ItemBean>>> response) {
                        Log.i("OkGo", "失败" + response.message());
                    }
                });

//        for (int i = 0; i < 100; i++) {
        //           ItemBean bean = new ItemBean();
        //           mList.add(bean);
//        }
    }
}
