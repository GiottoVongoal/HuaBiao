package com.ywy.mylibs.utils;

import android.content.Context;

import com.ywy.mylibs.wedgit.dialog.CommonLoadingDialog;

/**
 * @author shaoshuai.
 * @PackageName com.cheyipai.core.base.retrofit.uitls
 * @date 2016-10-21 15:33
 * @description
 */
public class CommonDialogUtils {

    /**
     * @param context
     * @param message
     * @Title: showLoadingDialog
     * @Description: 显示Loading框
     * @return: void
     */
    public static CommonLoadingDialog showLoadingDialog(Context context,
                                                        String message) {
        CommonLoadingDialog mLoadingDialog = null;
        if (mLoadingDialog == null) {
            mLoadingDialog = new CommonLoadingDialog(context, message);
        }

        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
        return mLoadingDialog;
    }

    /**
     * @param mLoadingDialog
     * @Title: dismissLoadingDialog
     * @Description: 关闭对话框
     * @return: void
     */
    public static void dismissLoadingDialog(CommonLoadingDialog mLoadingDialog) {
        if (mLoadingDialog == null)
            return;
        if (!mLoadingDialog.isShowing())
            return;
        mLoadingDialog.dismiss();
    }
}
