package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-08 13:20
 * @description
 */
public class FinanceEvakuateReportBean {

    /**
     * tradeimg : https://b-ssl.duitang.com/uploads/item/201504/30/20150430230912_tiAms.thumb.700_0.jpeg
     * tradename : 海飞丝
     * trademarkclassification : 日化用品
     * classificationid : 03
     * tradestatus : 1
     * tradegrade : 98
     * linechart : {"tradename":"海飞丝","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"亚马逊","linecolor":"#419BF9","linevalue":[9,7,6,7,8,6,8]}]}
     * timelimit : 2014-01-28~2024-01-27
     * servicearealist : [{"classificationname":"铝","classificationid":"0601"},{"classificationname":"金属管道","classificationid":"0602"},{"classificationname":"金属门；金属窗","classificationid":"0603"},{"classificationname":"金属门；金属窗","classificationid":"0605"},{"classificationname":"铝","classificationid":"0601"},{"classificationname":"金属管道","classificationid":"0602"},{"classificationname":"金属门；金属窗","classificationid":"0603"},{"classificationname":"金属门；金属窗","classificationid":"0605"}]
     * trendsinfo : 安塞乐米塔尔：到2021年将提升日化品产量25% 07-18 10:07
     * 国家统计局：6月全国日化用品均产量244.1万吨 07-18 09:58
     */

    private String tradeimg;
    private String tradename;
    private String trademarkclassification;
    private String classificationid;
    private int tradestatus;
    private String tradegrade;
    private LineChartBean linechart;
    private String timelimit;
    private String trendsinfo;
    private List<ClassificationBean> servicearealist;

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

    public int getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(int tradestatus) {
        this.tradestatus = tradestatus;
    }

    public String getTradegrade() {
        return tradegrade;
    }

    public void setTradegrade(String tradegrade) {
        this.tradegrade = tradegrade;
    }

    public LineChartBean getLinechart() {
        return linechart;
    }

    public void setLinechart(LineChartBean linechart) {
        this.linechart = linechart;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public String getTrendsinfo() {
        return trendsinfo;
    }

    public void setTrendsinfo(String trendsinfo) {
        this.trendsinfo = trendsinfo;
    }

    public List<ClassificationBean> getServicearealist() {
        return servicearealist;
    }

    public void setServicearealist(List<ClassificationBean> servicearealist) {
        this.servicearealist = servicearealist;
    }
}
