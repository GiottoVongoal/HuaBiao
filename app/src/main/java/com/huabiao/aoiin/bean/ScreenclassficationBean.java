package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/9/1.
 */

public class ScreenclassficationBean {

    /**
     * sname : 所有分类
     * slist : [{"classificationname":"化学制剂","classificationid":"01"},{"classificationname":"颜料油漆","classificationid":"02"},{"classificationname":"日化用品","classificationid":"03"},{"classificationname":"燃料油脂","classificationid":"04"},{"classificationname":"医药","classificationid":"05"},{"classificationname":"金属材料","classificationid":"06"},{"classificationname":"机械设备","classificationid":"07"},{"classificationname":"手工器械","classificationid":"08"},{"classificationname":"科学仪器","classificationid":"09"},{"classificationname":"医疗器械","classificationid":"10"}]
     */

    private String sname;
    private List<SlistBean> slist;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public List<SlistBean> getSlist() {
        return slist;
    }

    public void setSlist(List<SlistBean> slist) {
        this.slist = slist;
    }

    public static class SlistBean {
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
    }
}
