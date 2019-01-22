package com.wenjie.mvp.view.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wenjie.R;
import com.wenjie.base.BaseMvpFragment;
import com.wenjie.entity.New;
import com.wenjie.entity.Topic;
import com.wenjie.mvp.presenter.user.HomePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseMvpFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textView = view.findViewById(R.id.result);
        mPresenter.getNews(20);
        return view;
    }

    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setNews(List<New> news) {
        textView.setText("setNews" + news.size());
    }
}
