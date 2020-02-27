package com.example.feature_login.mock;

import com.example.feature_login.UserInfo;

/**
 * @author：jooper Email：jooperge@163.com
 * 描述：模拟请求回调
 * 修改历史:
 * <p>
 * 创建于： 2020/2/27
 */
public interface ICallback {
    void onSuccess(UserInfo userInfo);

    void onError(String error);
}