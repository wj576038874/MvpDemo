package com.wenjie.mvp.model.user;

import com.wenjie.api.TopicsService;
import com.wenjie.base.BaseImpl;
import com.wenjie.entity.New;
import com.wenjie.mvp.view.user.HomeContract;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.model.user
 * Author: wenjie
 * Date: 2019-01-16 12:29
 * Description:
 */
public class HomeModel extends BaseImpl<TopicsService> implements HomeContract.Model {

    @Override
    public Observable<Response<List<New>>> loadNewsList(Integer limit) {
        return mService.loadNewsList(limit);
    }
}
