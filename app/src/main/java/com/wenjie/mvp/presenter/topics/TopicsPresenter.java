package com.wenjie.mvp.presenter.topics;

import com.wenjie.common.RequestCallback;
import com.wenjie.entity.New;
import com.wenjie.entity.Topic;
import com.wenjie.mvp.model.topics.TopicsModel;
import com.wenjie.mvp.view.topics.TopicsContract;

import java.util.List;

import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.presenter.login.topics
 * Author: wenjie
 * Date: 2019-01-11 16:15
 * Description:
 */
public class TopicsPresenter extends TopicsContract.Presenter {

    private TopicsModel topicsModel;

    public TopicsPresenter(){
        this.topicsModel = new TopicsModel();
    }

    @Override
    public void getNews(Integer limit) {
        if (mView==null) return;
        mView.showLoading();

        subscribe(topicsModel.loadNewsList(limit), new RequestCallback<Response<List<New>>>() {
            @Override
            public void onSuccess(Response<List<New>> data) {
                if (mView==null) return;
                mView.hideLoading();
                if (data.isSuccessful()){
                    if (data.body() != null){
                        mView.setNews(data.body());
                    }
                }else {
                    mView.showMsg(data.message());
                }
            }

            @Override
            public void onFailure(String msg) {
                if (mView==null) return;
                mView.hideLoading();
                mView.showMsg(msg);
            }
        });
    }

    @Override
    public void getTopics(Integer limit) {
        if (mView==null) return;
        mView.showLoading();
        subscribe(topicsModel.loadTopicList(limit), new RequestCallback<Response<List<Topic>>>() {
            @Override
            public void onSuccess(Response<List<Topic>> data) {
                if (mView==null) return;
                mView.hideLoading();
                if (data.isSuccessful()){
                    if (data.body() != null){
                        mView.setTopics(data.body());
                    }
                }else {
                    mView.showMsg(data.message());
                }
            }

            @Override
            public void onFailure(String msg) {
                if (mView==null) return;
                mView.hideLoading();
                mView.showMsg(msg);
            }
        });
    }
}
