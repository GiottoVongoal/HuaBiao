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
     * means : 我是这个商标名的含义,美丽的思考
     * classification : {"classificationname":"颜料油漆","classificationid":"02","classficationsmalltype":[{"classificationsmallname":"饮料色素","classificationsmallid":"0203","trademarkstatus":3},{"classificationsmallname":"食品用着色剂","classificationsmallid":"0203","trademarkstatus":3},{"classificationsmallname":"麦芽焦糖（食品色素）","classificationsmallid":"0203","trademarkstatus":3},{"classificationsmallname":"打印机和复印机用已填充的鼓粉盒","classificationsmallid":"0204","trademarkstatus":3},{"classificationsmallname":"天然树脂（原料）","classificationsmallid":"0207","trademarkstatus":3},{"classificationsmallname":"山达脂","classificationsmallid":"0207","trademarkstatus":3}]}
     * linechart : {"tradename":"飘柔","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"京东","linecolor":"#FF4300","linevalue":[7,7,6,7,5,6,8]},{"linename":"亚马逊","linecolor":"#FFEA00","linevalue":[2,2,5,4,7,7,3]}]}
     */

    private String means;
    private ClassificationBean classification;
    private LineChartBean linechart;

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

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

    public static class ClassificationBean {
        /**
         * classificationname : 颜料油漆
         * classificationid : 02
         * classficationsmalltype : [{"classificationsmallname":"饮料色素","classificationsmallid":"0203","trademarkstatus":3},{"classificationsmallname":"食品用着色剂","classificationsmallid":"0203","trademarkstatus":3},{"classificationsmallname":"麦芽焦糖（食品色素）","classificationsmallid":"0203","trademarkstatus":3},{"classificationsmallname":"打印机和复印机用已填充的鼓粉盒","classificationsmallid":"0204","trademarkstatus":3},{"classificationsmallname":"天然树脂（原料）","classificationsmallid":"0207","trademarkstatus":3},{"classificationsmallname":"山达脂","classificationsmallid":"0207","trademarkstatus":3}]
         */

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
