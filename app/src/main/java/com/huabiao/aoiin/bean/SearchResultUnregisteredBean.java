package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-07 13:21
 * @description 查询结果--未注册
 */
public class SearchResultUnregisteredBean {

    /**
     * classification : [{"classificationname":"化学制剂、肥料","classificationid":"01"},{"classificationname":"颜料油漆、染料、防腐制品","classificationid":"02"}]
     * otherclassification : [{"classificationname":"化学制剂、肥料","classificationid":"01"},{"classificationname":"颜料油漆、染料、防腐制品","classificationid":"02"},{"classificationname":"日化用品、洗护、香料","classificationid":"03"}]
     * linechart : {"trademarkname":"飞丝","trademarkclassification":"日化用品、洗护、香料","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"百度","linecolor":"#419BF9","linevalue":[9,7,6,7,8,6,8]},{"linename":"淘宝","linecolor":"#FF4081","linevalue":[2,5,7,4,4,8,6]}]}
     */

    private LineChartBean linechart;
    private List<ClassificationBean> classification;
    private List<OtherclassificationBean> otherclassification;

    public LineChartBean getLinechart() {
        return linechart;
    }

    public void setLinechart(LineChartBean linechart) {
        this.linechart = linechart;
    }

    public List<ClassificationBean> getClassification() {
        return classification;
    }

    public void setClassification(List<ClassificationBean> classification) {
        this.classification = classification;
    }

    public List<OtherclassificationBean> getOtherclassification() {
        return otherclassification;
    }

    public void setOtherclassification(List<OtherclassificationBean> otherclassification) {
        this.otherclassification = otherclassification;
    }

    public static class ClassificationBean {
        /**
         * classificationname : 化学制剂、肥料
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

    public static class OtherclassificationBean {
        /**
         * classificationname : 化学制剂、肥料
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
