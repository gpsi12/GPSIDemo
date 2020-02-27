package com.example.feature_login;

import android.content.Context;
import android.text.TextUtils;

import com.example.feature_login.mock.ICallback;
import com.example.feature_login.mock.MockApiServer;

/**
 * @author：jooper Email：jooperge@163.com
 * 描述：登录模块Presenter
 * <p>
 * Presenter层可以称为控制层，它是 V(View)和M(Model)之间的纽带，用于处理视图和数据之间的交互。
 * 简化的MVP可以将P和M融合为P，比如此类就是一个融合后的P，如果把请求数据的实现(如：MockApiServer.login())写在一个新的类中，如 LoginModel，在LoginModel中
 * 只处理和数据相关的实现，则 LoginModel 就是 M层。
 * <p>
 * 修改历史:
 * <p>
 * 创建于： 2020/2/27
 */
public class LoginPresenter {

    private LoginView mILoginView;

    /**
     * 该方法将实现了LoginView接口的对象传入此类，在此类中即可通过 mILoginView 对象调用 接口LoginView中定义好的接口方法。
     * 这种写法类似与普通的方法回调，也可以称为 依赖注入 (依赖是指此类的某些业务依赖与 LoginView 这个对象，注入代表将 LoginView 这个对象注入到此类中)。
     *
     * @param loginView
     */
    public void attachView(LoginView loginView) {
        mILoginView = loginView;
    }

    public void doLoginRequest(Context context, String userName, String password) {
        if (null == mILoginView) {
            /**
             * 此处如果 null == mILoginView，则代表页面初始化异常，主要发生在【用户关闭页面但页面未完全销毁的场景下，因为用户的意图是
             * 关闭页面，故此处直接 return 即可，不需要作出提示。。。真实业务场景下具体情况具体分析】
             */
            return;
        }
        if (TextUtils.isEmpty(userName)) {
            mILoginView.onLoginError(context.getString(R.string.login_error_empty_usernmae));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mILoginView.onLoginError(context.getString(R.string.login_error_empty_password));
            return;
        }

        /**
         * 用户名和密码都不为空或者经过了自定义的规则检查，则继续执行并请求接口
         */
        MockApiServer.login(userName, password, "此处模拟其他参数", new ICallback() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                /**
                 * 此处判空(mILoginView)，是因为请求大多数情况都是异步的，判空可以避免页面关闭但未正常销毁的时候请求依然在进行且成功返回数据的时候导致异常，
                 * 真实环境会有更优雅的处理方式，因为多次判空会导致看上去很乱
                 */
                if (null != mILoginView) {
                    if (null != userInfo) {
                        mILoginView.onLoginSuccess(userInfo);
                    } else {
                        /**
                         * 此处模拟当接口返回成功的时候，经过一系列判断都不满足，最终走到此处，故提示 未知异常，真实环境下各种提示都是预定好的，具体情况具体分析
                         */
                        onError("未知异常");
                    }
                }
            }

            @Override
            public void onError(String error) {
                mILoginView.onLoginError(error);
            }
        });
    }
}
