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
     * classification : {"classificationname":"颜料油漆","classificationid":"02","trademarkstatus":3,"classficationsmalltype":[{"classificationsmallname":"食品着色剂","classificationsmallid":"0203","detailed":[{"id":"020004","typename":"饮料色素"},{"id":"020005","typename":"食用色素"},{"id":"020005","typename":"食品用着色剂"},{"id":"020023","typename":"黄油色素"},{"id":"020024","typename":"啤酒色素"},{"id":"020034","typename":"焦糖（食品色素）"},{"id":"020035","typename":"麦芽焦糖（食品色素）"},{"id":"020048","typename":"麦芽色素"},{"id":"020088","typename":"利口酒用色素"}]},{"classificationsmallname":"油墨","classificationsmallid":"0204","detailed":[{"id":"020033","typename":"制革用墨"},{"id":"020066","typename":"印刷油墨"},{"id":"020121","typename":"复印机用墨（调色剂）"},{"id":"020123","typename":"打印机和复印机用已填充的鼓粉盒"},{"id":"C020015","typename":"激光打印机墨盒"},{"id":"C020016","typename":"喷墨打印机墨盒"},{"id":"C020017","typename":"复印机用碳粉"},{"id":"020128","typename":"可食用墨"},{"id":"020129","typename":"已填充可食用墨的打印机墨盒"}]},{"classificationsmallname":"未加工的天然树脂","classificationsmallid":"0207","detailed":[{"id":"020022","typename":"加拿大香脂"},{"id":"020046","typename":"松香"},{"id":"020050","typename":"天然硬树脂"},{"id":"020061","typename":"天然树脂（原料）"},{"id":"020078","typename":"树胶脂"},{"id":"020061","typename":"天然树脂（原料）"},{"id":"020100","typename":"山达脂"}]}]}
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
         * classficationsmalltype : [{"classificationsmallname":"食品着色剂","classificationsmallid":"0203","detailed":[{"id":"020004","typename":"饮料色素"},{"id":"020005","typename":"食用色素"},{"id":"020005","typename":"食品用着色剂"},{"id":"020023","typename":"黄油色素"},{"id":"020024","typename":"啤酒色素"},{"id":"020034","typename":"焦糖（食品色素）"},{"id":"020035","typename":"麦芽焦糖（食品色素）"},{"id":"020048","typename":"麦芽色素"},{"id":"020088","typename":"利口酒用色素"}]},{"classificationsmallname":"油墨","classificationsmallid":"0204","detailed":[{"id":"020033","typename":"制革用墨"},{"id":"020066","typename":"印刷油墨"},{"id":"020121","typename":"复印机用墨（调色剂）"},{"id":"020123","typename":"打印机和复印机用已填充的鼓粉盒"},{"id":"C020015","typename":"激光打印机墨盒"},{"id":"C020016","typename":"喷墨打印机墨盒"},{"id":"C020017","typename":"复印机用碳粉"},{"id":"020128","typename":"可食用墨"},{"id":"020129","typename":"已填充可食用墨的打印机墨盒"}]},{"classificationsmallname":"未加工的天然树脂","classificationsmallid":"0207","detailed":[{"id":"020022","typename":"加拿大香脂"},{"id":"020046","typename":"松香"},{"id":"020050","typename":"天然硬树脂"},{"id":"020061","typename":"天然树脂（原料）"},{"id":"020078","typename":"树胶脂"},{"id":"020061","typename":"天然树脂（原料）"},{"id":"020100","typename":"山达脂"}]}]
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
             * classificationsmallname : 食品着色剂
             * classificationsmallid : 0203
             * detailed : [{"id":"020004","typename":"饮料色素"},{"id":"020005","typename":"食用色素"},{"id":"020005","typename":"食品用着色剂"},{"id":"020023","typename":"黄油色素"},{"id":"020024","typename":"啤酒色素"},{"id":"020034","typename":"焦糖（食品色素）"},{"id":"020035","typename":"麦芽焦糖（食品色素）"},{"id":"020048","typename":"麦芽色素"},{"id":"020088","typename":"利口酒用色素"}]
             */

            private String classificationsmallname;
            private String classificationsmallid;
            private int trademarkstatus;
            private List<DetailedBean> detailed;

            public int getTrademarkstatus() {
                return trademarkstatus;
            }

            public void setTrademarkstatus(int trademarkstatus) {
                this.trademarkstatus = trademarkstatus;
            }

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

            public List<DetailedBean> getDetailed() {
                return detailed;
            }

            public void setDetailed(List<DetailedBean> detailed) {
                this.detailed = detailed;
            }

            public static class DetailedBean {
                /**
                 * id : 020004
                 * typename : 饮料色素
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
