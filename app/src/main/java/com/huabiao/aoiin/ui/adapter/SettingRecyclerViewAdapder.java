package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-07 11:48
 * @description
 */
public class SettingRecyclerViewAdapder extends RecyclerView.Adapter<SettingRecyclerViewAdapder.ViewHolder> {
    private String[] text;
    private Context mContext;

    public SettingRecyclerViewAdapder(Context mContext, String[] text) {
        this.text = text;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.me_recyclerview_item_layout, parent, false);
        return new ViewHolder(layout);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.me_recyclerview_item_tv.setText(text[position]);
        holder.me_recyclerview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return text == null ? 0 : text.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView me_recyclerview_item_tv;
        public CardView me_recyclerview_item;

        public ViewHolder(View itemView) {
            super(itemView);
            me_recyclerview_item_tv = (TextView) itemView.findViewById(R.id.me_recyclerview_item_tv);
            me_recyclerview_item = (CardView) itemView.findViewById(R.id.me_recyclerview_item);
        }
    }
}
