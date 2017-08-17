package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-07 11:41
 * @description 热搜词Bean
 */
public class HotWordsListBean {

    private List<HotwordsBean> hotwordslist;

    public List<HotwordsBean> getHotwordslist() {
        return hotwordslist;
    }

    public void setHotwordslist(List<HotwordsBean> hotwordslist) {
        this.hotwordslist = hotwordslist;
    }

    public static class HotwordsBean {
        /**
         * name : 梅勒
         * industrytype : 建筑行业
         * interpretation : 如果你无法简洁的表达你的想法，那只说明你还不够了解它。-- 阿尔伯特·爱因斯坦操控速度很快sigh看很快日个护ski红人馆日诶一人挂号费的话而忒日银行股i额虽然跟黑人和是如何合肥热乎乎日开完会惹我哈日股份hi和人格热管业务 广发银行委员会哈佛i委员会我。
         * linechart : {"trademarkname":"梅勒","trademarkclassification":"建筑行业","classificationid":"05","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"百度","linecolor":"#419BF9","linevalue":[9,7,6,7,8,6,8]},{"linename":"阿里","linecolor":"#FF4081","linevalue":[2,5,7,4,4,8,6]}]}
         */

        private String name;
        private String industrytype;
        private String interpretation;
        private LineChartBean linechart;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndustrytype() {
            return industrytype;
        }

        public void setIndustrytype(String industrytype) {
            this.industrytype = industrytype;
        }

        public String getInterpretation() {
            return interpretation;
        }

        public void setInterpretation(String interpretation) {
            this.interpretation = interpretation;
        }

        public LineChartBean getLinechart() {
            return linechart;
        }

        public void setLinechart(LineChartBean linechart) {
            this.linechart = linechart;
        }
    }
}
