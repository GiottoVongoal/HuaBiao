package com.ywy.mylibs.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ywy on 2016/5/30.
 */
public class ContextUtils {


    private static LayoutInflater inflater;

    public static View inflate(Context context, int res) {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater.inflate(res, null);
    }

    public static View inflate(Context context, int res, ViewGroup parent, boolean attachToRoot) {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater.inflate(res, parent, attachToRoot);
    }


}
