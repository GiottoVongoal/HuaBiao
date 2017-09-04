package com.huabiao.aoiin.wedgit;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.LineChartBean;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-07-06 19:59
 * @description 折线图
 */
public class DrawLineChartView extends RelativeLayout {
    LinearLayout line_chart_ll;//
    LineChartView chart;
    List<Line> lines = new ArrayList<>();

    TextView line_chart_trademark_name;
//    TextView line_chart_trademark_classification;

    LineChartBean bean;

    public DrawLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.line_chart_view_layout, this);
    }

    public void setLineChartBean(LineChartBean bean) {
        this.bean = bean;
        drawLineChart();
        setTextString();
        initLineChart();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView(this);
    }

    private void initView(View lineChartView) {
        line_chart_ll = (LinearLayout) lineChartView.findViewById(R.id.line_chart_ll);
        line_chart_ll.setVisibility(GONE);
        chart = (LineChartView) lineChartView.findViewById(R.id.chart);
        chart.setVisibility(INVISIBLE);
        line_chart_trademark_name = (TextView) lineChartView.findViewById(R.id.line_chart_trademark_name);
//        line_chart_trademark_classification = (TextView) lineChartView.findViewById(R.id.line_chart_trademark_classification);
    }


    //显示
    private void setTextString() {
        if (!(TextUtils.isEmpty(bean.getTradename())) && !(TextUtils.isEmpty(bean.getTrademarkclassification()))) {
            line_chart_trademark_name.setText(bean.getTradename());
//            line_chart_trademark_classification.setText(bean.getTrademarkclassification());
        }
    }

    private void drawLineChart() {
        line_chart_ll.removeAllViews();
        lines.clear();
        if (bean.getLines() != null && bean.getLines().size() > 0) {
            for (int i = 0; i < bean.getLines().size(); i++) {
                LineChartBean.LinesBean data = bean.getLines().get(i);
                //增加折线标注
//                TextView tv = new TextView(getContext());
//                tv.setPadding(0, 10, 0, 0);
//                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                line_chart_ll.addView(tv, lp);
//                tv.setTextSize(11);
//                tv.setTextColor(Color.parseColor(data.getLinecolor()));
//                tv.setText(data.getLinename());
                List<PointValue> mPointValues = new ArrayList<>();
                for (int j = 0; j < data.getLinevalue().size(); j++) {
                    mPointValues.add(new PointValue(j, data.getLinevalue().get(j)));
                }
                Line line = new Line(mPointValues).setColor(Color.parseColor(data.getLinecolor())).setCubic(false).setStrokeWidth(2);  //折线的颜色,粗细
                line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.DIAMOND  ValueShape.CIRCLE  ValueShape.SQUARE）
                line.setCubic(true);//曲线是否平滑
                line.setFilled(true);//是否填充曲线的面积
                // line.setHasLabels(true);//曲线的数据坐标是否加上备注
                line.setPointRadius(1);//设置坐标点大小
                line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
                line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
                // line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
                lines.add(line);
            }
        }
    }

    private void initLineChart() {
        LineChartData data = new LineChartData();
        data.setLines(lines);
        //X 轴的显示
        List<AxisValue> mAxisValues = new ArrayList<>();
        for (int i = 0; i < bean.getXaxistag().size(); i++) {
            mAxisValues.add(new AxisValue(i).setLabel(bean.getXaxistag().get(i)));
        }
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);//X轴文字水平
        axisX.setTextColor(getResources().getColor(R.color.grey_282828));  //设置字体颜色
//        axisX.setName("未来几天的天气");  //表格名称
        axisX.setTextSize(7);//设置字体大小
        axisX.setMaxLabelChars(7);  //最多几个X轴坐标
        axisX.setValues(mAxisValues);  //填充X轴的坐标名称
        axisX.setHasLines(true);//x 轴分割线
        data.setAxisXBottom(axisX); //x 轴在底部
//      data.setAxisXTop(axisX);  //x 轴在顶部

        Axis axisY = new Axis();  //Y轴
        axisY.setTextColor(getResources().getColor(R.color.grey_282828));
//        axisY.setName("温度");//y轴标注
        axisY.setTextSize(7);//设置字体大小
        axisY.setHasLines(true);
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        data.setAxisYLeft(axisY);  //Y轴设置在左边
//      data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        chart.setInteractive(true);
        chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chart.setLineChartData(data);
        chart.setVisibility(View.VISIBLE);
    }
}
