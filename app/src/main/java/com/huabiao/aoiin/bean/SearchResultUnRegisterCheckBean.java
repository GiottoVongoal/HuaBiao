package com.huabiao.aoiin.bean;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-14 17:05
 * @description 原来监测未注册的两个GridView的选择情况
 */
public class SearchResultUnRegisterCheckBean extends ClassificationBean {

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
