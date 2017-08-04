package com.huabiao.aoiin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.BuyingInfoBean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/3.
 */

public class BuyingAdapter2 extends RecyclerView.Adapter<BuyingAdapter2.VH2> {

    private List<BuyingInfoBean.NoticeBean> noticedata;

    public BuyingAdapter2(List<BuyingInfoBean.NoticeBean> noticedata) {
        this.noticedata = noticedata;
    }

    @Override
    public BuyingAdapter2.VH2 onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_buying_recyclerview2_item, viewGroup, false);
        VH2 vh2 = new VH2(view2);
        return vh2;
    }

    @Override
    public void onBindViewHolder(BuyingAdapter2.VH2 holder, int position) {
        BuyingInfoBean.NoticeBean noticeBean = noticedata.get(position);
        holder.noticeitem.setText(noticeBean.getTitle());
        holder.noticeitemtime.setText(noticeBean.getTime());
        //公告是否可见
        int status = noticeBean.getIsvisible();
        switch (status) {
            case 0:
                holder.noticevisable.setVisibility(View.INVISIBLE);
                break;
            case 1:
                holder.noticevisable.setVisibility(View.VISIBLE);
                break;
        }
        if(position==noticedata.size()-1){
            holder.buying_rcv2_linear.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return noticedata == null ? 0 : noticedata.size();
    }

    class VH2 extends RecyclerView.ViewHolder {
        //公告以及状态
        private TextView noticeitem;
        private TextView noticeitemtime;
        private TextView noticevisable;
        private View buying_rcv2_linear;

        public VH2(View itemView) {
            super(itemView);
            noticeitem = (TextView) itemView.findViewById(R.id.buying_rcv2_status1);
            noticeitemtime = (TextView) itemView.findViewById(R.id.buying_rcv2_status1_date);
            noticevisable = (TextView) itemView.findViewById(R.id.buying_rcv2_noticevisable);
            buying_rcv2_linear = itemView.findViewById(R.id.buying_rcv2_linear);
        }
    }
}