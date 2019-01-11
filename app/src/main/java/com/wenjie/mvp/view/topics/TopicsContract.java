package com.wenjie.mvp.view.topics;

import com.wenjie.base.BasePresenter;
import com.wenjie.base.BaseModel;
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
public interface TopicsContract {

    interface View extends BaseView {

        void setTopics(List<Topic> topics);

        void setNews(List<New> news);

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getNews(Integer limit);

        public abstract void getTopics(Integer limit);
    }

    interface Model extends BaseModel {

        Observable<Response<List<New>>> loadNewsList(Integer limit);

        Observable<Response<List<Topic>>> loadTopicList(Integer limit);
    }
}
