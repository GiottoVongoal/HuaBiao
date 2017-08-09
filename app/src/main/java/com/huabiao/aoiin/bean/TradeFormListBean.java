package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-09 15:49
 * @description 商标注册表单列表Bean
 */
public class TradeFormListBean {

    private List<TradeFormItemBean> tradeformlist;

    public List<TradeFormItemBean> getTradeformlist() {
        return tradeformlist;
    }

    public void setTradeformlist(List<TradeFormItemBean> tradeformlist) {
        this.tradeformlist = tradeformlist;
    }

    public static class TradeFormItemBean {
        /**
         * tradename : 飘柔商标注册表单
         * trademarkclassification : 日化用品
         * classificationid : 03
         * logoimg :
         */

        private String tradename;
        private String trademarkclassification;
        private String classificationid;
        private String logoimg;

        public String getTradename() {
            return tradename;
        }

        public void setTradename(String tradename) {
            this.tradename = tradename;
        }

        public String getTrademarkclassification() {
            return trademarkclassification;
        }

        public void setTrademarkclassification(String trademarkclassification) {
            this.trademarkclassification = trademarkclassification;
        }

        public String getClassificationid() {
            return classificationid;
        }

        public void setClassificationid(String classificationid) {
            this.classificationid = classificationid;
        }

        public String getLogoimg() {
            return logoimg;
        }

        public void setLogoimg(String logoimg) {
            this.logoimg = logoimg;
        }
    }
}
