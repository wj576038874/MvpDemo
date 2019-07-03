package com.wenjie.mvp.view.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wenjie.R;
import com.wenjie.base.BaseMvpActivity;
import com.wenjie.entity.MilitaryNews;
import com.wenjie.mvp.presenter.news.NewsPresenter;
import com.wenjie.mvp.view.news.NewsContract;

import java.util.List;

public class NewsActivity extends BaseMvpActivity<NewsContract.View , NewsContract.Presenter> implements NewsContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mPresenter.getNews();
    }

    @Override
    protected NewsContract.Presenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void setData(List<MilitaryNews> result) {

    }
}
