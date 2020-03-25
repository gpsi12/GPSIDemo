package com.example.feature_demo;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.feature_common.BaseActivity;
import com.example.feature_demo.fragment.GoFragment;
import com.example.feature_demo.fragment.GtFragment;
import com.example.feature_demo.fragment.GthreeFragment;
import com.example.feature_demo.fragment.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo模块
 */
public class DemoActivity extends BaseActivity {

    private ViewPager mViewPager;
    private BottomView bv_main;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Log.i("GPSI_Activity", "onCreate()-正在创建，此时Activity可见但不再前台");
//        startService(new Intent(this,DomeService.class));
        initView();
//        FragmentPagerItems pagerItems = new FragmentPagerItems(this);
//        pagerItems.add(FragmentPagerItem.of("1", GoFragment.class));
//        pagerItems.add(FragmentPagerItem.of("2", GtFragment.class));
//        pagerItems.add(FragmentPagerItem.of("3", GthreeFragment.class));
//
//        mViewPager.setCurrentItem(3);
//        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pagerItems);
//        mViewPager.setAdapter(adapter);

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new GoFragment());
        mFragmentList.add(new GtFragment());
        mFragmentList.add(new GthreeFragment());
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(myAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        bv_main = findViewById(R.id.bv_main);
        bv_main.setOnPageSelectListener(new BottomView.IOnPageSelectedListener() {
            @Override
            public void onPageSelect(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        setStatusBarHeight(findViewById(R.id.activity_main_demo));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("GPSI", "onStart()-正在启动，此时Activity可见且在前台并开始活动");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("GPSI", "onRestoreInstanceState()-在onStart()之后，将onSaveInstanceState()保存的Bundle取出进行恢复。在onCreate恢复前要判断是否有保存数据");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("GPSI", "onPause()-正在停止,做数据存储、停止动画等操作");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("GPSI", "onSaveInstanceState()-非人为终止Activity时，用来保存状态，方法调用在onStop之前，但是和onPsuse没有时序关系--人为退出时不会调用次方法");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("GPSI", "onResume()-获得焦点，可见且在前台并开始活动");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("GPSI", "onStop()-即将停止，可以做稍微重量级回收工作");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("GPSI", "onDestroy()-即将销毁，通常用来做回收工作，资源释放");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("GPSI", "onRestart()-后台切换到前台，不可见到可见-表示Activity重新启动");
    }

}
