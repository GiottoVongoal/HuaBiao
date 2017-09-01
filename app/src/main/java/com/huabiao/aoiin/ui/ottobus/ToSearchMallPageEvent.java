package com.huabiao.aoiin.ui.ottobus;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.ottobus
 * @date 2017-09-01 16:22
 * @description 去商城页面事件
 */
public class ToSearchMallPageEvent {
    private int index;
    private String searchStr;

    public ToSearchMallPageEvent(int index) {
        this.index = index;
    }

    public ToSearchMallPageEvent(int index, String searchStr) {
        this.index = index;
        this.searchStr = searchStr;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    @Override
    public String toString() {
        return "ToSearchMallPageEvent{" +
                "index=" + index +
                ", searchStr='" + searchStr + '\'' +
                '}';
    }
}
