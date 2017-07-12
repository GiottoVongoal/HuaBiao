package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean;
import com.huabiao.aoiin.model.RegisterOneModel;
import com.huabiao.aoiin.ui.adapter.CustomerServiceAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-12 14:24
 * @description 推荐客服列表
 */
public class CustomerServiceListFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.customer_service_list_rv)
    RecyclerView list_rv;
    private CustomerServiceAdapter adapter;

    private String tradename = "", industry = "";

    //排序
    @Bind(R.id.customer_service_list_all_tv)
    TextView all_tv;
    @Bind(R.id.customer_service_list_success_tv)
    TextView success_tv;
    @Bind(R.id.customer_service_list_time_tv)
    TextView time_tv;
    @Bind(R.id.customer_service_list_service_tv)
    TextView service_tv;

    //查询
    @Bind(R.id.customer_service_list_search_et)
    EditText search_et;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        Bundle bundle = getActivity().getIntent().getExtras();
        tradename = bundle.getString("tradename");
        industry = bundle.getString("industry");
        ALog.i("bundle", "tradename = " + tradename + "\nindustry = " + industry);

        showData();
        setClick();
    }

    //展示数据
    private void showData() {
        list_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        RegisterOneModel.getCustomerServiceList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    CustomerServiceListBean bean = (CustomerServiceListBean) mData;
                    adapter = new CustomerServiceAdapter(getContext(), bean.getCustomerservicelist());
                    list_rv.setAdapter(adapter);
                }
            }
        });
    }

    private void setClick() {
        all_tv.setOnClickListener(this);
        success_tv.setOnClickListener(this);
        time_tv.setOnClickListener(this);
        service_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.customer_service_list_all_tv:
                break;
            case R.id.customer_service_list_success_tv:
                break;
            case R.id.customer_service_list_time_tv:
                break;
            case R.id.customer_service_list_service_tv:
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.customer_service_list_layout;
    }
}
