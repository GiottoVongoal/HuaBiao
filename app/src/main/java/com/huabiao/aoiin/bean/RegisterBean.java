package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-21 16:53
 * @description 注册页面获取的显示数据
 */
public class RegisterBean {

    private String means;
    private LineChartBean linechart;
    private List<ClassificationBean> classification;

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
}
