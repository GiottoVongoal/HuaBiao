package com.huabiao.aoiin.hellocharts.formatter;

import com.huabiao.aoiin.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
