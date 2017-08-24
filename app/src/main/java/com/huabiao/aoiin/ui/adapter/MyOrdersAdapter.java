package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MyOrdersBean;
import com.ywy.mylibs.utils.BitmapLoader;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/24.
 */

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {
    private Context context;
    private List<MyOrdersBean.MyorderslistBean> myOrdersList;

    public MyOrdersAdapter(Context context, List<MyOrdersBean.MyorderslistBean> myOrdersList) {
        this.context = context;
        this.myOrdersList = myOrdersList;
    }

    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myorderslist_rcv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyOrdersAdapter.ViewHolder holder, int position) {
        MyOrdersBean.MyorderslistBean bean=myOrdersList.get(position);
        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"点击付款",Toast.LENGTH_SHORT).show();
            }
        });
        holder.payshow.setText("实付款："+bean.getPrice()+"元");
        holder.tradename.setText(bean.getGoodsname());
        holder.classfication.setText(bean.getClassificationid()+"-"+bean.getClassificationname());
        BitmapLoader.ins().loadImage(bean.getGoodsimg(), R.mipmap.logobg, holder.imgview);
    }

    @Override
    public int getItemCount() {
        return myOrdersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tradename;
        private TextView classfication;
        private ImageView imgview;
        private TextView payshow;
        private TextView pay;

        public ViewHolder(View itemView) {
            super(itemView);
            tradename = (TextView) itemView.findViewById(R.id.myorders_tradename_tv);
            classfication = (TextView) itemView.findViewById(R.id.myorders_classfication_TextView);
            imgview = (ImageView) itemView.findViewById(R.id.myorders_listitem_imageView);
            payshow = (TextView) itemView.findViewById(R.id.myorders_payshow_tv);
            pay =(TextView)itemView.findViewById(R.id.myorders_pay_tv);
        }
    }
}
