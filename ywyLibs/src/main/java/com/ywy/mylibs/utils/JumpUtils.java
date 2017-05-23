package com.ywy.mylibs.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ywy.mylibs.base.BindFragmentAct;
import com.ywy.mylibs.base.WebViewAct;
import com.ywy.mylibs.base.impl.IWebView;

/**
 * 跳转事件处理
 */

public class JumpUtils {


    /**
     * 根据id进行页面跳转,针对跳转到fragment
     *
     * @param context
     * @param toClass
     */
    public static void startFragmentByName(Context context, Class toClass) {
        startFragmentById(context, toClass, null);
    }

    public static void startFragmentById(Context context, Class toClass, Bundle bundle) {
        String className = toClass.getName();
        Intent intent = new Intent(context, BindFragmentAct.class);
        intent.putExtra("className", className);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }


    /**
     * 根据id进行页面跳转(无参数)
     *
     * @param context
     * @param toClass
     */
    public static void startActivity(Context context, Class toClass) {
        startActivity(context, toClass, null);
    }

    /**
     * 根据id进行页面跳转(带参数)
     *
     * @param context
     * @param toClass
     * @param bundle
     */
    public static void startActivity(Context context, Class toClass, Bundle bundle) {
        Intent intent = new Intent(context, toClass);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }


    public static void loadWebViewWithOutTitle(Context context, String url) {
        loadWebView(context, url, null, false, null);
    }

    public static void loadWebView(Context context, String url) {
        loadWebView(context, url, null, true, null);
    }

    public static void loadWebView(Context context, String url, String title) {
        loadWebView(context, url, title, true, null);
    }

    /**
     * 跳转webview
     *
     * @param context
     * @param url
     * @param title
     * @param haiTitle
     */
    public static void loadWebView(Context context, String url, String title, boolean haiTitle, IWebView iWebView) {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewAct.KEY_URL, url);
        bundle.putString(WebViewAct.KEY_TITLE, title);
        bundle.putBoolean(WebViewAct.KEY_HASTITLLE, haiTitle);
        bundle.putSerializable(WebViewAct.KEY_IWEBVIEW, iWebView);
        startActivity(context, WebViewAct.class, bundle);
    }
}
