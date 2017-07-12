package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean.IndustrylistBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-12 10:40
 * @description
 */
public class IndustryPopupWindowAdapter extends RecyclerView.Adapter<IndustryPopupWindowAdapter.ViewHolder> {
    List<IndustrylistBean> list = new ArrayList<>();
    private final Context context;
    private int place;

    //创建点击事件
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public IndustryPopupWindowAdapter(Context context, int place, List<IndustrylistBean> list) {
        this.context = context;
        this.place = place;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.industry_popupwindow_dialog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //建立起VIewHolder中试视图与数据的关联
        holder.dialog_item_tv.setText(list.get(position).getIndustryname());
        if (place == position) {
            holder.dialog_item_iv.setVisibility(View.VISIBLE);
        } else {
            holder.dialog_item_iv.setVisibility(View.INVISIBLE);
        }
        //增加点击事件
        if (null != itemClickListener) {
            // 为item增加点击事件
            holder.dialog_item_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClickListener(v, position);
                }
            });
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dialog_item_tv;
        public ImageView dialog_item_iv;
        public LinearLayout dialog_item_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            dialog_item_tv = (TextView) itemView.
                    findViewById(R.id.industry_popupwindow_dialog_item_tv);
            dialog_item_iv = (ImageView) itemView.
                    findViewById(R.id.industry_popupwindow_dialog_item_iv);
            dialog_item_ll = (LinearLayout) itemView.
                    findViewById(R.id.industry_popupwindow_dialog_item_ll);
        }
    }
}
