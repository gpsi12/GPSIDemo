package com.example.feature_common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * 类描述：Fragment基类
 * Created by Gpsi on 2020-04-03.
 */
public class BaseFragment extends Fragment {
    public Activity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//方法1：保证Fragment即使在onDetach后,仍有Activiry的引用，但是有引起内存泄漏的风险
        mActivity = (Activity) context;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
//方法2：注释super.，让其不再保存fragment的状态，达到fragment随activity一起销毁的目的
        super.onSaveInstanceState(outState);
//方法3：只要发生onSaveInstanceState就remove all Fragment
//        if (outState != null){
//            outState.remove("android:support:fragments");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
