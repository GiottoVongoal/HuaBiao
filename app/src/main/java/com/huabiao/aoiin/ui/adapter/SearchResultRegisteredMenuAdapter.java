package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultClassificationListBean.ClassificationBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-13 13:55
 * @description
 */
public class SearchResultRegisteredMenuAdapter extends RecyclerView.Adapter<SearchResultRegisteredMenuAdapter.MyViewHolder> {
    private Context context;
    private List<ClassificationBean> list;

    public SearchResultRegisteredMenuAdapter(Context context, List<ClassificationBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_popwin, parent, false);
        return new MyViewHolder(view);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ClassificationBean bean = list.get(position);
        holder.popwind_tv.setText(bean.getClassificationname());
        holder.popwind_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView popwind_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            popwind_tv = (TextView) itemView.findViewById(R.id.popwind_tv);

        }
    }
}
