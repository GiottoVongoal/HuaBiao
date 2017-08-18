package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-18 17:52
 * @description 收货地址列表
 */
public class UserAddressListFragment extends BaseFragment {
    @Bind(R.id.user_address_list_rv)
    RecyclerView address_list_rv;

    @Bind(R.id.user_address_list_add_tv)
    TextView add_tv;


    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("我的地址管理");
        setBackEnable();



    }

    @Override
    public int getContentLayout() {
        return R.layout.user_address_list_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
