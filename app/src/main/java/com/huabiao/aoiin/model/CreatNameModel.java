package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.GetCreatNameBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-11 14:59
 * @description 取名Model
 */
public class CreatNameModel {
    /**
     * @param context
     * @param goodsname 商品名
     * @param type      商品类别
     * @param callback
     * @describe 取名的推荐数据
     */
    public static void getCreatNameResult(Context context, String goodsname, String type, final InterfaceManager.CallBackCommon callback) {
        String jsonString = GetJsonToName.getJson(context, "getcreatnamelist.json");
        Gson gson = new Gson();
        GetCreatNameBean bean = gson.fromJson(jsonString, GetCreatNameBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }
}
