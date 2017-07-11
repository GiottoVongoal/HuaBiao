package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-10 11:13
 * @description 分类--小类bean
 */
public class ClassificationItemBean {

    private String classificationname;
    private String classificationid;
    private boolean nextlevel;

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

    public boolean isNextlevel() {
        return nextlevel;
    }

    public void setNextlevel(boolean nextlevel) {
        this.nextlevel = nextlevel;
    }
}
