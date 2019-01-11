package com.wenjie.base;

import android.text.TextUtils;
import com.wenjie.common.RequestCallback;
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

    protected <T> void subscribe(final Observable<T> observable, final RequestCallback<T> requestCallback) {
        if (NetworkUtil.checkedNetWork(MyApplication.getContext())) {
            rxManager.register(observable.subscribeOn(Schedulers.io())//发送事件在子线程
                    .unsubscribeOn(Schedulers.io())//解除订阅子线程
                    .observeOn(AndroidSchedulers.mainThread())//接收事件主线程（获取到网络的数据进行UI的更新显示故在主线程）
                    .subscribe(new Consumer<T>() {
                        @Override
                        public void accept(T t) {
                            requestCallback.onSuccess(t);
//                            BaseResp baseResp = (BaseResp) t;
//                            switch (baseResp.getCode()) {
//                                case "00000":
//                                    requestCallback.onSuccess(t);//确保 onSuccess执行的时候一定是请求成功并且返回数据的
//                                    break;
//                                default://其他错误代码 服务器返回什么就显示什么
//                                    requestCallback.onFailure(baseResp.getMessage());
//                                    break;
//                            }
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
