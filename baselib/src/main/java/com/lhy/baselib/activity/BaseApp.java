package com.lhy.baselib.activity;

import android.app.Application;
import android.content.Context;

import com.lhy.baselib.network.HttpClientManager;
import com.lhy.baselib.network.HttpManager;
import com.lhy.baselib.utils.LoggerUtil;
import com.lhy.baselib.view.Constants;

/**
 * Created by Administrator on 2017/4/7.
 * 全局管理
 */

public class BaseApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
        initHttpManager();
        initLoggerManager();
    }

    private void initContext() {
        context = this;
    }

    /**
     * 初始化Log日志
     */
    private void initLoggerManager() {
        LoggerUtil.setDebug(Constants.DEBUG);
    }

    /**
     * 初始化网络配置
     */
    private void initHttpManager() {
        HttpClientManager.getInstance().init();
    }
}
