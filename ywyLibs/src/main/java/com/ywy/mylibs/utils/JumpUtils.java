package com.ywy.mylibs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.ywy.mylibs.R;
import com.ywy.mylibs.base.BindFragmentAct;
import com.ywy.mylibs.base.WebViewAct;
import com.ywy.mylibs.base.impl.IWebView;

/**
 * 跳转事件处理
 */

public class JumpUtils {
    static final String EXTRA_SAMPLE = "sample";
    static final String EXTRA_TYPE = "type";
    static final int TYPE_PROGRAMMATICALLY = 0;
    static final int TYPE_XML = 1;

    /**
     * 根据id进行页面跳转,针对跳转到fragment
     *
     * @param context
     * @param toClass
     */
    public static void startFragmentByName(Context context, Class toClass) {
        startFragmentByName(context, toClass, null);
    }

    public static void startFragmentByName(Context context, Class toClass, Bundle bundle) {
        Intent i = new Intent(context, BindFragmentAct.class);
//        i.putExtra(EXTRA_SAMPLE, "sample");
//        i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
//        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants((Activity) context, true);
//        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs);
        String className = toClass.getName();
        i.putExtra("className", className);
        if (bundle != null)
            i.putExtras(bundle);
//        context.startActivity(i, transitionActivityOptions.toBundle());
        context.startActivity(i);
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
        Intent i = new Intent(context, toClass);
//        i.putExtra(EXTRA_SAMPLE, "sample");
//        i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
//        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants((Activity) context, true);
//        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs);
        if (bundle != null)
            i.putExtras(bundle);
//        context.startActivity(i, transitionActivityOptions.toBundle());
        context.startActivity(i);
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
