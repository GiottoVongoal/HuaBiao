package com.ywy.mylibs.listener;

/**
 * @author shaoshuai.
 * @PackageName com.cyp.p.retrofit.call
 * @date 2016-09-28 17:04
 * @description down callback
 */
public abstract class DownLoadCallBack {

    public void onStart() {
    }

    public void onCancel() {
    }

    public void onCompleted() {
    }

    abstract public void onError(Throwable e);

    public void onProgress(long fileSizeDownloaded) {
    }

    abstract public void onSucess(String path, String name, long fileSize);
}
