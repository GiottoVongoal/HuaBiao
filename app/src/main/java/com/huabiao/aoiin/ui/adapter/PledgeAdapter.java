package com.huabiao.aoiin.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.PledgeBean.PledgelistBean;
import com.huabiao.aoiin.wedgit.XCRoundRectImageView;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.IntentUtils;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/23.
 */

public class PledgeAdapter extends RecyclerView.Adapter<PledgeAdapter.ViewHolder> {
    private Context context;
    private List<PledgelistBean> pledgelist;

    public PledgeAdapter(Context context, List<PledgelistBean> pledgelist) {
        this.context = context;
        this.pledgelist = pledgelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_finance_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PledgelistBean bean = pledgelist.get(position);
        holder.phonenum_tv.setText(bean.getPhonenumber());
        holder.bankname_tv.setText(bean.getBankname());
        holder.connect_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).startActivity(IntentUtils.getDialIntent(bean.getPhonenumber()));
            }
        });
        BitmapLoader.ins().loadImage(bean.getImg(), R.mipmap.logobg, holder.bank_img);
    }

    @Override
    public int getItemCount() {
        return pledgelist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView phonenum_tv, bankname_tv, connect_tv;
        private XCRoundRectImageView bank_img;

        public ViewHolder(View itemView) {
            super(itemView);
            bank_img = (XCRoundRectImageView) itemView.findViewById(R.id.auctionrcv_img);
            bankname_tv = (TextView) itemView.findViewById(R.id.auctionrcv_companyname_tv);
            phonenum_tv = (TextView) itemView.findViewById(R.id.auctionrcv_phonenumber_tv);
            connect_tv = (TextView) itemView.findViewById(R.id.auctionrcv_connect_tv);
        }
    }
}
