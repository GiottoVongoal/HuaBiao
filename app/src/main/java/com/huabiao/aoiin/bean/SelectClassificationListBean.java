package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-13 13:40
 * @description
 */
public class SelectClassificationListBean {

    private List<ClassificationBean> selectclassificationlist;

    public List<ClassificationBean> getClassification() {
        return selectclassificationlist;
    }

    public void setClassification(List<ClassificationBean> selectclassificationlist) {
        this.selectclassificationlist = selectclassificationlist;
    }

    @Override
    public String toString() {
        return "SelectClassificationListBean{" +
                "classification=" + selectclassificationlist +
                '}';
    }
}
