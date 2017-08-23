package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/23.
 */

public class PledgeBean {
    private List<PledgelistBean> pledgelist;

    public List<PledgelistBean> getPledgelist() {
        return pledgelist;
    }

    public void setPledgelist(List<PledgelistBean> pledgelist) {
        this.pledgelist = pledgelist;
    }

    public static class PledgelistBean {
        /**
         * img : https://tse1-mm.cn.bing.net/th?id=OIP.xguCW8y3OC_7_HYgA87p2gEsBj&w=300&h=300&p=0&pid=1.7
         * bankname : 渤海银行
         * phonenumber : 18720180683
         */

        private String img;
        private String bankname;
        private String phonenumber;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }
    }
}
