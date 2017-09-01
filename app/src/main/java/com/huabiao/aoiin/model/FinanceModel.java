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

//
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "aaa");
//        map.put("id", "1");
//        RetrofitClinetImpl.getInstance(context)
//                .newRetrofitClient()
//                .executePost("sdfds/sdfa"
//                        , map
//                        , new RetrofitClient.ResponseCallBack<FinanceEvakuateReportBean>() {
//                            @Override
//                            public void onSucceess(FinanceEvakuateReportBean response) {
//                                if (response.getresultCode() == 10000) {
//                                    if (callBack != null) {
//                                        callBack.getCallBackCommon(response);
//                                    }
//                                }else{
//                                    ALog.i(response.getstateDescription());
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Throwable e) {
//                                ALog.i(e.getMessage());
                            }
//                        });
//    }

}
