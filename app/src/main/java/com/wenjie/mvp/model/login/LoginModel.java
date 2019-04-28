package com.wenjie.mvp.model.login;

import com.wenjie.api.UserService;
import com.wenjie.base.BaseImpl;
import com.wenjie.base.BaseResponse;
import com.wenjie.base.MyApplication;
import com.wenjie.entity.Token;
import com.wenjie.entity.UserDetail;
import com.wenjie.mvp.view.login.LoginContract;
import com.wenjie.utils.CacheUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.model
 * Author: wenjie
 * Date: 2019-01-11 12:26
 * Description:
 */
public class LoginModel extends BaseImpl<UserService> implements LoginContract.Model {


    @Override
    public Observable<Response<Token>> login(String client_id, String client_secret, String grant_type, String username, String password) {
        return mService.getToken(client_id, client_secret, grant_type, username, password);
    }

    @Override
    public Observable<Response<UserDetail>> getMe(String client_id, String client_secret, String grant_type, String username, String password) {
        return mService.getToken(client_id, client_secret, grant_type, username, password)
                .flatMap(new Function<Response<Token>, ObservableSource<Response<UserDetail>>>() {
                    @Override
                    public ObservableSource<Response<UserDetail>> apply(Response<Token> response) throws Exception {
                        //第一个“登录请求”完成之后 会返回token信息，我们把token保存在本地，以便于第二个“获取用户信息”的请求可以从
                        //本地获取到token作为请求所需要的参数
                        if (response.isSuccessful()) {
                            CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());
                            Token token = response.body();
                            assert token != null;
                            cacheUtil.saveToken(token);
                            return mService.getMe();
                        }
                        //如果用户登录出错，没有获取到token的话，那么我们就把用户登录的请求返回的response
                        //封装到 Response<UserDetail> 然后发射出去，进行code的值判断显示错误信息
                        ResponseBody responseBody = response.errorBody();
                        assert responseBody != null;
                        Response<UserDetail> userDetailResponse = Response.error(response.code(), responseBody);
                        return Observable.just(userDetailResponse);
                    }
                }).map(new Function<Response<UserDetail>, Response<UserDetail>>() {
                    @Override
                    public Response<UserDetail> apply(Response<UserDetail> userDetailResponse) throws Exception {
                        return userDetailResponse;
                    }
                });
    }

    @Override
    public Observable<BaseResponse<Token>> login2(String client_id, String client_secret, String grant_type, String username, String password) {
        return mService.getToken2(client_id, client_secret, grant_type, username, password);
    }

    /***********************************************************************************************************************************************/

    @Override
    public Observable<BaseResponse<UserDetail>> getMe2(String client_id, String client_secret, String grant_type, String username, String password) {
        return mService.getToken2(client_id, client_secret, grant_type, username, password)
                .flatMap(new Function<BaseResponse<Token>, ObservableSource<BaseResponse<UserDetail>>>() {
                    @Override
                    public ObservableSource<BaseResponse<UserDetail>> apply(BaseResponse<Token> baseResponse) throws Exception {

                        CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());
                        cacheUtil.saveToken(baseResponse.getData());
                        return mService.getMe2();

                    }
                }).map(new Function<BaseResponse<UserDetail>, BaseResponse<UserDetail>>() {
                    @Override
                    public BaseResponse<UserDetail> apply(BaseResponse<UserDetail> userDetailResponse) throws Exception {
                        return userDetailResponse;
                    }
                });
    }


    /***********************************************************************************************************************************************/

    @Override
    public Observable<Token> login3(String client_id, String client_secret, String grant_type, String username, String password) {
        return mService.getToken3(client_id, client_secret, grant_type, username, password);
    }

    @Override
    public Observable<UserDetail> getMe3(String client_id, String client_secret, String grant_type, String username, String password) {
        return mService.getToken3(client_id, client_secret, grant_type, username, password)
                .flatMap(new Function<Token, ObservableSource<UserDetail>>() {
                    @Override
                    public ObservableSource<UserDetail> apply(Token token) throws Exception {
                        //第一个“登录请求”完成之后 会返回token信息，我们把token保存在本地，以便于第二个“获取用户信息”的请求可以从
                        //本地获取到token作为请求所需要的参数
                        CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());
                        if (token != null){
                            cacheUtil.saveToken(token);
                            return mService.getMe3();
                        }
                        else {
                            //没获取到直接抛出null异常
                            throw new NullPointerException();
                        }
                    }
                }).map(new Function<UserDetail, UserDetail>() {
                    @Override
                    public UserDetail apply(UserDetail userDetailResponse) throws Exception {
                        return userDetailResponse;
                    }
                });
    }
}
