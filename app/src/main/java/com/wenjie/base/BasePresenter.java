package com.wenjie.base;

import android.text.TextUtils;

import com.wenjie.common.BaseResponseRequestCallback;
import com.wenjie.common.RequestCallback;
import com.wenjie.common.ResponseRequestCallback;
import com.wenjie.common.RxManager;
import com.wenjie.utils.NetworkUtil;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.base
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description:
 */
public class BasePresenter<V extends BaseView> {

    /**
     * v层泛型引用
     */
    protected V mView;

    private WeakReference<V> weakReferenceView;
    private RxManager rxManager = new RxManager();

    public void attachMvpView(V view) {
        weakReferenceView = new WeakReference<>(view);
        this.mView = weakReferenceView.get();
    }


    public void detachMvpView() {
        rxManager.unSubscribe();
        weakReferenceView.clear();
        weakReferenceView = null;
        mView = null;
    }

    /*--------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Retrofit的Response包裹
     * Observable<T> observable  可以再加一层包裹变为Observable<Response<T>> observable这样回调中就无需咋强转成Response
     *
     * @see #subscribe_1(Observable, ResponseRequestCallback)
     * <p>
     * RequestCallback<T> requestCallback 可以再加一层包裹变为RequestCallback<Response<T>> requestCallback
     * @see #subscribe_2(Observable, RequestCallback)
     * <p>
     * 一般情况 要么都包裹要么都不包裹
     */
    protected <T> void subscribe(final Observable<T> observable, final RequestCallback<T> requestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<T>() {
                        @Override
                        public void accept(T t) {
                            //这里的T 是Response<数据>
                            if (t != null) {
                                Response response = (Response) t;
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        requestCallback.onSuccess(t);
                                    } else {
                                        requestCallback.onFailure("response is null");
                                    }
                                } else {
                                    requestCallback.onFailure("请求出错了" + response.message() + "，错误代码" + response.code());
                                }
                            } else {
                                requestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        requestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                requestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            requestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }

    protected <T> void subscribe_1(final Observable<Response<T>> observable, final ResponseRequestCallback<T> responseRequestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<Response<T>>() {
                        @Override
                        public void accept(Response<T> response) {
                            //这里的T 是 数据
                            if (response != null) {
                                if (response.isSuccessful() && response.body() != null) {
                                    responseRequestCallback.onSuccess(response);
                                } else {
                                    responseRequestCallback.onFailure("response is null");
                                }
                            } else {
                                responseRequestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        responseRequestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                responseRequestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            responseRequestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }

    protected <T> void subscribe_2(final Observable<Response<T>> observable, final RequestCallback<Response<T>> requestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<Response<T>>() {
                        @Override
                        public void accept(Response<T> response) {
                            //这里的T 是 数据
                            if (response != null) {
                                if (response.isSuccessful() && response.body() != null) {
                                    requestCallback.onSuccess(response);
                                } else {
                                    requestCallback.onFailure("response is null");
                                }
                            } else {
                                requestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        requestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                requestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            requestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }

    /*--------------------------------------------------------------------------------------------------------------------------*/

    /**
     * BaseResponse包裹
     * Observable<T> observable  可以再加一层包裹变为Observable<BaseResponse<T>> observable这样回调中就无需咋强转成Response
     *
     * @see #subscribe2_1(Observable, BaseResponseRequestCallback)
     * <p>
     * RequestCallback<T> requestCallback 可以再加一层包裹变为RequestCallback<BaseResponse<T>> requestCallback
     * @see #subscribe2_2(Observable, RequestCallback)
     * <p>
     * 一般情况 要么都包裹要么都不包裹
     */
    protected <T> void subscribe2(final Observable<T> observable, final RequestCallback<T> requestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<T>() {
                        @Override
                        public void accept(T t) {
                            //这里的T是BaseResponse<数据>
                            if (t != null) {
                                BaseResponse baseResponse = (BaseResponse) t;
                                switch (baseResponse.getCode()) {
                                    case "00000":
                                        requestCallback.onSuccess(t);//确保 onSuccess执行的时候一定是请求成功并且返回数据的
                                        break;
                                    default://其他错误代码 服务器返回什么就显示什么
                                        requestCallback.onFailure(baseResponse.getMsg());
                                        break;
                                }
                            } else {
                                requestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        requestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                requestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            requestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }


    protected <T> void subscribe2_1(final Observable<BaseResponse<T>> observable, final BaseResponseRequestCallback<T> baseResponseRequestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<BaseResponse<T>>() {
                        @Override
                        public void accept(BaseResponse<T> baseResponse) {
                            //这里的T是 数据
                            if (baseResponse != null) {
                                switch (baseResponse.getCode()) {
                                    case "00000":
                                        baseResponseRequestCallback.onSuccess(baseResponse);//确保 onSuccess执行的时候一定是请求成功并且返回数据的
                                        break;
                                    default://其他错误代码 服务器返回什么就显示什么
                                        baseResponseRequestCallback.onFailure(baseResponse.getMsg());
                                        break;
                                }
                            } else {
                                baseResponseRequestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        baseResponseRequestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                baseResponseRequestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            baseResponseRequestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }

    protected <T> void subscribe2_2(final Observable<BaseResponse<T>> observable, final RequestCallback<BaseResponse<T>> requestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<BaseResponse<T>>() {
                        @Override
                        public void accept(BaseResponse<T> baseResponse) {
                            //这里的T是 数据
                            if (baseResponse != null) {
                                switch (baseResponse.getCode()) {
                                    case "00000":
                                        requestCallback.onSuccess(baseResponse);//确保 onSuccess执行的时候一定是请求成功并且返回数据的
                                        break;
                                    default://其他错误代码 服务器返回什么就显示什么
                                        requestCallback.onFailure(baseResponse.getMsg());
                                        break;
                                }
                            } else {
                                requestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        requestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                requestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            requestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }

    /*--------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 无包裹
     */
    protected <T> void subscribe3(final Observable<T> observable, final RequestCallback<T> requestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<T>() {
                        @Override
                        public void accept(T t) {
                            //这里的T 是数据
                            if (t != null) {
                                requestCallback.onSuccess(t);
                            } else {
                                requestCallback.onFailure("response is null");
                            }
                        }
                    }, new Consumer<Throwable>() {//异常处理
                        @Override
                        public void accept(Throwable throwable) {
                            String msg = null;
                            if (throwable instanceof HttpException) {
                                HttpException he = (HttpException) throwable;
                                switch (he.code()) {
                                    case 400:
                                        msg = "手机号或验证码错误";
                                        break;
                                    case 401:
                                        requestCallback.onFailure("登录失效");
                                        break;
                                    default:
                                        msg = "请求出错了，错误代码" + he.code();
                                        break;
                                }
                            } else if (throwable instanceof SocketTimeoutException) {
                                msg = "请求超时。请稍后重试！";
                            } else if (throwable instanceof ConnectException) {
                                msg = "请求超时。请稍后重试！";
                            } else {
                                msg = "请求出错了。请稍后重试！";
                            }
                            if (!TextUtils.isEmpty(msg)) {
                                requestCallback.onFailure(msg);
                            }
                        }
                    }));
        } else {
            requestCallback.onFailure("无网络链接,请检查您的网络，");
        }
    }

}
