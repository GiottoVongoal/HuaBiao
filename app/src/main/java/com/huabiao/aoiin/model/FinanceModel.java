package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.FinanceEvakuateReportBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-08-08 13:23
 * @description 金融页面Model
 */
public class FinanceModel {
    /**
     * 获取商标评估报告数据
     *
     * @param context
     * @param callBack
     */
    public static void getFinanceEvaluateReport(Context context, final InterfaceManager.CallBackCommon callBack) {
        String json = GetJsonToName.getJson(context, "financeevaluatereport.json");
        Gson gson = new Gson();
        FinanceEvakuateReportBean bean = gson.fromJson(json, FinanceEvakuateReportBean.class);
        if (callBack != null) {
            callBack.getCallBackCommon(bean);
        }
    }

}
