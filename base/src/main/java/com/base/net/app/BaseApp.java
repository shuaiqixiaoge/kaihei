package com.base.net.app;

import android.app.Application;

import com.base.net.HttpClientManager;

/**
 * Created by zwy on 2017/8/2.
 * package_name is com.base.net.app
 * 描述:SJ
 */

public class BaseApp extends Application {
    public static BaseApp context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        HttpClientManager.getInstance().initHttp();//网络加载初始化
    }
}
