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

import static com.huabiao.aoiin.R.id.customer_service_call;
import static com.huabiao.aoiin.R.id.customer_service_company_tv;
import static com.huabiao.aoiin.R.id.customer_service_consult_tv;
import static com.huabiao.aoiin.R.id.customer_service_img;
import static com.huabiao.aoiin.R.id.customer_service_name_tv;
import static com.huabiao.aoiin.R.id.customer_service_number_tv;
import static com.huabiao.aoiin.R.id.customer_service_reputable_tv;
import static com.huabiao.aoiin.R.id.customer_service_servicerate_tv;
import static com.huabiao.aoiin.R.id.customer_successrate_rate_tv;
import static com.huabiao.aoiin.R.id.customer_usedtime_time_tv;

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
        holder.service_consult_tv.setText("咨询量"+customerservicelistBean.getConsultnumber());
        holder.service_reputable_tv.setText("点赞数"+customerservicelistBean.getReputablenumber());
        holder.usedtime_time_tv.setText(customerservicelistBean.getUsedtime());
        holder.service_name_tv.setText(customerservicelistBean.getCustomerservicename());
        holder.service_number_tv.setText(customerservicelistBean.getCustomerserviceid());
        holder.successrate_rate_tv.setText(customerservicelistBean.getSuccessrate());
        holder.service_servicerate_tv.setText(customerservicelistBean.getService());
        holder.service_call_tv.setOnClickListener(this);
        BitmapLoader.ins().loadImage(customerservicelistBean.getCustomerserviceimg(), R.mipmap.touxiang,holder.service_img);    }

    @Override
    public int getItemCount() {
        return customerservicelist.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case customer_service_call:
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
        //客服编号
        private TextView service_number_tv;
        //用时
        private TextView usedtime_time_tv;
        //点赞数
        private TextView service_reputable_tv;
        //成功率
        private TextView successrate_rate_tv;
        //满意度
        private TextView service_servicerate_tv;
        //咨询量
        private TextView service_consult_tv;
        //拨号图片
        private  ImageView service_call_tv;
        public CustomerServiceVH(View view) {
            super(view);
            service_name_tv = (TextView) view.findViewById(customer_service_name_tv);
            service_company_tv = (TextView) view.findViewById(customer_service_company_tv);
            service_number_tv = (TextView) view.findViewById(customer_service_number_tv);
            usedtime_time_tv = (TextView) view.findViewById(customer_usedtime_time_tv);
            service_reputable_tv = (TextView) view.findViewById(customer_service_reputable_tv);
            successrate_rate_tv = (TextView) view.findViewById(customer_successrate_rate_tv);
            service_servicerate_tv = (TextView) view.findViewById(customer_service_servicerate_tv);
            service_consult_tv = (TextView) view.findViewById(customer_service_consult_tv);
            service_img = (ImageView) view.findViewById(customer_service_img);
           service_call_tv=(ImageView)view.findViewById(customer_service_call);

        }
    }
}
