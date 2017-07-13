package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-06 14:30
 * @description 查询结果--已注册Bean
 */
public class SearchResultRegisteredBean {

    /**
     * classification : {"classificationname":"颜料油漆","classificationid":"02","trademarkstatus":3,"detailed":[{"id":"0201","typename":"染料，媒染剂（不包括食用）"},{"id":"0202","typename":"颜料（不包括食用、绝缘用），画家、装饰家、印刷商和艺术家用金属箔"},{"id":"0203","typename":"食品着色剂"},{"id":"0204","typename":"油墨"},{"id":"0205","typename":"涂料，油漆及附料（不包括绝缘漆）"},{"id":"0206","typename":"防锈剂，木材防腐剂"},{"id":"0207","typename":"未加工的天然树脂"}]}
     * linechart : {"tradename":"海飞丝","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"百度","linecolor":"#419BF9","linevalue":[9,7,6,7,8,6,8]},{"linename":"阿里","linecolor":"#FF4081","linevalue":[2,5,7,4,4,8,6]}]}
     * recommend : [{"tradename":"推荐名字1","trademarkclassification":"类别1","classificationid":"01","trademarkstatus":1},{"tradename":"推荐名字2","trademarkclassification":"类别2","classificationid":"02","trademarkstatus":1},{"tradename":"推荐名字3","trademarkclassification":"类别3","classificationid":"03","trademarkstatus":2}]
     */

    private ClassificationBean classification;
    private LineChartBean linechart;
    private List<RecommendBean> recommend;

    public ClassificationBean getClassification() {
        return classification;
    }

    public void setClassification(ClassificationBean classification) {
        this.classification = classification;
    }

    public LineChartBean getLinechart() {
        return linechart;
    }

    public void setLinechart(LineChartBean linechart) {
        this.linechart = linechart;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class ClassificationBean {
        /**
         * classificationname : 颜料油漆
         * classificationid : 02
         * trademarkstatus : 3
         * detailed : [{"id":"0201","typename":"染料，媒染剂（不包括食用）"},{"id":"0202","typename":"颜料（不包括食用、绝缘用），画家、装饰家、印刷商和艺术家用金属箔"},{"id":"0203","typename":"食品着色剂"},{"id":"0204","typename":"油墨"},{"id":"0205","typename":"涂料，油漆及附料（不包括绝缘漆）"},{"id":"0206","typename":"防锈剂，木材防腐剂"},{"id":"0207","typename":"未加工的天然树脂"}]
         */

        private String classificationname;
        private String classificationid;
        private int trademarkstatus;
        private List<DetailedBean> detailed;

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

        public int getTrademarkstatus() {
            return trademarkstatus;
        }

        public void setTrademarkstatus(int trademarkstatus) {
            this.trademarkstatus = trademarkstatus;
        }

        public List<DetailedBean> getDetailed() {
            return detailed;
        }

        public void setDetailed(List<DetailedBean> detailed) {
            this.detailed = detailed;
        }

        public static class DetailedBean {
            /**
             * id : 0201
             * typename : 染料，媒染剂（不包括食用）
             */

            private String id;
            private String typename;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }
    }

    public static class RecommendBean {
        /**
         * tradename : 推荐名字1
         * trademarkclassification : 类别1
         * classificationid : 01
         * trademarkstatus : 1
         */

        private String tradename;
        private String trademarkclassification;
        private String classificationid;
        private int trademarkstatus;

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

        public int getTrademarkstatus() {
            return trademarkstatus;
        }

        public void setTrademarkstatus(int trademarkstatus) {
            this.trademarkstatus = trademarkstatus;
        }
    }
}
