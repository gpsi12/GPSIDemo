package com.example.feature_demo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过 Content Provider获取联系人
 */
public class ProviderPNumberActivity extends Activity {

    private ListView mListView;
    private ArrayAdapter mAdaoter;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_providerpn);
        mListView = findViewById(R.id.lv_numberpp);
        mList = new ArrayList<>();
        mAdaoter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdaoter);
        init();

    }

    public void init() {
        //判断用户是否已经授权给我们了 如果没有，调用下面方法向用户申请授权，之后系统就会弹出一个权限申请的对话框
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            //获取联系人信息
            Cursor cursor1 = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cursor1 != null) {
                while (cursor1.moveToNext()) {
                    String name = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    int id = cursor1.getInt(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "+" + id, null, null);

                    if (cursor2 != null) {
                        while (cursor2.moveToNext()) {
                            String phone = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            mList.add(name + "+" + phone);
                            Log.i("GPSI_DEMO", name + "+" + phone);
                            mAdaoter.addAll(mList);
                            mAdaoter.notifyDataSetChanged();
                        }
                    }
                    cursor2.close();
                }
                cursor1.close();
            }
        }


    }
}
