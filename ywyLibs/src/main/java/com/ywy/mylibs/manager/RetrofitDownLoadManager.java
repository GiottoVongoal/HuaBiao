package com.ywy.mylibs.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.blankj.ALog;
import com.ywy.mylibs.listener.DownLoadCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class RetrofitDownLoadManager {

    private DownLoadCallBack callBack;
    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive";
    private static String PNG_CONTENTTYPE = "image/png";
    private static String JPG_CONTENTTYPE = "image/jpg";
    private static String fileSuffix = "";
    private Handler handler;
    public static boolean isDownLoading = false;
    public static boolean isCancel = false;
    private String apkName;

    public RetrofitDownLoadManager(String apkName, DownLoadCallBack callBack) {
        this.callBack = callBack;
        this.apkName = apkName;
        handler = new Handler(Looper.getMainLooper());
    }

    private static RetrofitDownLoadManager sInstance;

    /**
     * DownLoadManager getInstance
     */
    public static synchronized RetrofitDownLoadManager getInstance(String downloadPath, DownLoadCallBack callBack) {
        if (sInstance == null) {
            sInstance = new RetrofitDownLoadManager(downloadPath, callBack);
        }
        return sInstance;
    }

    public boolean writeResponseBodyToDisk(Context context, ResponseBody body) {

        ALog.d("contentType:>>>>" + body.contentType().toString());

        String type = body.contentType().toString();

        if (type.equals(APK_CONTENTTYPE)) {
            fileSuffix = ".apk";
        } else if (type.equals(PNG_CONTENTTYPE)) {
            fileSuffix = ".png";
        }
        // 其他同上 自己判断加入
//        final String name = System.currentTimeMillis() + fileSuffix;
        final String name = apkName + fileSuffix;
        final String path = context.getExternalFilesDir(null) + File.separator + name;
        ALog.d("path:-->" + path);
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(path);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                final long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                ALog.d("file length: " + fileSize);
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1 || isCancel) {
                        break;
                    }

                    isDownLoading = true;
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    ALog.d("file download: " + fileSizeDownloaded + " of " + fileSize);
                    if (callBack != null) {
                        final long finalFileSizeDownloaded = fileSizeDownloaded;
                        callBack.onLoading(fileSize, finalFileSizeDownloaded);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onProgress(finalFileSizeDownloaded);
                            }
                        }, 200);
                    }
                }

                outputStream.flush();
                ALog.d("file downloaded: " + fileSizeDownloaded + " of " + fileSize);

                isDownLoading = false;

                if (callBack != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                callBack.onSucess(path, name, fileSize);
                            }
                        }
                    });
                    ALog.d("file downloaded: " + fileSizeDownloaded + " of " + fileSize);
                }
                return true;
            } catch (IOException e) {
                if (callBack != null) {
                    callBack.onError(e);
                }
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            if (callBack != null) {
                callBack.onError(e);
            }
            return false;
        }
    }
}
