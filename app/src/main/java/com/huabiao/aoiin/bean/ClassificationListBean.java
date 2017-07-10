package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-10 11:13
 * @description
 */
public class ClassificationListBean {

    private List<ClassificationlistBean> classificationlist;

    public List<ClassificationlistBean> getClassificationlist() {
        return classificationlist;
    }

    public void setClassificationlist(List<ClassificationlistBean> classificationlist) {
        this.classificationlist = classificationlist;
    }

    public static class ClassificationlistBean {
        /**
         * classificationname : 氙
         * classificationid : 010551
         * nextlevel : false
         */

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
}
