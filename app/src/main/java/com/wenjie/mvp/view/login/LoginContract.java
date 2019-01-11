package com.wenjie.mvp.view.login;

import com.wenjie.base.BasePresenter;
import com.wenjie.base.BaseView;
import com.wenjie.base.BaseModel;
import com.wenjie.entity.Token;
import com.wenjie.entity.UserDetail;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.view
 * Author: wenjie
 * Date: 2019-01-11 15:25
 * Description:
 */
public interface LoginContract {

    interface View extends BaseView {
        /**
         * 将数据返回给view
         *
         * @param result resuklt
         */
        void setText(String result);

        /**
         * 设置用户信息
         */
        void setUserDetail(UserDetail userDetail);
    }


    abstract class Presenter extends BasePresenter<View> {


        public abstract void login(String username , String password);

        public abstract void getMe(String username , String password);
    }

    interface Model extends BaseModel {
        /**
         * 登陆方法
         */
        Observable<Response<Token>> login(String client_id, String client_secret, String grant_type, String username, String password);

        /**
         * 获取用户信息，用户登录成之后获取到token 然后拿到token再用token去获取用户信息
         */
        Observable<Response<UserDetail>> getMe(String client_id, String client_secret, String grant_type, String username, String password);
    }

}
