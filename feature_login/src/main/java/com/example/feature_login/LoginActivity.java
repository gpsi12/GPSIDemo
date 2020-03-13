package com.example.feature_login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @author：jooper Email：jooperge@163.com
 * 描述：登录模块
 * <p>
 * 该activity是 V 层，即视图层，专门用来做UI渲染
 * <p>
 * 修改历史:
 * <p>
 * 创建于： 2020/2/27
 */
public class LoginActivity extends Activity implements LoginView {

    private LoginPresenter mLoginPresenter;
    private EditText et_username, et_password;
    private TextView tv_result;

    public static void newInstance(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);

        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);

        initView();
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_result = findViewById(R.id.tv_result);
    }

    public void doLogin(View view) {
        if (null != mLoginPresenter) {
            mLoginPresenter.doLoginRequest(this, getUserName(), getPassword());
        }
    }

    private String getPassword() {
        return et_password != null ? et_password.getText().toString() : null;
    }

    private String getUserName() {
        return et_username != null ? et_username.getText().toString() : null;
    }

    @Override
    public void onLoginSuccess(UserInfo userInfo) {
        tv_result.setText("登录成功\n" + "欢迎 " + userInfo.getName());
    }

    @Override
    public void onLoginError(String errMsg) {
        tv_result.setText(errMsg);
    }
}
