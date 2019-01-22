package com.wenjie.mvp.presenter.user;

import com.wenjie.common.RequestCallback;
import com.wenjie.entity.New;
import com.wenjie.mvp.model.user.HomeModel;
import com.wenjie.mvp.view.user.HomeContract;

import java.util.List;

import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.presenter.user
 * Author: wenjie
 * Date: 2019-01-16 12:28
 * Description:
 */
public class HomePresenter extends HomeContract.Presenter {

    private HomeModel homeModel;

    public HomePresenter(){
        this.homeModel = new HomeModel();
    }

    @Override
    public void getNews(Integer limit) {
        if (mView==null) return;
        mView.showLoading();

        subscribe(homeModel.loadNewsList(limit), new RequestCallback<Response<List<New>>>() {
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
}
