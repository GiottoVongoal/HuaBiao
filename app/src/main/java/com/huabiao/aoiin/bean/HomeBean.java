package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-18 19:13
 * @description
 */
public class HomeBean {
    private List<BannarlistBean> bannarlist;
    private List<HomeinfolistBean> homeinfolist;
    private List<HotwordslistBean> hotwordslist;

    public List<BannarlistBean> getBannarlist() {
        return bannarlist;
    }

    public void setBannarlist(List<BannarlistBean> bannarlist) {
        this.bannarlist = bannarlist;
    }

    public List<HomeinfolistBean> getHomeinfolist() {
        return homeinfolist;
    }

    public void setHomeinfolist(List<HomeinfolistBean> homeinfolist) {
        this.homeinfolist = homeinfolist;
    }

    public List<HotwordslistBean> getHotwordslist() {
        return hotwordslist;
    }

    public void setHotwordslist(List<HotwordslistBean> hotwordslist) {
        this.hotwordslist = hotwordslist;
    }

    public static class BannarlistBean {
        /**
         * pageUrl : https://b-ssl.duitang.com/uploads/item/201706/19/20170619141204_ErF2i.thumb.700_0.jpeg
         * pagetitle : 我是第一张广告图
         */

        private String pageUrl;
        private String pagetitle;

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public String getPagetitle() {
            return pagetitle;
        }

        public void setPagetitle(String pagetitle) {
            this.pagetitle = pagetitle;
        }

        @Override
        public String toString() {
            return "BannarlistBean{" +
                    "pageUrl='" + pageUrl + '\'' +
                    ", pagetitle='" + pagetitle + '\'' +
                    '}';
        }
    }

    public static class HomeinfolistBean {
        /**
         * homeinfoUrl : https://b-ssl.duitang.com/uploads/blog/201509/29/20150929164702_KMUBn.thumb.700_0.jpeg
         */

        private String homeinfoUrl;

        public String getHomeinfoUrl() {
            return homeinfoUrl;
        }

        public void setHomeinfoUrl(String homeinfoUrl) {
            this.homeinfoUrl = homeinfoUrl;
        }

        @Override
        public String toString() {
            return "HomeinfolistBean{" +
                    "homeinfoUrl='" + homeinfoUrl + '\'' +
                    '}';
        }
    }

    public static class HotwordslistBean {
        /**
         * name : 舒肤佳
         * industrytype : 日化用品
         * interpretation : Safeguard 保护者; safe 是“安全”的意思，guard 是“门卫”的意思，safeguard是个合成词，保护者，很形象的描述了舒肤佳香皂的功能和产品定位。
         * linechart : {"trademarkname":"舒肤佳","trademarkclassification":"日化用品","classificationid":"05","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"百度","linecolor":"#fdd400","linevalue":[9,7,6,7,8,6,8]},{"linename":"阿里","linecolor":"#AED79B","linevalue":[2,5,7,4,4,8,6]}]}
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

        @Override
        public String toString() {
            return "HotwordslistBean{" +
                    "name='" + name + '\'' +
                    ", industrytype='" + industrytype + '\'' +
                    ", interpretation='" + interpretation + '\'' +
                    ", linechart=" + linechart +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "bannarlist=" + bannarlist +
                ", homeinfolist=" + homeinfolist +
                ", hotwordslist=" + hotwordslist +
                '}';
    }
}
