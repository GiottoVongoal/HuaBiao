package com.huabiao.aoiin.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean.CustomerservicelistBean;
import com.huabiao.aoiin.ui.interfaces.ICardAdapter;
import com.ywy.mylibs.utils.BitmapLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-08-31 17:51
 * @description
 */
public class CustomServiceCardAdapter extends PagerAdapter implements ICardAdapter {
    private List<RelativeLayout> mViews;
    private List<CustomerservicelistBean> mData;

    public CustomServiceCardAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CustomerservicelistBean item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public RelativeLayout getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.customer_service_item_layout, container, false);
        container.addView(view);
        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.customer_service_rl);
        bind(mData.get(position), view);
        mViews.set(position, rl);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(final CustomerservicelistBean item, View view) {
        TextView service_name_tv = (TextView) view.findViewById(R.id.customer_service_name_tv);
        TextView service_company_tv = (TextView) view.findViewById(R.id.customer_service_company_tv);
        TextView customer_service_successrate_tv = (TextView) view.findViewById(R.id.customer_service_successrate_tv);
        TextView customer_successrate_rate_tv = (TextView) view.findViewById(R.id.customer_successrate_rate_tv);
        TextView customer_register_count_tv = (TextView) view.findViewById(R.id.customer_register_count_tv);
        ImageView service_img = (ImageView) view.findViewById(R.id.customer_service_img);
        TextView customer_yuyin_tv = (TextView) view.findViewById(R.id.customer_yuyin_tv);

        service_company_tv.setText(item.getCustomerservicecompany());
        customer_register_count_tv.setText(item.getConsultnumber());
        service_name_tv.setText(item.getCustomerservicename());
        customer_service_successrate_tv.setText(item.getSuccessrate());
        customer_successrate_rate_tv.setText(item.getService());
        BitmapLoader.ins().loadImage(item.getCustomerserviceimg(), R.mipmap.touxiang, service_img);
        customer_yuyin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ALog.i("id--->" + item.getCustomerserviceid() + ",name--->" + item.getCustomerservicename());
            }
        });
    }
}
