package com.wenjie.base;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.base
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description:
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    /**
     * 弹出消息
     *
     * @param msg msg
     */
    void showMsg(String msg);
}
