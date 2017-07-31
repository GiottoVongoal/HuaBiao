package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/7/18.
 */

public class CreatNameBean {

    private List<RecommendnamelistBean> recommendnamelist;

    public List<RecommendnamelistBean> getRecommendnamelist() {
        return recommendnamelist;
    }

    public void setRecommendnamelist(List<RecommendnamelistBean> recommendnamelist) {
        this.recommendnamelist = recommendnamelist;
    }

    public static class RecommendnamelistBean {
        /**
         * means : 我是这个商标名的含义,美丽的思考--飘柔
         * linechart : {"trademarkname":"飘柔","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"京东","linecolor":"#FF4300","linevalue":[7,7,6,7,5,6,8]},{"linename":"亚马逊","linecolor":"#FFEA00","linevalue":[2,2,5,4,7,7,3]}]}
         */

        private String means;
        private LineChartBean linechart;

        public String getMeans() {
            return means;
        }

        public void setMeans(String means) {
            this.means = means;
        }

        public LineChartBean getLinechart() {
            return linechart;
        }

        public void setLinechart(LineChartBean linechart) {
            this.linechart = linechart;
        }

        @Override
        public String toString() {
            return "RecommendnamelistBean{" +
                    "means='" + means + '\'' +
                    ", linechart=" + linechart +
                    '}';
        }
    }
}

