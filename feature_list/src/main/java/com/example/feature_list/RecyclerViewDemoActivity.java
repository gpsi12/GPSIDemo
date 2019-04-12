package com.example.feature_list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        for (int i = 0; i < 100; i++) {
            ItemBean bean = new ItemBean(i + " 条");
            mList.add(bean);
        }
    }
}
