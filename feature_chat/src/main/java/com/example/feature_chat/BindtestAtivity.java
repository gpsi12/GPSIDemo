package com.example.feature_chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.feature_chat.databinding.BindtestactBinding;

/**
 * 类描述：测试ViewBinding
 * Created by Gpsi on 2020-03-13.
 */
public class BindtestAtivity extends Activity {
    private BindtestactBinding bbinding;

    public static void newInstance(Context context){
        context.startActivity(new Intent(context,BindtestAtivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bindtestact);
        bbinding = BindtestactBinding.inflate(LayoutInflater.from(this));
        setContentView(bbinding.getRoot());
        bbinding.tvOn.setText("通过View Binding");
        bbinding.tvT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbinding.tvDemo.setText("葛葛葛葛葛葛");
            }
        });
    }
}
