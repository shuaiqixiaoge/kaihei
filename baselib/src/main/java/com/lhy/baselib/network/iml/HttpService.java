package com.lhy.baselib.network.iml;


import com.google.gson.JsonObject;
import com.lhy.baselib.bean.response.PBannerBean;
import com.lhy.baselib.bean.response.PRedMessageBean;
import com.lhy.baselib.bean.response.PWDDetailBean;
import com.lhy.baselib.bean.response.PXGMainBean;
import com.lhy.baselib.bean.response.PXGTypeBean;
import com.lhy.baselib.bean.response.PZxBean;
import com.lhy.baselib.network.HttpBaseBean;
import com.lhy.baselib.network.HttpUrlManager;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zwy on 2017/3/31.
 */
public interface HttpService {

    //首页标题
    @GET(HttpUrlManager.BANNER_URL)
    Observable<HttpBaseBean<List<PBannerBean>>> getBanner(@Query("test") String content);

    //获取服务器时间
    @GET(HttpUrlManager.SYS_TIME)
    Observable<HttpBaseBean<String>> getSystemTime();

    //获取启动页
    @GET(HttpUrlManager.AD_URL)
    Observable<HttpBaseBean<JsonObject>> getSplashAD();

    //登录
    @FormUrlEncoded
    @POST(HttpUrlManager.USER_LOGIN)
    Observable<HttpBaseBean<JsonObject>> getLogin(@Field("user_name") String username, @Field("pwd") String pwd, @Field("device_type") String device_type, @Field("time") String time, @Field("sign") String sign);

    //资讯
    @FormUrlEncoded
    @POST(HttpUrlManager.ZX)
    Observable<HttpBaseBean<PZxBean>> getZx(@Field("uid") String uid, @Field("time") String time, @Field("sign") String sign, @Field("page") int page);

    //校股标题类型
    @POST(HttpUrlManager.XG_USER_TYPE)
    Observable<HttpBaseBean<List<PXGTypeBean>>> getXGUserType();

    @FormUrlEncoded
    @POST(HttpUrlManager.XG_MAIN)
    Observable<HttpBaseBean<List<PXGMainBean>>> getXGItem(@Field("uid") String uid, @Field("page") int page, @Field("user_type") String user_type, @Field("new_price_sort") String new_price_sort, @Field("fluctua_range_sort") String fluctua_range_sort, @Field("time") String time, @Field("sign") String sign);

    //我的 ---详情界面
    @FormUrlEncoded
    @POST(HttpUrlManager.PERSON_URL)
    Observable<HttpBaseBean<PWDDetailBean>> getWDDetail(@Field("uid") String uid, @Field("time") String time, @Field("sign") String sign);

    //系统消息
    @FormUrlEncoded
    @POST(HttpUrlManager.RED_MESSAGE_URL)
    Observable<HttpBaseBean<PRedMessageBean>> getRedMessage(@Field("uid") String uid, @Field("time") String time, @Field("sign") String sign);
}
