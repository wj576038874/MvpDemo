package com.wenjie.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.entity
 * Author: wenjie
 * Date: 2019-05-05 11:15
 * Description:
 */
public class BaseNewsPageData implements Serializable {

    private List<MilitaryNews> data;
    private String pageToken;
    private String retcode;
    private boolean hasNext;
    private String dataType;
    private String appCode;

    public void setData(List<MilitaryNews> data) {
        this.data = data;
    }

    public List<MilitaryNews> getData() {
        return data;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppCode() {
        return appCode;
    }

}
