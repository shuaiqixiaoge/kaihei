package com.base.net.service;

import com.base.net.URLManager;
import com.base.net.bean.BaseResonseBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zwy on 2017/8/2.
 * package_name is com.base.net.service
 * 描述:SJ
 */

public  interface HttpService<T> {
    //首页标题
    @GET(URLManager.BANNER_URL)
    Observable<BaseResonseBean<T>> getBanner(@Query("test") String content);

    //获取服务器时间
    @GET(URLManager.SYS_TIME)
    Observable<BaseResonseBean<T>> getSystemTime();
}
