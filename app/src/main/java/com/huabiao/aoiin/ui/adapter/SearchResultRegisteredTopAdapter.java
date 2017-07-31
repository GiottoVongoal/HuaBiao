package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultRegisteredBean.Classification.ClassficationsmalltypeBean;
import com.huabiao.aoiin.wedgit.ShowRegisteredTypePopupWindow;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-15 11:16
 * @description 查询--已注册--筛选后的次级分类Adapter
 */
public class SearchResultRegisteredTopAdapter extends RecyclerView.Adapter<SearchResultRegisteredTopAdapter.TopHolder> {
    private List<ClassficationsmalltypeBean> historyList;
    private Context context;

    public SearchResultRegisteredTopAdapter(Context context, List<ClassficationsmalltypeBean> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public SearchResultRegisteredTopAdapter.TopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_top_item_layout, parent, false);
        return new TopHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultRegisteredTopAdapter.TopHolder holder, final int position) {
        final ClassficationsmalltypeBean bean = historyList.get(position);
        String showText = bean.getClassificationsmallid() + " - " + bean.getClassificationsmallname();
        holder.search_history_item_tv.setText(showText);
        holder.search_history_item_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击显示小分类和注册状态
                int direction = (position % 2 == 0) ? 1 : 2;
                ShowRegisteredTypePopupWindow popupWindow = new ShowRegisteredTypePopupWindow(
                        context, bean.getDetailed(), direction, bean.getTrademarkstatus());
                if (direction == 1)
                    popupWindow.showAtDropDownLeft(view);
                else
                    popupWindow.showAtDropDownRight(view);
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

    class TopHolder extends RecyclerView.ViewHolder {
        TextView search_history_item_tv;

        public TopHolder(View itemView) {
            super(itemView);
            search_history_item_tv = (TextView) itemView.findViewById(R.id.search_result_top_item_tv);
        }
    }

}
