package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.UserAddressListBean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-08-18 19:27
 * @description 收货地址列表Adapter
 */
public class UserAddressListAdapter extends RecyclerView.Adapter<UserAddressListAdapter.ViewHolder> {
    private Context context;
    private List<UserAddressListBean> list;

    public UserAddressListAdapter(Context context, List<UserAddressListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_address_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserAddressListBean bean = list.get(position);
        holder.name_tv.setText(bean.getName());
        holder.phone_tv.setText(bean.getPhone());
        holder.address_tv.setText(bean.getSelectAddress() + "   " + bean.getAddress());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_tv, phone_tv, address_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            name_tv = (TextView) itemView.findViewById(R.id.user_address_item_name_tv);
            phone_tv = (TextView) itemView.findViewById(R.id.user_address_item_phone_tv);
            address_tv = (TextView) itemView.findViewById(R.id.user_address_item_address_tv);
        }
    }
}
