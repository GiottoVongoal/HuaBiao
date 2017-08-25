package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/25.
 */

public class AuctionBean {
    private List<AuctionlistBean> auctionlist;

    public List<AuctionlistBean> getAuctionlist() {
        return auctionlist;
    }

    public void setAuctionlist(List<AuctionlistBean> auctionlist) {
        this.auctionlist = auctionlist;
    }

    public static class AuctionlistBean {
        /**
         * img : http://picapi.ooopic.com/10/17/86/50b1OOOPIC9e.jpg
         * company : 上海商标拍卖有限公司
         * phone : 400-525445
         */

        private String img;
        private String company;
        private String phone;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
