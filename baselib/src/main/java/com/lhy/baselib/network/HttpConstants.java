package com.lhy.baselib.network;

/**
 * Created by Administrator on 2017/4/5.
 * 请求管理类
 */
public class HttpConstants {
    public static final int errorCode = 0;
    public static final int successCode = 1;

    /**
     * 请求实体
     */
    public enum EventCode {
        BANNER,//首页轮播图
        TIME,//获取时间差值
        LOGIN,//登录
        ZX,//资讯
        XG_USER_TYPE,//校股标题类型
        XG_ITEM, //校股主页数据
        WD_DETAIL,//我的 详情
        FX_MESSAGE_SHOW,//发现红点提醒
        SPLASH_AD,//欢迎页--广告页
    }
}
