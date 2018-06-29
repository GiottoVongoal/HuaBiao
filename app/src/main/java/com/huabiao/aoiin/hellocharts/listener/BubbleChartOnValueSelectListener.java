package com.huabiao.aoiin.hellocharts.listener;

import com.huabiao.aoiin.hellocharts.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int bubbleIndex, BubbleValue value);

}
