package com.wenjie.mvp.model.topics;

import com.wenjie.api.TopicsService;
import com.wenjie.base.BaseImpl;
import com.wenjie.entity.New;
import com.wenjie.entity.Topic;
import com.wenjie.mvp.view.topics.TopicsContract;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.model.login.topics
 * Author: wenjie
 * Date: 2019-01-11 16:15
 * Description:
 */
public class TopicsModel extends BaseImpl<TopicsService> implements TopicsContract.Model {

    @Override
    public Observable<Response<List<New>>> loadNewsList(Integer limit) {
        return mService.loadNewsList(limit);
    }

    @Override
    public Observable<Response<List<Topic>>> loadTopicList(Integer limit) {
        return mService.loadTopicList(limit);
    }
}
