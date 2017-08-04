package com.lhy.baselib.network;

import com.lhy.baselib.bean.RUserIdBean;
import com.lhy.baselib.bean.requestparams.RLoginBean;
import com.lhy.baselib.bean.requestparams.RTestBean;
import com.lhy.baselib.bean.requestparams.RXGItemBean;
import com.lhy.baselib.bean.requestparams.RZXBean;
import com.lhy.baselib.exception.RetryWhenNetworkException;
import com.lhy.baselib.network.iml.HttpService;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/10.
 * 添加观察者
 */

public class HttpObservable<T> {
    private HttpService httpService;
    private Observable observable;

    public HttpObservable(HttpService httpService) {
        this.httpService = httpService;
    }

    /**
     * 请求不带参数的,无须加密的
     *
     * @param code
     * @return
     */
    public Observable<HttpBaseBean> getObservable(HttpConstants.EventCode code) {
        switch (code) {
            case TIME:
                observable = httpService.getSystemTime();
                break;
            case XG_USER_TYPE:
                observable = httpService.getXGUserType();
                break;
            case SPLASH_AD:
                observable = httpService.getSplashAD();
                break;
        }
        return observable.retryWhen(new RetryWhenNetworkException());
    }

    /**
     * 请求带参数,需要加密的
     *
     * @param code
     * @param params
     * @return
     */
    public Observable<HttpBaseBean> getObservable(HttpConstants.EventCode code, T params) {
        switch (code) {
            case BANNER:
                RTestBean testBean = (RTestBean) params;
                observable = httpService.getBanner(testBean.getTest());
                break;
            case LOGIN:
                RLoginBean loginBean = (RLoginBean) params;
                observable = httpService.getLogin(loginBean.getUser_name(), loginBean.getPwd(), loginBean.getDevice_type(), loginBean.getTime(), loginBean.getSign());
            case ZX:
                RZXBean zxBean = (RZXBean) params;
                observable = httpService.getZx(zxBean.getUid(), zxBean.getTime(), zxBean.getSign(), zxBean.getPage());
                break;
            case XG_ITEM:
                RXGItemBean xgItemBean = (RXGItemBean) params;
                observable = httpService.getXGItem(xgItemBean.getUid(), xgItemBean.getPage(), xgItemBean.getUser_type(), xgItemBean.getNew_price_sort(), xgItemBean.getFluctua_range_sort(), xgItemBean.getTime(), xgItemBean.getSign());
                break;
            case WD_DETAIL:
                RUserIdBean userIdBean = (RUserIdBean) params;
                observable = httpService.getWDDetail(userIdBean.getUid(), userIdBean.getTime(), userIdBean.getSign());
                break;
            case FX_MESSAGE_SHOW:
                userIdBean = (RUserIdBean) params;
                observable = httpService.getRedMessage(userIdBean.getUid(), userIdBean.getTime(), userIdBean.getSign());
                break;
        }
        return observable.retryWhen(new RetryWhenNetworkException());
    }
}
