package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/3.
 */

public class BuyingInfoBean {

    /**
     * img : https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9HrJrTb1_vagmDY_szyKvC4Ijq-hc5ZSFgLPILiDFZEnCHgK0tA
     * name : 海飞丝
     * classificationid : 06
     * classifictiontype : 金属材料
     * status : 1
     * baseinfo : {"trademarkname":"海飞丝","registrationnumber":"16404103","classificationid":"06","applicantname":"上海宝洁日化有限公司","applicantaddress":"安徽省安庆市同安路西区1栋1单元1楼","proxycompany":"安庆华诚商标事务有限公司"}
     * registerinfo : {"applicationdate":"2014-04-28","registerdate":"2014-01-28","preliminarynoticedate":"2013-10-27","registrationnoticedate":"2014-01-28","dedicatedterm":"2014-01-28~2024-01-27"}
     * servicelist : [{"id":"0601","classname":"铝"},{"id":"0602","classname":"金属管道"},{"id":"0603","classname":"金属门;金属窗"},{"id":"0601","classname":"铝"},{"id":"0602","classname":"金属管道"},{"id":"0603","classname":"金属门；金属窗"}]
     * notice : [{"title":"商标注册申请中","time":"2014-04-28","isvisible":0},{"title":"商标注册申请等待受理通知书发文","time":"2014-05-28","isvisible":0},{"title":"商标初步审定公告第1493期","time":"2014-06-28","isvisible":1},{"title":"商标注册公告（一）第1505期","time":"2014-07-28","isvisible":1},{"title":"商标转让/转移公告第1518期","time":"2014-08-28","isvisible":1}]
     */

    private String img;
    private String name;
    private String classificationid;
    private String classifictiontype;
    private String status;
    private BaseinfoBean baseinfo;
    private RegisterinfoBean registerinfo;
    private List<ServicelistBean> servicelist;
    private List<NoticeBean> notice;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassificationid() {
        return classificationid;
    }

    public void setClassificationid(String classificationid) {
        this.classificationid = classificationid;
    }

    public String getClassifictiontype() {
        return classifictiontype;
    }

    public void setClassifictiontype(String classifictiontype) {
        this.classifictiontype = classifictiontype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BaseinfoBean getBaseinfo() {
        return baseinfo;
    }

    public void setBaseinfo(BaseinfoBean baseinfo) {
        this.baseinfo = baseinfo;
    }

    public RegisterinfoBean getRegisterinfo() {
        return registerinfo;
    }

    public void setRegisterinfo(RegisterinfoBean registerinfo) {
        this.registerinfo = registerinfo;
    }

    public List<ServicelistBean> getServicelist() {
        return servicelist;
    }

    public void setServicelist(List<ServicelistBean> servicelist) {
        this.servicelist = servicelist;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public static class BaseinfoBean {
        /**
         * trademarkname : 海飞丝
         * registrationnumber : 16404103
         * classificationid : 06
         * applicantname : 上海宝洁日化有限公司
         * applicantaddress : 安徽省安庆市同安路西区1栋1单元1楼
         * proxycompany : 安庆华诚商标事务有限公司
         */

        private String trademarkname;
        private String registrationnumber;
        private String classificationid;
        private String applicantname;
        private String applicantaddress;
        private String proxycompany;

        public String getTrademarkname() {
            return trademarkname;
        }

        public void setTrademarkname(String trademarkname) {
            this.trademarkname = trademarkname;
        }

        public String getRegistrationnumber() {
            return registrationnumber;
        }

        public void setRegistrationnumber(String registrationnumber) {
            this.registrationnumber = registrationnumber;
        }

        public String getClassificationid() {
            return classificationid;
        }

        public void setClassificationid(String classificationid) {
            this.classificationid = classificationid;
        }

        public String getApplicantname() {
            return applicantname;
        }

        public void setApplicantname(String applicantname) {
            this.applicantname = applicantname;
        }

        public String getApplicantaddress() {
            return applicantaddress;
        }

        public void setApplicantaddress(String applicantaddress) {
            this.applicantaddress = applicantaddress;
        }

        public String getProxycompany() {
            return proxycompany;
        }

        public void setProxycompany(String proxycompany) {
            this.proxycompany = proxycompany;
        }
    }

    public static class RegisterinfoBean {
        /**
         * applicationdate : 2014-04-28
         * registerdate : 2014-01-28
         * preliminarynoticedate : 2013-10-27
         * registrationnoticedate : 2014-01-28
         * dedicatedterm : 2014-01-28~2024-01-27
         */

        private String applicationdate;
        private String registerdate;
        private String preliminarynoticedate;
        private String registrationnoticedate;
        private String dedicatedterm;

        public String getApplicationdate() {
            return applicationdate;
        }

        public void setApplicationdate(String applicationdate) {
            this.applicationdate = applicationdate;
        }

        public String getRegisterdate() {
            return registerdate;
        }

        public void setRegisterdate(String registerdate) {
            this.registerdate = registerdate;
        }

        public String getPreliminarynoticedate() {
            return preliminarynoticedate;
        }

        public void setPreliminarynoticedate(String preliminarynoticedate) {
            this.preliminarynoticedate = preliminarynoticedate;
        }

        public String getRegistrationnoticedate() {
            return registrationnoticedate;
        }

        public void setRegistrationnoticedate(String registrationnoticedate) {
            this.registrationnoticedate = registrationnoticedate;
        }

        public String getDedicatedterm() {
            return dedicatedterm;
        }

        public void setDedicatedterm(String dedicatedterm) {
            this.dedicatedterm = dedicatedterm;
        }
    }

    public static class ServicelistBean {
        /**
         * id : 0601
         * classname : 铝
         */

        private String id;
        private String classname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }
    }

    public static class NoticeBean {
        /**
         * title : 商标注册申请中
         * time : 2014-04-28
         * isvisible : 0
         */

        private String title;
        private String time;
        private int isvisible;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getIsvisible() {
            return isvisible;
        }

        public void setIsvisible(int isvisible) {
            this.isvisible = isvisible;
        }
    }
}
