package com.wenjie.mvp.view.topics;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.wenjie.R;
import com.wenjie.base.BaseMvpActivity;
import com.wenjie.entity.New;
import com.wenjie.entity.Topic;
import com.wenjie.mvp.presenter.topics.TopicsPresenter;
import java.util.List;

public class TopicsActivity extends BaseMvpActivity<TopicsContract.View,TopicsContract.Presenter> implements TopicsContract.View{

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        result = findViewById(R.id.result);

        findViewById(R.id.btn_topics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTopics();
            }
        });

        findViewById(R.id.btn_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews();
            }
        });
    }

    @Override
    protected TopicsContract.Presenter createPresenter() {
        return new TopicsPresenter();
    }

    private void getNews(){
        mPresenter.getNews(10);
    }

    private void getTopics(){
        mPresenter.getTopics(20);
    }

    @Override
    public void setTopics(List<Topic> topics) {
        result.setText(topics.size()+"");
    }

    @Override
    public void setNews(List<New> news) {
        result.setText(news.size()+"");
    }
}
