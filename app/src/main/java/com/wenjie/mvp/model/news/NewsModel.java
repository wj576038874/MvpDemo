package com.wenjie.mvp.model.news;

import com.wenjie.api.NewsService;
import com.wenjie.base.BaseImpl;
import com.wenjie.entity.BaseNewsPageData;
import com.wenjie.mvp.view.news.NewsContract;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.model.news
 * Author: wenjie
 * Date: 2019-05-05 11:24
 * Description:
 */
public class NewsModel extends BaseImpl<NewsService> implements NewsContract.Model {

    @Override
    public Observable<Response<BaseNewsPageData>> getNews() {
        return mService.getNews();
    }
}
