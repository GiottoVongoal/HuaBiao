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
         * reputablenumber : 6643
         * consultnumber : 564
         * customerserviceid : 001
         * successrate : 98%
         * usedtime : 30分钟
         * service : 满意
         * customerserviceimg : https://b-ssl.duitang.com/uploads/blog/201403/30/20140330105846_aKVfH.thumb.700_0.jpeg
         */

        private String customerservicename;
        private String customerservicecompany;
        private String reputablenumber;
        private String consultnumber;
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

        public String getReputablenumber() {
            return reputablenumber;
        }

        public void setReputablenumber(String reputablenumber) {
            this.reputablenumber = reputablenumber;
        }

        public String getConsultnumber() {
            return consultnumber;
        }

        public void setConsultnumber(String consultnumber) {
            this.consultnumber = consultnumber;
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
