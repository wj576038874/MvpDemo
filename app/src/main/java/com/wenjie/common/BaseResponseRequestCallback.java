package com.wenjie.common;

import com.wenjie.base.BaseResponse;

import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.api
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description: BaseResponse包装数据
 */
public interface BaseResponseRequestCallback<T> {

    /**
     * 请求成功
     *
     * @param data 服务器返回的结果数据
     */
    void onSuccess(BaseResponse<T> data);


    /**
     * 请求失败
     *
     * @param msg 错误信息
     */
    void onFailure(String msg);
}
