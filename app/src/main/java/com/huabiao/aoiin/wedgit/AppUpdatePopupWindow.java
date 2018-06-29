package com.huabiao.aoiin.wedgit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.ALog;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.retrofit.RetrofitClinetImpl;
import com.huabiao.aoiin.tools.ComUtil;
import com.huabiao.aoiin.tools.ImageUtil;
import com.ywy.mylibs.listener.DownLoadCallBack;
import com.ywy.mylibs.utils.DeviceUtils;

import java.io.File;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * 版本更新
 */

public class AppUpdatePopupWindow extends PopupWindow implements View.OnClickListener {

    private View mPopView;
    private NumberProgressBar mNumberProgressBar;
    private Context mContext;
    private Button btnUpdate;
    private ImageView ivCancel;
    private Boolean isDownloadSuccess = false;// 是否下载成功
    private String mServerPath;// 服务器下载地址
    private String mDownloadPath;// 下载本地地址
    private String mUpdateCon;// 更新内容
    private Boolean isForceUpdate = false;// 是否强制更新
    private TextView mTvUpdateCon;
    private final int DOWNLOAD_SCHEDULE = 200;
    private File mDownLoadFile;
    private TextView number_progress_bar_tv;

    private String apkName = "app";


    public AppUpdatePopupWindow(Context context, boolean isForceUpdate, String serverPath, String updateCon) {
        super(context);
        mContext = context;
        this.isForceUpdate = isForceUpdate;
        this.mServerPath = serverPath;
        this.mUpdateCon = updateCon;
        init(context);
        setPopupWindow();
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.layout_popup_window_app_update, null);
        // 设置图片
        ImageView ivTop = (ImageView) mPopView.findViewById(R.id.iv_top);
        ImageUtil.calImageHeightBySpecifiWidth(mContext, ivTop, R.mipmap.bg_app_update_top,
                DeviceUtils.dip2px(context, 350));
        // 更新内容
        mTvUpdateCon = (TextView) mPopView.findViewById(R.id.tv_update_con);
        mTvUpdateCon.setText(mUpdateCon);
        // 更新
        btnUpdate = (Button) mPopView.findViewById(R.id.btn_update);
        // 取消
        ivCancel = (ImageView) mPopView.findViewById(R.id.iv_cancel);
        // 进度条
        mNumberProgressBar = (NumberProgressBar) mPopView.findViewById(R.id.number_progress_bar);

        number_progress_bar_tv = (TextView) mPopView.findViewById(R.id.number_progress_bar_tv);

        btnUpdate.setOnClickListener(this);
        ivCancel.setOnClickListener(this);
        mDownloadPath = ComUtil.getSDPath() + "/app.apk";// 设置下载地址
        // 强制更新隐藏取消按钮
        if (isForceUpdate) {
            ivCancel.setVisibility(View.GONE);
        }
    }

    /**
     * 设置窗口的相关属性
     */
    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);// 设置弹出窗口的高
        this.setFocusable(false);// 设置弹出窗口可
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:// 更新
                if (isDownloadSuccess) {
                    ComUtil.installApk(mContext, mDownloadPath);// 安装
                } else {
                    downloadFile(mServerPath);
                }
                break;
            case R.id.iv_cancel:// 取消
                dismiss();
                break;
        }
    }

    private void downloadFile(final String url) {
        mNumberProgressBar.setVisibility(View.VISIBLE);
        RetrofitClinetImpl.getInstance(mContext)
                .newRetrofitClient()
                .download(apkName
                        , url
                        , new DownLoadCallBack() {
                            @Override
                            public void onSucess(String path, String name, long fileSize) {
                                mDownLoadFile = new File(path);
                                // 安装APK
//                                ComUtil.installApk(mContext, path);
                                installApk(path);
                            }

                            @Override
                            public void onLoading(long total, long current) {
                                super.onLoading(total, current);
                                double i = current / (double) total * 100;
                                final int progress = (int) Math.ceil(i);
                                mNumberProgressBar.setProgress(progress);
                                number_progress_bar_tv.setText(progress + "");
                            }

                            @Override
                            public void onError(Throwable e) {
                                ALog.d("e: " + e.getMessage());
                                mNumberProgressBar.setProgress(0);

                            }
                        });
    }

    private void installApk(String path) {
        File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(context, "com.huabiao.aoiin", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }
}
