package com.huabiao.aoiin.wedgit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

import static android.R.attr.data;
import static android.R.attr.label;
import static android.R.id.list;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-07-07 18:09
 * @description
 */
public class DrawColumnChartView extends RelativeLayout {
    ColumnChartView chart;

    public DrawColumnChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.column_chart_view_layout, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView(this);
    }

    private void initView(View view) {
        chart = (ColumnChartView) view.findViewById(R.id.column);
        chart.setOnValueTouchListener(new ValueTouchListener());
    }

    public void generateDefaultData(List<TestBean> months) {
        //一个柱状图需要一个柱子集合
        List<Column> columnList = new ArrayList<>();
        //每根柱子又可以分为多根柱子
        List<SubcolumnValue> subcolumnValueList;
//        int columns = 7;//一共7根柱子
        int subColumn = 1;//每根柱子的子柱子为1根
        for (int i = 0; i < months.size(); i++) {
            subcolumnValueList = new ArrayList<>();
            for (int j = 0; j < subColumn; j++) {
                //每根子柱子需要一个值和颜色
                subcolumnValueList.add(new SubcolumnValue(months.get(i).getNum(), ChartUtils.pickColor()));
//                subcolumnValueList.add(new SubcolumnValue((float) Math.random(), getResources().getColor(R.color.gray_E9E7E7)));
            }
            //每根柱子需要一个子柱子集合
            Column column = new Column(subcolumnValueList);
            //这一步是能让圆柱标注数据显示带小数的重要一步
//            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(2);
//            column.setFormatter(chartValueFormatter);
            column.setHasLabels(true);//是否直接显示标注（其它的一些设置类似折线图）
            columnList.add(column);
        }
        ColumnChartData data = new ColumnChartData(columnList);
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("段位");
        axisX.setTextColor(Color.BLACK);
        axisY.setName("出场率%");
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(Color.BLACK);//颜色
        //给x轴设置值
        List<AxisValue> list = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            list.add(new AxisValue(i).setLabel(months.get(i).getName()));//i代表位置，label则是在轴上该位置的标注
        }
        axisX.setValues(list);
        // 给y轴设置值
        List<AxisValue> listY = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            listY.add(new AxisValue(i).setValue(i));//i代表位置，label则是在轴上该位置的标注
        }
        axisY.setValues(listY);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //设置是否让多根子柱子在同一根柱子上显示（会以断层的形式分开），由于这里子柱子只有一根，故设置true也无意义，读者可自行尝试
        data.setStacked(false);
        data.setFillRatio(0.3F);//设置柱状图宽度
        data.setValueLabelBackgroundAuto(false);// 设置数据背景是否跟随节点颜色
//        data.setValueLabelBackgroundColor(Color.BLUE);// 设置数据背景颜色
        data.setValueLabelBackgroundEnabled(false);// 设置是否有数据背景
        data.setValueLabelsTextColor(Color.BLACK);// 设置数据文字颜色
        data.setValueLabelTextSize(9);// 设置数据文字大小
        chart.setColumnChartData(data);
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
//            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub
        }
    }
}
