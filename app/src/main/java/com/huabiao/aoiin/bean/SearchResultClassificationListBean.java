package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-13 13:40
 * @description
 */
public class SearchResultClassificationListBean {

    private List<ClassificationBean> searchresultclassificationlist;

    public List<ClassificationBean> getClassification() {
        return searchresultclassificationlist;
    }

    public void setClassification(List<ClassificationBean> searchresultclassificationlist) {
        this.searchresultclassificationlist = searchresultclassificationlist;
    }

    @Override
    public String toString() {
        return "SearchResultClassificationListBean{" +
                "classification=" + searchresultclassificationlist +
                '}';
    }
}
