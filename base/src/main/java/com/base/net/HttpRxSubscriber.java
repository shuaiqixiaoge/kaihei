package com.base.net;

import android.content.Context;
import android.text.TextUtils;


import com.base.R;
import com.base.net.app.BaseApp;
import com.base.utils.NetWorkUtils;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * 自定义返回数据处理类
 *
 * @param <T>
 */
public abstract class HttpRxSubscriber<T> extends Subscriber<T> {

    public static final int ERROR_NET_CONNECT = 11;
    public static final int ERROR_TIME_OUT = 12;
    public static final int ERROR_UNKOWN = 13;
    public static final int ERROR_FAILURE = 14;
    public static final int ERROR_NONE = 15;


    private final WeakReference<Context> mContext;
    private String mMessage;


    public HttpRxSubscriber() {
        this(null, null);
    }

    public HttpRxSubscriber(Context context, String message) {
        super();
        mContext = new WeakReference<Context>(context);
        mMessage = message;
    }

    @Override
    public final void onCompleted() {
        onFinal();
    }

    @Override
    public final void onError(Throwable e) {
        doOnFail(e);
        onFinal();
    }

    @Override
    public final void onNext(T t) {
        onFinal();
        onSucceed(t);
    }

    private void doOnFail(Throwable e) {
//        LoggerUtil.e(e.toString());
        if (!NetWorkUtils.isNetConnected(BaseApp.context)) {
            onFailed(ERROR_NET_CONNECT, BaseApp.context.getResources().getString(R.string.load_net_work_error_tip));
        } else if (e instanceof SocketTimeoutException
                || e instanceof TimeoutException) {
            onFailed(ERROR_TIME_OUT, BaseApp.context.getResources().getString(R.string.load_net_work_weak_tip));
        } else if (e instanceof ApiException) {
            onFailed(TextUtils.equals(((ApiException) e).getStatus(), "True") ? ERROR_NONE : ERROR_FAILURE, e.getMessage());
        } else {
            onFailed(ERROR_UNKOWN, BaseApp.context.getResources().getString(R.string.load_failed_tip));
        }
    }

    public abstract void onSucceed(T t);

    public abstract void onFailed(int status, String msg);

    public void onFinal() {
//        SuccinctProgress.dismiss();
    }

}