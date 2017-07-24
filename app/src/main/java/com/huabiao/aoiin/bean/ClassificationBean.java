package com.huabiao.aoiin.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-14 16:08
 * @description 公用的类型名称和id
 */
public class ClassificationBean {
    private String classificationname;
    private String classificationid;

    public String getClassificationname() {
        return classificationname;
    }

    public void setClassificationname(String classificationname) {
        this.classificationname = classificationname;
    }

    public String getClassificationid() {
        return classificationid;
    }

    public void setClassificationid(String classificationid) {
        this.classificationid = classificationid;
    }

    @Override
    public String toString() {
        return "ClassificationBean{" +
                "classificationname='" + classificationname + '\'' +
                ", classificationid='" + classificationid + '\'' +
                '}';
    }

}
