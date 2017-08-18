package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.BuyingInfoBean;
import com.huabiao.aoiin.bean.ClassificationListBean;
import com.huabiao.aoiin.bean.ScreenBean;
import com.huabiao.aoiin.bean.SearchResultBean;
import com.huabiao.aoiin.bean.CustomerServiceListBean;
import com.huabiao.aoiin.bean.MallBean;
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
     * 获取查询结果--未注册
     *
     * @param context
     * @param tradeName 商标名
     * @param goodsName 商品名
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

    /**
     * 获取求购详情页面的数据
     * 以及获取抢注页面和可异议页面的数据
     *
     * @param context
     * @param callback
     */
    public static void getBuyingInfo(Context context, int index, final InterfaceManager.CallBackCommon callback) {
        String jsonString = "";
        switch (index) {
            case 1:
                jsonString = GetJsonToName.getJson(context, "buyinginfo.json");
                break;
            case 2:
                jsonString = GetJsonToName.getJson(context, "yiyi.json");
                break;
            case 3:
                jsonString = GetJsonToName.getJson(context, "cybersquatting.json");
                break;
        }
        Gson gson = new Gson();
        BuyingInfoBean bean = gson.fromJson(jsonString, BuyingInfoBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /*
    获取商城列表全部
     */
    public static void getShoppingMallList(Context context, int index, final InterfaceManager.CallBackCommon callback) {
        String jsonfile = "";
        switch (index) {
            case 1:
                jsonfile = "shoppingmall.json";
                break;
            case 2:
                jsonfile = "canbuy.json";
                break;
            case 3:
                jsonfile = "cancybersquatting.json";
                break;
            case 4:
                jsonfile = "canyiyi.json";
                break;
        }
        String jsonString = GetJsonToName.getJson(context, jsonfile);
        Gson gson = new Gson();
        MallBean bean = gson.fromJson(jsonString, MallBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    /**
     * 获取客服列表
     *
     * @param context
     * @param callback
     */
    public static void getCustomerSerCustomerServiceList(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "customerservicelist.json");
        Gson gson = new Gson();
        CustomerServiceListBean bean = gson.fromJson(jsonString, CustomerServiceListBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

}
