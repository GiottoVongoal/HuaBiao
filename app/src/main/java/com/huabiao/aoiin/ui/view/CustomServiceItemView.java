package com.huabiao.aoiin.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean.CustomerservicelistBean;
import com.ywy.mylibs.utils.BitmapLoader;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.view
 * @date 2017-08-31 17:05
 * @description
 */
public class CustomServiceItemView extends RelativeLayout {
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
    private TextView customer_yuyin_tv;

    private View view;
    private Context context;

    public CustomServiceItemView(Context context) {
        super(context);
        view = inflate(context, R.layout.customer_service_item_layout, this);
        this.context = context;
        service_name_tv = (TextView) view.findViewById(R.id.customer_service_name_tv);
        service_company_tv = (TextView) view.findViewById(R.id.customer_service_company_tv);
        customer_service_successrate_tv = (TextView) view.findViewById(R.id.customer_service_successrate_tv);
        customer_successrate_rate_tv = (TextView) view.findViewById(R.id.customer_successrate_rate_tv);
        customer_register_count_tv = (TextView) view.findViewById(R.id.customer_register_count_tv);
        service_img = (ImageView) view.findViewById(R.id.customer_service_img);
        customer_yuyin_tv = (TextView) view.findViewById(R.id.customer_yuyin_tv);
    }

    public void getShowData(final CustomerservicelistBean bean) {
        service_company_tv.setText(bean.getCustomerservicecompany());
        customer_register_count_tv.setText(bean.getConsultnumber());
        service_name_tv.setText(bean.getCustomerservicename());
        customer_service_successrate_tv.setText(bean.getSuccessrate());
        customer_successrate_rate_tv.setText(bean.getService());
        customer_yuyin_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ALog.i("id--->" + bean.getCustomerserviceid() + ",name--->" + bean.getCustomerservicename());
            }
        });
        BitmapLoader.ins().loadImage(bean.getCustomerserviceimg(), R.mipmap.logobg, service_img);
    }
}
