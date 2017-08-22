package com.huabiao.aoiin.bean;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-08 13:20
 * @description 金融评估数据展示Bean
 */
public class FinanceEvakuateReportBean {

    /**
     * tradeimg : https://b-ssl.duitang.com/uploads/item/201504/30/20150430230912_tiAms.thumb.700_0.jpeg
     * tradename : 海飞丝
     * trademarkclassification : 日化用品
     * classificationid : 03
     * regnumber : 115521315
     * tradestatus : 1
     * tradegrade : 84
     * monthsales : 5645
     * hotrank : 23
     * similarproduct : {"tradename":"海飞丝","trademarkclassification":"日化用品","classificationid":"03","tradegrade":84,"monthsales":"5645","hotrank":"23"}
     */

    private String tradeimg;
    private String tradename;
    private String trademarkclassification;
    private String classificationid;
    private String regnumber;
    private int tradestatus;
    private int tradegrade;
    private String monthsales;
    private String hotrank;
    private SimilarproductBean similarproduct;

    public String getTradeimg() {
        return tradeimg;
    }

    public void setTradeimg(String tradeimg) {
        this.tradeimg = tradeimg;
    }

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

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }

    public int getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(int tradestatus) {
        this.tradestatus = tradestatus;
    }

    public int getTradegrade() {
        return tradegrade;
    }

    public void setTradegrade(int tradegrade) {
        this.tradegrade = tradegrade;
    }

    public String getMonthsales() {
        return monthsales;
    }

    public void setMonthsales(String monthsales) {
        this.monthsales = monthsales;
    }

    public String getHotrank() {
        return hotrank;
    }

    public void setHotrank(String hotrank) {
        this.hotrank = hotrank;
    }

    public SimilarproductBean getSimilarproduct() {
        return similarproduct;
    }

    public void setSimilarproduct(SimilarproductBean similarproduct) {
        this.similarproduct = similarproduct;
    }

    public static class SimilarproductBean {
        /**
         * tradename : 海飞丝
         * trademarkclassification : 日化用品
         * classificationid : 03
         * tradegrade : 84
         * monthsales : 5645
         * hotrank : 23
         */

        private String tradename;
        private String trademarkclassification;
        private String classificationid;
        private int tradegrade;
        private String monthsales;
        private String hotrank;

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

        public int getTradegrade() {
            return tradegrade;
        }

        public void setTradegrade(int tradegrade) {
            this.tradegrade = tradegrade;
        }

        public String getMonthsales() {
            return monthsales;
        }

        public void setMonthsales(String monthsales) {
            this.monthsales = monthsales;
        }

        public String getHotrank() {
            return hotrank;
        }

        public void setHotrank(String hotrank) {
            this.hotrank = hotrank;
        }
    }
}
