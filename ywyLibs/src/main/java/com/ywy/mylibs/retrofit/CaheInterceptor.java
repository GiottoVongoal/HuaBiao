package com.ywy.mylibs.retrofit;

import android.app.Activity;
import android.app.usage.NetworkStats;
import android.content.Context;
import android.widget.Toast;

import com.blankj.ALog;
import com.ywy.mylibs.utils.NetWorkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CaheInterceptor implements Interceptor {

    private Context context;
    private String cacheControlValue;
    private String cacheOnlineControlValue;
    //set cahe times is 3 days
    private static final int maxStale = 60 * 60 * 24 * 3;
    // read from cache for 60 s
    private static final int maxOnlineStale = 60;

    public CaheInterceptor(Context context) {
        this(context, String.format("max-stale=%d", maxOnlineStale));
    }

    public CaheInterceptor(Context context, String cacheControlValue) {
        this(context, cacheControlValue, String.format("max-stale=%d", maxStale));
    }

    public CaheInterceptor(Context context, String cacheControlValue, String cacheOnlineControlValue) {
        this.context = context;
        this.cacheControlValue = cacheControlValue;
        this.cacheOnlineControlValue = cacheOnlineControlValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String requestMessage = "--> " + request.method() + ' ' + request.url();
        ALog.i(requestMessage);
        if (NetWorkUtils.isNetWorkConnected(context)) {
            Response response = chain.proceed(request);
            String cacheControl = request.cacheControl().toString();
            ALog.e(maxOnlineStale + " Load Cahe " + cacheControl);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, " + cacheOnlineControlValue)
                    .build();
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "请检查网络连接情况!", Toast.LENGTH_SHORT).show();
                }
            });
            ALog.e("No NetWork Load Cahe");
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, " + cacheControlValue)
                    .build();
        }
    }
}
