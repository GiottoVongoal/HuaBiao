package com.ywy.mylibs.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 设备信息管理工具
 * Created by ywy on 2016/4/15.
 */
public class DeviceUtils {

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * dip转换px
     */
    public static int dip2px(Context context, float dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }


    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @param mContext
     * @return
     */
    public static int getScreenWidth(Context mContext) {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param mContext
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * setting margins
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModels() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static String getPhoneVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取sdk版本号
     *
     * @return
     */
    public static String getPhoneVersionSDK() {
        return android.os.Build.VERSION.SDK;
    }

    /**
     * 获取imei
     *
     * @return
     */
    public static String getImeiCode(Context context) {
        String sImeiCode;

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        sImeiCode = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(sImeiCode)) {// imei码为空时
            String property = PropertiesConfig.getInstance().getProperty("TAG");
            if (!TextUtils.isEmpty(property)) {
                sImeiCode = property;
            } else {// 生成标识
                sImeiCode = MD5Utils.MD5(System.nanoTime() + "@xiaozhu");
                PropertiesConfig.getInstance().setProperty("TAG", sImeiCode);
            }
        }
        return sImeiCode;
    }

    /**
     * 获取缩放等级
     *
     * @return
     */
    public static int getScale(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width) / new Double(640);
        val = val * 100d;
        return val.intValue();
    }
}
