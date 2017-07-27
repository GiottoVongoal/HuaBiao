package com.huabiao.aoiin.bean;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-27 09:52
 * @description 注册--申请人选择Bean
 */
public class RegisterApplicantPersonBean {

    private static RegisterApplicantPersonBean instance;

    private RegisterApplicantPersonBean() {
    }

    public static RegisterApplicantPersonBean getInstance() {
        if (null == instance) {
            instance = new RegisterApplicantPersonBean();
        }
        return instance;
    }

    //公有(个体工商户)
    private String personName;//申请人姓名
    private String selectAddress;//选择行政区划
    private String personAddress;//申请人地址
    private String contactsPersonName;//联系人
    private String contactsPersonPhone;//联系人电话
    private String contactsPersonCode;//邮政编码
    private String collectPersonName;//收件人姓名
    private String collectPersonPhone;//收件人电话
    private String collectPersonSelectAddress;//选择收件人地址
    private String collectPersonAddress;//收件人地址
    // 法人或其他组织
    private String legalPersonName;//法人姓名
    //自然人
    private String certificatesType;//身份证件文件名称
    private String certificatesID;//身份证件文件号码

    //修改申请人类型
    private int changeType;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSelectAddress() {
        return selectAddress;
    }

    public void setSelectAddress(String selectAddress) {
        this.selectAddress = selectAddress;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getContactsPersonName() {
        return contactsPersonName;
    }

    public void setContactsPersonName(String contactsPersonName) {
        this.contactsPersonName = contactsPersonName;
    }

    public String getContactsPersonPhone() {
        return contactsPersonPhone;
    }

    public void setContactsPersonPhone(String contactsPersonPhone) {
        this.contactsPersonPhone = contactsPersonPhone;
    }

    public String getContactsPersonCode() {
        return contactsPersonCode;
    }

    public void setContactsPersonCode(String contactsPersonCode) {
        this.contactsPersonCode = contactsPersonCode;
    }

    public String getCollectPersonName() {
        return collectPersonName;
    }

    public void setCollectPersonName(String collectPersonName) {
        this.collectPersonName = collectPersonName;
    }

    public String getCollectPersonPhone() {
        return collectPersonPhone;
    }

    public void setCollectPersonPhone(String collectPersonPhone) {
        this.collectPersonPhone = collectPersonPhone;
    }

    public String getCollectPersonSelectAddress() {
        return collectPersonSelectAddress;
    }

    public void setCollectPersonSelectAddress(String collectPersonSelectAddress) {
        this.collectPersonSelectAddress = collectPersonSelectAddress;
    }

    public String getCollectPersonAddress() {
        return collectPersonAddress;
    }

    public void setCollectPersonAddress(String collectPersonAddress) {
        this.collectPersonAddress = collectPersonAddress;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(String certificatesType) {
        this.certificatesType = certificatesType;
    }

    public String getCertificatesID() {
        return certificatesID;
    }

    public void setCertificatesID(String certificatesID) {
        this.certificatesID = certificatesID;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    @Override
    public String toString() {
        return "RegisterApplicantPersonBean{" +
                "personName='" + personName + '\'' +
                ", selectAddress='" + selectAddress + '\'' +
                ", personAddress='" + personAddress + '\'' +
                ", contactsPersonName='" + contactsPersonName + '\'' +
                ", contactsPersonPhone='" + contactsPersonPhone + '\'' +
                ", contactsPersonCode='" + contactsPersonCode + '\'' +
                ", collectPersonName='" + collectPersonName + '\'' +
                ", collectPersonPhone='" + collectPersonPhone + '\'' +
                ", collectPersonSelectAddress='" + collectPersonSelectAddress + '\'' +
                ", collectPersonAddress='" + collectPersonAddress + '\'' +
                ", legalPersonName='" + legalPersonName + '\'' +
                ", certificatesType='" + certificatesType + '\'' +
                ", certificatesID='" + certificatesID + '\'' +
                ", changeType=" + changeType +
                '}';
    }
}
