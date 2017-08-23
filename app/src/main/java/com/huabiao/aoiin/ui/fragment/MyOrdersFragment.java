package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;

import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

/**
 * Created by Aoiin-9 on 2017/8/23.
 */

public class MyOrdersFragment extends BaseFragment {
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {


    setTitle("我的订单");
    }

    @Override
    public int getContentLayout() {
        return 0;
    }
}
