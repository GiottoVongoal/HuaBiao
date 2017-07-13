package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultRegisteredBean.ClassificationBean.DetailedBean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-15 11:16
 * @description
 */
public class SearchResultRegisteredTopAdapter extends RecyclerView.Adapter<SearchResultRegisteredTopAdapter.TopHolder> {
    private List<DetailedBean> historyList;
    private Context context;

    public SearchResultRegisteredTopAdapter(Context context, List<DetailedBean> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public SearchResultRegisteredTopAdapter.TopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_top_item_layout, parent, false);
        return new TopHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultRegisteredTopAdapter.TopHolder holder, int position) {
        String showText = "[" + historyList.get(position).getId() + "]\t" + historyList.get(position).getTypename();
        holder.search_history_item_tv.setText(showText);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }

    class TopHolder extends RecyclerView.ViewHolder {
        TextView search_history_item_tv;

        public TopHolder(View itemView) {
            super(itemView);
            search_history_item_tv = (TextView) itemView.findViewById(R.id.search_result_top_item_tv);
        }
    }

}
