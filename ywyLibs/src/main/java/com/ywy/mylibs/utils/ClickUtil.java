package com.ywy.mylibs.utils;

import android.app.Instrumentation;
import android.view.KeyEvent;

/**
 * 防止用户多次连续暴力点击
 */

public class ClickUtil {
    private static long lastClickTime;
    private static long heartClickTime;
    private static long exitClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isExitDoubleClick() {

        long time = System.currentTimeMillis();
        long timeD = time - exitClickTime;
        if (timeD < 2000) {
            return true;
        }
        exitClickTime = time;
        return false;
    }

    public static boolean isAddHeartClick() {
        long time = System.currentTimeMillis();
        long timeD = time - heartClickTime;
        if (0 < timeD && timeD < 10000) {
            return false;
        }
        heartClickTime = time;
        return true;
    }


    public static void onBackClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
            }
        }).start();
    }
}
