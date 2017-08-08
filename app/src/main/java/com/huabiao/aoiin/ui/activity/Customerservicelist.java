package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.CustomerservicelistAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/7.
 */

public class Customerservicelist extends BaseFragment {
    private CustomerservicelistAdapter cAdapter;
    @Bind(R.id.customerservice_recyclerview)
    RecyclerView customerservice_recyclerview;

    @Override
    public void bindView(Bundle savedInstanceState) {
        SearchModel.getCustomerSerCustomerServiceList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    CustomerServiceListBean cb = (CustomerServiceListBean) mData;
                    cAdapter = new CustomerservicelistAdapter(cb.getCustomerservicelist());
                    customerservice_recyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    customerservice_recyclerview.setLayoutManager(manager);
//                    customerservice_recyclerview.setLayoutManager(new HotSearchWordLayoutManager());
//                    SnapHelper snapHelper=new LinearSnapHelper();
//                    snapHelper.attachToRecyclerView(customerservice_recyclerview);
                    customerservice_recyclerview.setAdapter(cAdapter);
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.layout_customerservice;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
