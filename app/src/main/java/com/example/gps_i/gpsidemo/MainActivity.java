package com.example.gps_i.gpsidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.feature_common.BaseActivity;
import com.example.feature_demo.fragment.GoFragment;
import com.example.feature_demo.fragment.GtFragment;
import com.example.feature_login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import fragment.IndexFragment;
import fragment.MyAdapter;
import widget.BottomView;

/**
 * 主模块
 */
public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private BottomView mBView;
    private List<Fragment> mFragmentList;

    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Intent intent = new Intent(MainActivity.this, RecyclerViewDemoActivity.class);
//      Intent intent = new Intent(MainActivity.this, ChatActivity.class);
//      Intent intent = new Intent(MainActivity.this, MapDemoActivity.class);

        initview();
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new IndexFragment());
        mFragmentList.add(new GtFragment());
        mFragmentList.add(new GoFragment());

//        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),mFragmentList);
//        mViewPager.setAdapter(myAdapter);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),mFragmentList));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               mBView.btnChangeState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setStatusBarHeight(findViewById(R.id.activity_main_app));

    }
    private void initview(){
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mBView = findViewById(R.id.bv_main);
        mBView.setOnPageSelectListener(new BottomView.IOnPageSelectedListener() {
            @Override
            public void onPageSelect(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
    }




//     * 设置状态栏背景颜色
//     * @param color 透明：Color.TRANSPARENT
//    public void setNavigationStatusColor(int color) {
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setNavigationBarColor(color);
//            getWindow().setStatusBarColor(color);
//        }
//    }

    //反射获取状态栏高度，设置padding
//    public void gsetStatusBarHeight() {
//        int result = 0;
//        //获取状态栏高度的资源id
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = getResources().getDimensionPixelSize(resourceId);
//        }
////        ViewGroup rootView = getWindow().getDecorView().findViewById(R.id.activity_main_app);
////        rootView.setPadding(0, result, 0, 0);
//
//    }
}
