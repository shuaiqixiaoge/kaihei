package com.lhy.baselib.network;

import com.lhy.baselib.network.iml.HttpService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lhy.baselib.network.HttpUrlManager.HOST_URL;


/**
 * Created by Administrator on 2017/4/24.
 * 网络 客户端初始化
 */

public class HttpClientManager {
    public HttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public HttpObservable getHttpObservable() {
        return httpObservable;
    }

    public void setHttpObservable(HttpObservable httpObservable) {
        this.httpObservable = httpObservable;
    }

    private static HttpClientManager INSTANCE = null;
    private HttpService httpService;
    private HttpObservable httpObservable;


    public static HttpClientManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpClientManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpClientManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化
     */
    public void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory
                .create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(HOST_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
        httpObservable = new HttpObservable(httpService);
        setHttpService(httpService);
        setHttpObservable(httpObservable);
    }
}
