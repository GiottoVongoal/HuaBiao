package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.BuyingInfoBean;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/3.
 */

public class BuyingAdapter2 extends RecyclerView.Adapter<BuyingAdapter2.VH2> {

    private Context context;
    private List<BuyingInfoBean.NoticeBean> noticedata;

    public BuyingAdapter2(Context context, List<BuyingInfoBean.NoticeBean> noticedata) {
        this.context = context;
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
            holder.noticeitem_tv.setText(noticeBean.getTitle());
            holder.noticeitemtime_tv.setText(noticeBean.getTime());
        //公告是否可见
        int status = noticeBean.getIsvisible();
        switch (status) {
            case 0:
                holder.noticevisable_tv.setVisibility(View.INVISIBLE);
                break;
            case 1:
                holder.noticevisable_tv.setVisibility(View.VISIBLE);
                break;
        }
        //布局中最后一条线不可见
        if (position == noticedata.size() - 1) {
            holder.buying_rcv2_linear.setVisibility(View.INVISIBLE);
            holder.user_progress_date_item_spot_iv1.setBackground(context.getResources().getDrawable(R.mipmap.point_yellow));
        }else if(position==0){
            holder.buying_rcv1_linear.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return noticedata == null ? 0 : noticedata.size();
    }

    class VH2 extends RecyclerView.ViewHolder {
        //公告与状态
        private TextView noticeitem_tv;
        private TextView noticeitemtime_tv;
        private TextView noticevisable_tv;
        private View buying_rcv1_linear;
        private View buying_rcv2_linear;
        private ImageView user_progress_date_item_spot_iv1;
        public VH2(View itemView) {
            super(itemView);
            noticeitem_tv = (TextView) itemView.findViewById(R.id.buying_rcv2_status1);
            noticeitemtime_tv = (TextView) itemView.findViewById(R.id.buying_rcv2_status1_date);
            noticevisable_tv = (TextView) itemView.findViewById(R.id.buying_rcv2_noticevisable);
            buying_rcv2_linear = itemView.findViewById(R.id.buying_rcv2_linear);
            buying_rcv1_linear=itemView.findViewById(R.id.buying_rcv1_linear);
            user_progress_date_item_spot_iv1= (ImageView) itemView.findViewById(R.id.user_progress_date_item_spot_iv1);
        }
    }
}