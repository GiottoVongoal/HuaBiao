package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-06 14:30
 * @description 查询结果--已注册Bean
 */
public class SearchResultBean {

    private ResultClassificationBean classification;
    private LineChartBean linechart;

    public ResultClassificationBean getClassification() {
        return classification;
    }

    public void setClassification(ResultClassificationBean classification) {
        this.classification = classification;
    }

    public LineChartBean getLinechart() {
        return linechart;
    }

    public void setLinechart(LineChartBean linechart) {
        this.linechart = linechart;
    }

    public static class ResultClassificationBean {

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
}
