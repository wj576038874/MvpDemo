package com.wenjie.mvp.view.user;

import com.wenjie.base.BaseModel;
import com.wenjie.base.BasePresenter;
import com.wenjie.base.BaseView;
import com.wenjie.entity.New;
import com.wenjie.entity.Topic;
import com.wenjie.mvp.view.topics.TopicsContract;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.view.user
 * Author: wenjie
 * Date: 2019-01-16 12:26
 * Description:
 */
public interface HomeContract {

    interface View extends BaseView {

        void setNews(List<New> news);

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getNews(Integer limit);

    }

    interface Model extends BaseModel {

        Observable<Response<List<New>>> loadNewsList(Integer limit);

    }

}
