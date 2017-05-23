package com.ywy.mylibs.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.ALog;
import com.ywy.mylibs.base.BaseApplication;
import com.ywy.mylibs.manager.AppManager;

/**
 * 创建于 2014/4/17
 *
 * @author yangqinghai
 */
public class ToastUtils {

    private static ToastUtils mInstance;
    /**
     * toast实例
     */
    private Toast mToast;
    /**
     * toast 内容TextView
     */
    private TextView mContentTV;
    /**
     * toast 自定义View
     */
    private View mToastCustomView;

    private ToastUtils() {
    }

    /**
     * 获取Toast助手类实例
     *
     * @return
     */
    private static class SingletonHolder {
        private static final ToastUtils INTANCE = new ToastUtils();
    }

    public static ToastUtils getInstance() {
        return ToastUtils.SingletonHolder.INTANCE;
    }




    /**
     * 显示toast内容
     *
     * @param content：内容
     * @return
     */
    public Toast showToast(String content) {
        if(AppManager.getAppManager().getTopActivity() == null){
            ALog.e("TopActivity == null");
            return mToast;
        }
        if(mToast == null){
            mToast = Toast.makeText(AppManager.getAppManager().getTopActivity(),content, Toast.LENGTH_SHORT);
        }else {
            mToast.setText(content);
        }
        mToast.show();
        return mToast;
    }
    /**
     * 显示toast内容
     *
     * @param contentId：内容id
     * @return
     */
    public Toast showToast(int contentId){
        return showToast(BaseApplication.getContext().getString(contentId));
    }

    /**
     * 初始化自定义toast
     *
     * @param layout
     * @return
     */
    private Toast initToast(View layout) {
        if (mToast == null) {
            mToast = new Toast(BaseApplication.getContext());
            mToast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
//        mToast.setView(layout);
        return mToast;
    }
    /**
     * 初始化 toast
     * @return
     */
    private Toast initToast() {
        if (mToast == null) {
            mToast = new Toast(BaseApplication.getContext());
            mToast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
//        mToast.setView(layout);
        return mToast;
    }
}
