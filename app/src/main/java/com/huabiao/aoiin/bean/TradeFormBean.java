package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-09 14:47
 * @description 商标注册表单Bean
 */
public class TradeFormBean {

    /**
     * traderegisterform : {"linechart":{"tradename":"飘柔","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"京东","linecolor":"#FF4300","linevalue":[7,7,6,7,5,6,8]},{"linename":"亚马逊","linecolor":"#FFEA00","linevalue":[2,2,5,4,7,7,3]}]},"classification":[{"classificationname":"氙","classificationid":"010551"},{"classificationname":"氡","classificationid":"010457"},{"classificationname":"氧","classificationid":"010413"},{"classificationname":"氖","classificationid":"010401"},{"classificationname":"氪","classificationid":"010372"},{"classificationname":"氢","classificationid":"010359"},{"classificationname":"氨","classificationid":"010061"}],"userbasicinfo":{"username":"张安","userphone":"13266667777","contractaddress":"上海市闵行区城开中心中庚集团TI 3F","code":"200000"},"persontypeinfo":{"persontype":1,"personname":"申","legalname":"法","personaddress":"上海市闵行区","certificatesid":""},"imginfo":{"logoimg":"","proxyimg":"","businesslicenceimg":""},"servicemode":1}
     */

    private FormBean traderegisterform;

    public FormBean getTraderegisterform() {
        return traderegisterform;
    }

    public void setTraderegisterform(FormBean traderegisterform) {
        this.traderegisterform = traderegisterform;
    }

    public static class FormBean {
        /**
         * linechart : {"tradename":"飘柔","trademarkclassification":"日化用品","classificationid":"03","Xaxistag":["周一","周二","周三","周四","周五","周六","周日"],"lines":[{"linename":"京东","linecolor":"#FF4300","linevalue":[7,7,6,7,5,6,8]},{"linename":"亚马逊","linecolor":"#FFEA00","linevalue":[2,2,5,4,7,7,3]}]}
         * classification : [{"classificationname":"氙","classificationid":"010551"},{"classificationname":"氡","classificationid":"010457"},{"classificationname":"氧","classificationid":"010413"},{"classificationname":"氖","classificationid":"010401"},{"classificationname":"氪","classificationid":"010372"},{"classificationname":"氢","classificationid":"010359"},{"classificationname":"氨","classificationid":"010061"}]
         * userbasicinfo : {"username":"张安","userphone":"13266667777","contractaddress":"上海市闵行区城开中心中庚集团TI 3F","code":"200000"}
         * persontypeinfo : {"persontype":1,"personname":"申","legalname":"法","personaddress":"上海市闵行区","certificatesid":""}
         * imginfo : {"logoimg":"","proxyimg":"","businesslicenceimg":""}
         * serviceode : 1
         */

        private LineChartBean linechart;
        private UserBasicInfoBean userbasicinfo;
        private PersonTypeInfoBean persontypeinfo;
        private ImgInfoBean imginfo;
        private int servicemode;
        private List<ClassificationBean> classification;

        public LineChartBean getLinechart() {
            return linechart;
        }

        public void setLinechart(LineChartBean linechart) {
            this.linechart = linechart;
        }

        public UserBasicInfoBean getUserbasicinfo() {
            return userbasicinfo;
        }

        public void setUserbasicinfo(UserBasicInfoBean userbasicinfo) {
            this.userbasicinfo = userbasicinfo;
        }

        public PersonTypeInfoBean getPersontypeinfo() {
            return persontypeinfo;
        }

        public void setPersontypeinfo(PersonTypeInfoBean persontypeinfo) {
            this.persontypeinfo = persontypeinfo;
        }

        public ImgInfoBean getImginfo() {
            return imginfo;
        }

        public void setImginfo(ImgInfoBean imginfo) {
            this.imginfo = imginfo;
        }

        public int getServicemode() {
            return servicemode;
        }

        public void setServicemode(int servicemode) {
            this.servicemode = servicemode;
        }

        public List<ClassificationBean> getClassification() {
            return classification;
        }

        public void setClassification(List<ClassificationBean> classification) {
            this.classification = classification;
        }

        public static class UserBasicInfoBean {
            /**
             * username : 张安
             * userphone : 13266667777
             * contractaddress : 上海市闵行区城开中心中庚集团TI 3F
             * code : 200000
             */

            private String username;
            private String userphone;
            private String contractaddress;
            private String code;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUserphone() {
                return userphone;
            }

            public void setUserphone(String userphone) {
                this.userphone = userphone;
            }

            public String getContractaddress() {
                return contractaddress;
            }

            public void setContractaddress(String contractaddress) {
                this.contractaddress = contractaddress;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }

        public static class PersonTypeInfoBean {
            /**
             * persontype : 1
             * personname : 申
             * legalname : 法
             * personaddress : 上海市闵行区
             * certificatesid :
             */

            private int persontype;
            private String personname;
            private String legalname;
            private String personaddress;
            private String certificatesid;

            public int getPersontype() {
                return persontype;
            }

            public void setPersontype(int persontype) {
                this.persontype = persontype;
            }

            public String getPersonname() {
                return personname;
            }

            public void setPersonname(String personname) {
                this.personname = personname;
            }

            public String getLegalname() {
                return legalname;
            }

            public void setLegalname(String legalname) {
                this.legalname = legalname;
            }

            public String getPersonaddress() {
                return personaddress;
            }

            public void setPersonaddress(String personaddress) {
                this.personaddress = personaddress;
            }

            public String getCertificatesid() {
                return certificatesid;
            }

            public void setCertificatesid(String certificatesid) {
                this.certificatesid = certificatesid;
            }
        }

        public static class ImgInfoBean {
            /**
             * logoimg :
             * proxyimg :
             * businesslicenceimg :
             */

            private String logoimg;
            private String proxyimg;
            private String businesslicenceimg;

            public String getLogoimg() {
                return logoimg;
            }

            public void setLogoimg(String logoimg) {
                this.logoimg = logoimg;
            }

            public String getProxyimg() {
                return proxyimg;
            }

            public void setProxyimg(String proxyimg) {
                this.proxyimg = proxyimg;
            }

            public String getBusinesslicenceimg() {
                return businesslicenceimg;
            }

            public void setBusinesslicenceimg(String businesslicenceimg) {
                this.businesslicenceimg = businesslicenceimg;
            }
        }
    }
}
