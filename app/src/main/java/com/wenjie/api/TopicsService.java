package com.wenjie.api;

import com.wenjie.entity.New;
import com.wenjie.entity.Topic;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.api
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description:
 */
public interface TopicsService {

    @GET("news.json")
    Observable<Response<List<New>>> loadNewsList(@Query("limit") Integer limit);

    @GET("topics.json")
    Observable<Response<List<Topic>>> loadTopicList(@Query("limit") Integer limit);
}
