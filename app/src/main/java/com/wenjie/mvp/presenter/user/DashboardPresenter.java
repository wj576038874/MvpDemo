package com.wenjie.mvp.presenter.user;

import com.wenjie.common.RequestCallback;
import com.wenjie.entity.New;
import com.wenjie.entity.Topic;
import com.wenjie.mvp.model.topics.TopicsModel;
import com.wenjie.mvp.model.user.DashboardModel;
import com.wenjie.mvp.view.topics.TopicsContract;
import com.wenjie.mvp.view.user.DashboardContract;

import java.util.List;

import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.presenter.login.topics
 * Author: wenjie
 * Date: 2019-01-11 16:15
 * Description:
 */
public class DashboardPresenter extends DashboardContract.Presenter {

    private DashboardModel dashboardModel;

    public DashboardPresenter(){
        this.dashboardModel = new DashboardModel();
    }


    @Override
    public void getTopics(Integer limit) {
        if (mView==null) return;
        mView.showLoading();
        subscribe(dashboardModel.loadTopicList(limit), new RequestCallback<Response<List<Topic>>>() {
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
