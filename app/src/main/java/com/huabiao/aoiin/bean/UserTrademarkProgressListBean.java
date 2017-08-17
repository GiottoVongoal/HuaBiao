package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-26 13:00
 * @description 用户进度商标列表Bean
 */
public class UserTrademarkProgressListBean {

    private List<TrademarkprogresslistBean> trademarkprogresslist;

    public List<TrademarkprogresslistBean> getTrademarkprogresslist() {
        return trademarkprogresslist;
    }

    public void setTrademarkprogresslist(List<TrademarkprogresslistBean> trademarkprogresslist) {
        this.trademarkprogresslist = trademarkprogresslist;
    }

    public static class TrademarkprogresslistBean {
        /**
         * trademarkname : 商标1
         * trademarkid : 1001
         */

        private String trademarkname;
        private String trademarkid;

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
    }
}
