package com.wenjie.base;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.base
 * Author: wenjie
 * Date: 2019-04-28 14:48
 * Description: 服务器返回数据的包装类
 */
public class BaseResponse<T> {

    /**
     * 请求成功之后的数据
     */
    private T data;

    /**
     * 服务器返回标识
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
