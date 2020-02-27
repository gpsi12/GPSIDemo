package com.example.feature_login;

/**
 * @author：jooper Email：jooperge@163.com
 * 描述：登录模块
 * <p>
 * 该类可以称为视图接口层，专门用来定义 V(即视图层)对外提供的接口，以供P层调用对应接口触发V层做视图更新
 * <p>
 * 修改历史:
 * <p>
 * 创建于： 2020/2/27
 */
public interface LoginView {

    /**
     * @param userInfo 该参数模拟用户登录成功后返回的用户信息 【真实场景下会有其他数据，具体视公司业务而定】
     */
    void onLoginSuccess(UserInfo userInfo);

    /**
     * @param errMsg 该参数模拟用户登录异常的原因【 真实场景下会有其他数据，具体视公司业务而定】
     */
    void onLoginError(String errMsg);
}
