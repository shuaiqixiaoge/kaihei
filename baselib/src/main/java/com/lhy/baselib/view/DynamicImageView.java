package com.lhy.baselib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.lhy.baselib.R;


/**
 * Created by LiZhanPing onShrinkScreen 2016/12/29.
 */

public class DynamicImageView extends AppCompatImageView {

    private float mRatio;

    public DynamicImageView(Context context) {
        this(context, null);
    }

    public DynamicImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.FIT_XY);

        final TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.DynamicImageView, 0, 0);
        mRatio = td.getFloat(R.styleable.DynamicImageView_heightRatio, (float) 2 / 3);
        td.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(widthMeasureSpec) * mRatio), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
