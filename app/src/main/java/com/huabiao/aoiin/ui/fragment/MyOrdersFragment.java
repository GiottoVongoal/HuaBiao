package com.huabiao.aoiin.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MyOrdersBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.MyOrdersAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/23.
 */

public class MyOrdersFragment extends BaseFragment implements View.OnClickListener {
    private Context context;
    private MyOrdersAdapter ordersAdapter;
    //全部按钮
    @Bind(R.id.myorders_all_tv)
    TextView all;
    //取消按钮
    @Bind(R.id.myorders_cancel_tv)
    TextView cancel;
    //已完成按钮
    @Bind(R.id.myorders_finish_tv)
    TextView finish;
    //待支付按钮
    @Bind(R.id.myorders_yetpay_tv)
    TextView yetpay;
    @Bind(R.id.myorders_recyclerview)
    RecyclerView ordersrcyclerview;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的订单");
        setBackEnable();
        finish.setOnClickListener(this);
        all.setOnClickListener(this);
        cancel.setOnClickListener(this);
        yetpay.setOnClickListener(this);
        SearchModel.getMyordersList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    MyOrdersBean bean = (MyOrdersBean) mData;
                    ordersrcyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                    ordersAdapter = new MyOrdersAdapter(getContext(), bean.getMyorderslist());
                    ordersrcyclerview.setAdapter(ordersAdapter);
                }
            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.layout_myorders;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myorders_all_tv:
                showToast("全部");
                break;
            case R.id.myorders_cancel_tv:
                showToast("取消");
                break;
            case R.id.myorders_yetpay_tv:
                showToast("未付款");
                break;
            case R.id.myorders_finish_tv:
                showToast("已完成");
                break;
        }
    }
}
