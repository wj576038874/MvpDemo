package com.wenjie.mvp.presenter.login;

import android.text.TextUtils;

import com.wenjie.common.RequestCallback;
import com.wenjie.entity.Token;
import com.wenjie.entity.UserDetail;
import com.wenjie.mvp.model.login.LoginModel;
import com.wenjie.mvp.view.login.LoginContract;

import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.api
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description:
 */
public class LoginPresenter extends LoginContract.Presenter {

    /**
     * m层
     */
    private LoginModel loginModel;

    /**
     * mvp模式  p层持有  v 和m 的接口引用 来进行数据的传递  起一个中间层的作用
     */
    public LoginPresenter() {
        this.loginModel = new LoginModel();
    }

    /**
     * 登陆
     */
    @Override
    public void login(String username, String password) {
        if (mView == null) return;
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mView.showMsg("用户名或密码不能为空");
            return;
        }
        mView.showLoading();
        subscribe(loginModel.login("", "", "password", username, password), new RequestCallback<Response<Token>>() {
            @Override
            public void onSuccess(Response<Token> data) {
                if (mView == null) return;
                mView.hideLoading();
                if (data.isSuccessful()) {
                    assert data.body() != null;
                    mView.setText(data.body().getAccess_token());
                } else if (data.code() == 401) {
                    mView.showMsg("用户名或密码错误");
                } else {
                    mView.showMsg("登录失败");
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.showMsg(msg);
            }
        });

    }


    /**
     * 获取用户信息
     */
    @Override
    public void getMe(String username, String password) {
        if (mView == null) return;
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mView.showMsg("用户名或密码不能为空");
            return;
        }
        mView.showLoading();
        subscribe(loginModel.getMe("", "", "password", username, password), new RequestCallback<Response<UserDetail>>() {
            @Override
            public void onSuccess(Response<UserDetail> data) {
                if (mView == null) return;
                mView.hideLoading();
                if (data.isSuccessful()) {
                    mView.setUserDetail(data.body());
                } else if (data.code() == 401) {
                    mView.showMsg("用户名或密码错误");
                } else {
                    mView.showMsg("获取失败:" + data.message());
                }
            }

            @Override
            public void onFailure(String msg) {
                if (mView == null) return;
                mView.hideLoading();
                mView.showMsg(msg);
            }
        });
    }

}
