package com.example.feature_login.mock;

import android.text.TextUtils;

import com.example.feature_login.UserInfo;

/**
 * @author：jooper Email：jooperge@163.com
 * 描述：模拟服务端接口处理
 * 修改历史:
 * <p>
 * 创建于： 2020/2/27
 */
public class MockApiServer {
    public static void login(String userName, String password, String xxx, ICallback callback) {
        if (TextUtils.isEmpty(userName)) {
            callback.onError("服务端返回：用户名为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            callback.onError("服务端返回：密码为空");
            return;
        }

        if (userName.equals("123") && password.equals("123")) {//模拟用户名密码匹配成功
            UserInfo userInfo = new UserInfo();
            userInfo.setName("张三");
            userInfo.setAge("18");
            userInfo.setToken("abc123abc");
            callback.onSuccess(userInfo);
        } else {//模拟用户名密码匹配失败
            callback.onError("用户名或密码错误");
        }
    }
}
