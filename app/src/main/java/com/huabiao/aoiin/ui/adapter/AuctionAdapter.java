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
import com.huabiao.aoiin.bean.AuctionBean;
import com.huabiao.aoiin.wedgit.XCRoundRectImageView;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.IntentUtils;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/25.
 */

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.ViewHolder> {
    private Context context;
    private List<AuctionBean.AuctionlistBean> auctionlist;

    public AuctionAdapter(Context context, List<AuctionBean.AuctionlistBean> auctionlist) {
        this.context = context;
        this.auctionlist = auctionlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_auction_rcv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AuctionBean.AuctionlistBean bean = auctionlist.get(position);
        holder.phonenum_tv.setText(bean.getPhone());
        holder.companyname_tv.setText(bean.getCompany());
        holder.connect_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).startActivity(IntentUtils.getDialIntent(bean.getPhone()));
            }
        });
        BitmapLoader.ins().loadImage(bean.getImg(), R.mipmap.logobg, holder.bank_img);
    }

    @Override
    public int getItemCount() {
        return auctionlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView phonenum_tv, companyname_tv, connect_tv;
        private XCRoundRectImageView bank_img;

        public ViewHolder(View itemView) {
            super(itemView);
            bank_img = (XCRoundRectImageView) itemView.findViewById(R.id.auctionrcv_img);
            companyname_tv = (TextView) itemView.findViewById(R.id.auctionrcv_companyname_tv);
            phonenum_tv = (TextView) itemView.findViewById(R.id.auctionrcv_phonenumber_tv);
            connect_tv = (TextView) itemView.findViewById(R.id.auctionrcv_connect_tv);
        }
    }
}
