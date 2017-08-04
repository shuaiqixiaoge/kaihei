package com.xiaohui.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lhy.baselib.utils.LoggerUtil;
import com.xiaohui.android.R;

/**
 * Created by zwy on 2017/6/9.
 * package_name is com.xiaohui.android.view
 * 描述:XH2
 */

public class AnimImageView extends View {
    private String mText;
    private int mColor;
    private int mSize;
    private Bitmap mImage;
    private int mImageScaleType;
    private int mDivider;


    private int width;
    private int height;


    private Paint mPaint;
    private Rect mRect;
    private Rect mTextBounds;


    public AnimImageView(Context context) {
        this(context, null);
    }

    public AnimImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AnimImageView, defStyleAttr, 0);
        int num = array.getIndexCount();
        for (int i = 0; i < num; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.AnimImageView_tv:
                    mText = array.getString(attr);
                    break;
                case R.styleable.AnimImageView_tvColor:
                    mColor = array.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.AnimImageView_tvSize:
                    mSize = array.getDimensionPixelSize(attr, 16);
                    break;
                case R.styleable.AnimImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(), array.getResourceId(attr, R.drawable.splash_bg));
                    break;
                case R.styleable.AnimImageView_imageScaleType:
                    mImageScaleType = array.getInt(attr, 0);
                    break;
                case R.styleable.AnimImageView_tvDivider:
                    mDivider = array.getDimensionPixelSize(attr, 10);
                    break;
            }
        }
        array.recycle();
        mPaint = new Paint();
        mRect = new Rect();
        mTextBounds = new Rect();
        mPaint.setTextSize(mSize);
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int widthImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            int widthTitle = getPaddingLeft() + getPaddingRight() + mTextBounds.width();
            if (widthMode == MeasureSpec.AT_MOST) {
                int desire = Math.max(widthImg, widthTitle);
                width = Math.min(desire, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int heightImg = getPaddingTop() + mImage.getHeight();
            int heightTitle = getPaddingBottom() + mTextBounds.height();
            if (heightMode == MeasureSpec.AT_MOST) {
                int desire = heightImg + heightTitle;
                height = Math.min(desire, heightSize) + mDivider;
            }
        }
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.YELLOW);

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);

        if (mTextBounds.width() > width) {
            TextPaint textPaint = new TextPaint(mPaint);
            String title = TextUtils.ellipsize(mText, textPaint, width - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END).toString();
            canvas.drawText(title, getPaddingLeft(), height - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(mText, width / 2 - mTextBounds.width() / 2, height - getPaddingBottom(), mPaint);
        }
        mRect.left = getPaddingLeft();
        mRect.right = width - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = height - getPaddingBottom();

        mRect.bottom -= mTextBounds.height();
        if (mImageScaleType == 0) {
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        } else {
            mRect.left = width / 2 - mImage.getWidth() / 2;
            mRect.right = width / 2 + mImage.getWidth() / 2;
            mRect.top = height / 2 - mImage.getHeight() / 2 - mDivider / 2;
            mRect.bottom = height / 2 + mImage.getHeight() / 2 - mDivider / 2;

            LoggerUtil.e(mDivider + "====" + mRect.left + "====" + mRect.right + "====" + mRect.top + "====" + mRect.bottom);

            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }


    }
}
