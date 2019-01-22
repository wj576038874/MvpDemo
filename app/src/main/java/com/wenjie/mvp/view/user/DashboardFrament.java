package com.wenjie.mvp.view.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wenjie.R;
import com.wenjie.base.BaseMvpFragment;
import com.wenjie.entity.Topic;
import com.wenjie.mvp.presenter.user.DashboardPresenter;

import java.util.List;

public class DashboardFrament extends BaseMvpFragment<DashboardContract.View , DashboardContract.Presenter> implements DashboardContract.View {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textView = view.findViewById(R.id.result);
        mPresenter.getTopics(50);
        return view;
    }

    @Override
    protected DashboardContract.Presenter createPresenter() {
        return new  DashboardPresenter();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setTopics(List<Topic> topics) {
        textView.setText("topics"+topics.size());
    }
}
