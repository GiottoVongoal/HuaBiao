package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.wedgit.CustomerServiceCallback;
import com.huabiao.aoiin.wedgit.CustomerServiceLayoutManager;
import com.huabiao.aoiin.ui.adapter.CustomerservicelistAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/7.
 * 客服列表
 */

public class CustomerServiceListFragment extends BaseFragment {
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
                    cAdapter = new CustomerservicelistAdapter(getContext(), cb.getCustomerservicelist());
                    customerservice_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    customerservice_recyclerview.setAdapter(cAdapter);
                    CustomerServiceCallback callback = new CustomerServiceCallback(customerservice_recyclerview.getAdapter(), cb.getCustomerservicelist());
                    final ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                    final CustomerServiceLayoutManager cardLayoutManager = new CustomerServiceLayoutManager(customerservice_recyclerview, touchHelper);
                    customerservice_recyclerview.setLayoutManager(cardLayoutManager);
                    touchHelper.attachToRecyclerView(customerservice_recyclerview);
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
