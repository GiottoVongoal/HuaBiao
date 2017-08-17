package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/7/27.
 */

public class MallBean {

    private List<ShoppingmalllistBean> shoppingmalllist;

    public List<ShoppingmalllistBean> getShoppingmalllist() {
        return shoppingmalllist;
    }

    public void setShoppingmalllist(List<ShoppingmalllistBean> shoppingmalllist) {
        this.shoppingmalllist = shoppingmalllist;
    }

    public static class ShoppingmalllistBean {
        /**
         * goodsname : 海飞丝
         * goodsimg :
         * regnumber : 16404103
         * applicantname : 广州美思古智能家居有限公司
         * classificationname : 金属材料
         * classificationid : 06
         * status : 1
         */

        private String goodsname;
        private String goodsimg;
        private String regnumber;
        private String applicantname;
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

        public String getRegnumber() {
            return regnumber;
        }

        public void setRegnumber(String regnumber) {
            this.regnumber = regnumber;
        }

        public String getApplicantname() {
            return applicantname;
        }

        public void setApplicantname(String applicantname) {
            this.applicantname = applicantname;
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
