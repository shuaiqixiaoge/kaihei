package com.base.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by yeqiu on 2016/11/18.
 *
 * @describe 属性动画
 */

public class AnimUtil {
    public static AnimUtil animUtil;

    public static synchronized AnimUtil getInstance() {
        if (animUtil == null) {
            animUtil = new AnimUtil();
        }
        return animUtil;
    }

    public void TranslateY(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 100f, 0f);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    public void ScaleCenter(View view, int duration) {
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(duration);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.playTogether(objectAnimatorX, objectAnimatorY);
        animSet.start();
    }

    public void Rotate(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 10f);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new CycleInterpolator(5));
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(5);
        objectAnimator.start();
    }

}
