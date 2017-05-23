package com.ywy.mylibs.wedgit.customWebviewInterceptor;

import android.content.Context;
import android.webkit.WebView;

import com.ywy.mylibs.base.impl.IWebView;
import com.ywy.mylibs.utils.ParseUtils;

import java.util.Map;

/**
 * Created by yangweiyi on 16/9/2.
 */
public class DefaultIWebView implements IWebView {

    @Override
    public boolean shouldOverrideUrl(Context context, WebView view, String url) {
        if (url != null && url.contains("?action=")) {
            Map<String, String> map = ParseUtils.URLRequest(url);
            if (map != null) {
                String action = map.get("action");
                if ("PTVRecharge".equalsIgnoreCase(action)) {
                    //跳转充值页面
                    return true;
                }
            }
        }
        return false;
    }

}
