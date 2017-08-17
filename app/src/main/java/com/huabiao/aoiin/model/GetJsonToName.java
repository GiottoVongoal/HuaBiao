package com.huabiao.aoiin.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.model
 * @date 2017-07-11 15:00
 * @description 通过文件名获取json
 */
public class GetJsonToName {
    public static String getJson(Context context, String jsonFile) {
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
}
