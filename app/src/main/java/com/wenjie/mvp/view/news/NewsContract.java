package com.wenjie.mvp.view.news;

import com.wenjie.base.BaseModel;
import com.wenjie.base.BasePresenter;
import com.wenjie.base.BaseView;
import com.wenjie.entity.BaseNewsPageData;
import com.wenjie.entity.MilitaryNews;
import com.wenjie.entity.UserDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.mvp.view.news
 * Author: wenjie
 * Date: 2019-05-05 11:25
 * Description:
 */
public interface NewsContract {

    interface View extends BaseView {
        /**
         * 将数据返回给view
         *
         * @param result resuklt
         */
        void setData(List<MilitaryNews> result);

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getNews();

    }


    interface Model extends BaseModel {

        Observable<Response<BaseNewsPageData>> getNews();

    }

}
