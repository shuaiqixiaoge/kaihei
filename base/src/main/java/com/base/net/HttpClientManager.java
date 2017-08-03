package com.base.net;

import com.base.net.service.HttpService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.base.net.URLManager.HOST_URL;

/**
 * Created by zwy on 2017/8/2.
 * package_name is com.base.net
 * 描述:SJ
 */

public class HttpClientManager {
    private static HttpClientManager INSTANCE = null;

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

    private Retrofit retrofit;

    /**
     * 获取HttpService
     * @return
     */
    public HttpService getHttpService() {
        return retrofit.create(HttpService.class);
    }

    public void initHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory
                .create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(HOST_URL)
                .build();
    }
}
