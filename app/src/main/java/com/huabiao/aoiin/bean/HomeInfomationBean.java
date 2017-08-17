package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-16 12:41
 * @description
 */
public class HomeInfomationBean {

    private List<HomeinfolistBean> homeinfolist;

    public List<HomeinfolistBean> getHomeinfolist() {
        return homeinfolist;
    }

    public void setHomeinfolist(List<HomeinfolistBean> homeinfolist) {
        this.homeinfolist = homeinfolist;
    }

    public static class HomeinfolistBean {
        /**
         * homeinfoUrl : https://b-ssl.duitang.com/uploads/item/201706/19/20170619141204_ErF2i.thumb.700_0.jpeg
         */

        private String homeinfoUrl;

        public String getHomeinfoUrl() {
            return homeinfoUrl;
        }

        public void setHomeinfoUrl(String homeinfoUrl) {
            this.homeinfoUrl = homeinfoUrl;
        }
    }
}
