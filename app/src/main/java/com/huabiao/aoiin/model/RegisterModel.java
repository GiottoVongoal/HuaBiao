package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.CustomerServiceListBean;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-12 11:06
 * @description Tab中的注册Model
 */
public class RegisterModel {

    /**
     * 获取行业列表
     *
     * @param context
     * @param callBack
     */
    public static void getIndustryList(Context context, final InterfaceManager.CallBackCommon callBack) {
        String json = GetJsonToName.getJson(context, "registeroneindustry.json");
        Gson gson = new Gson();
        RegisterOneIndustryBean bean = gson.fromJson(json, RegisterOneIndustryBean.class);
        if (callBack != null) {
            callBack.getCallBackCommon(bean);
        }
    }

    /**
     * 获取推荐客服列表
     *
     * @param context
     * @param callBack
     */
    public static void getCustomerServiceList(Context context, final InterfaceManager.CallBackCommon callBack) {
        String json = GetJsonToName.getJson(context, "customerservicelist.json");
        Gson gson = new Gson();
        CustomerServiceListBean bean = gson.fromJson(json, CustomerServiceListBean.class);
        if (callBack != null) {
            callBack.getCallBackCommon(bean);
        }
    }

    /**
     * 获取注册页面数据
     *
     * @param context
     * @param callBack
     */
    public static void getRegisterData(Context context, final InterfaceManager.CallBackCommon callBack) {
        String json = GetJsonToName.getJson(context, "registerjson.json");
        Gson gson = new Gson();
        RegisterBean bean = gson.fromJson(json, RegisterBean.class);
        if (callBack != null) {
            callBack.getCallBackCommon(bean);
        }
    }

}
