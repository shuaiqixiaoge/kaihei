package com.xiaohui.android.constants;

import com.lhy.baselib.activity.BaseApp;
import com.lhy.baselib.network.HttpUrlManager;
import com.lhy.baselib.utils.SpUtils;

/**
 * Created by zwy on 2017/5/9.
 * package_name is com.xiaohui.android.net
 * 描述:加载web页面的配置信息
 */

public class WebConstants {
    public static final String Key_Code = "code";
    public static final String Key_Url = "url";
    private static StringBuilder stringBuilder;

    public WebConstants buildWebURL(String url) {
        stringBuilder.append(HttpUrlManager.HOST_URL);
        stringBuilder.append(url);
        return this;
    }

    public WebConstants buildLocalURL(String url) {
        stringBuilder.append(url);
        return this;
    }

    public String buildFinish() {
        return stringBuilder.toString();
    }

    public String buildFinishUID() {
        String uid = SpUtils.getUid(BaseApp.context);
        stringBuilder.append(uid);
        return stringBuilder.toString();
    }

    public static WebConstants Build() {
        WebConstants webConstants = new WebConstants();
        stringBuilder = new StringBuilder();
        return webConstants;
    }


    /***************************不包含标题************************/
    public static final int CODE_PROTOCOL_SECRECY = 1;//保密授权协议
    public static final int CODE_PROTOCOL_BORROW = 2;//借款协议
    public static final int CODE_PROTOCOL_QUESTIONS = 3;//常见问题
    public static final int CODE_PROTOCOL_CHEATS = 4;//武林秘籍
    public static final int CODE_PROTOCOL_SHOP = 5;//积分商城
    public static final int CODE_PROTOCOL_DEAL = 6;//交易须知
    public static final int CODE_PROTOCOL_SHARE = 7;//分享赚积分
    public static final int CODE_PROTOCOL_SJ = 10;//时见
    public static final int CODE_PROTOCOL_RULE = 11;//积分规则
    public static final int CODE_PROTOCOL_TRAIL = 12;//年华变动轨迹
    public static final int CODE_PROTOCOL_USER = 14;//用户协议
    public static final int CODE_PROTOCOL_SYSTEM = 15;//系统消息
    public static final int CODE_PROTOCOL_ZX = 16;//资讯
    public static final int CODE_PROTOCOL_SIGN = 17;//签约管理
    public static final int CODE_AD = 18;//广告页 广告
    /*************************包含标题************************/
    public static final int CODE_URL_WH = 4;//网红
    public static final int CODE_URL_JY = 2;//教育
    public static final int CODE_URL_MZX = 3;//漫资讯

    public static final String URL_PROTOCOL_SECRECY = "file:///android_asset/bm_deal.html";//保密授权协议
    public static final String URL_PROTOCOL_BORROW = "file:///android_asset/jk_deal.html";//借款协议
    public static final String URL_PROTOCOL_QUESTIONS = "file:///android_asset/issue.html";//常见问题
    public static final String URL_PROTOCOL_CHEATS = "file:///android_asset/miji.html";//武林秘籍
    public static final String URL_PROTOCOL_SHOP = "/jifen/index.html?uid=";//积分商城
    public static final String URL_PROTOCOL_DEAL = "file:///android_asset/democssss.html";//交易须知
    public static final String URL_PROTOCOL_SHARE = "/jifen/share_commod.html?uid=";//分享赚积分
    public static final String URL_PROTOCOL_SJ = "file:///android_asset/demo2.html";//时见
    public static final String URL_PROTOCOL_RULE = "file:///android_asset/demo1.html";//积分规则
    public static final String URL_PROTOCOL_TRAIL = "/jifen/bar/bar.html?uid=";//年华变动轨迹
    public static final String URL_PROTOCOL_USER = "file:///android_asset/bm_deal.html";//用户协议
    public static final String URL_PROTOCOL_SIGN = "http://newwyj.xueshengtai.cn/jifen/xg/signed.html?uid=";//签约管理

    public static final String URL_WH = "/index.php/home/internetstar/internetstar/uid/";//网红
    public static final String URL_JY = "/jifen/education/education.html?uid=";//教育
    public static final String URL_MZX = "/jifen/cartoon/cartoon.html?uid=";//网红
}
