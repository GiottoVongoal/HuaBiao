package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultBean.ClassificationBean;
import com.huabiao.aoiin.bean.SearchResultBean.ClassificationBean.ClassficationsmalltypeBean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-15 11:16
 * @description 查结果--筛选后的次级分类Adapter
 */
public class SearchResultTopAdapter extends RecyclerView.Adapter<SearchResultTopAdapter.TopHolder> {
    private ClassificationBean bean;
    private Context context;

    public SearchResultTopAdapter(Context context, ClassificationBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public SearchResultTopAdapter.TopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_top_item_layout, parent, false);
        return new TopHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultTopAdapter.TopHolder holder, final int position) {
        //左边显示最大分类
        String leftTextt = bean.getClassificationid() + " - " + bean.getClassificationname();
        holder.item_left_tv.setText(leftTextt);

        ClassficationsmalltypeBean smallBean = bean.getClassficationsmalltype().get(position);

        //上边显示第二分类的ID和最小分类的name
        String topTex = smallBean.getClassificationsmallid() + " - " + smallBean.getClassificationsmallname();
        holder.item_tv.setText(topTex);
        //右边显示状态
        String statusString = "";
        switch (smallBean.getTrademarkstatus()) {
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
        holder.item_right_tv.setText(statusString);
//        holder.item_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return bean.getClassficationsmalltype() == null ? 0 : bean.getClassficationsmalltype().size();
    }

    class TopHolder extends RecyclerView.ViewHolder {
        LinearLayout item_ll;
        TextView item_tv, item_left_tv, item_right_tv;

        public TopHolder(View itemView) {
            super(itemView);
            item_ll = (LinearLayout) itemView.findViewById(R.id.search_result_top_item_ll);
            item_tv = (TextView) itemView.findViewById(R.id.search_result_top_item_tv);
            item_left_tv = (TextView) itemView.findViewById(R.id.search_result_top_item_left_tv);
            item_right_tv = (TextView) itemView.findViewById(R.id.search_result_top_item_right_tv);
        }
    }

}
