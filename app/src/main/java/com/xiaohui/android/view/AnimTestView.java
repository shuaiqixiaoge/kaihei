package com.xiaohui.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.lhy.baselib.utils.LoggerUtil;
import com.xiaohui.android.R;

/**
 * Created by zwy on 2017/5/27.
 * package_name is com.xiaohui.android.view
 * 描述:自定义View
 */

public class AnimTestView extends AppCompatTextView {

    /**
     * 问题总结
     * 命名空间要正确  AS和Eclipse有明显的区别 Eclipse需要完整的包名空间，AS不需要
     * mPaint.getTextBounds(mText, 0, mText.length(), mRound);与Measure测量的区别
     * 前者是标准的宽度后者有相对的Padding值和TextSize有关
     * <p>
     * 自定义属性需要建立attrs文件  需要format格式化数据格式
     * 资源文件用reference
     * 颜色用color
     * 文本用string
     * 整型用Integer
     * 等可以搜索自定义属性format来搜索
     */
    private String mText;
    private int mColor;
    private int mSize;

    private Paint mPaint;
    private Rect mRound;


    public AnimTestView(Context context) {
        this(context, null);
    }

    public AnimTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AnimTestView, defStyleAttr, 0);
        int num = array.getIndexCount();
        for (int i = 0; i < num; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.AnimTestView_text:
                    mText = array.getString(attr);
                    break;
                case R.styleable.AnimTestView_textColor:
                    mColor = array.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.AnimTestView_textSize:
                    mSize = array.getDimensionPixelSize(attr, 16);
                    break;
            }
        }
        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mSize);
        mPaint.setColor(mColor);

        mRound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mRound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRound);
            float textWidth = mRound.width();
            int disired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = disired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRound);
            float textHeight = mRound.height();
            int disired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = disired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mColor);
        LoggerUtil.e(getWidth() / 2 + "========" + mRound.width() / 2 + "=========" + getHeight() / 2 + "========" + mRound.height() / 2);
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        canvas.drawText(mText, getWidth() / 2 - mRound.width() / 2, getHeight() / 2 - (fontMetricsInt.ascent + fontMetricsInt.descent) / 2, mPaint);
    }
}
