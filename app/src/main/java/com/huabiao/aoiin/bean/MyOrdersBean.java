package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/24.
 */

public class MyOrdersBean {
    private List<MyorderslistBean> myorderslist;

    public List<MyorderslistBean> getMyorderslist() {
        return myorderslist;
    }

    public void setMyorderslist(List<MyorderslistBean> myorderslist) {
        this.myorderslist = myorderslist;
    }

    public static class MyorderslistBean {
        /**
         * goodsname : 海飞丝
         * goodsimg :
         * price : 98500
         * classificationname : 日用品
         * classificationid : 01
         * status : 1
         */

        private String goodsname;
        private String goodsimg;
        private String price;
        private String classificationname;
        private String classificationid;
        private String status;

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getGoodsimg() {
            return goodsimg;
        }

        public void setGoodsimg(String goodsimg) {
            this.goodsimg = goodsimg;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
