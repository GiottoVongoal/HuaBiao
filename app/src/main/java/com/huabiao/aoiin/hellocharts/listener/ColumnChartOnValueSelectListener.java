package com.huabiao.aoiin.hellocharts.listener;

import com.huabiao.aoiin.hellocharts.model.SubcolumnValue;

public interface ColumnChartOnValueSelectListener extends  OnValueDeselectListener {

    public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

}
