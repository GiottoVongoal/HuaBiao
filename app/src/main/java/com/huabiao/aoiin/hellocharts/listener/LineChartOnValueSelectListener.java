package com.huabiao.aoiin.hellocharts.listener;

import com.huabiao.aoiin.hellocharts.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
