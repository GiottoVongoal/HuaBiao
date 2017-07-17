package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-07 13:21
 * @description 查询结果--未注册Bean
 */
public class SearchResultUnregisteredAndCreatNameBean {

    /**
     * classification : [{"classificationname":"化学制剂","classificationid":"01"},{"classificationname":"颜料油漆","classificationid":"02"}]
     * otherclassification : [{"classificationname":"化学制剂","classificationid":"01"},{"classificationname":"颜料油漆","classificationid":"02"},{"classificationname":"日化用品","classificationid":"03"}]
     * linechart : {"tradename":"飘柔","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"京东","linecolor":"#FF4300","linevalue":[7,7,6,7,5,6,8]},{"linename":"亚马逊","linecolor":"#FFEA00","linevalue":[2,2,5,4,7,7,3]}]}
     */
    private String means;
    private LineChartBean linechart;
    private List<ClassificationBean> classification;
    private List<ClassificationBean> otherclassification;

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

    public List<ClassificationBean> getClassification() {
        return classification;
    }

    public void setClassification(List<ClassificationBean> classification) {
        this.classification = classification;
    }

    public List<ClassificationBean> getOtherclassification() {
        return otherclassification;
    }

    public void setOtherclassification(List<ClassificationBean> otherclassification) {
        this.otherclassification = otherclassification;
    }
}
