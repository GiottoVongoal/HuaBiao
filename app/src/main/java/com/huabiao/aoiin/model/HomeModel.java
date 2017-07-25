package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.HomeBannarBean;
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
     * @describe 获取Banner图集合
     */
    public static void getBannarList(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "bannarjson.json");
        Gson gson = new Gson();
        HomeBannarBean bean = gson.fromJson(jsonString, HomeBannarBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }
}
