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

public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

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
        Button mEmailSignInButton3 = findViewById(R.id.email_sign_in_button3);
        Button mEmailSignInButton4 = findViewById(R.id.email_sign_in_button4);
        Button mEmailSignInButton5 = findViewById(R.id.email_sign_in_button5);
        Button mEmailSignInButton6 = findViewById(R.id.email_sign_in_button6);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        mEmailSignInButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getMe();
            }
        });

        mEmailSignInButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login2();
            }
        });

        mEmailSignInButton4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getMe2();
            }
        });

        mEmailSignInButton5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login3();
            }
        });

        mEmailSignInButton6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getMe3();
            }
        });
    }

    /*------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 登录
     * Observable<Response<Token>>
     */
    private void login() {
        mPresenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    /**
     * 获取用户信息
     * Observable<Response<UserDetail>>
     */
    private void getMe() {
        mPresenter.getMe(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    /*------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 登录
     * Observable<BaseResponse<Token>>
     */
    private void login2() {
        mPresenter.login2(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    /**
     * 获取用户信息
     * Observable<BaseResponse<UserDetail>>
     */
    private void getMe2() {
        mPresenter.getMe2(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    /*------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 登录
     * Observable<Token>
     */
    private void login3() {
        mPresenter.login3(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    /**
     * 获取用户信息
     * Observable<UserDetail>
     */
    private void getMe3() {
        mPresenter.getMe3(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
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

