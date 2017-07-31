package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultRegisteredBean.RecommendBean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-15 11:16
 * @description
 */
public class SearchResultRegisteredBottomAdapter extends RecyclerView.Adapter<SearchResultRegisteredBottomAdapter.TopHolder> {
    private List<RecommendBean> recommendList;
    private Context context;

    public SearchResultRegisteredBottomAdapter(Context context, List<RecommendBean> recommendList) {
        this.context = context;
        this.recommendList = recommendList;
    }

    @Override
    public SearchResultRegisteredBottomAdapter.TopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_bottom_item_layout, parent, false);
        return new TopHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultRegisteredBottomAdapter.TopHolder holder, int position) {
        RecommendBean bean = recommendList.get(position);
        holder.title_tv.setText(bean.getTradename());
        String typeString = "[" + bean.getClassificationid() + "]\t" + bean.getTrademarkclassification();
        holder.type_tv.setText(typeString);
        String statusString = "";
        switch (bean.getTrademarkstatus()) {
            case 1:
                statusString = "已注册";
                break;
            case 2:
                statusString = "未注册";
                break;
            case 3:
                statusString = "待审核";
                break;
        }
        holder.status_tv.setText(statusString);
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
        TextView title_tv, type_tv, status_tv;

        public TopHolder(View itemView) {
            super(itemView);
            title_tv = (TextView) itemView.findViewById(R.id.search_result_bottom_item_title_tv);
            type_tv = (TextView) itemView.findViewById(R.id.search_result_bottom_item_type_tv);
            status_tv = (TextView) itemView.findViewById(R.id.search_result_bottom_item_status_tv);
        }
    }

}
