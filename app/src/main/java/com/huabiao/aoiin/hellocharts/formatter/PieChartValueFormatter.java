package com.huabiao.aoiin.hellocharts.formatter;

import com.huabiao.aoiin.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);
}
