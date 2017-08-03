package com.base.net;

import com.base.net.bean.BaseResonseBean;
import com.base.net.exception.RetryWhenNetworkException;
import com.base.net.service.ApiCallBack;
import com.base.net.service.HttpService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zwy on 2017/8/2.
 * package_name is com.base.net
 * 描述:SJ
 */

public class HttpManager<Q, P> {
    private HttpService getHttpService() {
        return HttpClientManager.getInstance().getHttpService();
    }

    private Observable observable;
    private static HttpManager INSTANCE = null;

    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public HttpManager addObservable(ApiCallBack<P> apiCallBack, final HttpConstants.EventCode code, Q params) {
        observable = getObservable(code, params);
        Request(apiCallBack, observable);
        return this;
    }

    public HttpManager addObservable(ApiCallBack<P> apiCallBack, final HttpConstants.EventCode code) {
        observable = getObservable(code);
        Request(apiCallBack, observable);
        return this;
    }

    private void Request(final ApiCallBack<P> apiCallBack, Observable observable) {
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread
                ()).subscribe(new HttpRxSubscriber<BaseResonseBean<P>>() {
            @Override
            public void onSucceed(BaseResonseBean<P> httpBaseBean) {
//                LoggerUtil.e("status==>" + httpBaseBean.getStatus() + "\n" + "msg==>" + httpBaseBean.getMsg() + "\n" + "data==>" + httpBaseBean.getData().toString());
                if (httpBaseBean.getStatus() == 1) {
                    apiCallBack.onSuccess(httpBaseBean.getData());
                } else {
                    apiCallBack.onFailed(httpBaseBean.getStatus(), httpBaseBean.getMsg());
                }
            }

            @Override
            public void onFailed(int status, String msg) {
                apiCallBack.onFailed(status, msg);
//                LoggerUtil.e("请求错误==>" + msg);
            }

            @Override
            public void onStart() {
                super.onStart();
                apiCallBack.onStart();
            }
        });
    }

    /**
     * 请求不带参数的,无须加密的
     *
     * @param code
     * @return
     */
    public Observable<BaseResonseBean> getObservable(HttpConstants.EventCode code) {
        switch (code) {
            case XG_USER_TYPE:
                observable = getHttpService().getBanner("");
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
    public Observable<BaseResonseBean> getObservable(HttpConstants.EventCode code, Q params) {
        switch (code) {
            case ZX:
//                RZXBean zxBean = (RZXBean) params;
                observable = getHttpService().getBanner("");
                break;
        }
        return observable.retryWhen(new RetryWhenNetworkException());
    }


}
