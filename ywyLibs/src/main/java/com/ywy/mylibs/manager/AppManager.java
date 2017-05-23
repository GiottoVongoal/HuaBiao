/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：AppManager.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-8-6
 */
package com.ywy.mylibs.manager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.util.Stack;

/**
 * @author panjichang
 *         应用程序Activity管理类：用于Activity管理和应用程序退出
 * @date 2014-8-6 下午6:04:25
 */
public class AppManager {

    private static Stack<Activity> activityStack;

    private AppManager() {
    }

    private static class SingletonHolder {
        private static final AppManager INTANCE = new AppManager();
    }

    public static AppManager getAppManager() {
        return AppManager.SingletonHolder.INTANCE;
    }


    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivityAndExit(Context context) {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    public Activity getTopActivity() {
        if (activityStack.empty()) {
            return null;
        } else {
            return activityStack.peek();
        }
    }

}