package com.wenjie.mvp.presenter.news;

import com.wenjie.common.RequestCallback;
import com.wenjie.entity.BaseNewsPageData;
import com.wenjie.mvp.model.news.NewsModel;
import com.wenjie.mvp.view.news.NewsContract;

import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.presenter.news
 * Author: wenjie
 * Date: 2019-05-05 11:28
 * Description:
 */
public class NewsPresenter extends NewsContract.Presenter {


    private NewsModel newsModel;

    public NewsPresenter() {
        this.newsModel = new NewsModel();
    }

    @Override
    public void getNews() {
        mView.showLoading();
        subscribe(newsModel.getNews(), new RequestCallback<Response<BaseNewsPageData>>() {
            @Override
            public void onSuccess(Response<BaseNewsPageData> data) {
                if (mView == null) return;
                mView.hideLoading();
                mView.setData(data.body().getData());
            }

            @Override
            public void onFailure(String msg) {
                if (mView == null) return;
                mView.hideLoading();
                mView.showMsg(msg);
            }
        });


        subscribe_2(newsModel.getNews(), new RequestCallback<Response<BaseNewsPageData>>() {
            @Override
            public void onSuccess(Response<BaseNewsPageData> data) {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
