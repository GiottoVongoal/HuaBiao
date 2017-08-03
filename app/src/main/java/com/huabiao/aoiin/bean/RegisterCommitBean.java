package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-03 16:54
 * @description 注册提交的所有数据
 */
public class RegisterCommitBean {
    private static RegisterCommitBean instance;

    private RegisterCommitBean() {
    }

    public static RegisterCommitBean getInstance() {
        if (null == instance) {
            instance = new RegisterCommitBean();
        }
        return instance;
    }

    private List<ClassificationBean> claList;//可注册类别
    private String username;//客户姓名
    private String userphone;//客户电话
    private String contractAddress;//合同地址
    private String code;//邮政编码

    //公有
    private String personName;//申请人姓名
    private String collectAddress;//选择行政区
    //私有
    private String legalPersonName;//法人姓名(法人1/个体2)
    private String certificatesID;//身份证件文件号码(自然人3)
    //申请人类型标识
    private int personType;

    private String logoImg;//商标图样
    private String proxyImg;//委托书
    private String businessLicenceImg;//营业执照

    private int serviceMode;//服务方式

    public static void setInstance(RegisterCommitBean instance) {
        RegisterCommitBean.instance = instance;
    }

    public List<ClassificationBean> getClaList() {
        return claList;
    }

    public void setClaList(List<ClassificationBean> claList) {
        this.claList = claList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCollectAddress() {
        return collectAddress;
    }

    public void setCollectAddress(String collectAddress) {
        this.collectAddress = collectAddress;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getCertificatesID() {
        return certificatesID;
    }

    public void setCertificatesID(String certificatesID) {
        this.certificatesID = certificatesID;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getProxyImg() {
        return proxyImg;
    }

    public void setProxyImg(String proxyImg) {
        this.proxyImg = proxyImg;
    }

    public String getBusinessLicenceImg() {
        return businessLicenceImg;
    }

    public void setBusinessLicenceImg(String businessLicenceImg) {
        this.businessLicenceImg = businessLicenceImg;
    }

    public int getServiceMode() {
        return serviceMode;
    }

    public void setServiceMode(int serviceMode) {
        this.serviceMode = serviceMode;
    }

    @Override
    public String toString() {
        return "RegisterCommitBean{" +
                "claList=" + claList +
                ", username='" + username + '\'' +
                ", userphone='" + userphone + '\'' +
                ", contractAddress='" + contractAddress + '\'' +
                ", code='" + code + '\'' +
                ", personName='" + personName + '\'' +
                ", collectAddress='" + collectAddress + '\'' +
                ", legalPersonName='" + legalPersonName + '\'' +
                ", certificatesID='" + certificatesID + '\'' +
                ", personType=" + personType +
                ", logoImg='" + logoImg + '\'' +
                ", proxyImg='" + proxyImg + '\'' +
                ", businessLicenceImg='" + businessLicenceImg + '\'' +
                ", serviceMode=" + serviceMode +
                '}';
    }
}
