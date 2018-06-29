package com.huabiao.aoiin.hellocharts.listener;

import com.huabiao.aoiin.hellocharts.model.PointValue;
import com.huabiao.aoiin.hellocharts.model.SubcolumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

    public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);

}
