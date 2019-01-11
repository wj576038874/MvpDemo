package com.wenjie.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.base
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description:
 */
public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
