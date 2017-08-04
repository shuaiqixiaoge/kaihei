package com.shell.view.refresh.header;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shell.R;
import com.shell.view.refresh.IHeaderView;
import com.shell.view.refresh.OnAnimEndListener;

/**
 * Created by lcodecore on 2016/10/2.
 */

public class ClassicRefreshView extends FrameLayout implements IHeaderView {

    private ImageView refreshArrow;
    private ImageView loadingView;
    private TextView refreshTextView;

    public ClassicRefreshView(Context context) {
        this(context, null);
    }

    public ClassicRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = View.inflate(getContext(), R.layout.view_sinaheader, null);
        refreshArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
        refreshTextView = (TextView) rootView.findViewById(R.id.tv);
        loadingView = (ImageView) rootView.findViewById(R.id.iv_loading);
        addView(rootView);
    }

    public void setArrowResource(@DrawableRes int resId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), resId);
        int color = refreshTextView.getCurrentTextColor();
        if (drawable != null) {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            refreshArrow.setImageDrawable(drawable);
        }
    }

    public void setThemeColor(@ColorInt int color) {
        changeArrowDrawableColor(color);
        refreshTextView.setTextColor(color);
    }

    public void setPullDownStr(String pullDownStr1) {
        pullDownStr = pullDownStr1;
    }

    private void changeArrowDrawableColor(@ColorInt int color) {
        Drawable drawable = refreshArrow.getDrawable();
        if (drawable != null) {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            refreshArrow.setImageDrawable(drawable);
        }
    }

    public void setReleaseRefreshStr(String releaseRefreshStr1) {
        releaseRefreshStr = releaseRefreshStr1;
    }

    public void setRefreshingStr(String refreshingStr1) {
        refreshingStr = refreshingStr1;
    }

    private String pullDownStr = "下拉刷新";
    private String releaseRefreshStr = "释放刷新";
    private String refreshingStr = "正在刷新";

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void hide() {
        setVisibility(GONE);
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight, boolean refreshEnabled) {
        if (fraction <= 1f) refreshTextView.setText(pullDownStr);
        if (fraction > 1f) refreshTextView.setText(releaseRefreshStr);

        float ratio = fraction > 1f ? 1f : fraction;
        ViewCompat.setRotation(refreshArrow, ratio * 180);
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight, boolean refreshEnabled) {
        if (fraction < 1f) {
            refreshTextView.setText(pullDownStr);
            ViewCompat.setRotation(refreshArrow, fraction * 180);
            if (refreshArrow.getVisibility() == GONE) {
                refreshArrow.setVisibility(VISIBLE);
                loadingView.setVisibility(GONE);
            }
        }
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight, boolean refreshEnabled) {
        refreshTextView.setText(refreshingStr);
        refreshArrow.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        ((AnimationDrawable) loadingView.getDrawable()).start();
    }

    @Override
    public void onFinish(OnAnimEndListener listener, boolean refreshEnabled) {
        listener.onAnimEnd();
    }

    @Override
    public void reset() {
        refreshArrow.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        refreshTextView.setText(pullDownStr);
    }
}
