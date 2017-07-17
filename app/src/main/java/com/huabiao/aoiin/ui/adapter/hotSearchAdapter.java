package com.huabiao.aoiin.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.HotSearchWordsBean.HotwordsBean;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.wedgit.DrawLineChartView;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/7/11.
 */

public class HotSearchAdapter extends RecyclerView.Adapter<HotSearchAdapter.VH> {

    private List<HotwordsBean> datas1;
    private Context mContext;

    //构造器，接收数据集
    public HotSearchAdapter(List<HotwordsBean> datas) {
        this.datas1 = datas;
    }
    @Override//将布局转化为View并传递给RecycleView封装好的ViewHolder
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_hot, viewGroup, false);
        VH vh = new VH(view);
        return vh;
    }

    //建立起ViewHolder中视图与数据的关联
    @Override
    public void onBindViewHolder(HotSearchAdapter.VH viewHolder, int i) {
        //将数据填充到具体的view中
        HotwordsBean bean = datas1.get(i);
        viewHolder.title.setText(bean.getIndustrytype() + ":" + bean.getName());
        viewHolder.content.setText(bean.getInterpretation());

        //折线图
        LineChartBean linechart = bean.getLinechart();
        if (linechart != null) {
            viewHolder.hsw_line_chart.setLineChartBean(linechart);
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas1.size();
    }

    class VH extends RecyclerView.ViewHolder {
        public TextView content;
        public DrawLineChartView hsw_line_chart;
        public TextView title;

        public VH(View v) {
            super(v);
            hsw_line_chart = (DrawLineChartView) v.findViewById(R.id.hsw_line_chart);//这里应该是去取折线图
            title = (TextView) v.findViewById(R.id.hsw_title);
            content = (TextView) v.findViewById(R.id.hsw_content);//取数据库文章
        }
    }
}