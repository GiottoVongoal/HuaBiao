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
import com.huabiao.aoiin.ui.fragment.PayInfoDetailFragment;
import com.huabiao.aoiin.ui.fragment.RegisterFragment;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/24.
 */

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> implements View.OnClickListener {
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

    public void updateListView(List<MyOrdersBean.MyorderslistBean> list) {
        myOrdersList = list;
        notifyDataSetChanged();
    }

    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myorderslist_rcv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyOrdersAdapter.ViewHolder holder, int position) {
        MyOrdersBean.MyorderslistBean bean = myOrdersList.get(position);
        //建立监听
        holder.pay.setOnClickListener(this);
        holder.cancel.setOnClickListener(this);
        holder.traderegister.setOnClickListener(this);
        //页面内容显示
        holder.payshow.setText("实付款：" + bean.getPrice() + "元");
        holder.tradename.setText(bean.getGoodsname());
        holder.status.setText(bean.getStatus());
        holder.classfication.setText(bean.getClassificationid() + "-" + bean.getClassificationname());
        BitmapLoader.ins().loadImage(bean.getGoodsimg(), R.mipmap.logobg, holder.imgview);
    }

    @Override
    public int getItemCount() {
        return myOrdersList.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myorders_traderge_tv:
                JumpUtils.startFragmentByName(context, RegisterFragment.class);
                break;
            case R.id.myorders_pay_tv:
               JumpUtils.startFragmentByName(context, PayInfoDetailFragment.class);
                break;
            case R.id.myorders_cancel_tv:
                Toast.makeText(context, "点击取消", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tradename;//商标名
        private TextView classfication;//分类
        private ImageView imgview;//订单图片
        private TextView payshow;//付款显示
        private TextView pay;//付款
        private TextView cancel;//取消付款
        private TextView status;//状态
        //跳转注册页面
        private TextView traderegister;

        public ViewHolder(View itemView) {
            super(itemView);
            tradename = (TextView) itemView.findViewById(R.id.myorders_tradename_tv);
            classfication = (TextView) itemView.findViewById(R.id.myorders_classfication_TextView);
            imgview = (ImageView) itemView.findViewById(R.id.myorders_listitem_imageView);
            payshow = (TextView) itemView.findViewById(R.id.myorders_payshow_tv);
            pay = (TextView) itemView.findViewById(R.id.myorders_pay_tv);
            cancel=(TextView)itemView.findViewById(R.id.myorders_cancel_tv);
            status=(TextView)itemView.findViewById(R.id.myorders_status);
            traderegister = (TextView) itemView.findViewById(R.id.myorders_traderge_tv);
        }
    }
}
