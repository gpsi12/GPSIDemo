package com.example.feature_net;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * okgo初始化
 */
public class Mokgo {

    public static void init(Application application){
        OkGo.getInstance().init(application);

    }

}
