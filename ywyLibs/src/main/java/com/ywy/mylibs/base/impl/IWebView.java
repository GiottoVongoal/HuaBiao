package com.ywy.mylibs.base.impl;

import android.content.Context;
import android.webkit.WebView;

import java.io.Serializable;

/**
 * Created by yangweiyi on 16/9/2.
 */
public interface IWebView extends Serializable {
    boolean shouldOverrideUrl(Context context, WebView view, String url);
}
