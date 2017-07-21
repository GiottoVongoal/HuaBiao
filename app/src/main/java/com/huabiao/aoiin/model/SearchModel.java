package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.ClassificationListBean;
import com.huabiao.aoiin.bean.SearchResultClassificationListBean;
import com.huabiao.aoiin.bean.SearchResultRegisteredBean;
import com.huabiao.aoiin.bean.SearchResultUnregisteredAndCreatNameBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-06 14:51
 * @description 查询Model
 */

public class SearchModel {

    /**
     * 获取查询结果--已注册--类型
     *
     * @param context
     * @param tradeName
     * @param goodsName
     * @param callback
     */
    public static void getSearchClassificationList(Context context, String tradeName, String goodsName, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "searchresultregisteredclassificationlist.json");
        Gson gson = new Gson();
        SearchResultClassificationListBean bean = gson.fromJson(jsonString, SearchResultClassificationListBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * 获取查询结果--已注册
     *
     * @param context
     * @param tradeName  商标名
     * @param goodsName      商品名
     * @param classification 类型
     * @param callback
     */
    public static void getSearchResult(Context context, String tradeName, String goodsName, String classification, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "searchresultregistered.json");
        Gson gson = new Gson();
        SearchResultRegisteredBean bean = gson.fromJson(jsonString, SearchResultRegisteredBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * 获取查询结果--未注册
     *
     * @param context
     * @param tradeName 商标名
     * @param goodsName     商品名
     * @param callback
     */
    public static void getSearchUnregisterResult(Context context, String tradeName, String goodsName, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "searchresultunregisteredandcreatname.json");
        Gson gson = new Gson();
        SearchResultUnregisteredAndCreatNameBean bean = gson.fromJson(jsonString, SearchResultUnregisteredAndCreatNameBean.class);
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
