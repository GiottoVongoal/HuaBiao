package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-26 13:01
 * @description 用户进度当前商标进度Bean
 */
public class UserProgressListBean {

    private String trademarkname;
    private String trademarkid;
    private String latestprogress;
    private String latestime;
    private List<ProgresslistBean> userprogresslist;

    public String getTrademarkname() {
        return trademarkname;
    }

    public void setTrademarkname(String trademarkname) {
        this.trademarkname = trademarkname;
    }

    public String getTrademarkid() {
        return trademarkid;
    }

    public void setTrademarkid(String trademarkid) {
        this.trademarkid = trademarkid;
    }

    public String getLatestprogress() {
        return latestprogress;
    }

    public void setLatestprogress(String latestprogress) {
        this.latestprogress = latestprogress;
    }

    public String getLatestime() {
        return latestime;
    }

    public void setLatestime(String latestime) {
        this.latestime = latestime;
    }

    public List<ProgresslistBean> getUserprogresslist() {
        return userprogresslist;
    }

    public void setUserprogresslist(List<ProgresslistBean> userprogresslist) {
        this.userprogresslist = userprogresslist;
    }

    public static class ProgresslistBean {
        /**
         * progressimg :
         * progresscontent : 提交
         * progressstatustext : 提交成功
         * progresstime : 2017年1月1日
         */

        private String progressimg;
        private String progresscontent;
        private String progressstatustext;
        private String progresstime;

        public String getProgressimg() {
            return progressimg;
        }

        public void setProgressimg(String progressimg) {
            this.progressimg = progressimg;
        }

        public String getProgresscontent() {
            return progresscontent;
        }

        public void setProgresscontent(String progresscontent) {
            this.progresscontent = progresscontent;
        }

        public String getProgressstatustext() {
            return progressstatustext;
        }

        public void setProgressstatustext(String progressstatustext) {
            this.progressstatustext = progressstatustext;
        }

        public String getProgresstime() {
            return progresstime;
        }

        public void setProgresstime(String progresstime) {
            this.progresstime = progresstime;
        }
    }
}
