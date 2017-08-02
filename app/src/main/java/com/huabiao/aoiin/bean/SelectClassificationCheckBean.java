package com.huabiao.aoiin.bean;

import android.os.Parcel;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-14 17:05
 * @description 可注册类别的选择项
 */
public class SelectClassificationCheckBean extends ClassificationBean {

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
