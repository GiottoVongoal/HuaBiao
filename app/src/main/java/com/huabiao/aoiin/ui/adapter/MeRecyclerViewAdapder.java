package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-06-07 11:48
 * @description
 */
public class MeRecyclerViewAdapder extends RecyclerView.Adapter<MeRecyclerViewAdapder.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private String[] text;
    private Context mContext;
    private View mHeaderView;

    public MeRecyclerViewAdapder(Context mContext, String[] text) {
        this.text = text;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.me_recyclerview_item_layout, parent, false);
        return new ViewHolder(layout);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            return;
        final String textString = text[position - 1];
        holder.me_recyclerview_item_tv.setText(textString);
        holder.me_recyclerview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? text.length : text.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
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
