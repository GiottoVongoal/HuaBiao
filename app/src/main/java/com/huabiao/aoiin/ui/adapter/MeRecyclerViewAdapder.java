package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.model.MeRecyclerViewBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
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
    private List<MeRecyclerViewBean> mList = new ArrayList<>();
    private Context mContext;
    private int mHeaderCount = 1;//头部View个数
    private View mHeaderView;

    public MeRecyclerViewAdapder(Context mContext, List<MeRecyclerViewBean> mList) {
        this.mList = mList;
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
        final MeRecyclerViewBean bean = mList.get(position - 1);
        holder.me_recyclerview_item_iv.setImageResource(bean.getImg());
        holder.me_recyclerview_item_tv.setText(bean.getText());
        holder.me_recyclerview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mList.size() : mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView me_recyclerview_item_iv;
        public TextView me_recyclerview_item_tv;
        public ConstraintLayout me_recyclerview_item;

        public ViewHolder(View itemView) {
            super(itemView);
            me_recyclerview_item_iv = (ImageView) itemView.findViewById(R.id.me_recyclerview_item_iv);
            me_recyclerview_item_tv = (TextView) itemView.findViewById(R.id.me_recyclerview_item_tv);
            me_recyclerview_item = (ConstraintLayout) itemView.findViewById(R.id.me_recyclerview_item);
        }
    }
}
