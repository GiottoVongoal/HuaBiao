package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-06 14:30
 * @description 查询结果--已注册Bean
 */
public class SearchResultBean {

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

        private String classificationname;
        private String classificationid;
        private List<ClassficationsmalltypeBean> classficationsmalltype;

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

        public List<ClassficationsmalltypeBean> getClassficationsmalltype() {
            return classficationsmalltype;
        }

        public void setClassficationsmalltype(List<ClassficationsmalltypeBean> classficationsmalltype) {
            this.classficationsmalltype = classficationsmalltype;
        }

        public static class ClassficationsmalltypeBean {
            /**
             * classificationsmallname : 饮料色素
             * classificationsmallid : 0203
             * trademarkstatus : 3
             */

            private String classificationsmallname;
            private String classificationsmallid;
            private int trademarkstatus;

            public String getClassificationsmallname() {
                return classificationsmallname;
            }

            public void setClassificationsmallname(String classificationsmallname) {
                this.classificationsmallname = classificationsmallname;
            }

            public String getClassificationsmallid() {
                return classificationsmallid;
            }

            public void setClassificationsmallid(String classificationsmallid) {
                this.classificationsmallid = classificationsmallid;
            }

            public int getTrademarkstatus() {
                return trademarkstatus;
            }

            public void setTrademarkstatus(int trademarkstatus) {
                this.trademarkstatus = trademarkstatus;
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
    }
}
