package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.TradeFormBean;
import com.huabiao.aoiin.bean.TradeFormListBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.CallBackCommon;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-08-09 14:48
 * @description
 */
public class MeModel {

    /**
     * 获取个人中心商标注册表单详情数据
     *
     * @param tradeid
     * @param context
     * @param callBack
     */
    public static void getTradeFormBean(Context context, String tradeid, final CallBackCommon callBack) {
        String jsonString = GetJsonToName.getJson(context, "tradeform.json");
        Gson gson = new Gson();
        TradeFormBean bean = gson.fromJson(jsonString, TradeFormBean.class);
        if (callBack != null) {
            callBack.getCallBackCommon(bean);
        }
    }

    /**
     * 获取个人中心商标注册表单列表数据
     *
     * @param context
     * @param callback
     */
    public static void getTradeFormList(Context context, final CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "tradeformlist.json");
        Gson gson = new Gson();
        TradeFormListBean bean = gson.fromJson(jsonString, TradeFormListBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }
}
