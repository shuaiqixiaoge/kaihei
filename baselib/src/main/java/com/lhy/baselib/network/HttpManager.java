package com.lhy.baselib.network;


import com.lhy.baselib.network.iml.ApiCallBack;
import com.lhy.baselib.utils.LoggerUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/4/7.
 * 网络管理类
 */

public class HttpManager<Q, P> {
    private static HttpManager INSTANCE = null;
    private Observable observable;

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
        observable = getObservable(code, null);
        Request(apiCallBack, observable);
        return this;
    }


    private Observable getObservable(final HttpConstants.EventCode code, Q params) {
        Observable observable;
        if (params == null) {
            observable = HttpClientManager.getInstance().getHttpObservable().getObservable(code);
        } else {
            observable = HttpClientManager.getInstance().getHttpObservable().getObservable(code, params);
        }
        return observable;
    }

    private void Request(final ApiCallBack<P> apiCallBack, Observable observable) {
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread
                ()).subscribe(new HttpRxSubscriber<HttpBaseBean<P>>() {
            @Override
            public void onSucceed(HttpBaseBean<P> httpBaseBean) {
                LoggerUtil.e("status==>" + httpBaseBean.getStatus() + "\n" + "msg==>" + httpBaseBean.getMsg() + "\n" + "data==>" + httpBaseBean.getData().toString());
                if (httpBaseBean.getStatus() == 1) {
                    apiCallBack.onSuccess(httpBaseBean.getData());
                } else {
                    apiCallBack.onFailure(httpBaseBean.getStatus(), httpBaseBean.getMsg());
                }
            }

            @Override
            public void onFailed(int status, String msg) {
                apiCallBack.onFailure(status, msg);
                LoggerUtil.e("请求错误==>" + msg);
            }

            @Override
            public void onStart() {
                super.onStart();
                apiCallBack.onStartRequest();
            }
        });
    }

}
