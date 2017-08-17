package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-09 13:17
 * @description 查询结果的筛选数据
 */
public class ScreenBean {

    private List<ScreenlistBean> screenlist;

    public List<ScreenlistBean> getScreenlist() {
        return screenlist;
    }

    public void setScreenlist(List<ScreenlistBean> screenlist) {
        this.screenlist = screenlist;
    }

    public static class ScreenlistBean {
        /**
         * sname : 相关分类
         * slist : [{"classificationname":"化学制剂","classificationid":"01"},{"classificationname":"颜料油漆","classificationid":"02"},{"classificationname":"金属材料","classificationid":"06"},{"classificationname":"机械设备","classificationid":"07"}]
         */

        private String sname;
        private List<ClassificationBean> slist;

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public List<ClassificationBean> getSlist() {
            return slist;
        }

        public void setSlist(List<ClassificationBean> slist) {
            this.slist = slist;
        }
    }
}
