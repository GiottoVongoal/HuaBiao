package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-12 11:01
 * @description Tab中的注册输入页面--选择行业Bean
 */
public class RegisterOneIndustryBean {

    private List<IndustrylistBean> registeroneindustrylist;

    public List<IndustrylistBean> getRegisteroneindustrylist() {
        return registeroneindustrylist;
    }

    public void setRegisteroneindustrylist(List<IndustrylistBean> registeroneindustrylist) {
        this.registeroneindustrylist = registeroneindustrylist;
    }

    public static class IndustrylistBean {
        /**
         * industryname : 服装纺织
         * industryid : 0001
         */

        private String industryname;
        private String industryid;

        public String getIndustryname() {
            return industryname;
        }

        public void setIndustryname(String industryname) {
            this.industryname = industryname;
        }

        public String getIndustryid() {
            return industryid;
        }

        public void setIndustryid(String industryid) {
            this.industryid = industryid;
        }
    }
}
