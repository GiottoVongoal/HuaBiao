package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-21 16:53
 * @description
 */
public class RegisterBean {

    /**
     * classification : [{"classificationname":"化学制剂","classificationid":"01"},{"classificationname":"颜料油漆","classificationid":"02"},{"classificationname":"金属材料","classificationid":"06"},{"classificationname":"机械设备","classificationid":"07"},{"classificationname":"医药","classificationid":"05"}]
     * linechart : {"tradename":"飘柔","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"京东","linecolor":"#FF4300","linevalue":[7,7,6,7,5,6,8]},{"linename":"亚马逊","linecolor":"#FFEA00","linevalue":[2,2,5,4,7,7,3]}]}
     */

    private LineChartBean linechart;
    private List<ClassificationBean> classification;

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
}
