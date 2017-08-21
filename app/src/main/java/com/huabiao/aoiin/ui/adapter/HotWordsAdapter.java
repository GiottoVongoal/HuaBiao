package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.HomeBean;
import com.huabiao.aoiin.bean.HomeBean.HotwordslistBean;
import com.huabiao.aoiin.wedgit.DrawLineChartView;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-08-07 11:34
 * @description 热搜词Adapter
 */
public class HotWordsAdapter extends RecyclerView.Adapter<HotWordsAdapter.ViewHolder> {
    private Context context;
    private List<HotwordslistBean> mList;

    public HotWordsAdapter(Context context, List<HotwordslistBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_words_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HotwordslistBean bean = mList.get(position);
        holder.top_tv.setText(bean.getName());
        holder.industrytype_tv.setText(bean.getIndustrytype());
        holder.line_chart.setLineChartBean(bean.getLinechart());
        holder.means_tv.setText(bean.getInterpretation());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView top_tv, industrytype_tv;
        private DrawLineChartView line_chart;
        private TextView means_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            top_tv = (TextView) itemView.findViewById(R.id.hot_words_item_top_tv);
            industrytype_tv = (TextView) itemView.findViewById(R.id.hot_words_item_industrytype_tv);
            line_chart = (DrawLineChartView) itemView.findViewById(R.id.hot_words_item_line_chart);
            means_tv = (TextView) itemView.findViewById(R.id.hot_words_item_means_tv);
        }
    }
}
