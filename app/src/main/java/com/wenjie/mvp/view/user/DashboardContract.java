package com.wenjie.mvp.view.user;

import com.wenjie.base.BaseModel;
import com.wenjie.base.BasePresenter;
import com.wenjie.base.BaseView;
import com.wenjie.entity.New;
import com.wenjie.entity.Topic;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.view.topics
 * Author: wenjie
 * Date: 2019-01-11 16:33
 * Description:
 */
public interface DashboardContract {

    interface View extends BaseView {

        void setTopics(List<Topic> topics);

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getTopics(Integer limit);

    }

    interface Model extends BaseModel {

        Observable<Response<List<Topic>>> loadTopicList(Integer limit);

    }
}
