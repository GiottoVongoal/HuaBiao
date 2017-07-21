package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-20 17:36
 * @description
 */
public class HomeBannarBean {

    private List<BannarlistBean> bannarlist;

    public List<BannarlistBean> getBannarlist() {
        return bannarlist;
    }

    public void setBannarlist(List<BannarlistBean> bannarlist) {
        this.bannarlist = bannarlist;
    }

    public static class BannarlistBean {
        /**
         * pageUrl : http://www.tangutv.com
         */

        private String pageUrl;
        private String pagetitle;

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public String getPagetitle() {
            return pagetitle;
        }

        public void setPagetitle(String pagetitle) {
            this.pagetitle = pagetitle;
        }
    }
}
