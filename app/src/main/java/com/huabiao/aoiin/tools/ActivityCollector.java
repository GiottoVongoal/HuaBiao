package com.huabiao.aoiin.tools;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.huabiao.aoiin.tools
 * @date 2016-11-07 13:33
 * @description Activity管理器
 */
public class ActivityCollector {

    public static List<Activity> activitys = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activitys) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
