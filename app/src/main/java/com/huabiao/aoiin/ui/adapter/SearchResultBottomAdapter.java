package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultBean.RecommendBean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-15 11:16
 * @description
 */
public class SearchResultBottomAdapter extends RecyclerView.Adapter<SearchResultBottomAdapter.TopHolder> {
    private List<RecommendBean> recommendList;
    private Context context;

    public SearchResultBottomAdapter(Context context, List<RecommendBean> recommendList) {
        this.context = context;
        this.recommendList = recommendList;
    }

    @Override
    public SearchResultBottomAdapter.TopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_bottom_item_layout, parent, false);
        return new TopHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultBottomAdapter.TopHolder holder, int position) {
        RecommendBean bean = recommendList.get(position);
        holder.title_tv.setText(bean.getTradename());
        String typeString = "[" + bean.getClassificationid() + "]\t" + bean.getTrademarkclassification();
        holder.type_tv.setText(typeString);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return recommendList == null ? 0 : recommendList.size();
    }

    class TopHolder extends RecyclerView.ViewHolder {
        TextView title_tv, type_tv;

        public TopHolder(View itemView) {
            super(itemView);
            title_tv = (TextView) itemView.findViewById(R.id.search_result_bottom_item_title_tv);
            type_tv = (TextView) itemView.findViewById(R.id.search_result_bottom_item_type_tv);
        }
    }

}
