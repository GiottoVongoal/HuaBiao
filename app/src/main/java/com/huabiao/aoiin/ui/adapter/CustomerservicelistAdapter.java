package com.huabiao.aoiin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean.CustomerservicelistBean;
import com.ywy.mylibs.utils.BitmapLoader;

import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/8.
 */

public class CustomerservicelistAdapter extends RecyclerView.Adapter<CustomerservicelistAdapter.CustomerServiceVH> {

    private List<CustomerservicelistBean> customerservicelist;

    public CustomerservicelistAdapter(List<CustomerservicelistBean> customerservicelist) {
        this.customerservicelist = customerservicelist;
    }

    public CustomerServiceVH onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_customerservicelist, viewGroup,false);
        CustomerServiceVH customerServiceVH = new CustomerServiceVH(view);
        return customerServiceVH;
    }

    @Override
    public void onBindViewHolder(CustomerservicelistAdapter.CustomerServiceVH holder, int position) {
        CustomerservicelistBean customerservicelistBean = customerservicelist.get(position);
        holder.customer_service_company.setText(customerservicelistBean.getCustomerservicecompany());
        holder.customer_service_consult.setText("咨询量"+customerservicelistBean.getConsultnumber());
        holder.customer_service_reputable.setText("点赞数"+customerservicelistBean.getReputablenumber());
        holder.customer_usedtime_time.setText(customerservicelistBean.getUsedtime());
        holder.customer_service_name.setText(customerservicelistBean.getCustomerservicename());
        holder.customer_service_number.setText(customerservicelistBean.getCustomerserviceid());
        holder.customer_successrate_rate.setText(customerservicelistBean.getSuccessrate());
        holder.customer_service_servicerate.setText(customerservicelistBean.getService());
        BitmapLoader.ins().loadImage(customerservicelistBean.getCustomerserviceimg(), R.mipmap.perter_portrait,holder.customer_service_img);    }

    @Override
    public int getItemCount() {
        return customerservicelist.size();
    }

    class CustomerServiceVH extends RecyclerView.ViewHolder {
        //客服图片
        private ImageView customer_service_img;
        //客服名
        private TextView customer_service_name;
        //商标公司
        private TextView customer_service_company;
        //客服编号
        private TextView customer_service_number;
        //用时
        private TextView customer_usedtime_time;
        //点赞数
        private TextView customer_service_reputable;
        //成功率
        private TextView customer_successrate_rate;
        //满意度
        private TextView customer_service_servicerate;
        //咨询量
        private TextView customer_service_consult;

        public CustomerServiceVH(View view) {
            super(view);
            customer_service_name = (TextView) view.findViewById(R.id.customer_service_name);
            customer_service_company = (TextView) view.findViewById(R.id.customer_service_company);
            customer_service_number = (TextView) view.findViewById(R.id.customer_service_number);
            customer_usedtime_time = (TextView) view.findViewById(R.id.customer_usedtime_time);
            customer_service_reputable = (TextView) view.findViewById(R.id.customer_service_reputable);
            customer_successrate_rate = (TextView) view.findViewById(R.id.customer_successrate_rate);
            customer_service_servicerate = (TextView) view.findViewById(R.id.customer_service_servicerate);
            customer_service_consult = (TextView) view.findViewById(R.id.customer_service_consult);
            customer_service_img = (ImageView) view.findViewById(R.id.customer_service_img);
        }
    }
}
