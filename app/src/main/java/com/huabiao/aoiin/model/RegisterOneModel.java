package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-12 11:06
 * @description Tab中的注册Model
 */
public class RegisterOneModel {

    /**
     * 获取注册页面的行业列表
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
}