package com.shell.view.refresh.Footer;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shell.R;
import com.shell.view.refresh.IBottomView;
import com.shell.view.refresh.utils.DensityUtil;


/**
 * Created by lcodecore on 2016/10/3.
 */

public class LoadingView extends FrameLayout implements IBottomView {

    private ImageView loadingImage;
    private TextView messageView;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        params.gravity = Gravity.CENTER;
        setLayoutParams(params);

        params = new FrameLayout.LayoutParams(-2, -2);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = DensityUtil.dp2px(context, 10);
        messageView = new TextView(context);
        messageView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        messageView.setLayoutParams(params);
        addView(messageView);
        messageView.setVisibility(View.GONE);

        int size = DensityUtil.dp2px(context, 34);
        loadingImage = new ImageView(context);
        loadingImage.setImageResource(R.drawable.anim_loading_view);
        params = new FrameLayout.LayoutParams(size, size);
        params.gravity = Gravity.CENTER;
        loadingImage.setLayoutParams(params);
        addView(loadingImage);
    }

    /**
     * 没有更多时展示
     *
     * @param text
     */
    public void setExtraMessage(CharSequence text) {
        messageView.setText(text);
    }

    public void setExtraMessage(@StringRes int textResId) {
        messageView.setText(textResId);
    }

    public void setExtraTextColor(@ColorInt int color) {
        messageView.setTextColor(color);
    }

    @Override
    public void setVisibility(int visibility) {
        loadingImage.setVisibility(visibility);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void show() {
        loadingImage.setVisibility(VISIBLE);
        messageView.setVisibility(GONE);
    }

    @Override
    public void hide() {
        loadingImage.setVisibility(GONE);
        messageView.setVisibility(VISIBLE);
    }

    @Override
    public void onPullingUp(float fraction, float maxHeadHeight, float headHeight, boolean loadMoreEnabled) {
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight, boolean loadMoreEnabled) {
        ((AnimationDrawable) loadingImage.getDrawable()).start();
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight, boolean loadMoreEnabled) {

    }

    @Override
    public void onFinish(boolean loadMoreEnabled) {

    }

    @Override
    public void reset() {
    }
}
