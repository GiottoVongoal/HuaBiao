package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MyOrdersBean;
import com.huabiao.aoiin.ui.fragment.PayInfoDetailActivity;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;
import com.huabiao.aoiin.wedgit.XCRoundRectImageView;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.JumpUtils;

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

    public void update(List<MyOrdersBean.MyorderslistBean> list) {
        myOrdersList = list;
        notifyDataSetChanged();
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myorderslist_rcv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyOrdersAdapter.ViewHolder holder, final int position) {
        final MyOrdersBean.MyorderslistBean bean = myOrdersList.get(position);
        //建立监听
        holder.traderegister.setText(bean.getGoodsname() + "商标注册");
        //页面内容显示
        holder.payshow.setText("实付款：" + bean.getPrice() + "元");
        holder.tradename.setText(bean.getGoodsname());
        switch (bean.getStatus()) {
            case 1:
                holder.status.setText("待支付");
                holder.myorders_btn_rl.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.status.setText("已完成");
                holder.myorders_btn_rl.setVisibility(View.GONE);
                break;
            case 3:
                holder.status.setText("已取消");
                holder.myorders_btn_rl.setVisibility(View.GONE);
                break;

        }
        holder.classfication.setText(bean.getClassificationid() + "-" + bean.getClassificationname());
        BitmapLoader.ins().loadImage(bean.getGoodsimg(), R.mipmap.logobg, holder.imgview);

        holder.myorders_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickListener(view, position);
            }
        });

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("status", bean.getStatus() == 1 ? 1 : 0);
                JumpUtils.startActivity(context, PayInfoDetailActivity.class, bundle);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeData(position);
                notifyItemRangeChanged(position + 1, getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrdersList.size();
    }

    public void removeData(int position) {
        myOrdersList.remove(position);
        notifyItemRemoved(position + 1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout myorders_ll;//item布局
        private TextView tradename;//商标名
        private TextView classfication;//分类
        private XCRoundRectImageView imgview;//订单图片
        private TextView payshow;//付款显示
        private RelativeLayout myorders_btn_rl;//按钮布局
        private TextView pay;//付款
        private TextView cancel;//取消付款
        private TextView status;//状态
        //跳转注册页面
        private TextView traderegister;

        public ViewHolder(View itemView) {
            super(itemView);
            myorders_ll = (LinearLayout) itemView.findViewById(R.id.myorders_ll);
            tradename = (TextView) itemView.findViewById(R.id.myorders_tradename_tv);
            classfication = (TextView) itemView.findViewById(R.id.myorders_classfication_TextView);
            imgview = (XCRoundRectImageView) itemView.findViewById(R.id.myorders_listitem_imageView);
            payshow = (TextView) itemView.findViewById(R.id.myorders_payshow_tv);
            myorders_btn_rl = (RelativeLayout) itemView.findViewById(R.id.myorders_btn_rl);
            pay = (TextView) itemView.findViewById(R.id.myorders_pay_tv);
            cancel = (TextView) itemView.findViewById(R.id.myorders_cancel_tv);
            status = (TextView) itemView.findViewById(R.id.myorders_status);
            traderegister = (TextView) itemView.findViewById(R.id.myorders_traderge_tv);
        }
    }
}
