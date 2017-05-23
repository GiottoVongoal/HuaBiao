package com.ywy.mylibs.base;

import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blankj.ALog;
import com.ywy.mylibs.R;
import com.ywy.mylibs.base.impl.IWebView;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.DeviceUtils;
import com.ywy.mylibs.utils.StringUtil;
import com.ywy.mylibs.wedgit.customWebviewInterceptor.DefaultIWebView;

import butterknife.ButterKnife;

/**
 * Created by yangweiyi on 16/9/2.
 */
public class WebViewAct extends BaseActivity {

    //    @Bind(R.id.wv_webview_content)
    WebView wv_webview_content;
    //    @Bind(R.id.tv_webview_title)
    TextView tv_webview_title;
    //    @Bind(R.id.rl_webview_title)
    RelativeLayout rl_webview_title;


    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    public static final String KEY_HASTITLLE = "hasTitel";
    public static final String KEY_IWEBVIEW = "iWebView";

    private String url, title;
    private boolean hasTitel = true;
    private IWebView iWevView;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    public void setOverrideUrlLoading(IWebView iWevView) {
        this.iWevView = iWevView;
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString(KEY_URL);
            title = bundle.getString(KEY_TITLE);
            hasTitel = bundle.getBoolean(KEY_HASTITLLE, true);
        }
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        findView();
        //响应返回键
        ButterKnife.findById(mRootView, R.id.iv_webview_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wv_webview_content.canGoBack()) {
                    wv_webview_content.goBack();
                } else {
                    ClickUtil.onBackClick();
                }
            }
        });

        if (iWevView == null) {
            iWevView = new DefaultIWebView();
        }

        //remove session cookies
        CookieManager cm = CookieManager.getInstance();
        cm.removeSessionCookie();

        wv_webview_content.getSettings().setJavaScriptEnabled(true);
        wv_webview_content.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_webview_content.setPadding(0, 0, 0, 0);
        //缩放等级25-100
        wv_webview_content.setInitialScale(DeviceUtils.getScale(this));
        wv_webview_content.getSettings().setSupportZoom(true);
        wv_webview_content.getSettings().setBuiltInZoomControls(true);
        wv_webview_content.getSettings().setUseWideViewPort(true);
        wv_webview_content.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv_webview_content.requestFocus(View.FOCUS_DOWN);
        wv_webview_content.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                boolean shouldOverride = false;
                if (iWevView != null) {
                    shouldOverride = iWevView.shouldOverrideUrl(WebViewAct.this, view, url);
                }
                ALog.i("override url:" + url);

                return shouldOverride;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (wv_webview_content.getTitle() != null && wv_webview_content.getTitle().length() > 0)
                    tv_webview_title.setText(wv_webview_content.getTitle());
            }
        });

        if (!StringUtil.isEmpty(url)) {
            wv_webview_content.loadUrl(url);
        }
        if (!StringUtil.isEmpty(title)) {
            tv_webview_title.setText(title);
        }
        if (hasTitel) {
            rl_webview_title.setVisibility(View.VISIBLE);
        } else {
            rl_webview_title.setVisibility(View.GONE);
        }
    }

    private void findView() {
        wv_webview_content = (WebView) findViewById(R.id.wv_webview_content);
        tv_webview_title = (TextView) findViewById(R.id.tv_webview_title);
        rl_webview_title = (RelativeLayout) findViewById(R.id.rl_webview_title);
    }

    @Override
    public int getContentLayout() {
        return R.layout.act_webview;
    }

}
