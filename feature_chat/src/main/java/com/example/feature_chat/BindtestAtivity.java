package com.example.feature_chat;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.feature_chat.databinding.BindtestactBinding;

/**
 * 类描述：测试ViewBinding
 * Created by Gpsi on 2020-03-13.
 */
public class BindtestAtivity extends Activity {
    private BindtestactBinding bbinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.bindtestact);
        bbinding = BindtestactBinding.inflate(LayoutInflater.from(this));
        setContentView(bbinding.getRoot());
        bbinding.tvOn.setText("通过View Binding");
        bbinding.tvDemo.setText("修改控件");

    }
}
