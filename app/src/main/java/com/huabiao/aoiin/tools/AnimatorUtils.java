package com.huabiao.aoiin.tools;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.tools
 * @date 2017-09-08 09:48
 * @description
 */
public class AnimatorUtils {
    public AnimatorUtils() {
    }

    public void openHiddenView(final View view) {
        view.setVisibility(View.VISIBLE);
        view.measure(0, 0);////measure方法的参数值都设为0
        ValueAnimator valueAnimator = creatDropAnimator(view, 0, view.getMeasuredHeight());//getMeasuredHeight获取组件高度
        valueAnimator.start();
    }

    public void closeHiddenView(final View view) {
        ValueAnimator valueAnimator = creatDropAnimator(view, view.getHeight(), 0);
        //动画结束之后执行隐藏
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        valueAnimator.start();
    }

    public ValueAnimator creatDropAnimator(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int Value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = Value;
                view.setLayoutParams(layoutParams);
            }
        });
        return valueAnimator;
    }

}
