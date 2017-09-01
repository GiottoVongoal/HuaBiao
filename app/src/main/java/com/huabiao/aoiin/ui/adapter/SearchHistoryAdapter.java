package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-15 11:16
 * @description
 */
public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryHolder> {
    private List<String> historyList;
    private Context context;

    public SearchHistoryAdapter(Context context, List<String> historyList) {
        this.historyList = historyList;
        this.context = context;
    }

    public InterfaceManager.OnItemClickListener itemClickListener;

    public void setItemClickListener(InterfaceManager.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public SearchHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_history_item_layout, parent, false);
        return new SearchHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHistoryHolder holder, final int position) {
        holder.search_history_item_tv.setText(historyList.get(position));
        holder.search_history_item_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickListener(view, position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }

    class SearchHistoryHolder extends RecyclerView.ViewHolder {
        TextView search_history_item_tv;

        public SearchHistoryHolder(View itemView) {
            super(itemView);
            search_history_item_tv = (TextView) itemView.findViewById(R.id.search_history_item_tv);
        }
    }

}
