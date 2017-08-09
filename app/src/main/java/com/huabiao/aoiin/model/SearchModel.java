package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.ClassificationListBean;
import com.huabiao.aoiin.bean.ScreenBean;
import com.huabiao.aoiin.bean.SearchResultBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-06 14:51
 * @description 查询Model
 */

public class SearchModel {

    /**
     * 获取查询结果筛选条件列表
     *
     * @param context
     * @param callback
     */
    public static void getSelectClassificationList(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "screenlist.json");
        Gson gson = new Gson();
        ScreenBean bean = gson.fromJson(jsonString, ScreenBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * 获取查询结果
     *
     * @param context
     * @param classificationId 类型
     * @param callback
     */
    public static void getSearchResult(Context context, String classificationId, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "searchresultregistered.json");
        Gson gson = new Gson();
        SearchResultBean bean = gson.fromJson(jsonString, SearchResultBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * 获取商标分类
     *
     * @param context
     * @param jsonname
     * @param callback
     */
    public static void getClassificationResult(Context context, String jsonname, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, jsonname);
        Gson gson = new Gson();
        ClassificationListBean bean = gson.fromJson(jsonString, ClassificationListBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

}
