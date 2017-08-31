package com.huabiao.aoiin.ui.view;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.view
 * @date 2017-08-31 17:54
 * @description
 */
public class CustomerServiceCardItem {
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

    public CustomerServiceCardItem(int title, int text) {
//        mTitleResource = title;
//        mTextResource = text;
    }

    public ImageView getCustomerservicename() {
        return service_img;
    }

    public TextView getService_name_tv() {
        return service_name_tv;
    }

    public TextView getCustomerservicecompany() {
        return service_company_tv;
    }

    public TextView getCustomer_service_successrate_tv() {
        return customer_service_successrate_tv;
    }

    public TextView getCustomer_successrate_rate_tv() {
        return customer_successrate_rate_tv;
    }

    public TextView getCustomer_register_count_tv() {
        return customer_register_count_tv;
    }

    public TextView getCustomer_yuyin_tv() {
        return customer_yuyin_tv;
    }
}
