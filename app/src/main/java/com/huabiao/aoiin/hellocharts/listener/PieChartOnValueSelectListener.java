package com.huabiao.aoiin.hellocharts.listener;

import com.huabiao.aoiin.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int arcIndex, SliceValue value);

}
