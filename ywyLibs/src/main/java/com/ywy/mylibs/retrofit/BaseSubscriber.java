package com.ywy.mylibs.retrofit;

import android.content.Context;


import com.blankj.ALog;
import com.ywy.mylibs.utils.CommonDialogUtils;
import com.ywy.mylibs.wedgit.dialog.CommonLoadingDialog;

import rx.Subscriber;

/**
 * @author shaoshuai.
 * @PackageName com.cyp.p.retrofit.net
 * @date 2016-09-28 17:04
 * @description base result
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private CommonLoadingDialog mLoadingDialog;

    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        this.mContext = context;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setLoadingDialog(CommonLoadingDialog loadingDialog) {
        this.mLoadingDialog = loadingDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        // todo some common as show loadding  and check netWork is NetworkAvailable
        // todo if  NetworkAvailable no !   must to call onCompleted
    }

    @Override
    public void onError(Throwable e) {
        if (mLoadingDialog != null)
            CommonDialogUtils.dismissLoadingDialog(mLoadingDialog);
        if (e != null)
            ALog.e("onError--> " + e.getMessage());
    }

    @Override
    public void onCompleted() {
        // todo some common as dismiss loadding
        if (mLoadingDialog != null)
            CommonDialogUtils.dismissLoadingDialog(mLoadingDialog);
    }
}
