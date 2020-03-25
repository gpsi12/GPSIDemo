package com.example.feature_demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.feature_common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过 ContentProvider获取联系人
 */
public class ProviderPNumberActivity extends BaseActivity {

    private ListView mListView;
    private ArrayAdapter mAdaoter;
    private List<String> mList;
    // 号码
    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_providerpn);
        mListView = findViewById(R.id.lv_numberpp);
        mList = new ArrayList<>();
        mAdaoter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdaoter);
        setStatusBarHeight(findViewById(R.id.activity_providerpn));
        init();

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 201){
//            init();
//        }else {
//            return;
//        }
//    }

    public void init() {
        //判断用户是否已经授权给我们了 如果没有，调用下面方法向用户申请授权，之后系统就会弹出一个权限申请的对话框
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            //获取联系人信息
            Cursor cursor1 = getContentResolver().query(ContactsContract
                            .CommonDataKinds.Phone.CONTENT_URI, new String[]{NUM, NAME}
                    , null, null, null);

            if (cursor1 != null) {
                while (cursor1.moveToNext()) {
                    mList.add(cursor1.getString(cursor1.getColumnIndex(NAME)) +
                            "" + cursor1.getString(cursor1.getColumnIndex(NUM)));
                }
                mAdaoter.addAll(mList);
                mAdaoter.notifyDataSetChanged();
                cursor1.close();
            }
        }


    }


}
