package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-25 16:07
 * @description 日历进度Bean
 */
public class UserProgressDateBean {

    private List<UserprogressdatelistBean> userprogressdatelist;

    public List<UserprogressdatelistBean> getUserprogressdatelist() {
        return userprogressdatelist;
    }

    public void setUserprogressdatelist(List<UserprogressdatelistBean> userprogressdatelist) {
        this.userprogressdatelist = userprogressdatelist;
    }

    public static class UserprogressdatelistBean {
        /**
         * progressdatetime : 11:00
         * progressdatecontent : 暂未受到任何驳回通知
         * progressdateimg : https://b-ssl.duitang.com/uploads/item/201504/30/20150430230912_tiAms.thumb.700_0.jpeg
         */

        private String progressdatetime;
        private String progressdatecontent;
        private String progressdateimg;

        public String getProgressdatetime() {
            return progressdatetime;
        }

        public void setProgressdatetime(String progressdatetime) {
            this.progressdatetime = progressdatetime;
        }

        public String getProgressdatecontent() {
            return progressdatecontent;
        }

        public void setProgressdatecontent(String progressdatecontent) {
            this.progressdatecontent = progressdatecontent;
        }

        public String getProgressdateimg() {
            return progressdateimg;
        }

        public void setProgressdateimg(String progressdateimg) {
            this.progressdateimg = progressdateimg;
        }
    }
}
