package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-07 09:17
 * @description 折线图Bean
 */
public class LineChartBean {
    /**
     * tradename : 海飞丝
     * trademarkclassification : 日化用品
     * classificationid : 03
     * Xaxistag : ["周一","周二","周三","周四","周五","周六","周日"]
     * lines : [{"linename":"百度","linecolor":"#419BF9","linevalue":[9,7,6,7,8,6,8]},{"linename":"阿里","linecolor":"#FF4081","linevalue":[2,5,7,4,4,8,6]}]
     */

    private String tradename;
    private String trademarkclassification;
    private String classificationid;
    private List<String> Xaxistag;
    private List<LinesBean> lines;

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

    public List<String> getXaxistag() {
        return Xaxistag;
    }

    public void setXaxistag(List<String> Xaxistag) {
        this.Xaxistag = Xaxistag;
    }

    public List<LinesBean> getLines() {
        return lines;
    }

    public void setLines(List<LinesBean> lines) {
        this.lines = lines;
    }

    public static class LinesBean {
        /**
         * linename : 百度
         * linecolor : #419BF9
         * linevalue : [9,7,6,7,8,6,8]
         */

        private String linename;
        private String linecolor;
        private List<Integer> linevalue;

        public String getLinename() {
            return linename;
        }

        public void setLinename(String linename) {
            this.linename = linename;
        }

        public String getLinecolor() {
            return linecolor;
        }

        public void setLinecolor(String linecolor) {
            this.linecolor = linecolor;
        }

        public List<Integer> getLinevalue() {
            return linevalue;
        }

        public void setLinevalue(List<Integer> linevalue) {
            this.linevalue = linevalue;
        }

        @Override
        public String toString() {
            return "LinesBean{" +
                    "linename='" + linename + '\'' +
                    ", linecolor='" + linecolor + '\'' +
                    ", linevalue=" + linevalue +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LineChartBean{" +
                "tradename='" + tradename + '\'' +
                ", trademarkclassification='" + trademarkclassification + '\'' +
                ", classificationid='" + classificationid + '\'' +
                ", Xaxistag=" + Xaxistag +
                ", lines=" + lines +
                '}';
    }
}
