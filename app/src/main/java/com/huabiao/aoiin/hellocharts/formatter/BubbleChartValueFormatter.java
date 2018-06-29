package com.huabiao.aoiin.hellocharts.formatter;

import com.huabiao.aoiin.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    public int formatChartValue(char[] formattedValue, BubbleValue value);
}
