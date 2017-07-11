package com.huabiao.aoiin.model;

import android.content.Context;

import com.google.gson.Gson;
import com.huabiao.aoiin.bean.ClassificationItemBean;
import com.huabiao.aoiin.bean.ClassificationListBean;
import com.huabiao.aoiin.bean.SearchResultRegisteredBean;
import com.huabiao.aoiin.bean.SearchResultUnregisteredBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-06 14:51
 * @description 查询Model
 */

public class SearchModel {

    private static String getJson(Context context, String jsonFile) {
        try {
            InputStreamReader isr = new InputStreamReader(context.getAssets().open(jsonFile), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            isr.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void getSearchResult(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = getJson(context, "searchresultregistered.json");
        Gson gson = new Gson();
        SearchResultRegisteredBean bean = gson.fromJson(jsonString, SearchResultRegisteredBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    public static void getSearchUnregisterResult(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = getJson(context, "searchresultunregistered.json");
        Gson gson = new Gson();
        SearchResultUnregisteredBean bean = gson.fromJson(jsonString, SearchResultUnregisteredBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

    public static void getClassificationResult(Context context, String jsonname, final InterfaceManager.CallBackCommon callback) {
        String jsonString = getJson(context, jsonname);
        Gson gson = new Gson();
        ClassificationListBean bean = gson.fromJson(jsonString, ClassificationListBean.class);
        if (callback != null) {
            callback.getCallBackCommon(bean);
        }
    }

}
