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
     * classification : [{"classificationname":"化学制剂","classificationid":"01","trademarkstatus":1,"detailed":[{"id":"0101","typename":"工业气体,单质"},{"id":"0102","typename":"用于工业、科学、农业、园艺、森林的工业化工原料"},{"id":"0103","typename":"放射性元素及其化学品"},{"id":"0104","typename":"用于工业、科学的化学品、化学制剂，不属于其他类别的产品用的化学制品"},{"id":"0105","typename":"用于农业、园艺、林业的化学品、化学制剂"}]},{"classificationname":"颜料油漆","classificationid":"02","trademarkstatus":3,"detailed":[{"id":"0201","typename":"染料，媒染剂（不包括食用）"},{"id":"0202","typename":"颜料（不包括食用、绝缘用），画家、装饰家、印刷商和艺术家用金属箔"},{"id":"0203","typename":"食品着色剂"},{"id":"0204","typename":"油墨"},{"id":"0205","typename":"涂料，油漆及附料（不包括绝缘漆）"},{"id":"0206","typename":"防锈剂，木材防腐剂"},{"id":"0207","typename":"未加工的天然树脂"}]},{"classificationname":"日化用品","classificationid":"03","trademarkstatus":1,"detailed":[{"id":"301","typename":"肥皂，香皂及其他人用洗洁物品，洗衣用漂白剂及其他物料"},{"id":"0302","typename":"清洁、去渍用制剂"},{"id":"0303","typename":"抛光、擦亮制剂"},{"id":"0304","typename":"研磨用材料及其制剂"},{"id":"0305","typename":"香料，香精油"},{"id":"0306","typename":"化妆品（不包括动物用化妆品）"},{"id":"0307","typename":"牙膏，洗牙用制剂"},{"id":"0308","typename":"熏料"},{"id":"0309","typename":"动物用化妆品"},{"id":"0310","typename":"室内芳香剂"}]}]
     * linechart : {"tradename":"海飞丝","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"百度","linecolor":"#419BF9","linevalue":[9,7,6,7,8,6,8]},{"linename":"淘宝","linecolor":"#FF4081","linevalue":[2,5,7,4,4,8,6]}]}
     * recommend : [{"tradename":"推荐名字1","trademarkclassification":"类别1","classificationid":"01","trademarkstatus":1},{"tradename":"推荐名字2","trademarkclassification":"类别2","classificationid":"02","trademarkstatus":1},{"tradename":"推荐名字3","trademarkclassification":"类别3","classificationid":"03","trademarkstatus":2}]
     */

    private LineChartBean linechart;
    private List<ClassificationBean> classification;
    private List<RecommendBean> recommend;

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

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class ClassificationBean {
        /**
         * classificationname : 化学制剂
         * classificationid : 01
         * trademarkstatus : 1
         * detailed : [{"id":"0101","typename":"工业气体,单质"},{"id":"0102","typename":"用于工业、科学、农业、园艺、森林的工业化工原料"},{"id":"0103","typename":"放射性元素及其化学品"},{"id":"0104","typename":"用于工业、科学的化学品、化学制剂，不属于其他类别的产品用的化学制品"},{"id":"0105","typename":"用于农业、园艺、林业的化学品、化学制剂"}]
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
             * id : 0101
             * typename : 工业气体,单质
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
