package com.huabiao.aoiin.ui.view;

import android.content.Context;

/**
 * Created by Aoiin-9 on 2017/7/18.
 */

public class Util {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
