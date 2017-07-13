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

    public static class ClassificationBean {
        /**
         * classificationname : 化学制剂
         * classificationid : 01
         */

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

    @Override
    public String toString() {
        return "SearchResultClassificationListBean{" +
                "classification=" + searchresultclassificationlist +
                '}';
    }
}
