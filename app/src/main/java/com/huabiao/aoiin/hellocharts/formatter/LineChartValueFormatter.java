package com.huabiao.aoiin.hellocharts.formatter;

import com.huabiao.aoiin.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
