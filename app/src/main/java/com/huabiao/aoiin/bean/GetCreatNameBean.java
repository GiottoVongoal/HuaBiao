package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-11 14:08
 * @description 取名返回的4个推荐名字Bean
 */
public class GetCreatNameBean {

    private List<RecommendnameListBean> recommendnameList;

    public List<RecommendnameListBean> getRecommendnameList() {
        return recommendnameList;
    }

    public void setRecommendnameList(List<RecommendnameListBean> recommendnameList) {
        this.recommendnameList = recommendnameList;
    }

    public static class RecommendnameListBean {
        /**
         * trademarkname : 飘柔
         * trademarkclassification : 日化用品
         * classificationid : 03
         * means : 我是这个商标名的含义,美丽的思考--飘柔
         * Xaxistag : ["周一","周二","周三","周四","周五","周六","周日"]
         * lines : [{"linename":"京东","linecolor":"#FF4300","linevalue":[7,7,6,7,5,6,8]},{"linename":"亚马逊","linecolor":"#FFEA00","linevalue":[2,2,5,4,7,7,3]}]
         */

        private String trademarkname;
        private String trademarkclassification;
        private String classificationid;
        private String means;
        private List<String> Xaxistag;
        private List<LinesBean> lines;

        public String getTrademarkname() {
            return trademarkname;
        }

        public void setTrademarkname(String trademarkname) {
            this.trademarkname = trademarkname;
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

        public String getMeans() {
            return means;
        }

        public void setMeans(String means) {
            this.means = means;
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
             * linename : 京东
             * linecolor : #FF4300
             * linevalue : [7,7,6,7,5,6,8]
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
        }
    }
}
