package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SelectClassificationCheckBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-08-02 21:28
 * @description 自主注册第一张卡片中的可注册类别
 */
public class RegisterCardOneAdapter extends RecyclerView.Adapter<RegisterCardOneAdapter.ViewHolder> {
    private Context context;
    private List<SelectClassificationCheckBean> list = new ArrayList<>();

    public RegisterCardOneAdapter(Context context, List<SelectClassificationCheckBean> list) {
        this.context = context;
        this.list = list;
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_classification_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SelectClassificationCheckBean bean = list.get(position);
        holder.top_tv.setText(bean.getClassificationid());
        holder.bottom_tv.setText(bean.getClassificationname());
        boolean ischeck = bean.isCheck();
        if (ischeck) {
            holder.item_ll.setBackground(context.getResources().getDrawable(R.drawable.xuanzhong));
        } else {
            holder.item_ll.setBackground(context.getResources().getDrawable(R.drawable.weixuanzhong));
        }
        holder.item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickListener(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_ll;
        TextView top_tv;
        TextView bottom_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_ll = (LinearLayout) itemView.findViewById(R.id.select_classification_item);
            top_tv = (TextView) itemView.findViewById(R.id.select_classification_item_top_tv);
            bottom_tv = (TextView) itemView.findViewById(R.id.select_classification_item_bottom_tv);
        }
    }
}
