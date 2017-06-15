package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huabiao.aoiin.R;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-15 11:16
 * @description
 */
public class SearchHistoryAdapter extends BaseAdapter {
    private List<String> historyList;
    private Context context;
    private LayoutInflater minflater;

    public SearchHistoryAdapter(Context context, List<String> historyList) {
        this.historyList = historyList;
        this.context = context;
        minflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return historyList == null ? 0 : historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SearchHistoryHolder viewHold;
        if (convertView == null) {
            viewHold = new SearchHistoryHolder();
            convertView = minflater.inflate(R.layout.search_history_item_layout, null);
            viewHold.search_history_item_tv = (TextView) convertView.findViewById(R.id.search_history_item_tv);
            convertView.setTag(viewHold);
        } else {
            viewHold = (SearchHistoryHolder) convertView.getTag();
        }
        if (historyList != null) {
            viewHold.search_history_item_tv.setText(historyList.get(position));
        }
        return convertView;
    }

    class SearchHistoryHolder {
        TextView search_history_item_tv;
    }

}
