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

public class BuyingAdapter1 extends RecyclerView.Adapter<BuyingAdapter1.VH1> {

    private List<BuyingInfoBean.ServicelistBean> servicedata;

    public BuyingAdapter1(List<BuyingInfoBean.ServicelistBean> servicedata) {
        this.servicedata = servicedata;
    }

    @Override
    public BuyingAdapter1.VH1 onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_buying_recyclerview1_item, viewGroup, false);
        VH1 vh1 = new VH1(view1);
        return vh1;
    }

    @Override
    public void onBindViewHolder(BuyingAdapter1.VH1 holder, int position) {
        BuyingInfoBean.ServicelistBean servicelistBean = servicedata.get(position);
        holder.serviceitem.setText(servicelistBean.getId() + "——" + servicelistBean.getClassname());

    }

    @Override
    public int getItemCount() {
        return servicedata == null ? 0 : servicedata.size();
    }

    class VH1 extends RecyclerView.ViewHolder {
        //商标服务列表的item
        public TextView serviceitem;

        public VH1(View itemView) {
            super(itemView);
            serviceitem = (TextView) itemView.findViewById(R.id.buying_recyler1);
        }
    }
}
