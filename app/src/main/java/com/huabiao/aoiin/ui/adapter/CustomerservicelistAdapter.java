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
import com.huabiao.aoiin.bean.CustomerServiceListBean.CustomerservicelistBean;
import com.ywy.mylibs.utils.BitmapLoader;

import java.util.List;


/**
 * Created by Aoiin-9 on 2017/8/8.
 */

public class CustomerservicelistAdapter extends RecyclerView.Adapter<CustomerservicelistAdapter.CustomerServiceVH> implements View.OnClickListener{

    private Context context;
    private List<CustomerservicelistBean> customerservicelist;

    public CustomerservicelistAdapter(Context context, List<CustomerservicelistBean> customerservicelist) {
        this.context = context;
        this.customerservicelist = customerservicelist;
    }

    public CustomerServiceVH onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_customerservicelist, viewGroup,false);
        CustomerServiceVH customerServiceVH = new CustomerServiceVH(view);
        return customerServiceVH;
    }

    @Override
    public void onBindViewHolder(CustomerservicelistAdapter.CustomerServiceVH holder, int position) {
        CustomerservicelistBean customerservicelistBean = customerservicelist.get(position);
        holder.service_company_tv.setText(customerservicelistBean.getCustomerservicecompany());
        holder.customer_register_count_tv.setText(customerservicelistBean.getConsultnumber());
        holder.service_name_tv.setText(customerservicelistBean.getCustomerservicename());
        holder.customer_service_successrate_tv.setText(customerservicelistBean.getSuccessrate());
        holder.customer_successrate_rate_tv.setText(customerservicelistBean.getService());
        holder.customer_yuyin_tv.setOnClickListener(this);
        BitmapLoader.ins().loadImage(customerservicelistBean.getCustomerserviceimg(), R.mipmap.logobg,holder.service_img);
    }

    @Override
    public int getItemCount() {
        return customerservicelist.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.customer_yuyin_tv:
                Toast toast=Toast.makeText(context,"咨询客服",Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }

    class CustomerServiceVH extends RecyclerView.ViewHolder {
        //客服图片
        private ImageView service_img;
        //客服名
        private TextView service_name_tv;
        //商标公司
        private TextView service_company_tv;
        //成功率
        private TextView customer_service_successrate_tv;
        //满意度
        private TextView customer_successrate_rate_tv;
        //注册量
        private TextView customer_register_count_tv;
        //语音按钮
        private  TextView customer_yuyin_tv;
        public CustomerServiceVH(View view) {
            super(view);
            service_name_tv = (TextView) view.findViewById(R.id.customer_service_name_tv);
            service_company_tv = (TextView) view.findViewById(R.id.customer_service_company_tv);
            customer_service_successrate_tv = (TextView) view.findViewById(R.id.customer_service_successrate_tv);
            customer_successrate_rate_tv = (TextView) view.findViewById(R.id.customer_successrate_rate_tv);
            customer_register_count_tv = (TextView) view.findViewById(R.id.customer_register_count_tv);
            service_img = (ImageView) view.findViewById(R.id.customer_service_img);
           customer_yuyin_tv=(TextView)view.findViewById(R.id.customer_yuyin_tv);

        }
    }
}
