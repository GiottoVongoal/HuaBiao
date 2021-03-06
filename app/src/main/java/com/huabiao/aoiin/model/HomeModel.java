package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.HomeBean;
import com.huabiao.aoiin.bean.UserProgressDateBean;
import com.huabiao.aoiin.bean.UserProgressListBean;
import com.huabiao.aoiin.bean.UserTrademarkProgressListBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-20 17:41
 * @description
 */
public class HomeModel {
    /**
     * @param context
     * @param callback
     * @describe 获取首页数据
     */
    public static void getHomeData(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "home.json");
        Gson gson = new Gson();
        HomeBean bean = gson.fromJson(jsonString, HomeBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * @param context
     * @param year
     * @param month
     * @param day
     * @param callback
     * @describe 获取进度中当前日期的进度列表数据
     */
    public static void getProgressDateList(Context context, int year, int month, int day, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "progressdatelistjson.json");
        Gson gson = new Gson();
        UserProgressDateBean bean = gson.fromJson(jsonString, UserProgressDateBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * @param context
     * @param callback
     * @describe 获取进度中商标列表
     */
    public static void getUserTrademarkProgressListBean(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "usertrademarkprogresslist.json");
        Gson gson = new Gson();
        UserTrademarkProgressListBean bean = gson.fromJson(jsonString, UserTrademarkProgressListBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * @param context
     * @param trademarkId
     * @param callback
     * @describe 首页进度中根据商标ID获取商标进度数据
     */
    public static void getUserProgressList(Context context, String trademarkId, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "userprogresslist.json");
        Gson gson = new Gson();
        UserProgressListBean bean = gson.fromJson(jsonString, UserProgressListBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }
}
