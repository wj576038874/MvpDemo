package com.wenjie.common;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.api
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description:
 */
public class RxManager {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();// 管理订阅者者

    public void register(Disposable d) {
        mCompositeDisposable.add(d);
    }

    public void unSubscribe() {
        mCompositeDisposable.dispose();// 取消订阅
    }
}