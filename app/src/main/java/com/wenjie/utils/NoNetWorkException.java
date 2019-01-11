package com.wenjie.utils;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.utils
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.utils.NoNetWorkException.java
 * Date: 2018/1/29 17:07
 * Description:
 */

public class NoNetWorkException extends Exception {
    private String msg;

    public NoNetWorkException() {
    }

    public NoNetWorkException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
