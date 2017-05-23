package com.ywy.mylibs.utils;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangweiyi on 2016/10/26.
 */

public class AnimationUtil {

    private static final int ANIMATION_2_BOTTOM = 1;
    private static final int ANIMATION_FROM_BOTTOM = 2;
    private static final int ANIMATION_2_LEFT = 3;
    private static final int ANIMATION_FROM_LEFT = 4;

    private Map<Integer, Animation> mAnimationMap;

    private AnimationUtil() {
        mAnimationMap = new HashMap<>();
    }

    private static class SingletonHolder {
        private static final AnimationUtil INTANCE = new AnimationUtil();
    }

    public static AnimationUtil getInstance() {
        return AnimationUtil.SingletonHolder.INTANCE;
    }


    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public TranslateAnimation move2SelfBottom() {
        TranslateAnimation mHiddenAction = (TranslateAnimation) mAnimationMap.get(ANIMATION_2_BOTTOM);
        if (mHiddenAction == null) {
            mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
            mHiddenAction.setDuration(400);
            mAnimationMap.put(ANIMATION_2_BOTTOM, mHiddenAction);
        }
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public TranslateAnimation restoreFromSelfBottom() {
        TranslateAnimation mHiddenAction = (TranslateAnimation) mAnimationMap.get(ANIMATION_FROM_BOTTOM);
        if (mHiddenAction == null) {
            mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mHiddenAction.setDuration(400);
            mAnimationMap.put(ANIMATION_FROM_BOTTOM, mHiddenAction);
        }
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public TranslateAnimation move2SelfLeft() {
        TranslateAnimation mHiddenAction = (TranslateAnimation) mAnimationMap.get(ANIMATION_2_LEFT);
        if (mHiddenAction == null) {
            mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mHiddenAction.setDuration(400);
            mAnimationMap.put(ANIMATION_2_LEFT, mHiddenAction);
        }
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public TranslateAnimation restoreFromSelfLeft() {
        TranslateAnimation mHiddenAction = (TranslateAnimation) mAnimationMap.get(ANIMATION_FROM_LEFT);
        if (mHiddenAction == null) {
            mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mHiddenAction.setDuration(400);
            mAnimationMap.put(ANIMATION_FROM_LEFT, mHiddenAction);
        }
        return mHiddenAction;
    }
}
