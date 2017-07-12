package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.os.UserManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean.CustomerservicelistBean;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.wedgit.wedgit.CircleView;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-12 15:42
 * @description
 */
public class CustomerServiceAdapter extends RecyclerView.Adapter<CustomerServiceAdapter.MyViewHolder> {
    private Context context;
    private List<CustomerservicelistBean> list;

    public CustomerServiceAdapter(Context context, List<CustomerservicelistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_service_list_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CustomerservicelistBean bean = list.get(position);
        holder.item_name_tv.setText(bean.getCustomerservicename());
        holder.item_address_tv.setText(bean.getCustomerservicecompany());
        holder.item_rb.setIsIndicator(true);//显示作用,无法进行交互
        holder.item_rb.setRating(bean.getCustomerservicerank());

        BitmapLoader.ins().loadImage(bean.getCustomerserviceimg(), R.mipmap.perter_portrait, holder.item_photo_iv);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CircleView item_photo_iv;
        TextView item_name_tv;
        TextView item_address_tv;
        RatingBar item_rb;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_photo_iv = (CircleView) itemView.findViewById(R.id.customer_service_list_item_photo_iv);
            item_name_tv = (TextView) itemView.findViewById(R.id.customer_service_list_item_name_tv);
            item_address_tv = (TextView) itemView.findViewById(R.id.customer_service_list_item_address_tv);
            item_rb = (RatingBar) itemView.findViewById(R.id.customer_service_list_item_rb);
        }
    }
}
