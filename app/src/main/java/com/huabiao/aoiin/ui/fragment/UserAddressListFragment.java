package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.UserAddressListBean;
import com.huabiao.aoiin.ui.adapter.UserAddressListAdapter;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-18 17:52
 * @description 收货地址列表
 */
public class UserAddressListFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.user_address_list_rv)
    RecyclerView address_list_rv;
    private UserAddressListAdapter adapter;
    private List<UserAddressListBean> list;

    @Bind(R.id.user_address_list_add_tv)
    TextView add_tv;


    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("我的地址管理");
        setBackEnable();

        list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            UserAddressListBean bean = new UserAddressListBean("name" + i, "phone" + i, "北京  北京  东城区", "address" + i, "code" + i);
            list.add(bean);
        }
        adapter = new UserAddressListAdapter(getContext(), list);
        address_list_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        address_list_rv.setAdapter(adapter);

        add_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_address_list_add_tv:
                JumpUtils.startFragmentByName(getContext(), UserAddressAddFragment.class);
                break;
        }
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
