package com.huabiao.aoiin.bean;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-18 19:35
 * @description 收货地址列表Bean
 */
public class UserAddressListBean {
    private String name;
    private String phone;
    private String selectAddress;
    private String address;
    private String code;

    public UserAddressListBean() {
    }

    public UserAddressListBean(String name, String phone, String selectAddress, String address, String code) {
        this.name = name;
        this.phone = phone;
        this.selectAddress = selectAddress;
        this.address = address;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSelectAddress() {
        return selectAddress;
    }

    public void setSelectAddress(String selectAddress) {
        this.selectAddress = selectAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
