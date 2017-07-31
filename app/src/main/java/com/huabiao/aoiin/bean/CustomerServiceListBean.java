package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-12 15:46
 * @description
 */
public class CustomerServiceListBean {

    private List<CustomerservicelistBean> customerservicelist;

    public List<CustomerservicelistBean> getCustomerservicelist() {
        return customerservicelist;
    }

    public void setCustomerservicelist(List<CustomerservicelistBean> customerservicelist) {
        this.customerservicelist = customerservicelist;
    }

    public static class CustomerservicelistBean {
        /**
         * customerservicename : 客服1
         * customerservicecompany : 上海商标公司
         * customerservicerank : 5
         * customerserviceid : 001
         * successrate : 98%
         * usedtime : 30分钟
         * service : 满意
         * customerserviceimg : https://b-ssl.duitang.com/uploads/blog/201403/30/20140330105846_aKVfH.thumb.700_0.jpeg
         */

        private String customerservicename;
        private String customerservicecompany;
        private int customerservicerank;
        private String customerserviceid;
        private String successrate;
        private String usedtime;
        private String service;
        private String customerserviceimg;

        public String getCustomerservicename() {
            return customerservicename;
        }

        public void setCustomerservicename(String customerservicename) {
            this.customerservicename = customerservicename;
        }

        public String getCustomerservicecompany() {
            return customerservicecompany;
        }

        public void setCustomerservicecompany(String customerservicecompany) {
            this.customerservicecompany = customerservicecompany;
        }

        public int getCustomerservicerank() {
            return customerservicerank;
        }

        public void setCustomerservicerank(int customerservicerank) {
            this.customerservicerank = customerservicerank;
        }

        public String getCustomerserviceid() {
            return customerserviceid;
        }

        public void setCustomerserviceid(String customerserviceid) {
            this.customerserviceid = customerserviceid;
        }

        public String getSuccessrate() {
            return successrate;
        }

        public void setSuccessrate(String successrate) {
            this.successrate = successrate;
        }

        public String getUsedtime() {
            return usedtime;
        }

        public void setUsedtime(String usedtime) {
            this.usedtime = usedtime;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getCustomerserviceimg() {
            return customerserviceimg;
        }

        public void setCustomerserviceimg(String customerserviceimg) {
            this.customerserviceimg = customerserviceimg;
        }
    }
}
