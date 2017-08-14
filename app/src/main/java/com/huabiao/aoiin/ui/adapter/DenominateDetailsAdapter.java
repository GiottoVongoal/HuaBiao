package com.huabiao.aoiin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultUnregisteredAndCreatNameBean.ClassificationBean;

/**
 * Created by Aoiin-9 on 2017/8/11.
 */

public class DenominateDetailsAdapter extends RecyclerView.Adapter<DenominateDetailsAdapter.ViewHolder> {
    private ClassificationBean cmdata;

    public DenominateDetailsAdapter(ClassificationBean cmdata) {
        this.cmdata = cmdata;
    }

    @Override
    public DenominateDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_denominate_details_rcv, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       ClassificationBean.ClassficationsmalltypeBean cb=cmdata.getClassficationsmalltype().get(position);
        holder.tv2.setText(cmdata.getClassificationid()+"-"+cmdata.getClassificationname());
        holder.tv1.setText(cb.getClassificationsmallid()+"-"+cb.getClassificationsmallname());
    }

    @Override
    public int getItemCount() {
        return cmdata.getClassficationsmalltype() == null ? 0 : cmdata.getClassficationsmalltype().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //取名详情页的recyclerview的商品分类
        public TextView tv1;
        public TextView tv2;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.denominate_details_rcv_tv1);
            tv2=(TextView)itemView.findViewById(R.id.denominate_details_rcv_tv2);
        }
    }
}
