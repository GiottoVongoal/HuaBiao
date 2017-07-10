package com.huabiao.aoiin.selecttool;

import com.huabiao.aoiin.bean.ClassificationItemBean;

import java.util.ArrayList;

public interface SelectedListener {
    /**
     * 回调接口，根据选择深度，按顺序返回选择的对象。
     */
    void onAddressSelected(ArrayList<ClassificationItemBean> selectAbles);
}
