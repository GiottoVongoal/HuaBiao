package com.ywy.mylibs.retrofit;

import android.content.Context;

import com.blankj.ALog;
import com.ywy.mylibs.listener.DownLoadCallBack;
import com.ywy.mylibs.manager.RetrofitDownLoadManager;

import rx.Subscriber;

public class DownSubscriber<ResponseBody extends okhttp3.ResponseBody> extends Subscriber<ResponseBody> {
    private DownLoadCallBack callBack;
    private Context context;
    private String apkName;

    public DownSubscriber(String apkName, DownLoadCallBack callBack, Context context) {
        this.apkName = apkName;
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (callBack != null) {
            callBack.onStart();
        }
    }

    @Override
    public void onCompleted() {
        if (callBack != null) {
            callBack.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        ALog.e("CoreDownSubscriber--> onError:" + e.getMessage());
        if (callBack != null) {
            callBack.onError(e);
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        ALog.d("CoreDownSubscriber:--> onNext");
        RetrofitDownLoadManager.getInstance(apkName, callBack).writeResponseBodyToDisk(context, responseBody);
    }
}
