package com.wenjie.mvp.view.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.wenjie.R;
import com.wenjie.base.BaseMvpActivity;
import com.wenjie.entity.UserDetail;
import com.wenjie.mvp.presenter.login.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginContract.View ,LoginContract.Presenter> implements LoginContract.View {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        textView = findViewById(R.id.result);

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        Button mEmailSignInButton2 = findViewById(R.id.email_sign_in_button2);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mEmailSignInButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptGetMe();
            }
        });
    }


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    private void attemptLogin() {
        mPresenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    private void attemptGetMe(){
        mPresenter.getMe(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    @Override
    public void setText(String result) {
        textView.setText(result);
    }

    @Override
    public void setUserDetail(UserDetail userDetail) {
        textView.setText(userDetail.toString());
    }

}

