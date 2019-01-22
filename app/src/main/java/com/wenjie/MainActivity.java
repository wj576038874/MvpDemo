package com.wenjie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.wenjie.mvp.view.login.LoginActivity;
import com.wenjie.mvp.view.topics.TopicsActivity;
import com.wenjie.mvp.view.user.HomeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toActivity(View view){
        startActivity(new Intent(this , LoginActivity.class));
    }

    public void toActivity2(View view){
        startActivity(new Intent(this , HomeActivity.class));
    }
}
